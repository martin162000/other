
public class Vypis { 
    
    private String text;
    
    public Vypis() {
        this.text = null;
    }
    
    public String get_vypis(boolean stav) {
    return text = (stav == true) ? "VYHRAL SI" : "PREHRAL SI" ;
    }
}
