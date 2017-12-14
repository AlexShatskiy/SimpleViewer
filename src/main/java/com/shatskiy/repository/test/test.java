package com.shatskiy.repository.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class test {
    public static void main(String[] args) {
        long start = System.nanoTime();
        long result1 = folderSize(new File("E:\\betolit"));

        long finish = System.nanoTime();
        long timeConsumedMillis = finish - start;
        System.out.println("1 meth " + result1 + "time: " + timeConsumedMillis);

//        long start2 = System.nanoTime();
//        long result2 = folderSize2(new File("E:\\betolit").getAbsolutePath());
//
//        long finish2 = System.nanoTime();
//        long timeConsumedMillis2 = finish2 - start2;
//        System.out.println("2 meth " + result2 + "time: " + timeConsumedMillis2);

//        double d1 = (double) timeConsumedMillis;
//        double d2 = (double) timeConsumedMillis2;
//
//        System.out.println(d2/d1);
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

    private static long folderSize2(String directory) {
        long size = 0;
        try {
            size = Files.walk(Paths.get(directory)).mapToLong(p -> p.toFile().length() ).sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
}
