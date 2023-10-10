package Lang;
import java.util.Locale;
import java.util.ResourceBundle;
public class Internationalization {
    ResourceBundle bundle;
    static Internationalization object;
    private Internationalization(){
        
    }
    public static Internationalization getObject(){
        if(object==null){
            object=new Internationalization();
            return object;
        }
        else 
            return object;
        
    }
    public ResourceBundle getEnglishBundle(){
        bundle = ResourceBundle.getBundle("MessageBundle", Locale.US);
        return bundle;
    }
    
    public ResourceBundle getGermanBundle(){
        Locale.setDefault(new Locale("de", "DE"));
        bundle = ResourceBundle.getBundle("MessageBundle");
        return bundle;
    }
    
}