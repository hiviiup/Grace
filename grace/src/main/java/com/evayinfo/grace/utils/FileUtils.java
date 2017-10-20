package com.evayinfo.grace.utils;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DEVIN on 2017/10/17.
 */

public class FileUtils {

    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            AppUtils.toast("内存卡未安装");
            return false;
        }
        return true;
    }

    @RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static void write2SdCard(String filename, InputStream is) {
        File file;
        if (hasSDCard()) {
            file = new File(AppUtils.context().getExternalFilesDir(null), filename);
            write(is, file);
        }
    }

    @RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static void write2InternalStorage(String filename, InputStream is) {
        File file;
        if (hasSDCard()) {
            file = new File(AppUtils.context().getFilesDir(), filename);
            write(is, file);
        }
    }

    /**
     * 写入文件
     *
     * @param in   输入流
     * @param file 文件
     */
    @RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static void write(InputStream in, File file) {
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            byte[] buffer = new byte[1024 * 128];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public static byte[] read(String path) {
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String byte2hex(byte[] b) // 二进制转字符串
    {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }

        }
        return sb.toString();
    }

    public static byte[] hex2byte(String str) { // 字符串转二进制
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    public static long getFileSize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                size = size + getFileSize(childFile);
            }
        } else {
            size = size + file.length();
        }
        return size;
    }

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                deleteFile(childFile);
            }
        } else {
            file.delete();
        }
    }


}
