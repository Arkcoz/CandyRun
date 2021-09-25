/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.foreground;

import candyrun.Manche;
import candyrun.background.Background_Debut;
import iut.Game;
import iut.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author lucas
 */
public class MenuDebut extends GameItem implements KeyListener{
    private Manche manche;
    private Background_Debut BD;

    
    public MenuDebut(Game g, double _x, double _y) {
        super(g,"menu_debut", _x, _y);

        this.manche = (Manche) g;
        this.BD = new Background_Debut(this.manche,0,0,2);
        this.manche.addItem(BD);
        
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
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
                   
            case KeyEvent.VK_ENTER :
                    demarrer();
                break;
            
            case KeyEvent.VK_SPACE :
                    demarrer();
                break;    
                   
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){ 
            
            
        }
    }
    
    public void quitter(){
        this.manche.quitter();
    }
    
    
    public void demarrer(){
        this.getGame().addItem(this.manche.getBackgroundd());
        this.getGame().addItem(this.manche.getSol());
        this.getGame().addItem(this.manche.getArbre());
        this.getGame().addItem(this.manche.getJoueur());
        this.getGame().addItem(this.manche.getVagueBonbon());
        this.getGame().addItem(this.manche.getPlateforme());
        this.getGame().addItem(this.manche.getBarriere());
        this.getGame().addItem(this.manche.getPetale());
        this.getGame().addItem(this.manche.getFondTemps());
        this.manche.getTemps().start();
        this.getGame().addItem(this.manche.getTemps());
        this.getGame().addItem(this.manche.getJauge());
        
        this.getGame().remove(this);
    }
    
    
}
