package com.shatskiy.repository.controller;

import com.shatskiy.repository.controller.command.Command;
import com.shatskiy.repository.controller.command.parameter.PageParameter;
import com.shatskiy.repository.controller.command.provider.CommandProviderXML;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerRepository extends HttpServlet {

    private static final CommandProviderXML provider = new CommandProviderXML();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String commandName = request.getParameter(PageParameter.COMMAND);

        Command command = provider.getCommand(commandName);
        command.execute(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String commandName = request.getParameter(PageParameter.COMMAND);

        Command command = provider.getCommand(commandName);
        command.execute(request, response);
    }
}
