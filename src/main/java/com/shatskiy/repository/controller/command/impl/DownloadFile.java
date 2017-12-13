package com.shatskiy.repository.controller.command.impl;

import com.shatskiy.repository.controller.command.Command;
import com.shatskiy.repository.controller.command.parameter.PageLibrary;
import com.shatskiy.repository.controller.command.parameter.PageParameter;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import com.shatskiy.repository.service.factory.ServiceFactory;
import com.shatskiy.repository.service.sort.SortByName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Set;
import java.util.TreeSet;


public class DownloadFile implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceFactory factory = ServiceFactory.getInstance();
        ServiceJavaExplorer serviceJavaExplorer = factory.getServiceJavaExplorer();

        String hardCode = "E:\\betolit\\Reinforcement_2014.dll";

        //String fullPath = request.getParameter(hardCode);


        try {
            serviceJavaExplorer.downloadFile(hardCode, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
