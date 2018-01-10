package com.shatskiy.repository.controller.helper;

import com.shatskiy.repository.dao.manager.PathPropertiesParameter;
import com.shatskiy.repository.dao.manager.PathPropertiesResourceManager;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.sort.SortByName;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class HelperForController {
    /**
     * @param pass
     * @return
     * It determines the path of the root folder
     */
    private static String getRootPath(String pass) {

        String result;
        String passMakedFirst = null;

        PathPropertiesResourceManager resourceManager = PathPropertiesResourceManager.getInstance();
        String mainPass = resourceManager.getValue(PathPropertiesParameter.FOLDER_PATH);

        if (pass.startsWith(mainPass)) {

            int first = pass.lastIndexOf(File.separator);
            passMakedFirst = pass.substring(0, first);

            if (passMakedFirst.startsWith(mainPass)) {
                result = passMakedFirst;
            } else {
                result = mainPass;
            }
        } else {
            result = mainPass;
        }
        return result;
    }

    /**
     * It determines true path for session
     * @param nameFolder name folder from request
     * @param switchPath route(back or forward(defolt)) from request
     * @param session current session
     */
    public static void setTruePathToSession(String nameFolder, String switchPath, HttpSession session){
        if (nameFolder == null || session.getAttribute(ParameterForController.PATH_FOLDER) == null) {

            session.setAttribute(ParameterForController.PATH_FOLDER, ParameterForController.ROOT_PATH);
        } else {
            String oldPathFolder = (String) session.getAttribute(ParameterForController.PATH_FOLDER);
            String newPathFolder;
            if ("back".equals(switchPath)) {
                newPathFolder = getRootPath(oldPathFolder);
            } else {
                newPathFolder = oldPathFolder + File.separator + nameFolder;
            }
            session.setAttribute(ParameterForController.PATH_FOLDER, newPathFolder);
        }
    }

    /**
     * It distributes folders and files by sets
     * @param set full set from service
     * @param setFolder empty set for folders
     * @param setFile empty set for files
     */
    public static void fillsetFoldersAndFiles(Set<FileModelPOJO> set, Set<FileModelPOJO> setFolder, Set<FileModelPOJO> setFile){
        for (FileModelPOJO model : set) {
            if (model.getDirectory()) {
                setFolder.add(model);
            } else {
                setFile.add(model);
            }
        }
    }
}
