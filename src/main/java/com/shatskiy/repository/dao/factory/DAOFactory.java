package com.shatskiy.repository.dao.factory;

import com.shatskiy.repository.dao.ModelDAO;
import com.shatskiy.repository.dao.impl.DirModelDAO;


/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class DAOFactory {
private static final DAOFactory instance = new DAOFactory();
	
	private final ModelDAO modelDAO = new DirModelDAO();

	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}

	public ModelDAO getModelDAO() {
		return modelDAO;
	}
}
