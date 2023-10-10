package de.unidue.inf.is;

import Lang.Internationalization;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.domain.fahrt;
import de.unidue.inf.is.stores.BewertungStore;
import de.unidue.inf.is.stores.FahrtStore;
import de.unidue.inf.is.stores.StoreException;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewDriveServlet extends HttpServlet  {
	static Configuration conf;
        FahrtStore fs;
        Map root=new HashMap();

    @Override
    public void init() throws ServletException {
            try {
                fs =new FahrtStore();
            } catch (StoreException ex) {
                Logger.getLogger(ViewDriveServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ViewDriveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        	
        protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws jakarta.servlet.ServletException ,IOException, TemplateException{

            //lang
            Internationalization global=Internationalization.getObject();
            //default is english
            ResourceBundle selectedBundle=global.getEnglishBundle();
            Locale l=request.getLocale();
            //german lang
            if(l.getLanguage().equals("de")){
                selectedBundle=global.getGermanBundle();
            }
            root.put("det1",selectedBundle.getString("det1"));
            root.put("det2",selectedBundle.getString("det2"));
            root.put("det3",selectedBundle.getString("det3"));
            root.put("det4",selectedBundle.getString("det4"));
            root.put("det5",selectedBundle.getString("det5"));
            root.put("det6",selectedBundle.getString("det6"));
            root.put("det7",selectedBundle.getString("det7"));
            root.put("det8",selectedBundle.getString("det8"));
            root.put("main6",selectedBundle.getString("main6"));
            root.put("main7",selectedBundle.getString("main7"));
            
            request.setAttribute("fid", (request.getParameter("fid")));
            int fid = Integer.parseInt((request.getParameter("fid")));
            //get rating
            BewertungStore b=new BewertungStore();
            double rating=b.Druchschnittsrating(fid);
            root.put("Rating", rating);
            ArrayList<String> texts=b.getBewertung(fid);
            root.put("texts", texts);
            fahrt fahrt1 = fs.getFahrtTeil1(fid);
            root.put("fahrt", fahrt1);
            conf=new Configuration(Configuration.VERSION_2_3_0);
            conf.setClassForTemplateLoading(ViewDriveServlet.class, "/drive");      
            response.setCharacterEncoding("UTF-8");
            Writer w=response.getWriter();
            FileTemplateLoader file=new FileTemplateLoader(new File("C:\\Users\\moham\\Downloads\\Demo last update\\DemoServlet\\web\\WEB-INF"));
            conf.setTemplateLoader(file);           
            Template tem=conf.getTemplate("view_Drive.ftl");
            tem.process(root, w);
        }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
            try {
                HttpSession session=request.getSession();
                if(session.getAttribute("user")!=null){
                    root=new HashMap();
                    benutzer user =(benutzer) request.getSession().getAttribute("user");
                    String name=user.getName();
                    root.put("name",name);
                    //fahrt reservieren
                    int fid = Integer.parseInt((request.getParameter("fid")));
                    int bid = user.getBid();
                    String reserved="no";
                    if(fs.getAnzPlaetze(bid, fid)!=-1){
                        reserved="yes";
                    }
                    root.put("reserved", reserved);
                    processRequest(request, response);
                }
                else{
                    response.sendRedirect("/DemoServlet/login");
                }
            } catch (TemplateException ex) {
                Logger.getLogger(ViewDriveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
       @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
            HttpSession session=request.getSession();
            if(session.getAttribute("user")!=null){
                request.setAttribute("fid", (request.getParameter("fid")));
                int fid = Integer.parseInt((request.getParameter("fid")));
                String type=request.getParameter("type");
                benutzer user =(benutzer) request.getSession().getAttribute("user");
                int bid=user.getBid();
                int n=Integer.parseInt((request.getParameter("n")));
                if(type.equals("add")){
                    fs.reservation(bid, fid, n);
                }
                else if(type.equals("cancel")){                 
                    int n2=fs.getAnzPlaetze(bid, fid);
                    int sets=0;
                    if(n2>=n){
                        sets=n2-n;
                        fs.cancel(bid, fid, sets,n);
                    }
                    if(n2==n){
                        fs.delete(bid, fid);
                   }           
                }
                doGet(request, response);
            }
            else{
                response.sendRedirect("/DemoServlet/login");
            }           
	}

}
