package com.shatskiy.repository.controller.command.impl;

import com.shatskiy.repository.controller.command.Command;
import com.shatskiy.repository.controller.command.helper.PassHelper;
import com.shatskiy.repository.controller.converter.ConverterJSON;
import com.shatskiy.repository.dao.manager.PathPropertiesParameter;
import com.shatskiy.repository.dao.manager.PathPropertiesResourceManager;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import com.shatskiy.repository.service.factory.ServiceFactory;
import com.shatskiy.repository.service.sort.SortByName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * It return JSON array(String) folders and files
 *
 * @author Shatskiy Alex
 * @version 1.0
 */
public class ShowRepositoryJSON implements Command {

    private static String ROOT_PATH = PathPropertiesResourceManager.getInstance().getValue(PathPropertiesParameter.FOLDER_PATH);
    private static String PATH_FOLDER = "pathFolder";
    private static String NAME_FOLDER = "nameFolder";
    //Hhis is necessary to determine true path(newPathFolder)
    private static String SWITCH_PATH = "switchPath";

    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("ROOT_PATH=" + ROOT_PATH);
        Set<FileModelPOJO> set = null;
        Set<FileModelPOJO> setFolder = new TreeSet<>(new SortByName());
        Set<FileModelPOJO> setFile = new TreeSet<>(new SortByName());

        HttpSession session = request.getSession(true);

        ServiceFactory factory = ServiceFactory.getInstance();
        ServiceJavaExplorer serviceJavaExplorer = factory.getServiceJavaExplorer();

        String nameFolder = request.getParameter(NAME_FOLDER);
        String switchPath = request.getParameter(SWITCH_PATH);

        if (nameFolder == null || session.getAttribute(PATH_FOLDER) == null) {

            session.setAttribute(PATH_FOLDER, ROOT_PATH);
        } else {
            String oldPathFolder = (String) session.getAttribute(PATH_FOLDER);
            String newPathFolder;
            if ("back".equals(switchPath)) {
                newPathFolder = PassHelper.getRootPath(oldPathFolder);
            } else {
                newPathFolder = oldPathFolder + File.separator + nameFolder;
            }
            session.setAttribute(PATH_FOLDER, newPathFolder);
        }

        try {
            log.info("command=ShowRepositoryJSON, full path file=" + ((String) session.getAttribute(PATH_FOLDER)));
            set = serviceJavaExplorer.getListFileModel((String) session.getAttribute(PATH_FOLDER));

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
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}