
public class Pohyb {
   
    private int pohybx;
    private int pohyby;
   
    public Pohyb() {
        this.pohybx = -3;
        this.pohyby = -3;
        
    }
    
    public int get_pohyb(String pohyb_text) {
        int rychlost;
        if(pohyb_text == "x") {
            rychlost = this.pohybx;
        }else if(pohyb_text == "y") {
            rychlost = this.pohyby;
        }else {
            rychlost = 0;
        }
        
        return rychlost;
    }
}
