package com.generator.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

import org.apache.commons.lang.StringUtils;

public class FileUtil {

    
    /**
    * @Title: isExists
    * @Description: 判断文件是否存在
    * @author zhuzq
    * @date  2021年4月14日 上午9:57:39
    * @param filePath
    * @return
    */
    public static boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
    * @Title: isDir
    * @Descrip	判断是否是文件夹
    * @author zhuzq
    * @date  2021年4月14日 上午9:57:47
    * @param path
    * @return
    */
    public static boolean isDir(String path) {
        File file = new File(path);
        if(file.exists()){
            return file.isDirectory();
        }else{
            return false;
        }
    }

    /**
     * 文件或者目录重命名
     * @param oldFilePath 旧文件路径
     * @param reFileName 新的文件名,可以是单个文件名和绝对路径
     * @return
     */
    public static boolean reFileName(String oldFilePath, String newName) {
        try {
            File oldFile = new File(oldFilePath);
            //若文件存在
            if(oldFile.exists()){
                //判断是全路径还是文件名
                if (newName.indexOf("/") < 0 && newName.indexOf("\\") < 0){
                    //单文件名，判断是windows还是Linux系统
                    String absolutePath = oldFile.getAbsolutePath();
                    if(newName.indexOf("/") > 0){
                        //Linux系统
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1)  + newName;
                    }else{
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("\\") + 1)  + newName;
                    }
                }
                File file = new File(newName);
                //判断重命名后的文件是否存在
                if(file.exists()){
                    System.out.println("该文件已存在,不能重命名");
                }else{
                    //不存在，重命名
                    return oldFile.renameTo(file);
                }
            }else {
                System.out.println("原该文件不存在,不能重命名");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
    * @Title: copyFile
    * @Description: 文件拷贝
    * @author zhuzq
    * @date  2021年4月14日 上午9:58:05
    * @param sourceFile
    * @param targetFile
    */
    public static void copyFile(String sourceFile, String targetFile) {
        File source = new File(sourceFile);
        File target = new File(targetFile);
        target.getParentFile().mkdirs();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            in = fis.getChannel();//得到对应的文件通道
            out = fos.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null){
                    out.close();
                }
                if (in != null){
                    in.close();
                }
                if (fos != null){
                    fos.close();
                }
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * @Title: readFile
    * @Description: 读取文件内容
    * @author zhuzq
    * @date  2021年4月14日 上午9:58:20
    * @param filePath
    * @return
    */
    public static String readFile(String filePath) {
        String lines = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

  
    /**
    * @Title: writeFile
    * @Description: 写文件
    * @author zhuzq
    * @date  2021年4月14日 上午9:57:06
    * @param filePath
    * @param content
    * @param isAppend
    */
    public static void writeFile(String filePath, String content,boolean isAppend) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(filePath,isAppend);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null){
                    outputStreamWriter.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void createFile(String path) throws IOException {

        if (StringUtils.isNotEmpty(path)) {

            File file = new File(path);

            if (!file.getParentFile().exists()) {

                file.getParentFile().mkdirs();

            }

            file.createNewFile();

        }

    }

}