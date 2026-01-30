package com.mycompany.servlethappysystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/EditaClienteServlet")
public class EditaClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String usuario = request.getParameter("usuario");
        String nuevoNombre = request.getParameter("nuevoNombre");
//        String nuevoEmail = request.getParameter("nuevoEmail");

        try (PrintWriter out = response.getWriter()) {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/bdhappysystem");

            try (Connection conn = ds.getConnection()) {

                String sql = "UPDATE cliente SET nombre=?, email=? WHERE usuario=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nuevoNombre);
//                ps.setString(2, nuevoEmail);
                ps.setString(3, usuario);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    out.println("<h3>Cliente editado correctamente</h3>");
                } else {
                    out.println("<h3>No se encontró el cliente</h3>");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}