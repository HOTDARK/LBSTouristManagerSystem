package com.xie.tsa.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static void transformToLocal(MultipartFile file,String path) throws IOException{

        InputStream inputStream = file.getInputStream();

        byte[] bytes = new byte[1024*5];
        File file1 = new File(path);
        if(!file1.exists()){
            file1.createNewFile();
            FileOutputStream out = new FileOutputStream(file1);
            while((inputStream.read(bytes)) != -1){
                out.write(bytes);
            }
            out.close();
            inputStream.close();
        }
    }

    public static File downFile(String path) {
        File file = new File(path);
        return file;
    }
}
