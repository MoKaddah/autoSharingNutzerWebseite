/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package de.unidue.inf.is;

import Lang.Internationalization;
import de.unidue.inf.is.stores.BenutzerStore;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Bashar
 */
public class Signup extends HttpServlet {

    static Configuration conf;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TemplateException {
        conf=new Configuration(Configuration.VERSION_2_3_0);
        conf.setClassForTemplateLoading(Signup.class, "/signup");          
        response.setCharacterEncoding("UTF-8");
        Writer w=response.getWriter();
        FileTemplateLoader file=new FileTemplateLoader(new File("C:\\Users\\moham\\Downloads\\Demo last update\\DemoServlet\\web\\WEB-INF"));
        conf.setTemplateLoader(file);           
        Template tem=conf.getTemplate("signup.ftl"); 
        //lang
        Internationalization global=Internationalization.getObject();
        //default is english
        ResourceBundle selectedBundle=global.getEnglishBundle();
        Locale l=request.getLocale();
        //german lang
        if(l.getLanguage().equals("de")){
            selectedBundle=global.getGermanBundle();
        }
        Map root=new HashMap();
        root.put("reg", selectedBundle.getString("register"));
        tem.process(root, w);
        
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
        try {
            processRequest(request, response);
        } catch (TemplateException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        BenutzerStore b=new BenutzerStore();
        b.addBenutzer(name, email, password);
        response.sendRedirect("/DemoServlet/");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
