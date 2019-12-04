
package graphics;

import java.awt.image.BufferedImage;


public class Assets {
    public static BufferedImage player;
    public static BufferedImage speed;
     public static BufferedImage azullaser,verdelaser,rojolaser;
    
    public static void init(){
       player=Loader.ImageLoader("/ships/Dr_opt_opt.png");
       speed=Loader.ImageLoader("/efectos/fire04.png");
       azullaser=Loader.ImageLoader("/laser/laserBlue16.png");
       
       verdelaser=Loader.ImageLoader("/laser/laserGreen10.png");
       
       rojolaser= Loader.ImageLoader("/laser/laserRed16.png");       
   } 
}
