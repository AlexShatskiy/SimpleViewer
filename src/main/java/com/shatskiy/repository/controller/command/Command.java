package com.shatskiy.repository.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * interface Command
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface Command {
	
	/**
	 * executes the command
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
