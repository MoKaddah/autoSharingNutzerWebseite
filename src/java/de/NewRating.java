/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package de.unidue.inf.is;

import Lang.Internationalization;
import de.unidue.inf.is.stores.BewertungStore;
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
public class NewRating extends HttpServlet {
    static Configuration conf;
    Map root=new HashMap();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //lang
            Internationalization global=Internationalization.getObject();
            //default is english
            ResourceBundle selectedBundle=global.getEnglishBundle();
            //german lang
            Locale l=request.getLocale();
            if(l.getLanguage().equals("de")){
                selectedBundle=global.getGermanBundle();
            }
            root.put("rate",selectedBundle.getString("rate"));
            root.put("add",selectedBundle.getString("add"));
            root.put("rateDic",selectedBundle.getString("rateDic"));
         
            conf=new Configuration(Configuration.VERSION_2_3_0);
            conf.setClassForTemplateLoading(Login.class, "/NewRating");          
            response.setCharacterEncoding("UTF-8");
            Writer w=response.getWriter();
            FileTemplateLoader file=new FileTemplateLoader(new File("C:\\Users\\moham\\Downloads\\Demo last update\\DemoServlet\\web\\WEB-INF"));
            conf.setTemplateLoader(file);           
            Template tem=conf.getTemplate("new_Rating.ftl");            
            tem.process(root, w);
        } catch (TemplateException ex) {
            Logger.getLogger(NewRating.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
        request.setAttribute("fid", (request.getParameter("fid")));
        String fids=request.getParameter("fid");
        int fid=Integer.parseInt(fids);
        root.put("fid", fid);
        processRequest(request, response);       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fid=request.getParameter("fid");
        String rate=request.getParameter("bRating");
        String text=request.getParameter("btext");
        BewertungStore b=new BewertungStore();
        b.insertBewertung(Integer.parseInt(rate), text, Integer.parseInt(fid));
        response.sendRedirect("/DemoServlet/drive?fid="+fid);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
