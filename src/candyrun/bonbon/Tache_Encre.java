/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.bonbon;

import candyrun.Manche;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Tache_Encre extends GameItem {

    public Manche manche;
    public long timer;
    
    public Tache_Encre(Game g, double _x, double _y) {
        super(g, "encre", _x, _y);
        this.manche = (Manche) g;
        this.timer = 5000;
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
        return "Encre";
    }

    @Override
    public void evolve(long l) {
        if (!this.manche.isIsFreeze()){
            this.timer -= l;
            if (this.timer <= 0){
                this.manche.remove(this);
            }
        }
        
        if(this.manche.getVagueBonbon().isRecommencer()){
            this.manche.remove(this);
        }
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
    
    
    
}
