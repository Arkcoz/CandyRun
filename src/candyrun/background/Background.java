/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.background;

import candyrun.Manche;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Background extends GameItem {

    private Manche manche;
    
    public Background(Game g, double _x, double _y) {
        super(g,"background", _x, _y);
        
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
       return "Background";
    }

    @Override
    public void evolve(long l) {
        if (this.manche.getTemps().getTempsTotal() >= 120000 ){
            if (this.getLeft() != -1000){
                this.moveXY(-10,0);
            }
        }
        
        if (this.getRight() <0){
            this.getGame().remove(this);
        }
    }
    
    public void reset(){
        int deltaPosX = 0;
        deltaPosX = 0 - this.getLeft();        
        this.moveXY(deltaPosX,0);
    }
}
