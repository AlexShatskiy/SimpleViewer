package com.shatskiy.repository.controller.command.impl;

import com.shatskiy.repository.controller.command.Command;
import com.shatskiy.repository.controller.command.helper.PassHelper;
import com.shatskiy.repository.controller.command.parameter.PageLibrary;
import com.shatskiy.repository.controller.command.parameter.PageParameter;
import com.shatskiy.repository.controller.converter.ConverterJSON;
import com.shatskiy.repository.dao.manager.PassPropertiesParameter;
import com.shatskiy.repository.dao.manager.PassPropertiesResourceManager;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import com.shatskiy.repository.service.factory.ServiceFactory;
import com.shatskiy.repository.service.sort.SortByName;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


public class ShowRepositoryJSON implements Command {

    private static String ROOT_PATH = PassPropertiesResourceManager.getInstance().getValue(PassPropertiesParameter.FOLDER_PATH);
    private static String PATH_FOLDER = "pathFolder";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Set<FileModelPOJO> set = null;
        Set<FileModelPOJO> setFolder = new TreeSet<>(new SortByName());
        Set<FileModelPOJO> setFile = new TreeSet<>(new SortByName());

        HttpSession session = request.getSession(true);

        ServiceFactory factory = ServiceFactory.getInstance();
        ServiceJavaExplorer serviceJavaExplorer = factory.getServiceJavaExplorer();

        String nameFolder = request.getParameter("nameFolder");
        String switchPath = request.getParameter("switchPath");

        if (nameFolder == null || session.getAttribute(PATH_FOLDER) == null) {

            session.setAttribute(PATH_FOLDER, ROOT_PATH);
        } else {
            String oldPathFolder = (String) session.getAttribute(PATH_FOLDER);
            String newPathFolder;
            if ("back".equals(switchPath)){
                newPathFolder = PassHelper.getRootPath(oldPathFolder);
            } else {
                System.out.println("wor");
                newPathFolder = oldPathFolder + "\\" + nameFolder;
            }
            session.setAttribute(PATH_FOLDER, newPathFolder);
        }

        try {
            set = serviceJavaExplorer.getListFileModel((String) session.getAttribute(PATH_FOLDER));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        for (FileModelPOJO model : set) {
            if (model.getDirectory()) {
                setFolder.add(model);
            } else {
                setFile.add(model);
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ConverterJSON.convertSetToStringJSON(setFolder, setFile));
    }
}
