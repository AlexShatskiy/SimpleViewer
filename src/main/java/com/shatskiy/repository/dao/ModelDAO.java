package com.shatskiy.repository.dao;

import com.shatskiy.repository.dao.exception.DaoException;
import com.shatskiy.repository.model.FileModelPOJO;

import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.Set;

public interface ModelDAO {
    Set<FileModelPOJO> getSetFileModelPOJO(String pass);
    void downloadFile(String pass, HttpServletResponse response) throws DaoException;
}
