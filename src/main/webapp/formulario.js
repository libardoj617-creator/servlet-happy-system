/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Cargar FORMULARIO
fetch("formulario.html")
  .then(res => res.text())
  .then(html => {
    document.getElementById("formulario").innerHTML = html;

    // 🔑 Cargar el JS del formulario después de insertarlo
    const script = document.createElement("script");
    script.src = "formulario.js"; // ajusta la ruta según tu proyecto
    document.body.appendChild(script);
  });



