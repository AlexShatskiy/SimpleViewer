package com.shatskiy.repository.dao.impl;

import com.shatskiy.repository.dao.ModelDAO;
import com.shatskiy.repository.dao.exception.DaoException;
import com.shatskiy.repository.dao.manager.FileModelCreator;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
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
@Repository("DirModelDAO")
public class DirModelDAO implements ModelDAO {

    private static final Logger log = LogManager.getRootLogger();
    private static final String ENCODING_TYPE = "UTF-8";
    private static final String DECODING_TYPE = "ISO8859_1";
    private static final String MIME_TYPE = "application/octet-stream";
    private static final String HEADER = "Content-Disposition";

    /**
     * return set all FileModelPOJO in path
     *
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
     *
     * @param path     full path for file
     * @param response
     * @throws ServiceException
     */
    @Override
    public void downloadFile(String path, HttpServletResponse response) throws DaoException {
        String fileName;
        String mimeType;

        File file = new File(path);

        try(InputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream()) {

            //encoding and decoding file name (for russian and other simbols)
            fileName = URLEncoder.encode(file.getName(), ENCODING_TYPE);
            fileName = URLDecoder.decode(fileName, DECODING_TYPE);
            log.info("File file == null" + (file == null));

            Path pathForMimeType = FileSystems.getDefault().getPath(path);
            mimeType = Files.probeContentType(pathForMimeType);

            response.setContentType(mimeType != null ? mimeType : MIME_TYPE);
            response.setContentLength((int) file.length());
            response.setHeader(HEADER, "attachment; filename=\"" + fileName + "\"");

            byte[] bufferData = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(bufferData)) != -1) {
                outputStream.write(bufferData, 0, read);
            }
            outputStream.flush();

            log.info("outputStream.flush()");
        } catch (IOException e) {
            log.error(e);
            throw new DaoException(e);
        }
    }
}
