/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.foreground;

import candyrun.curseur.Curseur_Pause;
import candyrun.Manche;
import iut.Game;
import iut.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author lucas
 */
public class MenuPause extends GameItem implements KeyListener {

    
    private Curseur_Pause curseur;
    private Manche manche;

    
    public MenuPause(Game g, double _x, double _y) {
        super(g,"menu_pause", _x, _y);

        this.manche = (Manche) g;
        
        this.manche.setIsFreeze(true);
        
        this.curseur = new Curseur_Pause(this.getGame(), 247,652);
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
        return "Menu Pause";
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
                if(this.curseur.getEtat() == 0){
                    this.curseur.setEtat(2);
                }else if(this.curseur.getEtat() == 1){
                    this.curseur.setEtat(0);
                }else{
                    this.curseur.setEtat(1);
                }
                break;
                    
            case KeyEvent.VK_DOWN :
                if(this.curseur.getEtat() == 0){
                    this.curseur.setEtat(1);
                }else if(this.curseur.getEtat() == 1){
                    this.curseur.setEtat(2);
                }else{
                    this.curseur.setEtat(0);
                }
                break;
        }
    }
    
    public void quitter(){
        this.manche.quitter();
    }
    
    public void recommencer(){
        this.manche.recommencer();
    }
    
    public void choix(){
        if (this.curseur.getEtat() == 0){
            this.manche.getJoueur().setTimerSortirMenu(200);
            this.manche.setIsFreeze(false);
        }else if(this.curseur.getEtat() == 1){
            this.manche.getJoueur().setTimerSortirMenu(200);
            recommencer();
        }else if(this.curseur.getEtat() == 2){
            quitter();
        }
    }
}
