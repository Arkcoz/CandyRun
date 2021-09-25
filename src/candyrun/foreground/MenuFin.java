/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.foreground;

import candyrun.curseur.Curseur_Fin;
import candyrun.Manche;
import iut.Game;
import iut.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author lucas
 */
public class MenuFin extends GameItem implements KeyListener{
    private Curseur_Fin curseur;
    private Manche manche;

    
    public MenuFin(Game g, double _x, double _y) {
        super(g,"menu_fin", _x, _y);

        this.manche = (Manche) g;
        
        this.manche.setIsFreeze(true);
        
        this.curseur = new Curseur_Fin(this.getGame(), 247,652);
        this.manche.addItem(curseur);
    }

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
        
    }

    @Override
    public String getItemType() {
        return "Menu Fin";
    }

    @Override
    public void evolve(long l) {
                
        if (!this.manche.isIsFreeze()){
            this.manche.remove(curseur);
            this.manche.remove(this);
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
           

            case KeyEvent.VK_ESCAPE :
                    this.manche.setIsFreeze(false);
                break;
                   
            case KeyEvent.VK_ENTER :
                    choix();
                break;
            
            case KeyEvent.VK_SPACE :
                    choix();
                break;    
                   
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){ 
            
            case KeyEvent.VK_UP :
                if(this.curseur.getEtat() == 1){
                    this.curseur.setEtat(0);
                }else{
                    this.curseur.setEtat(1);
                }
                break;
                    
            case KeyEvent.VK_DOWN :
                if(this.curseur.getEtat() == 1){
                    this.curseur.setEtat(0);
                }else{
                    this.curseur.setEtat(1);
                }
                break;
        }
    }
    
    public void quitter(){
        this.manche.quitter();
    }
    
    public  void recommencer(){
        this.manche.recommencer();
        this.manche.remove(this.curseur);
        this.manche.remove(this);
    }
    
    public void choix(){
        if (this.curseur.getEtat() == 0){
            recommencer();
            this.manche.getJoueur().setTimerSortirMenu(200);
        }else{
            quitter();
        }
    }
    
    
}
