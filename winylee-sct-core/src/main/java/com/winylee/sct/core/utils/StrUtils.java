package com.winylee.sct.core.utils;


import org.apache.commons.lang.StringUtils;

/**
 *  @author  shang.li on 2015/12/9.
 */
public class StrUtils {

    /**
     * 将string中每次遇到的符号去掉,并且将符号后面的第一个字母大写
     * @param str str
     * @param symbol  符号 如果为空，则默认值为_
     * @return str
     */
    public static String convertStrU(String str,String symbol){
        if(StringUtils.isBlank(str)){
            return str;
        }
        if(symbol==null){
            symbol="_";
        }
        StringBuffer buffer = new StringBuffer();
        String strs[] = str.split(symbol);
        int i = 0;
        for(String sp:strs){
            if(i==0){
                buffer.append(sp);
            }
            else{
                if(StringUtils.isBlank(sp)){
                    continue;
                }
                buffer.append( sp.substring(0,1).toUpperCase() + sp.substring(1));
            }
            i++;
        }
        return buffer.toString();
    }
}
