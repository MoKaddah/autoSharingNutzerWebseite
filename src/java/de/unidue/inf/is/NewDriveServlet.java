package de.unidue.inf.is;


import Lang.Internationalization;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.stores.FahrtStore;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class NewDriveServlet extends HttpServlet {
	static Configuration conf;
        Map root;
        String inout="Login";
        String inoutUrl="/DemoServlet/login";

    @Override
    public void init() throws ServletException {
        root=new HashMap(); 
        root.put("inout", inout);
        root.put("inoutUrl", inoutUrl);
    }

        protected void processRequest(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException ,IOException{
        
        try{
            //lang
            Internationalization global=Internationalization.getObject();
            //default is english
            ResourceBundle selectedBundle=global.getEnglishBundle();
            Locale l=request.getLocale();
            //german lang
            if(l.getLanguage().equals("de")){
                selectedBundle=global.getGermanBundle();
            }  
            root.put("lis1",selectedBundle.getString("home"));
            root.put("lis2",selectedBundle.getString("create_ride"));
            root.put("lis3",selectedBundle.getString("search"));
            root.put("main7",selectedBundle.getString("main7"));
            root.put("det4",selectedBundle.getString("det4"));
            root.put("det3",selectedBundle.getString("det3"));
            root.put("det5",selectedBundle.getString("det5"));
            root.put("newD1",selectedBundle.getString("newD1"));
            root.put("newD2",selectedBundle.getString("newD2"));
            root.put("newD3",selectedBundle.getString("newD3"));
            root.put("newD4",selectedBundle.getString("newD4"));
            root.put("newD5",selectedBundle.getString("newD5"));
            //
            conf=new Configuration(Configuration.VERSION_2_3_0);
            conf.setClassForTemplateLoading(NewDriveServlet.class, "/new_Drive");         
            response.setCharacterEncoding("UTF-8");
            Writer w=response.getWriter();
            FileTemplateLoader file=new FileTemplateLoader(new File("C:\\Users\\moham\\Downloads\\Demo last update\\DemoServlet\\web\\WEB-INF"));
            conf.setTemplateLoader(file);           
            Template tem=conf.getTemplate("new_Drive.ftl");
            tem.process(root, w);
               
        }
        catch(TemplateException e){
            
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                benutzer user =(benutzer) request.getSession().getAttribute("user");
                if(user!=null){   
                    inout="Logout";
                    inoutUrl="/DemoServlet/logout";
                    root.replace("inout", inout);
                    root.replace("inoutUrl", inoutUrl);
                    processRequest(request, response);
                }                 
                else{
                    response.sendRedirect("/DemoServlet/login");
                }
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        benutzer user1 =(benutzer) request.getSession().getAttribute("user");
        if(user1!=null){   
            inout="Logout";
            inoutUrl="/DemoServlet/logout";
            root.replace("inout", inout);
            root.replace("inoutUrl", inoutUrl);
            String startort = request.getParameter("startort");
            String zielort = request.getParameter("zielort");
            String maxPlaetze = request.getParameter("maxPlaetze");
            String fahrtkosten = request.getParameter("FahrtKosten");
            String transportmittle = request.getParameter("fahrt1");
            String fahrtdatumzeit = request.getParameter("Fahrtdatum");
            String beschreibung = request.getParameter("beschreibung");
            String fahrtuhrzeit=request.getParameter("Fahrtzeit");
            benutzer user = new benutzer();
            try {
               FahrtStore nd = new FahrtStore();
               nd.fahrtErstellen(startort,zielort,fahrtdatumzeit,maxPlaetze,fahrtkosten,user,transportmittle,beschreibung,fahrtuhrzeit);
               //request.getRequestDispatcher("/DemoServlet/new_Drive.ftl").forward(request, response);
               doGet(request,response);
               nd.complete();
           } catch (ServletException | IOException e) {
                e.printStackTrace();
           }
            
        }                 
        else{
            response.sendRedirect("/DemoServlet/login");
        }
    }
}
