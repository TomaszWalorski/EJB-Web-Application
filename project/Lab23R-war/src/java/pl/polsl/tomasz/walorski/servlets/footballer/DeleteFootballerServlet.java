package pl.polsl.tomasz.walorski.servlets.footballer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.tomasz.walorski.controller.FootballerController;
import pl.polsl.tomasz.walorski.model.Footballer;
import pl.polsl.tomasz.walorski.model.beans.FootballerBean;
import static pl.polsl.tomasz.walorski.servlets.CookieServlet.storeApplicationHistory;

/**
 * Servlet for updating footballers.
 * @author Tomasz Walorski 
 * @version 1.1
 */
@WebServlet(name = "DeleteFootballerServlet", urlPatterns = {"/DeleteFootballerServlet"})
public class DeleteFootballerServlet extends HttpServlet {
    
     /**
     * It allow footballer's operatrions in database. 
     */   
    @EJB
    FootballerBean footballerBean;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Footballer footballer = new Footballer();
            FootballerController controller = new FootballerController(footballer);
            final String footballerId = request.getParameter("footballerId");
            ArrayList <String> errors = new ArrayList<String>();
            
            if(controller.isInteger(footballerId, "id")!=null){
                errors.add(controller.isInteger(footballerId, "id"));
            }else{
                int id = Integer.parseInt(footballerId);                   
                if(footballerBean.read(id) == null){
                    errors.add("Footballer with such id does not exist in the database.");  
                }else{
                    footballer = footballerBean.read(id); 
                }
            }
            if(errors.isEmpty()){
                footballerBean.delete(footballer);                   
                out.println("Footballer was deleted corretly.");
                storeApplicationHistory("deleteFootballer",request,response);
            }else{
                 response.sendError(HttpServletResponse.SC_BAD_REQUEST, errors.toString()); 
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
