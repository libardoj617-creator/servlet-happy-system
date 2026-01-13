//package com.mycompany.servlethappysystem;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/IngresarServlet")
//public class IngresarServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    // Configuración de conexión a MySQL
//    private static final String URL = "jdbc:mysql://localhost:3306/bdhappysystem";
//    private static final String USER = "root";
//    private static final String PASSWORD = "1234"; // tu clave de MySQL
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("text/html;charset=UTF-8");
//
//        String usuario = request.getParameter("usuario");
//        String password = request.getParameter("password");
//
//        try (PrintWriter out = response.getWriter()) {
//            // Conexión a la base de datos
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//
//                // Consulta para validar usuario
//                String sql = "SELECT * FROM usuarios WHERE nombre=? AND password=?";
//                PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setString(1, usuario);
//                ps.setString(2, password);
//
//                ResultSet rs = ps.executeQuery();
//
//                if (rs.next()) {
//                    out.println("<h1>Login correcto. Bienvenido " + usuario + "</h1>");
//                } else {
//                    out.println("<h1>Usuario o contraseña incorrectos</h1>");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace(response.getWriter());
//        }
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Servlet de ingreso de usuarios con validación JDBC";
//    }
//}

