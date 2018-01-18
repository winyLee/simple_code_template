package com.winylee.sct.core.constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  @author  shang.li on 2017/3/17.
 * //TODO:winylee 修改为可以设置的参数，或者配置文件。
 */
public class CodeTemplateConstant {

    //PDM的路径
    public static final String PMD_PATH = "C:\\Users\\shang.li\\Desktop\\产品知识库\\1.pdm";

    //pdm生成的liquibase liquebase
    public static final String LIQUIBASE_TEMPLATE_PATH = "config/template/core/ftl/liquebase/eco";
    public static final String LIQUIBASE_TEMPLATE_NAME = "liquibase_xml.ftl";

    //当前项目的开始名称(如com.ecovacs,com.winylee)
//    public static String auth_package = "com.winylee.framework";
    public static String auth_package = "com.ecovacs";

    //当前项目的domain名称("如果不设置则为默认表明的第一个单词：如表名dist_sales_order" 则为dist)
    public static  String project_domain = "";
    // xml文件或者文件夹的路径(系统路径)

    //xml文件或者文件夹的路径(classpath目录)
    public static  String xmlFolder = "config/xml";

    //TODO:winylee 将这些属性修改为 设置
    //ftl模版文件路径(classpath的路径)
    public static  String ftlFolder = "config/template/ecovacs/ftl/jpa_ace";

    //输出的文件路径(会删除当前文件夹下所有文件)
    public static  String outFolder = "D:/outPut";

    //排除在外的filed不生存model的字段
    public static Set excludeFieldSet =new HashSet();

    public static Set excludeXmlFieldSet =new HashSet();

    //排除在外的filed不生存model的字段
    public static Map<String,String> typeConvertMap =new HashMap<>();
    static {
        excludeFieldSet.add("id");
        excludeFieldSet.add("creator");
        excludeFieldSet.add("updater");
        excludeFieldSet.add("create_time");
        excludeFieldSet.add("update_time");
        excludeFieldSet.add("version");
        excludeFieldSet.add("creater");
        excludeFieldSet.add("modifier");
        excludeFieldSet.add("modify_time");

        excludeXmlFieldSet.add("creator");
        excludeXmlFieldSet.add("updater");
        excludeXmlFieldSet.add("create_time");
        excludeXmlFieldSet.add("update_time");
        excludeXmlFieldSet.add("version");
        excludeXmlFieldSet.add("creater");
        excludeXmlFieldSet.add("modifier");
        excludeXmlFieldSet.add("modify_time");

        typeConvertMap.put("varchar","String");
        typeConvertMap.put("bigint","Long");
        typeConvertMap.put("double","BigDecimal");
        typeConvertMap.put("text","String");
        typeConvertMap.put("date","Date");
        typeConvertMap.put("time","Date");
    };


    public static String convertType(String orginalType){
        return typeConvertMap.get(orginalType)==null? "String" : typeConvertMap.get(orginalType);
    }
}
