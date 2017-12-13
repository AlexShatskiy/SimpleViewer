package com.shatskiy.repository.service.impl;

import com.shatskiy.repository.dao.ModelDAO;
import com.shatskiy.repository.dao.exception.DaoException;
import com.shatskiy.repository.dao.factory.DAOFactory;
import com.shatskiy.repository.dao.manager.PassPropertiesParameter;
import com.shatskiy.repository.dao.manager.PassPropertiesResourceManager;
import com.shatskiy.repository.model.FileModelPOJO;
import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.exception.ServiceException;
import com.shatskiy.repository.service.sort.SortByName;


import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ServiceJavaExplorerImpl implements ServiceJavaExplorer {

    private static String ROOT_PASS = PassPropertiesResourceManager.getInstance().getValue(PassPropertiesParameter.FOLDER_PATH);

    @Override
    public Set<FileModelPOJO> getListFileModel(String pass) throws ServiceException {

        Set<FileModelPOJO> set;
        String passForDAO;

        DAOFactory factory = DAOFactory.getInstance();
        ModelDAO modelDAO = factory.getModelDAO();

        if (pass == null){
            passForDAO = ROOT_PASS;
        } else {
            if (pass.startsWith(ROOT_PASS)){
                passForDAO = pass;
            } else {
                throw new ServiceException("Wrong pass. Are you a hacker?");
            }
        }
        set = modelDAO.getSetFileModelPOJO(passForDAO);

        return set;
    }

    @Override
    public void downloadFile(String pass, HttpServletResponse response) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        ModelDAO modelDAO = factory.getModelDAO();

        if (pass != null){
            try {
                if (pass.startsWith(ROOT_PASS)){
                    modelDAO.downloadFile(pass, response);
                } else {
                    throw new ServiceException("Wrong pass. Are you a hacker?");
                }
            } catch (DaoException e) {
                throw new ServiceException("fail in downloadFile", e);
            }
        } else {
            throw new ServiceException("pass == null");
        }
    }
}
