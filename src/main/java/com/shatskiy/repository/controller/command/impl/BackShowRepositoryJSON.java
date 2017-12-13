package com.shatskiy.repository.controller.command.impl;

import com.shatskiy.repository.controller.command.Command;
import com.shatskiy.repository.controller.command.helper.PassHelper;
import com.shatskiy.repository.controller.converter.ConverterJSON;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import com.shatskiy.repository.service.factory.ServiceFactory;
import com.shatskiy.repository.service.sort.SortByName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


public class BackShowRepositoryJSON implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Set<FileModelPOJO> set = null;
        Set<FileModelPOJO> setFolder = new TreeSet<>(new SortByName());
        Set<FileModelPOJO> setFile = new TreeSet<>(new SortByName());

        ServiceFactory factory = ServiceFactory.getInstance();
        ServiceJavaExplorer serviceJavaExplorer = factory.getServiceJavaExplorer();

        String fullPath = request.getParameter("pass");

        String pass = PassHelper.getRootPath(fullPath);

        try {
            set = serviceJavaExplorer.getListFileModel(pass);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        for (FileModelPOJO model : set){
            if (model.getDirectory()){
                setFolder.add(model);
            } else {
                setFile.add(model);
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if ("true".equals(request.getParameter("isFolder"))) {
            response.getWriter().write(ConverterJSON.convertSetToStringJSON(setFolder));
        } else {
            response.getWriter().write(ConverterJSON.convertSetToStringJSON(setFile));
        }
    }
}
