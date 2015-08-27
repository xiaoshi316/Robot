package com.xiaoshi.chattingrobot.zipfile;


import android.content.Context;
import android.os.Environment;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/***
 * 
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @TODO
 * @2015年7月29日
 *
 */
public class ZipCopyUtil {

    private static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

	/**
     *
     * @param context 上下文环境
     * @param assetsNamee 资源文件名
     * @param strOutFilePath 文件解压保存路径
     */
    public static void copyAndUnzip(Context context,String assetsNamee,String strOutFilePath){
//        Dialog dl = Comm.createLoadingDialog(context, "解压数据中，请稍后", false);
//            dl.show();
        try {
                File filePath = new File(strOutFilePath);
                if(!filePath.exists()){
                        filePath.mkdirs();
                }
                if(!assetsNamee.endsWith("zip")){
                        //拷贝文件
                copyDataToSD(context,assetsNamee,strOutFilePath);
//                dl.dismiss();
                return;
                }
            //拷贝文件
            copyDataToSD(context,assetsNamee,ROOT_PATH);
            //解压文件
            File temp = new File(strOutFilePath); 
//            if(!temp.exists()){
//                temp.mkdirs();  
//            }
            unZipFiles(new File(ROOT_PATH + "/" + assetsNamee ),temp);
            //删除压缩包
            File f = new File(ROOT_PATH + "/" + assetsNamee );
            if(f.exists()){
                f.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//            dl.dismiss();
    }

    /**
     * 从assets文件夹中拷贝数据到sd卡中
     * @param assetsNamee 资源文件名
     * @param strOutFilePath 拷贝到指定路劲
     * @param context 上下文环境
     * [url=home.php?mod=space&uid=2643633]@throws[/url] IOException
     */
    public static void copyDataToSD(Context context,String assetsNamee,String strOutFilePath) throws IOException
    {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(strOutFilePath + "/" + assetsNamee);
        myInput = context.getAssets().open(assetsNamee);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while(length > 0)
        {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    /**
     * 解压文件到指定目录
     * @param zipFile 要解压的文件
     * @param targFile 解压到此文件夹下
     */
    public static void unZipFiles(File zipFile,File targFile) throws IOException, InterruptedException {
        if(!targFile.exists()){
            targFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
        for(Enumeration entries = zip.entries();entries.hasMoreElements();){
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (targFile+"/"+zipEntryName).replaceAll("\\*", "/");;
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if(!file.exists()){
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是不需要解压
            File f = new File(outPath);
            if(f.isDirectory()){
                continue;
            }
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while((len=in.read(buf1))>0){
                out.write(buf1,0,len);
            }
            in.close();
            out.close();
        }
    }
}

