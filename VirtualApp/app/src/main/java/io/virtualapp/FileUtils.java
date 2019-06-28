package io.virtualapp;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;

/**
 * Created by Superhai on 2019/6/28
 */
public class FileUtils {

    public static void copyDirDb(String fdir, String tdir) {
        File filefrom = new File(fdir);
        File fileto = new File(tdir);
        if (!fileto.exists()) {
            fileto.mkdirs();
        }
        File[] li = filefrom.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".db");
            }
        });
        Log.e("hawkhai.copyFile***", filefrom.getAbsolutePath());
        if (li == null) {
            li = new File[0];
        }
        for (File file : li) {
            copyFile(file.getAbsolutePath(), fileto + "/" + file.getName());
        }

        // /data/data/io.va.exposed/virtual/opt/data@app@com.tencent.mm-1@base.apk@classes.dex
        // /storage/emulated/0/hawkhai/wechat.dex
        copyFile("/data/data/io.va.exposed/virtual/opt/data@app@com.tencent.mm-1@base.apk@classes.dex",
                "/storage/emulated/0/hawkhai/wechat.dex");

        copyFile("/data/data/io.va.exposed/virtual/data/app/com.tencent.mm/base.apk",
                "/storage/emulated/0/hawkhai/wechat.apk");
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {

        if (new File(newPath).exists()) {
            return;
        }

        Log.e("hawkhai.copyFile---", oldPath);
        Log.e("hawkhai.copyFile-->", newPath);

        try {
            int byteRead = 0;
            File oldFile = new File(oldPath);
            if (oldFile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
                fs.close();
            }
        } catch (Exception e) {
            LogUtil.e("copyFile", "复制单个文件操作出错");
            e.printStackTrace();
        }
    }
}
