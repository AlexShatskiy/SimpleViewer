package com.shatskiy.repository.dao.impl;

import com.shatskiy.repository.dao.ModelDAO;
import com.shatskiy.repository.dao.exception.DaoException;
import com.shatskiy.repository.dao.manager.FileModelCreator;
import com.shatskiy.repository.model.FileModelPOJO;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

public class DirModelDAO implements ModelDAO {

    @Override
    public Set<FileModelPOJO> getSetFileModelPOJO(String pass) {

        File dir = new File(pass);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        Set<FileModelPOJO> set = new HashSet<>();

        for (File file : lst) {

            set.add(FileModelCreator.create(file));
        }
        return set;
    }

    @Override
    public void downloadFile(String pass, HttpServletResponse response) throws DaoException {
        System.out.println("DAO pass: " + pass);
        File file = new File(pass);
        System.out.println("File name: " + file.getName());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());
        try {
            response.setHeader("Content-Disposition",
                    String.format("attachment; filename=\"%s\"", URLEncoder.encode(file.getName(), "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } catch (IOException e) {
            throw new DaoException("fail in downloadFile", e);
        }
    }
}
