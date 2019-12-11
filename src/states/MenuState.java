/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gameObjetcs.Constantes;
import graphics.Assets;
import java.awt.Graphics;
import java.util.ArrayList;
import ui.Accion;


import ui.Botones;

/**
 *
 * @author Intel
 */
public class MenuState extends State {
    private ArrayList<Botones> boton;
    public MenuState(){
        boton = new ArrayList<Botones>();
		
		boton.add(new Botones(
				Assets.greyBtn,
				Assets.blueBtn,
				Constantes.ancho/2   - Assets.greyBtn.getWidth()/2,
				Constantes.altura/2  - Assets.greyBtn.getHeight(),
				Constantes.PLAY,
				new Accion() {
					@Override
					public void doAccion() {
						State.cambiarEstado(new GameState());
					}
				}
				));
		
		boton.add(new Botones(
				Assets.greyBtn,
				Assets.blueBtn,
				Constantes.ancho / 2 - Assets.greyBtn.getWidth()/2,
				Constantes.altura / 2 + Assets.greyBtn.getHeight()/2 ,
				Constantes.EXIT,
				new Accion() {
					@Override
					public void doAccion() {
						System.exit(0);
					}
				}
				));
        
    }

    @Override
    public void actualizar() {
       for(Botones b: boton) {
            b.actualizar();
		}
    }

    @Override
    public void dibujar(Graphics g) {
        for(Botones b: boton) {
            b.dibujar(g);
		}
    }
    
}
