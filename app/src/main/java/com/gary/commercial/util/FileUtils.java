/*
 * Copyright (C) 2015-present, Ant Financial Services Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gary.commercial.util;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.gary.commercial.ComlApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by ruyao.yry on 2018/3/25.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static String readFile(File file) {
        String content = null;
        BufferedReader br = null;

        // 避免文件不存在抛异常
        if (!file.exists()) {
            return null;
        }

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            content = sb.toString();
        } catch (IOException e) {
            CLog.e(TAG, "Catch IOException: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    CLog.e(TAG, "Catch IOException: " + e.getMessage());
                }
            }
        }
        return content;
    }

    public static byte[] getFileBytes(File file) {
        byte[] buf = null;
        try {
            InputStream in = new FileInputStream(file);
            buf = new byte[in.available()];
            while (in.read(buf) != -1) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buf;
    }

    public static void writeToFile(String content, File file) {
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            OutputStream os = new FileOutputStream(file);
            writer = new OutputStreamWriter(os);
            bw = new BufferedWriter(writer);
            bw.write(content);
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File curFile : children) {
                deleteFile(curFile);
            }
        }
        file.delete();
    }

    public static long getFileSize(File file) {
        if (file.isFile()) {
            return file.length();
        }

        long length = 0;
        for (File curFile : file.listFiles()) {
            if (curFile.isFile())
                length += curFile.length();
            else
                length += getFileSize(curFile);
        }
        return length;
    }


    /**
     * 检查文件MD5值
     *
     * @param file
     * @param md5
     * @return
     */
    public static boolean checkFileMd5(File file, String md5) {
        if (!file.isFile() || StringUtil.isEmpty(md5)) {
            return false;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);

            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        BigInteger bigInt = new BigInteger(1, digest.digest());
        return new BigInteger(md5, 16).equals(bigInt);
    }
}
