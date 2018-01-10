package com.shatskiy.repository.service;

import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface ServiceJavaExplorer {

    /**
     * return set all FileModelPOJO in path
     * @param path
     * @return set of FileModelPOJO
     * @throws ServiceException
     */
    Set<FileModelPOJO> getListFileModel(String path) throws ServiceException;

    /**
     * download file
     * @param path full path for file
     * @param response
     * @throws ServiceException
     */
    void downloadFile(String path, HttpServletResponse response) throws ServiceException;
}
