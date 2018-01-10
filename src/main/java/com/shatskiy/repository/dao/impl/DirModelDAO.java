package com.shatskiy.repository.dao.impl;

import com.shatskiy.repository.dao.ModelDAO;
import com.shatskiy.repository.dao.exception.DaoException;
import com.shatskiy.repository.dao.manager.FileModelCreator;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class DirModelDAO implements ModelDAO {

    private static final Logger log = LogManager.getRootLogger();

    /**
     * return set all FileModelPOJO in path
     * @param path
     * @return set of FileModelPOJO
     * @throws ServiceException
     */
    @Override
    public Set<FileModelPOJO> getSetFileModelPOJO(String path) {
        log.info("DirModelDAO.getListFileModel(..)");
        File dir = new File(path);

        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        Set<FileModelPOJO> set = new HashSet<>();

        for (File file : lst) {
            set.add(FileModelCreator.create(file));
        }
        log.info("Set<FileModelPOJO> set empty? " + set.isEmpty());
        return set;
    }

    /**
     * download file
     * @param path full path for file
     * @param response
     * @throws ServiceException
     */
    @Override
    public void downloadFile(String path, HttpServletResponse response) throws DaoException {

        File file = new File(path);
        String fileName;
        String mimeType;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());

        log.info("DirModelDAO.downloadFile, file == null:" + (file == null));

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {

            fileName = URLEncoder.encode(file.getName(), "UTF-8");
            fileName = URLDecoder.decode(fileName, "ISO8859_1");
            Path pathForMimeType = FileSystems.getDefault().getPath(path);
            mimeType = Files.probeContentType(pathForMimeType);
            log.info("mime type=" + mimeType);
            if(mimeType != null){
                response.setContentType(mimeType);
            }
            log.info("response.setHeader(..), filename=" + fileName);
            response.setHeader( "Content-Disposition", "attachment; filename=" + fileName);

            byte[] buffer = new byte[4096];
            int length;
            log.info("out.write(buffer, 0, length); start");
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            log.info("out.write(buffer, 0, length); finish");
            out.flush();
            log.info("out.flush(); finish");
        } catch (IOException e) {
            log.error(e);
            throw new DaoException("fail in downloadFile", e);
        }
    }
}
