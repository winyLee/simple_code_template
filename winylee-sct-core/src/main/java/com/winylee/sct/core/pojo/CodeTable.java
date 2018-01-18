package com.winylee.sct.core.pojo;


import com.winylee.sct.core.utils.DateUtil;

/**
 *  @author  shang.li on 2017/3/17.
 */
public class CodeTable {
    private String authorName = System.getProperties().getProperty("user.name");

    private String createTimeStr = DateUtil.convertDateLongToDateString(DateUtil.DATE_STRING_FORMAT_ALL, System.currentTimeMillis());

    private String tableName;

    private String tableCode;

    private CodeColumn pkField;

    private boolean autoIncrementKey;

    private CodeColumn[] cols;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public CodeColumn[] getCols() {
        return cols;
    }

    public void setCols(CodeColumn[] cols) {
        this.cols = cols;
    }

    public CodeColumn getPkField() {
        return pkField;
    }

    public void setPkField(CodeColumn pkField) {
        this.pkField = pkField;
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

    public boolean isAutoIncrementKey() {
        return autoIncrementKey;
    }

    public void setAutoIncrementKey(boolean autoIncrementKey) {
        this.autoIncrementKey = autoIncrementKey;
    }
}
