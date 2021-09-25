/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.sol;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public abstract class Sol extends BoxGameItem {

    public Sol(Game g, String name, int x, int y) {
        super(g,name, x, y);
    }

    @Override
    public void collideEffect(GameItem gi) {
        
    }

    @Override
    public String getItemType() {
        return "Sol";
    }

    
    
}
