package com.winylee.sct.core.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * 〈一句话功能简述〉文件工具包 <br> 
 *  @author lishang
 */
public class FileUtils {
    /**
     * 
     * 功能描述: delete file or floder <br>
     * 〈功能详细描述〉
     *  if file  is file ,delete  this file ;
     *  if file  is directory ,delete this directory and  all files in the floder
     * @param file 需要删除的文件或者目录
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean deleteAllFile(File file){
        //判断文件是否存在
        if(file ==null || !file.exists()){
            return false;
        }
        //删除目录下所有文件盒文件夹
        if(file.isDirectory()){
            File[] fs = file.listFiles();
            //迭代
            for(File f: fs){
                deleteAllFile(f);
            }
            return file.delete();
        }else{
            return file.delete();
        }
    }

    /**
     * delete file or floder <br>
     * @param file
     * @param delThisFlg  如果file is folder , weather del this file
     * @return
     */
    public static boolean deleteAllFile(File file,boolean delThisFlg){
        //判断文件是否存在
        if(file ==null || !file.exists()){
            return false;
        }
        //删除目录下所有文件盒文件夹
        if(file.isDirectory()){
            File[] fs = file.listFiles();
            //迭代
            for(File f: fs){
                deleteAllFile(f);
            }
            if(delThisFlg)
                return file.delete();
            return true;
        }else{
            return file.delete();
        }
    }

    /**
     * 创建文件
     * @param f
     * @throws IOException
     */
    public static  void createF(File f) throws IOException {
        createF(f,false);
    }

    /**
     * 创建指定文件或目录；如果父目录不存在，则创建父目录
     * @param f
     * @param folder  是否是目录
     * @throws IOException
     */
    public static void createF(File f,boolean folder) throws IOException {
        if(!f.exists()){
            if(folder){
                f.mkdirs();
            }else{
                createF(f.getParentFile(),true);
                f.createNewFile();
            }
        }
    }

    public static StringBuffer readFile(String path) throws Exception{
        File file =  new File(path);
        StringBuffer buffer =  new StringBuffer();
        if(file.exists() && file.isFile()){
            InputStream is = new FileInputStream(file);
            BufferedReader  fi =  new BufferedReader(new InputStreamReader(is));
            int i = 0;
            while(fi.ready()){
                if(i==0){
                    buffer.append(fi.readLine());
                    i=1;
                }else{
                    buffer.append(fi.readLine());
                }
            }
        }
            return buffer;
    }

    public void writeFile(byte[] b,String path)throws Exception{
        File of = new File(path);
        if(!of.exists()){
            of.createNewFile();
        }else{
            of.delete();
            of.createNewFile();
        }
        FileOutputStream fo = new FileOutputStream(of);
        fo.write(b);
        fo.flush();
        fo.close();
    }

}
