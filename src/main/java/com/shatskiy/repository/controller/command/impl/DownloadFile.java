package com.shatskiy.repository.controller.command.impl;

import com.shatskiy.repository.controller.command.Command;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import com.shatskiy.repository.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * It download file
 * @author Shatskiy Alex
 * @version 1.0
 */
public class DownloadFile implements Command {

    private static final Logger log = LogManager.getRootLogger();

    private static String PATH_FOLDER = "pathFolder";
    private static String FILE_NAME = "fileName";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceFactory factory = ServiceFactory.getInstance();
        ServiceJavaExplorer serviceJavaExplorer = factory.getServiceJavaExplorer();

        HttpSession session = request.getSession(true);

        //Get full path from session and name file for download
        String fullPath = (String) session.getAttribute(PATH_FOLDER) + File.separator + request.getParameter(FILE_NAME);
        log.info("command=DownloadFile, full path file=" + fullPath);

        try {
            serviceJavaExplorer.downloadFile(fullPath, response);
        } catch (ServiceException e) {
            log.error(e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}