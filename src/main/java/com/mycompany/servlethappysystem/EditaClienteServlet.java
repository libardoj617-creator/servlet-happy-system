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

@WebServlet(name = "EditaClienteServlet", urlPatterns = {"/EditaClienteServlet"})
public class EditaClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String nombreActual = request.getParameter("usuarioEditar");
        String nuevoNombre = request.getParameter("nuevoNombre");

        try (PrintWriter out = response.getWriter()) {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/bdhappysystem");

            try (Connection conn = ds.getConnection()) {

                String sql = "UPDATE usuarios SET nombre=? WHERE nombre=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nuevoNombre);
                ps.setString(2, nombreActual);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    out.println("<h1>Usuario editado correctamente</h1>");
                } else {
                    out.println("<h1>No se encontró el usuario</h1>");
                }
            }

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}