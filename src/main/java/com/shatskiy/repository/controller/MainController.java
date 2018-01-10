package com.shatskiy.repository.controller;

import com.shatskiy.repository.controller.converter.ConverterJSON;
import com.shatskiy.repository.controller.helper.HelperForController;
import com.shatskiy.repository.controller.helper.ParameterForController;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import com.shatskiy.repository.service.sort.SortByName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * main controller
 * @author Shatskiy Alex
 * @version 1.0
 */
@Controller
public class MainController {

    private static final Logger log = LogManager.getRootLogger();

    @Autowired
    @Qualifier("ServiceJavaExplorer")
    private ServiceJavaExplorer service;

    /**
     * redirect to login page
     * @return login page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage() {

        return "redirect:admin";
    }

    /**
     * admin page after authorization
     * @return
     */
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName(ParameterForController.SINGLE_PAGE);

        return model;
    }

    /**
     * sign out from profile
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * view repository
     * @param request
     * @param response
     */
    @RequestMapping(value = "admin/viewRepository", method = RequestMethod.POST)
    public void viewRepository(HttpServletRequest request, HttpServletResponse response) {
        try {
            //set encoding
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");

            log.info("ROOT_PATH=" + ParameterForController.ROOT_PATH);
            Set<FileModelPOJO> set = null;
            Set<FileModelPOJO> setFolder = new TreeSet<>(new SortByName());
            Set<FileModelPOJO> setFile = new TreeSet<>(new SortByName());

            HttpSession session = request.getSession(true);

            String nameFolder = request.getParameter(ParameterForController.NAME_FOLDER);
            String switchPath = request.getParameter(ParameterForController.SWITCH_PATH);

            HelperForController.setTruePathToSession(nameFolder, switchPath, session);

            log.info("path in session=" + ((String) session.getAttribute(ParameterForController.PATH_FOLDER)));
            set = service.getListFileModel((String) session.getAttribute(ParameterForController.PATH_FOLDER));

            HelperForController.fillsetFoldersAndFiles(set, setFolder, setFile);

            log.info("set folder empty==" + setFolder.isEmpty());
            log.info("set files empty==" + setFile.isEmpty());
            response.getWriter().write(ConverterJSON.convertSetToStringJSON(setFolder, setFile));
            log.info("admin/viewRepository==success");
        } catch (ServiceException | IOException e) {
            log.error(e);
        }
    }

    /**
     * download file
     * @param request
     * @param response
     */
    @RequestMapping(value = "admin/download", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            //set encoding
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession(true);
            log.info("DownloadServlet, file name=" + request.getParameter(ParameterForController.FILE_NAME));
            //Get full path from session and name file for download
            String fullPath = (String) session.getAttribute(ParameterForController.PATH_FOLDER) + File.separator + request.getParameter(ParameterForController.FILE_NAME);
            log.info("DownloadServlet, full path=" + fullPath);

            service.downloadFile(fullPath, response);
            log.info("admin/download==success");
        } catch (ServiceException | IOException e) {
            log.error(e);
        }
    }
}
