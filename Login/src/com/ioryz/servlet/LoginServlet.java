package com.ioryz.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test";
            conn = DriverManager.getConnection(url, "root", "welcome123_");
            pstmt = conn.prepareStatement("SELECT * FROM login WHERE user_name=?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String userNameInDB = rs.getString("user_name");
                String passwordInDB = rs.getString("password");
                System.out.println("***id=>" + id + ", username=>" + userNameInDB + ", password=>" + passwordInDB);
                if (password != null && password.equals(passwordInDB)) {
                    HttpSession session = request.getSession();
                    User user = new User();
                    user.setId(id);
                    user.setUserName(userNameInDB);
                    user.setPassword(passwordInDB);
                    session.setAttribute("USER", user);
                    request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
