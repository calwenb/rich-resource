package com.wen.richresource.util;

import cn.hutool.core.io.IoUtil;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.vo.MovieVO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;

import javax.swing.plaf.PanelUI;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public static String getImageBase64(String path) {
        try {
            try (InputStream is = Files.newInputStream(Paths.get(path))) {
                byte[] bytes = IOUtils.toByteArray(is);
                return "data:image/jpg;base64," + Base64.encodeBase64String(bytes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveFile(String url, String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
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
