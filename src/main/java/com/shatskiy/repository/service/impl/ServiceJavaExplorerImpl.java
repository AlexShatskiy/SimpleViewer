package com.shatskiy.repository.service.impl;

import com.shatskiy.repository.dao.ModelDAO;
import com.shatskiy.repository.dao.exception.DaoException;
import com.shatskiy.repository.dao.factory.DAOFactory;
import com.shatskiy.repository.dao.manager.PathPropertiesParameter;
import com.shatskiy.repository.dao.manager.PathPropertiesResourceManager;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class ServiceJavaExplorerImpl implements ServiceJavaExplorer {

    private static final Logger log = LogManager.getRootLogger();

    //initial path
    private static String ROOT_PATH = PathPropertiesResourceManager.getInstance().getValue(PathPropertiesParameter.FOLDER_PATH);

    /**
     * return set all FileModelPOJO in path
     * @param path
     * @return set of FileModelPOJO
     * @throws ServiceException
     */
    @Override
    public Set<FileModelPOJO> getListFileModel(String path) throws ServiceException {

        Set<FileModelPOJO> set;
        String passForDAO;

        DAOFactory factory = DAOFactory.getInstance();
        ModelDAO modelDAO = factory.getModelDAO();

        if (path == null){
            passForDAO = ROOT_PATH;
        } else {
            if (path.startsWith(ROOT_PATH)){
                passForDAO = path;
            } else {
                log.info("ServiceException(\"Wrong path. Are you a hacker?\")");
                throw new ServiceException("Wrong path. Are you a hacker?");
            }
        }
        log.info("ServiceJavaExplorerImpl.getListFileModel(..)");
        set = modelDAO.getSetFileModelPOJO(passForDAO);

        return set;
    }

    /**
     * download file
     * @param path full path for file
     * @param response
     * @throws ServiceException
     */
    @Override
    public void downloadFile(String path, HttpServletResponse response) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        ModelDAO modelDAO = factory.getModelDAO();

        log.info("ServiceJavaExplorerImpl.downloadFile(String path, HttpServletResponse response)");
        if (path != null){
            try {
                if (path.startsWith(ROOT_PATH)){
                    log.info("ServiceJavaExplorerImpl.downloadFile(String path, HttpServletResponse response); path.startsWith(ROOT_PATH)");
                    modelDAO.downloadFile(path, response);
                } else {
                    log.info("fail:  ServiceJavaExplorerImpl.downloadFile(..); ServiceException(\"Wrong path. Are you a hacker?\")");
                    throw new ServiceException("Wrong path. Are you a hacker?");
                }
            } catch (DaoException e) {
                log.error(e);
                throw new ServiceException("fail:  fail in downloadFile", e);
            }
        } else {
            log.info("fail:  ServiceJavaExplorerImpl.downloadFile(..); path = null");
            throw new ServiceException("path = null");
        }
    }
}
