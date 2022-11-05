package com.wen.richresource.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author calwen
 * @since 2022/10/30
 */
public class FileUtil {

    public static void thumbnail(String path, String outPath) {
        try {
            Thumbnails.of(path)
                    .size(400, 400)
                    .outputFormat("jpg")
                    .toFile(outPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getImageBase64(String path) throws IOException {
        try (InputStream is = Files.newInputStream(Paths.get(path))) {
            byte[] bytes = IOUtils.toByteArray(is);
            return "data:image/jpg;base64," + Base64.encodeBase64String(bytes);
        }

    }


    public static void saveFile(String url, String path) {
        File f = new File(path);
        if (!f.exists()) {
            boolean mkdirs = f.getParentFile().mkdirs();
            LoggerUtil.info("mkdir=" + mkdirs, FileUtil.class);
        }
        try {
            InputStream is = new URL(url).openStream();
            DataInputStream inputStream = new DataInputStream(is);
            FileOutputStream outputStream = new FileOutputStream(f);
            byte[] buffer = new byte[1024 * 50];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
