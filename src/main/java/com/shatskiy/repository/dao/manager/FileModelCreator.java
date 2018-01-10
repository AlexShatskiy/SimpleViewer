package com.shatskiy.repository.dao.manager;

import com.shatskiy.repository.model.FileModelPOJO;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class FileModelCreator {

    /**
     * It creates a new FileModelPOJO from a file
     * @param file
     * @return
     */
    public static FileModelPOJO create(File file){
        FileModelPOJO model = new FileModelPOJO();

        model.setFileName(file.getName());
        model.setFullPath(file.getPath());
        model.setDirectory(file.isDirectory());
        if (file.isDirectory()){
            model.setSize(new BigInteger(Long.toString(folderSizeSpeed(file))));
        } else {
            model.setSize(new BigInteger(Long.toString(file.length())));
        }
        model.setParentFileName(file.getParentFile().getName());

        return model;
    }

    /**
     * It determines the size of the folder
     * @param directory
     * @return
     */
    @Deprecated
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

    /**
     * It determines the size of the folder
     * @param file
     * @return
     */
    private static long folderSizeSpeed(File file) {
        long size = 0;
        try {
            size = Files.walk(Paths.get(file.getAbsolutePath())).mapToLong(p -> p.toFile().length() ).sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

}
