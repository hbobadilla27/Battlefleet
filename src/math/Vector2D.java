/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author Cabrón en que lo cambie!
 */
public class Vector2D {
    private double x,y;
    public Vector2D(double x, double y){
        this.x=x;
        this.y=y;
        
    }
    public Vector2D(){
        x=0;
        y=0;
      
    }
    public Vector2D add(Vector2D v){
        return new Vector2D(x+v.getX(),y+v.getY());
    }
     public Vector2D subtract(Vector2D v){
        return new Vector2D(x-v.getX(),y-v.getY());
    }
    public Vector2D scale( double value){
        return new Vector2D(x*value,y*value);
    }
    public Vector2D limit(double value){
        if(getMagnitude()>value){
            return this.normalizar().scale(value);
            
        }
        return this;
           
       
        }

    public Vector2D normalizar(){
        double magnitude=getMagnitude();
        return new Vector2D(x/magnitude,y/magnitude);
    }
    public double getMagnitude(){
        return Math.sqrt(x*x+ y*y);
        
    }
    public Vector2D setDireccion(double angulo){
        double magnitude=getMagnitude();
        return new Vector2D( Math.cos(angulo)*magnitude,Math.sin(angulo)*magnitude);
    }
    public double getAngulo(){
        return Math.asin(y/getMagnitude());
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}
