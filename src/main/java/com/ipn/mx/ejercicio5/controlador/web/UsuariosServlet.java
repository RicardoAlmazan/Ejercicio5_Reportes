/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.ejercicio5.controlador.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipn.mx.ejercicio5.modelo.dao.UsuariosDAO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Iikt
 */
public class UsuariosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        if (accion.equals("listaUsuarios")) {
            listaUsuarios(request, response);
        } else if (accion.equals("generarReporte")) {
            verPDF(request, response);
        }
    }

    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
        Integer idUsuario = null;
        try {
            idUsuario = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, e);
            return;
        }

        ServletOutputStream sos = null;
        try {
            UsuariosDAO dao = new UsuariosDAO();
            sos = response.getOutputStream();
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/assets/UserReport.jasper"));
            Map params = new HashMap<>();
            params.put("id_usuario", idUsuario);
            System.out.println(params);
            byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), params, dao.getConnection());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            sos.write(bytes, 0, bytes.length);
            sos.flush();
        } catch (IOException | JRException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sos.close();
            } catch (IOException ex) {
                Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void listaUsuarios(HttpServletRequest request, HttpServletResponse response) {
        UsuariosDAO dao = new UsuariosDAO();
        try {
            List lista = dao.readAll();
            System.out.println(lista.toString());
            request.setAttribute("listaUsuarios", lista);
            RequestDispatcher vista = request.getRequestDispatcher("listUsers.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            System.out.println("EXCEP:::::::::: " + ex.getMessage());
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
