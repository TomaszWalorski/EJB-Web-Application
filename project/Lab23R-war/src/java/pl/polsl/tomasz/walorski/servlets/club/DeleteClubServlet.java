package pl.polsl.tomasz.walorski.servlets.club;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.tomasz.walorski.controller.ClubController;
import pl.polsl.tomasz.walorski.model.Club;
import pl.polsl.tomasz.walorski.model.beans.ClubBean;
import static pl.polsl.tomasz.walorski.servlets.CookieServlet.storeApplicationHistory;

/**
 * Servlet for deleting clubs.
 * @author Tomasz Walorski
 * @version 1.1
 */
@WebServlet(name = "DeleteClubServlet", urlPatterns = {"/DeleteClubServlet"})
public class DeleteClubServlet extends HttpServlet {

    /**
     * It allow club's operatrions in database. 
     */
    @EJB
    ClubBean clubBean;
    
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
            Club club = new Club();
            ClubController controller = new ClubController(club);
            final String clubId = request.getParameter("clubId");
            ArrayList <String> errors = new ArrayList<String>();
            
            if(controller.isInteger(clubId)!=null){
                errors.add(controller.isInteger(clubId));
            }else{
                int id = Integer.parseInt(clubId);                   
                if(clubBean.read(id) == null){
                    errors.add("Club with such id does not exist in the database.");  
                }else{
                    club = clubBean.read(id); 
                }
            }
            if(errors.isEmpty()){
                clubBean.delete(club);                   
                out.println("Club was deleted corretly.");
                storeApplicationHistory("deleteClub",request,response);
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
