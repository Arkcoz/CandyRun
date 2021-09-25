/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.foreground;

import candyrun.Manche;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Barriere extends GameItem{

    private Manche manche;
    
    public Barriere(Game g, double _x, double _y) {
        super(g, "barriere_0", _x, _y);
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
        return "Barriere";
    }

    @Override
    public void evolve(long l) {
        if (this.getRight() < 0){
            this.getGame().remove(this);
        }
        
        int deltaPosX = this.manche.getPlateforme().getMiddleX() - this.getMiddleX();
        int deltaPosY = this.manche.getPlateforme().getMiddleY() - this.getMiddleY()-28;

        this.moveXY(deltaPosX,deltaPosY);
    }
    
}
