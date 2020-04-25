package pl.polsl.tomasz.walorski.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet display application history of using operations.
 * @author Tomasz Walorski
 * @version 1.1
 */
public class CookieServlet extends HttpServlet {

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
            Cookie[] cookies = request.getCookies();
            
            if(cookies != null){
                for (Cookie cookie : cookies)                
                    out.print("Operation: " + cookie.getName() + " - number of executions: " + cookie.getValue() + "<br/>");              
            }
        }
    }
    
    /**
     * Method save new use of some operation.
     * @param operationName name of used operation
     * @param request servlet request
     * @param response servlet response
     */
    public static void storeApplicationHistory(String operationName, HttpServletRequest request, HttpServletResponse response){
        Cookie newCookie = null;
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName()==operationName){
                    newCookie = cookie;
                    break;
                }
            }
        }
        if(newCookie == null){
            newCookie = new Cookie(operationName, "1");
        }else{
            Integer newValue = Integer.parseInt(newCookie.getValue()) + 1;
            newCookie.setValue(newValue.toString());
        }
        response.addCookie(newCookie);
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
