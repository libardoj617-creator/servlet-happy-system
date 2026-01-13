/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
//package com.mycompany.servlethappysystem;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
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
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("text/html;charset=UTF-8");
//
//        String usuario = request.getParameter("usuario");
//        String password = request.getParameter("password");
//        String modo = request.getParameter("modo"); // "login" o "register"
//
//        if ("login".equals(modo)) {
//            // 🔑 Lógica de inicio de sesión
//            // Validar usuario y contraseña en la base de datos
//            response.getWriter().println("Procesando LOGIN para: " + usuario);
//        } else if ("register".equals(modo)) {
//            // 🔑 Lógica de registro
//            // Insertar nuevo usuario en la base de datos
//            response.getWriter().println("Procesando REGISTRO para: " + usuario);
//        } else {
//            response.getWriter().println("Modo desconocido");
//        }
//
//
//        try (PrintWriter out = response.getWriter()) {
//            // Obtener el DataSource desde JNDI
//            Context initContext = new InitialContext();
//            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/bdhappysystem");
//
//            try (Connection conn = ds.getConnection()) {
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
//        return "Servlet de ingreso de usuarios con validación vía DataSource JNDI";
//    }
//}