/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author Intel
 */
public class Mouse extends MouseAdapter{
    public static int X,Y;
    public static boolean MLB;
   
    

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)
            MLB=true;
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)
            MLB=false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        X=e.getX();
        Y=e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        X=e.getX();
        Y=e.getY();
    }
    
}
