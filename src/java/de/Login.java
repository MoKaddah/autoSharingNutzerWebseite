
package de.unidue.inf.is;

import Lang.Internationalization;
import de.unidue.inf.is.domain.benutzer;
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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bashar
 */

public class Login extends HttpServlet {
    static Configuration conf;
    Map root=new HashMap();
    private static benutzer user;
    
    static protected benutzer getUser(){
        return user;
    }

    @Override
    public void init() throws ServletException {
        root.put("error1", "");
        root.put("error2", "");
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TemplateException {
        //lang
        Internationalization global=Internationalization.getObject();
        //default is english
        ResourceBundle selectedBundle=global.getEnglishBundle();
        Locale l=request.getLocale();
        //german lang
        if(l.getLanguage().equals("de")){
            selectedBundle=global.getGermanBundle();
        }
        root.put("log1",selectedBundle.getString("log1"));
        root.put("log2",selectedBundle.getString("log2"));
        root.put("log3",selectedBundle.getString("log3"));
        
        conf=new Configuration(Configuration.VERSION_2_3_0);
        conf.setClassForTemplateLoading(Login.class, "/login");          
        response.setCharacterEncoding("UTF-8");
        Writer w=response.getWriter();
        FileTemplateLoader file=new FileTemplateLoader(new File("C:\\Users\\moham\\Downloads\\Demo last update\\DemoServlet\\web\\WEB-INF"));
        conf.setTemplateLoader(file);           
        Template tem=conf.getTemplate("index.ftl");            
        tem.process(root, w);
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TemplateException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        System.out.println("login");
        BenutzerStore b=new BenutzerStore();
        String redirect="/DemoServlet/ViewMain";
        try{
            if(b.getPassword(email).equals(password)&&!password.equals("")){
                int bid=b.getID(email);
                user=new benutzer(bid,b.getName(bid), email);
                HttpSession session=request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(redirect);
            }
            else if(password.equals("")){
                user=null;
                String message="Incorrect E-Mail";
                root.replace("error2", message);
                processRequest(request, response);
            }
            else{
                user=null;
                String message="Incorrect password";
                root.replace("error1", message);
                processRequest(request, response);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (TemplateException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
