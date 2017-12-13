package com.shatskiy.repository.service;

import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public interface ServiceJavaExplorer {
    Set<FileModelPOJO> getListFileModel(String pass) throws ServiceException;
    void downloadFile(String pass, HttpServletResponse response) throws ServiceException;
}
