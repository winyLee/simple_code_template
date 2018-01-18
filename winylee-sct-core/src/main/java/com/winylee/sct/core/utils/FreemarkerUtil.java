package com.winylee.sct.core.utils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.*;
import java.util.Map;

/**
 *  @author  shang.li on 2015/12/8.
 */
public class FreemarkerUtil {

    Configuration configuration;

    public   void  setEn(String path) throws IOException, TemplateException {
        Version v = new Version("2.3.21");
        configuration = new Configuration(v);
        path = ClassLoader.getSystemResource("").getPath()+ path;
        path = path.replaceAll("%20"," ");
        System.out.println(path);
        configuration.setDirectoryForTemplateLoading(new File(path));
    }

    public  void process(String templateName,String destName,Object o) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        File f = new File(destName);
        if(!f.exists())
            FileUtils.createF(f);
        template.process(o,new OutputStreamWriter(new FileOutputStream(f)));
    }

    public String getFileString(String templateName,Map map)throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        StringWriter sw = new StringWriter();
        template.process(map,sw);
        return sw.toString();
    }
}
