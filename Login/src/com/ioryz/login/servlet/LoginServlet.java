package com.ioryz.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ioryz.login.Constants;
import com.ioryz.login.exception.ParamException;
import com.ioryz.login.exception.ServiceException;
import com.ioryz.login.model.User;
import com.ioryz.login.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        try {
            User user = null;
            UserService userService = new UserService();
            user = userService.login(userName, password);
            session.setAttribute(Constants.USER, user);
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } catch (ParamException e) {
            session.setAttribute(Constants.ERROR, e.getErrorMessages());
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        } catch (ServiceException e) {
            session.setAttribute(Constants.BIZ_ERROR, "[" + e.getCode() + "]" + e.getMessage());
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

}
