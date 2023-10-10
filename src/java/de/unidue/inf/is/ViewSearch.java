/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package de.unidue.inf.is;

import Lang.Internationalization;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.domain.fahrt;
import de.unidue.inf.is.stores.FahrtStore;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bashar
 */
public class ViewSearch extends HttpServlet {
    static Configuration conf;
    Map root=new HashMap();
    ArrayList<fahrt> fahrts;

    @Override
    public void init() throws ServletException {
        fahrts=new ArrayList();
        root.put("fahrts", fahrts);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            //auth
            benutzer user =(benutzer) request.getSession().getAttribute("user");
            String inout="Login";
            String inoutUrl="/DemoServlet/login";
            if(user!=null){                    
                inout="Logout";
                inoutUrl="/DemoServlet/logout";
            }       
            //lang
            Internationalization global=Internationalization.getObject();
            //default is english
            ResourceBundle selectedBundle=global.getEnglishBundle();
            //german lang
            Locale l=request.getLocale();
            if(l.getLanguage().equals("de")){
                selectedBundle=global.getGermanBundle();
            }
            root.put("lis1",selectedBundle.getString("home"));
            root.put("lis2",selectedBundle.getString("create_ride"));
            root.put("lis3",selectedBundle.getString("search"));
            //
            root.put("main2",selectedBundle.getString("main2"));
            root.put("main3",selectedBundle.getString("main3"));
            root.put("newD2",selectedBundle.getString("newD2"));
            root.put("det3",selectedBundle.getString("det3"));
            root.put("det4",selectedBundle.getString("det4"));
            root.put("price",selectedBundle.getString("price"));
            //
            root.put("inout", inout);
            root.put("inoutUrl", inoutUrl);
            conf=new Configuration(Configuration.VERSION_2_3_0);
            conf.setClassForTemplateLoading(NewDriveServlet.class, "/ViewSearch");         
            response.setCharacterEncoding("UTF-8");
            Writer w=response.getWriter();
            FileTemplateLoader file=new FileTemplateLoader(new File("C:\\Users\\moham\\Downloads\\Demo last update\\DemoServlet\\web\\WEB-INF"));
            conf.setTemplateLoader(file);           
            Template tem=conf.getTemplate("view_search.ftl");
            tem.process(root, w);
        } catch (TemplateException ex) {
            Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check auth
        benutzer user =(benutzer) request.getSession().getAttribute("user");
        if(user!=null){
            fahrts=new ArrayList();
            root.replace("fahrts", fahrts);
            processRequest(request, response);
        }
        else{
            response.sendRedirect("/DemoServlet/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FahrtStore fs=new FahrtStore();
        try {
            String startort = request.getParameter("start");
            String zielort = request.getParameter("ziel");
            String fahrtdatumzeit = request.getParameter("date");
            fahrts=fs.search(startort,zielort,fahrtdatumzeit);
            root.replace("fahrts", fahrts);
        } catch (SQLException ex) {
            Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
