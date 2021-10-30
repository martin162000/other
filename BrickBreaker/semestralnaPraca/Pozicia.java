

public class Pozicia {
    
     private int pozicia_lopty_x;
     private int pozicia_lopty_y;
     private int pozicia_odrazaca_x;
     private int pozicia_odrazaca_y;
     private int pozicia_bloku_x;
     private int pozicia_bloku_y;
     
    public Pozicia() {
        this.pozicia_lopty_x = 270;
        this.pozicia_lopty_y = 690;
        this.pozicia_odrazaca_x = 275;
        this.pozicia_odrazaca_y = 710;
        this.pozicia_bloku_x = 70;
        this.pozicia_bloku_y = 50;
    }
    
        public int get_pozicia_lopty(String pozicia) {
            if(pozicia == "x") {
                return this.pozicia_lopty_x;
            }else if(pozicia == "y") {
                return this.pozicia_lopty_y;
            }else {
                return 0;
            }
    }
    
            public int get_pozicia_odrazaca(String pozicia) {
            if(pozicia == "x") {
                return this.pozicia_odrazaca_x;
            }else if(pozicia == "y") {
                return this.pozicia_odrazaca_y;
            }else {
                return 0;
            }
    }
    
            public int get_pozicia_bloku(String pozicia) {
            if(pozicia == "x") {
                return this.pozicia_bloku_x;
            }else if(pozicia == "y") {
                return this.pozicia_bloku_y;
            }else {
                return 0;
            }
    }
}
