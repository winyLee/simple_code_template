package com.winylee.sct.core;

import com.winylee.sct.core.GenPdm;

/**
 *  @author  shang.li on 2017/3/17.
 * 通过pdm来生成
 * //TODO:WINYLEE使用 fluent风格来重构代码
 */
public class WinyleeTemplate {


    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\shang.li\\Desktop\\PDM\\work\\2.pdm";
        //通过PDM生成
        //CodeTemplateConstant.PMD_PATH
        generateByPdm(path);
        //通过xml生成
//        generateByXml("C:\\Users\\shang.li\\Desktop\\模版");
    }

    public static void generateByPdm(String path) throws Exception {
        GenPdm.generate(path);
    }

    public static void generateByXml(String path) throws Exception{
        GenPdm.generateXml(path);
    }




}