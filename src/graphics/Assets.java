
package graphics;

import java.awt.image.BufferedImage;


public class Assets {
    public static BufferedImage player;
   
    public static BufferedImage speed;
   
    public static BufferedImage azullaser,verdelaser,rojolaser;
    public static BufferedImage space;
    public static BufferedImage enemigo;
    public static BufferedImage life;
    
   
   
    
    
    public static BufferedImage []grande=new BufferedImage[1];
    public static BufferedImage []mediano=new BufferedImage[1];
    public static BufferedImage []pequeño=new BufferedImage[1];
    
    
    public static BufferedImage []explosion=new BufferedImage[1]; 
    public static BufferedImage []numeros=new BufferedImage[11]; 
    
    public static void init(){
       space=Loader.ImageLoader("/ships/space.png");
       player=Loader.ImageLoader("/ships/Dr_opt_opt.png");
       speed=Loader.ImageLoader("/efectos/fire04.png");
       
       azullaser=Loader.ImageLoader("/laser/laserBlue16.png");
       verdelaser=Loader.ImageLoader("/laser/laserGreen10.png");
       rojolaser= Loader.ImageLoader("/laser/laserRed16.png");   
      
       
       for(int i=0;i<grande.length;i++)
           grande[i]=Loader.ImageLoader("/meteoros/grande"+(i+1)+".png");
       
       for(int i=0;i<grande.length;i++)
           mediano[i]=Loader.ImageLoader("/meteoros/mediano"+(i+1)+".png");
       
       for(int i=0;i<grande.length;i++)
           pequeño[i]=Loader.ImageLoader("/meteoros/pequeño"+(i+1)+".png");
       
      
       
       for(int i=0;i<explosion.length;i++)
           explosion[i]=Loader.ImageLoader("/efectos/explo"+i+".png");
       
       for(int i=0;i<numeros.length;i++)
           numeros[i]=Loader.ImageLoader("/numeros/"+i+".png");
       
       
       enemigo= Loader.ImageLoader("/ships/ene_opt.png");  
       life= Loader.ImageLoader("/otros/life.png"); 
   } 
}
