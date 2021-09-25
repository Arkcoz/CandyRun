/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun;

import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Jauge extends GameItem {

    public Manche manche;
    
    public Jauge(Game g, double _x, double _y) {
        super(g,"jauge_1", _x, _y);
        this.manche = (Manche) g;
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
        return "Jauge";
    }

    @Override
    public void evolve(long l) {
        changementSprite();
    }
    
    public void changementSprite(){
        if (!this.manche.isIsFreeze()){    
            if(this.manche.getJoueur().getEtat() != 5){
                this.changeSprite("jauge_"+this.manche.getJoueur().getEtat());
            }
        }
    }
    
}
