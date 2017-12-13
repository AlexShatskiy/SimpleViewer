package com.shatskiy.repository.dao.manager;

import com.shatskiy.repository.model.FileModelPOJO;

import java.io.File;
import java.math.BigInteger;

public class FileModelCreator {

    public static FileModelPOJO create(File file){
        FileModelPOJO model = new FileModelPOJO();

        model.setFileName(file.getName());
        model.setFullPath(file.getPath());
        model.setDirectory(file.isDirectory());
        if (file.isDirectory()){
            model.setSize(new BigInteger(Long.toString(folderSize(file))));
        } else {
            model.setSize(new BigInteger(Long.toString(file.length())));
        }
        model.setParentFileName(file.getParentFile().getName());

        return model;
    }

    private static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }
}
