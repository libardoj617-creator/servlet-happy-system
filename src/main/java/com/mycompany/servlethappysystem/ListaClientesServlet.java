package com.mycompany.servlethappysystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListaClientesServlet")
public class ListaClientesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> listaClientes = new ArrayList<>();

        try {
            // 1. Obtener DataSource por JNDI
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bdhappysystem");

            try (Connection con = ds.getConnection();
                 PreparedStatement ps = con.prepareStatement(
                         "SELECT nombre FROM usuarios");
                 ResultSet rs = ps.executeQuery()) {

                // 2. Leer resultados
                while (rs.next()) {
                    listaClientes.add(rs.getString("nombre"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Enviar lista al JSP
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("listaclientes.jsp")
               .forward(request, response);
    }
}
