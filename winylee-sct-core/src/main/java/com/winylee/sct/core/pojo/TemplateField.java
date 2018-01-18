package com.winylee.sct.core.pojo;

/**
 *  @author  shang.li on 2015/12/8.
 */
public class TemplateField {

    /**
     * 字段id（代码）
     */
    private String fieldId;

    /**
     * 字段id首字母大写（代码）
     */
    private String fieldUId;

    /**
     * 类型
     */
    private String fieldType;
    /**
     * 长度
     */
    private String fieldLength;

    /**
     * 字段名称（名称中文）
     */
    private String fieldName;

    /**
     * 表字段（名称如：user_name）
     */
    private String columnName;

    /**
     * 字段描述
     */
    private String fieldComment;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
        this.fieldUId = fieldId.substring(0,1).toUpperCase()+fieldId.substring(1);
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public String getFieldUId() {
        return fieldUId;
    }

//    public void setFieldUId(String fieldUId) {
//        this.fieldUId = fieldUId;
//    }


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }
}
