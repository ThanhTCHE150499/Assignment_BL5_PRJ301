/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.EmployeeDBContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author Admin
 */
@WebServlet(name="EmployeeServlet", urlPatterns="/Assignment_bl5")
public class EmployeeController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmployeeController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int eid = Integer.parseInt(request.getParameter("eid"));
        EmployeeDBContext employeeDb = new EmployeeDBContext();
        Employee employee = employeeDb.getEmployeeById(eid);
        employeeDb.delete(employee);
        response.sendRedirect("/Assignment_bl5/report");
        
//        String action = request.getParameter("action");
//        if(action == null) {
//            action = "";
//        }
//        
//        switch (action) {
//            case "create":
//                showCreateModal(request, response);
//                break;
//            case "edit":
////                updateEmployee(request, response);
//                break;
//            case "delete":
//                deleteProduct(request, response);
//                break;
//        }
    }
    
//    private void showCreateModal(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
//        dispatcher.forward(request, response);
//    }
//    
//    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        int eid = Integer.parseInt(request.getParameter("eid"));
//        EmployeeDBContext employeeDb = new EmployeeDBContext();
//        Employee employee = employeeDb.getEmployeeById(eid);
//        employeeDb.delete(employee);
//        
//        RequestDispatcher dispatcher = request.getRequestDispatcher("view/report.jsp");
//        dispatcher.forward(request, response);
//        response.sendRedirect("/Assignment_bl5/report");
//    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        
        switch (action) {
            case "create":
                createEmployee(request, response);
                break;
            case "edit":
//                updateEmployee(request, response);
                break;
        }
    }
    
    private void createEmployee(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        EmployeeDBContext employeeDb = new EmployeeDBContext();
        String name = request.getParameter("nameField");
        String role = request.getParameter("roleField");

        Employee e = new Employee(name, role);
        employeeDb.create(e);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }
    
//    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        EmployeeDBContext employeeDb = new EmployeeDBContext();
//        String name = request.getParameter("nameField");
//        String role = request.getParameter("roleField");
//
//        Employee e = new Employee(name, role);
//        employeeDb.edit(e);
//        
//        request.getRequestDispatcher("view/create.jsp").forward(request, response);
//    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
