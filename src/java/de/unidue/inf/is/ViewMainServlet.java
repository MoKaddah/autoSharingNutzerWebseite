package de.unidue.inf.is;

import Lang.Internationalization;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.domain.fahrt;
import de.unidue.inf.is.stores.FahrtStore;
import java.io.IOException;
//import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


public final class ViewMainServlet extends HttpServlet {
	static Configuration conf;
	private static final long serialVersionUID = 1L;
        
  

protected void processRequest(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException ,IOException{
        
        try{
            Map root=new HashMap();
            FahrtStore fs = new FahrtStore();
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
            String title=selectedBundle.getString("title");
            root.put("title",title);
            //
            ArrayList<fahrt> ReserviertFahrtList=new ArrayList<fahrt>();
            root.put("fahrt1", ReserviertFahrtList);
            //auth
            String authed="false";
            String inout="Login";
            String inoutUrl="/DemoServlet/login";
            HttpSession session=request.getSession();
            if(session.getAttribute("user")!=null){
                authed="true";
                inout="Logout";
                inoutUrl="/DemoServlet/logout";
                try{                  
                    benutzer user =(benutzer) request.getSession().getAttribute("user");                      
                    ReserviertFahrtList = fs.getReserviertFahrt(user.getBid());
                    root.replace("fahrt1", ReserviertFahrtList);
                }catch(Exception e){
                    root.put("fahrt1", new ArrayList<fahrt>());
                }
            } 
            root.put("inout", inout);
            root.put("inoutUrl", inoutUrl);
            root.put("authed", authed);
            root.put("title1",selectedBundle.getString("title1"));
            root.put("main1",selectedBundle.getString("main1"));
            root.put("main2",selectedBundle.getString("main2"));
            root.put("main3",selectedBundle.getString("main3"));
            root.put("Status","Status");
            root.put("title2",selectedBundle.getString("title2"));
            root.put("info",selectedBundle.getString("info"));
            root.put("main4",selectedBundle.getString("main4"));
            root.put("main5",selectedBundle.getString("main5"));
            root.put("main6",selectedBundle.getString("main6"));
            root.put("main7",selectedBundle.getString("main7"));
            try{                     
                ArrayList<fahrt> OffeneFahrtList = fs.getOffneFahrt(); 
                //remove reserved drives
                for(fahrt f1:ReserviertFahrtList){
                    for(fahrt f2:OffeneFahrtList){
                        if(f1.getFid()==f2.getFid()){
                            OffeneFahrtList.remove(f2);
                            break;
                        }
                    }
                }
                root.put("fahrt2", OffeneFahrtList);
                fs.complete();
            }catch(Exception e){
                root.put("fahrt2", new ArrayList<fahrt>());
            }
            conf=new Configuration(Configuration.VERSION_2_3_0);
            conf.setClassForTemplateLoading(ViewMainServlet.class, "/ViewMain");        
            response.setCharacterEncoding("UTF-8");
            Writer w=response.getWriter();
            FileTemplateLoader file=new FileTemplateLoader(new File("C:\\Users\\moham\\Downloads\\Demo last update\\DemoServlet\\web\\WEB-INF"));
            conf.setTemplateLoader(file);     
            Template tem=conf.getTemplate("view_Main2.ftl");
            tem.process(root, w);    
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
            processRequest(request, response);
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/DemoServlet/ViewMain");
	}

}
