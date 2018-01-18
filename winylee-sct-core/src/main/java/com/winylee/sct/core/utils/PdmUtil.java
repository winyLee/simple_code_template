package com.winylee.sct.core.utils;

import com.winylee.sct.core.constants.CodeTemplateConstant;
import com.winylee.sct.core.pojo.CodeColumn;
import com.winylee.sct.core.pojo.CodeTable;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  @author  shang.li on 2017/3/17.
 */
public class PdmUtil {

    public static List<CodeTable> parsePDMToList(String filePath) {
        List<CodeTable> tabList = new ArrayList<CodeTable>();
        CodeTable table = null;
        CodeColumn[] cols = null;
        File f = new File(filePath);
        SAXReader sr = new SAXReader();
        Document doc = null;
        try {
            doc = sr.read(f);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Iterator itr = doc.selectNodes("//c:Tables//o:Table").iterator();
        while (itr.hasNext()) {
            Element e_table = (Element) itr.next();
            String code = e_table.elementTextTrim("Code").toLowerCase();
            table = new CodeTable();
            cols = new CodeColumn[]{};
            List<CodeColumn> list = new ArrayList<CodeColumn>();
            CodeColumn col = null;

            table.setTableName(e_table.elementTextTrim("Name"));
            table.setTableCode(code);
            Iterator itr1 = e_table.element("Columns").elements("Column").iterator();
            while (itr1.hasNext()) {
                Element e_col = (Element) itr1.next();
                String columnCode = e_col.elementTextTrim("Code").toLowerCase();
                if(CodeTemplateConstant.excludeXmlFieldSet.contains(columnCode)){
                    continue;
                }
                try {
                    col = new CodeColumn();
                    String pkID = e_col.attributeValue("Id");
                    col.setDefaultValue(e_col.elementTextTrim("DefaultValue"));
                    String name = e_col.elementTextTrim("Name");
                    col.setName(name);
                    String comment = e_col.elementTextTrim("Comment");
                    if(StringUtils.isBlank(comment)){
                        comment = name;
                    }
                    col.setComment(comment);
                    String  dataTypeStr = e_col.elementTextTrim("DataType");
                    col.setDataTypeStr(dataTypeStr);
                    if (dataTypeStr.indexOf("(") > 0) {
                        col.setType(dataTypeStr.substring(0, dataTypeStr.indexOf("(")));
                    } else {
                        col.setType(dataTypeStr);
                    }
                    col.setCode(columnCode);
                    col.setLength(e_col.elementTextTrim("Length") == null ? null : Integer.parseInt(e_col.elementTextTrim("Length")));
                    if (e_table.element("Keys") != null) {
                        String keys_key_id = e_table.element("Keys").element("Key").attributeValue("Id");
                        String keys_column_ref = e_table.element("Keys").element("Key").element("Key.Columns")
                                .element("Column").attributeValue("Ref");
                        String keys_primarykey_ref_id = e_table.element("PrimaryKey").element("Key").attributeValue("Ref");

                        if (keys_primarykey_ref_id.equals(keys_key_id) && keys_column_ref.equals(pkID)) {
                            col.setPkFlag(true);
                            table.setPkField(col);
                            if(col.getDataTypeStr().equals("bigint") || col.getDataTypeStr().equals("int")){
                                table.setAutoIncrementKey(Boolean.TRUE);
                            }
                        }
                    }
                    list.add(col);
                } catch (Exception ex) {
                    // col.setType(e_col.elementTextTrim("DataType"));
                    ex.printStackTrace();
                }
            }
            table.setCols(list.toArray(cols));
            tabList.add(table);
        }
        return tabList;
    }

}
