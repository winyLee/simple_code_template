package com.winylee.sct.core;

import com.winylee.sct.core.constants.CodeTemplateConstant;
import com.winylee.sct.core.pojo.CodeTable;
import com.winylee.sct.core.pojo.TemplateField;
import com.winylee.sct.core.pojo.TemplatePojo;
import com.winylee.sct.core.utils.FileUtils;
import com.winylee.sct.core.utils.FreemarkerUtil;
import com.winylee.sct.core.utils.PdmUtil;
import com.winylee.sct.core.utils.StrUtils;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  @author  shang.li on 2017/3/17.
 * //TODO:winylee 修改为自己设置参数的风格
 */
public class GenPdm {


    public static void generate(String pdmPath) throws IOException, TemplateException {
        genarateXmlToBean(generatePdmToXml(pdmPath));
    }

    public static void generateXml(String xmlPath) throws IOException, TemplateException {
        FileUtils.deleteAllFile(new File(CodeTemplateConstant.outFolder), false);
        genarateXmlToBean(xmlPath);
    }

    /**
     * 据pdm文件模版生成方法
     * @param pdmPath
     */
    /**
     * @param pdmPath
     * @return 返回生成的xml文件目录
     * @throws IOException
     * @throws TemplateException
     */
    public static String generatePdmToXml(String pdmPath) throws IOException, TemplateException {
        FileUtils.deleteAllFile(new File(CodeTemplateConstant.outFolder), false);
        if (StringUtils.isBlank(pdmPath)) {
            pdmPath = CodeTemplateConstant.PMD_PATH;
        }
        List<CodeTable> tableList = PdmUtil.parsePDMToList(pdmPath);
        if (tableList == null  || tableList.isEmpty()) {
            System.out.println("-------PDM数据为空-----");
            return "";
        }
        FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
        freemarkerUtil.setEn(CodeTemplateConstant.LIQUIBASE_TEMPLATE_PATH);
        String generateXmlFolder = CodeTemplateConstant.outFolder + File.separator + "liquebase";
        for (CodeTable codeTable : tableList) {
            String destPath = generateXmlFolder + File.separator + codeTable.getTableCode() + ".xml";
            freemarkerUtil.process(CodeTemplateConstant.LIQUIBASE_TEMPLATE_NAME, destPath, codeTable);
        }
        System.out.println(generateXmlFolder);
        return generateXmlFolder;
    }


    /**
     * 根据xml文件夹路劲生成模版文件，
     * 注意：该方法会删除所有输出文件夹中的文件
     *
     * @throws java.io.IOException
     * @throws freemarker.template.TemplateException
     */
    private static void genarateXmlToBean(String xmlFolderPath) throws IOException, TemplateException {
        FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
        freemarkerUtil.setEn(CodeTemplateConstant.ftlFolder);
        String path = CodeTemplateConstant.ftlFolder;
        path = ClassLoader.getSystemResource("").getPath() + path;

        //目录有空格路径替换
        path = path.replaceAll("%20", " ");

        File folder = new File(path);

        List<TemplatePojo> data = paraseXml(xmlFolderPath);
        if (data == null || data.isEmpty()) {
            System.out.println("data 不能为空");
            return;
        }
        if (folder.exists()) {
            if (folder.isFile()) {
                for (TemplatePojo templatePojo : data) {
                    String destPath = CodeTemplateConstant.outFolder + File.separator + templatePojo.getNameSpace().replace(".", File.separator) + templatePojo.getClassName() + ".java";
                    freemarkerUtil.process(folder.getName(), destPath, templatePojo);
                }
                System.out.println("文件生成完成");
                return;
            }
            File[] fiels = folder.listFiles();
            for (TemplatePojo templatePojo : data) {
                String oldNameSpace = templatePojo.getNameSpace();
                StringBuffer destPath = null;
                for (File f : fiels) {
                    destPath = new StringBuffer();
                    destPath.append(CodeTemplateConstant.outFolder).append(File.separator);
                    templatePojo.setPackType(f.getName().split("\\.")[0].toLowerCase());
                    templatePojo.setNameSpace(oldNameSpace + "." + templatePojo.getPackType() + "." + templatePojo.getModuleName());
                    templatePojo.setDaoPackage(oldNameSpace + ".dao."  + templatePojo.getModuleName());
                    String classSuffix = f.getName().split("\\.")[0].substring(0, 1).toUpperCase() + f.getName().split("\\.")[0].substring(1).toLowerCase();
                    ;
                    String fileSuffix = ".java";
                    String fileName = templatePojo.getClassName();
                    if (f.getName().toLowerCase().equals("impl.ftl")) {
                        templatePojo.setPackType("service");
                        templatePojo.setNameSpace(oldNameSpace + "." + templatePojo.getPackType() + "." + templatePojo.getModuleName() + ".impl");
                        classSuffix = "ServiceImpl";
                    } else if (f.getName().toLowerCase().equals("dto.ftl")) {
                        templatePojo.setNameSpace(oldNameSpace + "." + f.getName().split("\\.")[0].toLowerCase() + "." + templatePojo.getTblId().split("_")[1]);
                        classSuffix = f.getName().split("\\.")[0].toUpperCase();
                    } else if (f.getName().toLowerCase().equals("model.ftl")) {
                        classSuffix = "";
                    } else if (f.getName().toLowerCase().indexOf("_jsp.ftl") > 0) {
                        templatePojo.setPackType("jsp");
                        templatePojo.setNameSpace(oldNameSpace + ".jsp" + "." + templatePojo.getMappingUrl());
                        classSuffix = "";
                        fileSuffix = ".jsp";
                        fileName = f.getName().substring(0, f.getName().toLowerCase().indexOf("_jsp.ftl"));
                    } else if (f.getName().equals("SqlMap.ftl")) {
                        templatePojo.setPackType("sqlMap");
                        classSuffix = "";
                        fileSuffix = "Mapper.liquebase";
                    }
                    destPath.append(templatePojo.getNameSpace().replace(".", File.separator)).append(File.separator).append(fileName).append(classSuffix).append(fileSuffix);

                    if (f.getName().equals("SqlMap.ftl")) {
                        String modelSpace = templatePojo.getNameSpace().replace("sqlmap", "model");
                        templatePojo.setNameSpace(modelSpace);
                    }

                    freemarkerUtil.process(f.getName(), destPath.toString(), templatePojo);
                }
            }
            System.out.println("所有文件生成完成");
        }
    }


    /**
     * 解析所有xml文件
     *
     * @return
     */
    private static List<TemplatePojo> paraseXml(String xmlFolderPath) {
        if (StringUtils.isBlank(xmlFolderPath)) {
            xmlFolderPath = CodeTemplateConstant.xmlFolder;
        }
        String path = xmlFolderPath;
        path = path.replaceAll("%20", " ");
        File xmlFolderFile = new File(path);
        if (xmlFolderFile != null && xmlFolderFile.exists()) {
            return parasFolder(xmlFolderFile);
        }
        return null;
    }

    /**
     * 解析一个文件或者文件夹
     *
     * @param file
     * @return
     */
    private static List<TemplatePojo> parasFolder(File file) {
        List<TemplatePojo> data = new ArrayList<>();
        if (file.isFile()) {
            TemplatePojo pojo = parasFile(file);
            if (pojo != null) data.add(pojo);
        } else if (file.isDirectory()) {
            File files[] = file.listFiles();
            if (files == null) return null;
            for (File f : files) {
                if (f.isDirectory()) {
                    data.addAll(parasFolder(f));
                } else {
                    TemplatePojo pojo = parasFile(f);
                    if (pojo != null) data.add(pojo);
                }
            }
        }
        return data;
    }

    /**
     * 解析一个文件
     *
     * @param file
     * @return
     */
    private static TemplatePojo parasFile(File file) {
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
            if (element.getName().equals("changeSet")) {
                for (Iterator j = element.elementIterator(); j.hasNext(); ) {
                    //createTable
                    Element tableElement = (Element) j.next();
                    if (tableElement.getName().equals("createTable")) {
                        templatePojo = new TemplatePojo();
                        String tableName = tableElement.attributeValue("tableName");
                        templatePojo.setTblId(tableName);
                        //设置table名称
                        templatePojo.setTblId(tableName);
                        String pD = CodeTemplateConstant.project_domain;
                        if (StringUtils.isBlank(pD)) {
                            pD = tableName.split("_")[0];
                        }
                        String domain = CodeTemplateConstant.auth_package + "." + pD;
                        templatePojo.setDomain(domain);
                        //设置package
                        templatePojo.setNameSpace(domain);
                        templatePojo.setModuleName(templatePojo.getTblId().split("_")[1]);
                        //设置class的名称
                        String className = StrUtils.convertStrU(tableName, "_").substring(0, 1).toUpperCase() + StrUtils.convertStrU(tableName, "_").substring(1);
                        templatePojo.setClassName(className);
                        String mappingUrl = getControllerUrl(tableName, "_");
                        templatePojo.setMappingUrl(mappingUrl);
                        //设置class的描述
                        templatePojo.setClassDes(tableElement.attributeValue("desc") == null ? tableName : tableElement.attributeValue("desc"));
                        List<TemplateField> fields = new ArrayList<>();
                        templatePojo.setFields(fields);
                        for (Iterator column = tableElement.elementIterator(); column.hasNext(); ) {
                            Element columnE = (Element) column.next();
                            if (columnE.getName().equals("column")) {
                                String columnName = columnE.attributeValue("name");
                                //如果排除在外的字段属性，不在entity中生存
                                if (columnName != null && CodeTemplateConstant.excludeFieldSet.contains(columnName)) {
                                    continue;
                                }
                                TemplateField templateField = new TemplateField();
                                templateField.setColumnName(columnName);
//                                templateField.setFieldComment(columnE.attributeValue("remarks") != null ? columnE.attributeValue("remarks") : "");
                                String types = columnE.attributeValue("type");
                                //varchar(32);
                                if(types.indexOf("(") ==-1){
                                    templateField.setFieldType(CodeTemplateConstant.convertType(types));
                                }else{
                                    String type = types.substring(0,types.indexOf("("));
                                    templateField.setFieldType(CodeTemplateConstant.convertType(type));
                                    String length = types.substring(types.indexOf("(") + 1, types.indexOf(")"));
                                    templateField.setFieldLength(length);
                                }

                                templateField.setFieldId(StrUtils.convertStrU(columnE.attributeValue("name"), "_"));
                                templateField.setFieldName(columnE.attributeValue("remarks") != null ? columnE.attributeValue("remarks") : templateField.getFieldId());
                                fields.add(templateField);
                            }
                        }
                    }
                }
            }
        }
        return templatePojo;
    }

    private static String getControllerUrl(String str, String symbol) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        if (symbol == null) {
            symbol = "_";
        }
        StringBuilder buffer = new StringBuilder();
        String strs[] = str.split(symbol);
        int length = strs.length;
        for (int i = 0; i < length; i++) {
            String sp = strs[i];
            //去掉第一个域名 如life_sys_user 去掉life
            if (i == 0) {
                continue;
            }
            if (StringUtils.isBlank(sp)) {
                continue;
            }
            if (buffer.toString().equals("")) {
                buffer.append(sp.toLowerCase());
            } else {
                if(i == 2){
                    buffer.append("/").append(sp.substring(0, 1).toLowerCase());
                }else{
                    buffer.append(sp.substring(0, 1).toUpperCase());
                }
                buffer.append( sp.substring(1));
            }
        }
        return buffer.toString();
    }

}
