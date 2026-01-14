/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlethappysystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IngresarServlet")
public class IngresarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String modo = request.getParameter("modo"); // "login" o "register"

        try (PrintWriter out = response.getWriter()) {
            // Obtener el DataSource desde JNDI
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/bdhappysystem");

            try (Connection conn = ds.getConnection()) {

                if ("login".equals(modo)) {
                    // 🔑 Lógica de inicio de sesión
                    String sql = "SELECT * FROM usuarios WHERE nombre=? AND password=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, usuario);
                    ps.setString(2, password);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        out.println("<h1>Login correcto. Bienvenido " + usuario + "</h1>");
                    } else {
                        out.println("<h1>Usuario o contraseña incorrectos</h1>");
                    }

                } else if ("register".equals(modo)) {
                    // 🔑 Lógica de registro
                    // Primero verificar si el usuario ya existe
                    String checkSql = "SELECT * FROM usuarios WHERE nombre=?";
                    PreparedStatement checkPs = conn.prepareStatement(checkSql);
                    checkPs.setString(1, usuario);
                    ResultSet checkRs = checkPs.executeQuery();

                    if (checkRs.next()) {
                        out.println("<h1>El usuario ya existe, elige otro nombre</h1>");
                    } else {
                        String insertSql = "INSERT INTO usuarios (nombre, password) VALUES (?, ?)";
                        PreparedStatement insertPs = conn.prepareStatement(insertSql);
                        insertPs.setString(1, usuario);
                        insertPs.setString(2, password);
                        insertPs.executeUpdate();

                        out.println("<h1>Registro exitoso para " + usuario + "</h1>");
                    }

                } else if ("delete".equals(modo)) {
                    // 🔥 Lógica de borrado 
                    String usuarioBorrar = request.getParameter("usuarioBorrar");

                    String deleteSql = "DELETE FROM usuarios WHERE nombre=?";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setString(1, usuarioBorrar);
                    int rows = deletePs.executeUpdate();

                    if (rows > 0) {
                        out.println("<h1>Usuario " + usuarioBorrar + " eliminado correctamente</h1>");
                    } else {
                        out.println("<h1>No se encontró el usuario " + usuarioBorrar + "</h1>");
                    }

                } else {
                    out.println("<h1>Modo desconocido</h1>");
                }

            }

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de ingreso, registro y borrado de usuarios";
    }
}
