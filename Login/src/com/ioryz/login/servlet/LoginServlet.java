package com.ioryz.login.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ioryz.login.exception.DBException;
import com.ioryz.login.model.User;
import com.ioryz.login.util.DBUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
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
        System.out.println("userName=>" + userName + "; password=>" + password);

        HttpSession session = request.getSession();
        Map<String, String> errorMessages = new HashMap<String, String>();

        if (userName == null || "".equals(userName)) {
            errorMessages.put("username", "User name is required!");
        }

        if (password == null || "".equals(password)) {
            errorMessages.put("password", "Password is required!");
        }

        if (!errorMessages.isEmpty()) {
            session.setAttribute("ERROR", errorMessages);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM login WHERE user_name=?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                int id = rs.getInt("id");
                String userNameInDB = rs.getString("user_name");
                String passwordInDB = rs.getString("password");
                user.setId(id);
                user.setUserName(userNameInDB);
                user.setPassword(passwordInDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        if (user == null) {
            errorMessages.put("username", "User does not exist!");
        } else if (password.equals(user.getPassword())) {
            session.setAttribute("USER", user);
            request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);
            return;
        } else {
            errorMessages.put("password", "Password is incorrect!");
        }

        if (!errorMessages.isEmpty()) {
            session.setAttribute("ERROR", errorMessages);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

}
