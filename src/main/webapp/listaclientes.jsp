<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Clientes</title>
        <style>
            body {
                background-color: #f2f2f2;
                font-family: Arial;
            }
            .contenedor {
                width: 400px;
                margin: 30px auto;
                background: white;
                padding: 20px;
                border-radius: 20px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            }

            .contenedor h2 {
                text-align: center;
                color: #ff8c00;
                margin-bottom: 15px;
            }

            .lista-clientes {
                list-style: none;
                padding: 0;
            }

            .lista-clientes li {
                padding: 10px;
                margin-bottom: 8px;
                background-color: #fff3e0;
                border-left: 5px solid #ff8c00;
                border-radius: 10px;
                font-weight: bold;
            }
            /* Cursor general con emoji */
            body {
                cursor: url("data:image/svg+xml;utf8,\
                    <svg xmlns='http://www.w3.org/2000/svg' width='32' height='32'>\
                    <text x='0' y='24' font-size='24'>💪</text>\
                    </svg>") 0 0, auto;
            }
            
        </style>

    </head>
    <body>

        <jsp:include page="header.html" />

        <div class="contenedor">
            <h2>Lista de Clientes</h2>

            <ul>
                <%
                    List<String> lista = (List<String>) request.getAttribute("listaClientes");
                    if (lista != null) {
                        for (String nombre : lista) {
                %>
                <li><%= nombre%></li>
                    <%
                            }
                        }
                    %>
            </ul>

        </div>
        <div id="footer"></div>
        <script>
            fetch("footer.html")
                    .then(res => res.text())
                    .then(html => {
                        document.getElementById("footer").innerHTML = html;
                    });
        </script>             
    </body>
</html>
