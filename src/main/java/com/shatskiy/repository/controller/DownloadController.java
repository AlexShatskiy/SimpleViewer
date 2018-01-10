package com.shatskiy.repository.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class DownloadController  extends HttpServlet {

    private static final Logger log = LogManager.getRootLogger();

    private static String PATH_FOLDER = "pathFolder";
    private static String FILE_NAME = "fileName";

    private static final String ENCODING_TYPE = "UTF-8";
    private static final String DECODING_TYPE = "ISO8859_1";
    private static final String MIME_TYPE = "application/octet-stream";
    private static final String HEADER = "Content-Disposition";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(true);
        log.info("DownloadServlet, file name=" + req.getParameter(FILE_NAME));
        //Get full path from session and name file for download
        String fullPath = (String) session.getAttribute(PATH_FOLDER) + File.separator + req.getParameter(FILE_NAME);
        log.info("DownloadServlet, full path=" + fullPath);
        try {

            File file = new File(fullPath);
            String fileName = null;
            //encoding and decoding file name (for russian and other simbols)
            try {
                fileName = URLEncoder.encode(file.getName(), ENCODING_TYPE);
                fileName = URLDecoder.decode(fileName, DECODING_TYPE);
                log.info("File file == null" + (file == null));
            } catch (UnsupportedEncodingException e) {
                log.error(e);
                e.printStackTrace();
            }
            //reading thread of bytes - and writenning new file
            ServletContext ctx = getServletContext();
            InputStream fis = null;
            fis = new FileInputStream(file);
            String mimeType = ctx.getMimeType(file.getAbsolutePath());
            resp.setContentType(mimeType != null ? mimeType : MIME_TYPE);
            resp.setContentLength((int) file.length());
            resp.setHeader(HEADER, "attachment; filename=\"" + fileName + "\"");
            ServletOutputStream os = resp.getOutputStream();
            byte[] bufferData = new byte[1024];
            int read = 0;
            while ((read = fis.read(bufferData)) != -1) {
                os.write(bufferData, 0, read);
            }
            os.flush();
            os.close();
            fis.close();
            log.info("os.flush(); os.close(); fis.close()");
        } catch (IOException e) {
            log.error(e);
        }
    }
}
