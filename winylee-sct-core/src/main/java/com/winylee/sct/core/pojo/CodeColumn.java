package com.winylee.sct.core.pojo;

/**
 *  @author  shang.li on 2017/3/17.
 */
public class CodeColumn {

    private String defaultValue;
    private String name;
    private String comment;
    //不包含长度的类型 varchar
    private String type;
    //包含长度的类型 varchar(64)
    private String dataTypeStr;
    private String code;
    private Integer length;
    private boolean pkFlag;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }


    public String getDataTypeStr() {
        return dataTypeStr;
    }

    public void setDataTypeStr(String dataTypeStr) {
        this.dataTypeStr = dataTypeStr;
    }

    public boolean isPkFlag() {
        return pkFlag;
    }

    public void setPkFlag(boolean pkFlag) {
        this.pkFlag = pkFlag;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
