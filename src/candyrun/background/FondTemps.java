/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.background;

import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class FondTemps extends GameItem {

    public FondTemps(Game g, double _x, double _y) {
        super(g,"fond", _x, _y);
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
        return "Fond temps";
    }

    @Override
    public void evolve(long l) {
        
    }
    
}
