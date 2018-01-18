package com.winylee.sct.core.pojo;


import com.winylee.sct.core.utils.DateUtil;

import java.util.List;

/**
 *  @author  shang.li on 2015/12/8.
 * @Description 封装所有信息
 */
public class TemplatePojo {

    private String authorName = System.getProperties().getProperty("user.name");

    private String createTimeStr = DateUtil.convertDateLongToDateString(DateUtil.DATE_STRING_FORMAT_ALL, System.currentTimeMillis());

    /**
     * 域
     */
    private String domain;

    /**
     * 类的包名分类 如 （service,dto,dao,controller）
     */
    private String packType;

    /**
     * 功能模块名称 如 （order,distributor,core）
     */
    private String  moduleName;


    /**
     * package
     */
    private String nameSpace;

    private String daoPackage;

    /**
     * dtopackage
     */
    private String dtoNameSpace;

    /**
     * db table name
     */
    private String tblId;

    /**
     * class name
     */
    private String className;
    /**
     * controller的URL和返回地址的前缀
     */
    private String mappingUrl;

    /**
     * class description
     */
    private String classDes;

    private List<TemplateField> fields;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
        if(nameSpace !=null){
            this.dtoNameSpace= nameSpace.replace("model","dto");
        }
    }

    public String getTblId() {
        return tblId;
    }

    public void setTblId(String tblId) {
        this.tblId = tblId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDes() {
        return classDes;
    }

    public void setClassDes(String classDes) {
        this.classDes = classDes;
    }

    public List<TemplateField> getFields() {
        return fields;
    }

    public void setFields(List<TemplateField> fields) {
        this.fields = fields;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDtoNameSpace() {
        return dtoNameSpace;
    }

    public void setDtoNameSpace(String dtoNameSpace) {
        this.dtoNameSpace = dtoNameSpace;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }
}
