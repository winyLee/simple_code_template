package com.winylee.sct.core;

import com.winylee.sct.core.pojo.TemplateField;
import com.winylee.sct.core.pojo.TemplatePojo;
import com.winylee.sct.core.utils.FileUtils;
import com.winylee.sct.core.utils.FreemarkerUtil;
import com.winylee.sct.core.utils.StrUtils;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *  @author  shang.li on 2015/12/8.
 * V1.0
 *  通过xml 来 生成模版代码
 *  V1.1 生成exe
 */
public class GenXmlTemplate {

    /**
     * 1：表示加载 按照xmlFolder属性进行
     * 2:表示加载 按照xmlSysFolder属性进行
     */
    public static final int  type = 1;

    //当前项目的开始名称(如com.ecovacs,com.winylee)
    private String auth_package = "com.winylee.framework";

    //当前项目的domain名称("如果不设置则为默认表明的第一个单词：如表名dist_sales_order" 则为dist)
    private String project_domain = "";
    // xml文件或者文件夹的路径(系统路径)
    private String xmlSysFolder = "D:/tables";

    //xml文件或者文件夹的路径(classpath目录)
    private String xmlFolder = "config/xml";

    //ftl模版文件路径(classpath的路径)
//    private String ftlFolder = "config/template/core/ftl/mybatis_ace";
    //TODO:winylee jpa模式下的代码模版controller 包的依赖就需要修改
    private String ftlFolder = "config/template/core/ftl/jpa_ace";

    //输出的文件路径(会删除当前文件夹下所有文件)
    private String outFolder = "D:/outPut";

    //排除在外的filed不生存model的字段
    private static Set excludeFieldSet =new HashSet();
    static {
        excludeFieldSet.add("id");
        excludeFieldSet.add("creater");
        excludeFieldSet.add("modifier");
        excludeFieldSet.add("create_time");
        excludeFieldSet.add("modify_time");
        excludeFieldSet.add("version");
    };


    private static Map<String,String> map =new HashMap<>();

    static{
        map.put("VARCHAR","String");
        map.put("BIGINT","Long");
        map.put("INT","Integer");
        map.put("DOUBLE","BigDecimal");
        map.put("TEXT","String");
        map.put("DATE","Date");
        map.put("TIME","Date");
        map.put("DATETIME","Date");
        //todo: winylee add other type
    }

    /**
     * 生成模版文件，
     * 注意：该方法会删除所有输出文件夹中的文件
     * @throws java.io.IOException
     * @throws TemplateException
     */
    public void genarate() throws IOException, TemplateException {

        FileUtils.deleteAllFile(new File(outFolder), false);

        FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
        freemarkerUtil.setEn(ftlFolder);
        String path  = ftlFolder;
        path = ClassLoader.getSystemResource("").getPath()+ path;

        //目录有空格路径替换
        path = path.replaceAll("%20"," ");

        File folder = new File(path);

        List<TemplatePojo> data =  paraseXml();
        if(data==null || data.isEmpty()){
            System.out.println("------------失败---------------");
            System.out.println("data 不能为空");
            return ;
        }
        if(folder.exists()){
            if(folder.isFile()){
                for(TemplatePojo templatePojo : data ) {
                    String destPath = outFolder + File.separator+ templatePojo.getNameSpace().replace(".",File.separator) + templatePojo.getClassName()+ ".java";
                    freemarkerUtil.process(folder.getName(), destPath, templatePojo);
                }
                System.out.println("文件生成完成");
                return ;
            }
            File[] fiels = folder.listFiles();
            for(TemplatePojo templatePojo : data ) {
                String oldNameSpace = templatePojo.getNameSpace();
                StringBuffer destPath = null;
                for(File f : fiels){
                    destPath = new StringBuffer();
                    destPath.append(outFolder).append(File.separator);
                    templatePojo.setPackType(f.getName().split("\\.")[0].toLowerCase());
                    templatePojo.setNameSpace(oldNameSpace + "."+ templatePojo.getPackType()+ "."  + templatePojo.getModuleName());
                    templatePojo.setDaoPackage(oldNameSpace + ".dao."  + templatePojo.getModuleName());

                    String classSuffix  = f.getName().split("\\.")[0].substring(0,1).toUpperCase()+ f.getName().split("\\.")[0].substring(1).toLowerCase();;
                    String fileSuffix = ".java";
                    String fileName = templatePojo.getClassName() ;
                    if(f.getName().toLowerCase().equals("impl.ftl")){
                        templatePojo.setPackType("service");
                        templatePojo.setNameSpace(oldNameSpace + "."+ templatePojo.getPackType()  + "." + templatePojo.getModuleName()+ ".impl") ;
                        classSuffix = "ServiceImpl";
                    }else if(f.getName().toLowerCase().equals("dto.ftl")){
                        templatePojo.setNameSpace(oldNameSpace + "."  +  f.getName().split("\\.")[0].toLowerCase() + "."+ templatePojo.getTblId().split("_")[1]);
                        classSuffix = f.getName().split("\\.")[0].toUpperCase();
                    }else if(f.getName().toLowerCase().equals("model.ftl")){
                        classSuffix = "";
                    }else if(f.getName().toLowerCase().indexOf("_jsp.ftl") > 0){
                        templatePojo.setPackType("jsp");
                        templatePojo.setNameSpace(oldNameSpace + ".jsp" + "." + templatePojo.getMappingUrl().toLowerCase()) ;
                        classSuffix = "";
                        fileSuffix = ".jsp";
                        fileName = f.getName().substring(0,f.getName().toLowerCase().indexOf("_jsp.ftl")) ;
                    }
                    destPath.append(templatePojo.getNameSpace().replace(".", File.separator)).append(File.separator).append(fileName).append(classSuffix).append(fileSuffix);
                    freemarkerUtil.process(f.getName(), destPath.toString(), templatePojo);
                }
            }
            System.out.println("所有文件生成完成");
        }
    }


    /**
     * 解析所有xml文件
     * @return
     */
    public List<TemplatePojo>  paraseXml() {
        String path  = "";
        if(type ==1){
            //目录有空格路径替换
            path = ClassLoader.getSystemResource("").getPath() + xmlFolder;
        }else{
            path = xmlSysFolder;
        }
        path = path.replaceAll("%20"," ");
        File xmlFolderFile = new File(path);
        if (xmlFolderFile!=null && xmlFolderFile.exists()) {
            return  parasFolder(xmlFolderFile);
        }
        return null;
    }

    /**
     * 解析一个文件或者文件夹
     * @param file
     * @return
     */
    public   List<TemplatePojo> parasFolder(File file){
        List<TemplatePojo> data = new ArrayList<>();
        if (file.isFile()) {
            TemplatePojo pojo = parasFile(file);
            if(pojo!=null)data.add(pojo);
        } else if (file.isDirectory()) {
            File files[] = file.listFiles();
            if(files==null)return null;
            for(File f: files){
                if(f.isDirectory()){
                    data.addAll(parasFolder(f));
                }else{
                    TemplatePojo pojo = parasFile(f);
                    if(pojo!=null)data.add(pojo);
                }
            }
        }
        return data;
    }

    /**
     * 解析一个文件
     * @param file
     * @return
     */
    public TemplatePojo parasFile(File file){
        SAXReader saxReader = new SAXReader();
        TemplatePojo templatePojo = null;
        Document document = null;

        try {
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //ROOT
        Element rootElement = document.getRootElement();
        for (Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
            //changeSet
            Element element = (Element) i.next();
            if(element.getName().equals("changeSet")){
                for (Iterator j = element.elementIterator(); j.hasNext(); ) {
                    //createTable
                    Element tableElement = (Element) j.next();
                    if(tableElement.getName().equals("createTable")){
                        templatePojo = new TemplatePojo();
                        String tableName = tableElement.attributeValue("tableName");
                        templatePojo.setTblId(tableName);
                        //设置table名称
                        templatePojo.setTblId(tableName);
                        String pD =  project_domain ;
                        if(StringUtils.isBlank(pD)){
                            pD =  tableName.split("_")[0];
                        }
                        String domain = auth_package + "." + pD;
                        templatePojo.setDomain(domain);
                        //设置package
                        templatePojo.setNameSpace(domain);
                        templatePojo.setModuleName(templatePojo.getTblId().split("_")[1]);
                        //设置class的名称
                        String className = StrUtils.convertStrU(tableName, "_").substring(0,1).toUpperCase()+StrUtils.convertStrU(tableName,"_").substring(1);
                        templatePojo.setClassName(className);
                        String mappingUrl =  getControllerUrl(tableName,"_");
                        templatePojo.setMappingUrl(mappingUrl);
                        //设置class的描述
                        templatePojo.setClassDes(tableElement.attributeValue("desc")==null?tableName:tableElement.attributeValue("desc"));
                        List<TemplateField> fields = new ArrayList<>();
                        templatePojo.setFields(fields);
                        for (Iterator column = tableElement.elementIterator(); column.hasNext(); ) {
                            Element columnE = (Element) column.next();
                            if(columnE.getName().equals("column")){
                                String columnName = columnE.attributeValue("name");
                                //如果排除在外的字段属性，不在entity中生存
                                if(columnName!=null && excludeFieldSet.contains(columnName)){
                                    continue;
                                }
                                TemplateField templateField = new TemplateField();
                                templateField.setColumnName(columnName);
                                templateField.setFieldComment(columnE.attributeValue("remarks")!=null?columnE.attributeValue("remarks"): "");
                                templateField.setFieldType(convertType(columnE.attributeValue("type")));
                                templateField.setFieldId(StrUtils.convertStrU(columnE.attributeValue("name"), "_"));
                                templateField.setFieldName(columnE.attributeValue("remarks")!=null?columnE.attributeValue("remarks"): "");
                                fields.add(templateField);
                            }
                        }
                    }
                }
            }
        }
        return templatePojo;
    }


    public String convertType(String orginalType){
        return map.get(orginalType.toUpperCase())==null? "String" : map.get(orginalType);
    }


    public static String getControllerUrl(String str,String symbol){
        if(StringUtils.isBlank(str))return str;
        if(symbol==null)symbol="_";
        StringBuffer buffer = new StringBuffer("");
        String strs[] = str.split(symbol);
        int i =0;
        for(String sp:strs){
            if(i==0){
                i++;
                continue;
            }
            if(StringUtils.isBlank(sp))continue;
            if(buffer.toString().equals("")){
                buffer.append(sp).append("/");
            }else{
                buffer.append(sp.substring(0, 1).toLowerCase() + sp.substring(1));
            }
        }
        return buffer.toString();
    }


    public static void main(String arges[]){
        GenXmlTemplate gen = new GenXmlTemplate();
        try {
            gen.genarate();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (TemplateException e1) {
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
