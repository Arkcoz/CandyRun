/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.sol;

import iut.Game;

/**
 *
 * @author lucas
 */
public class Sol_Main extends Sol {
    
    public Sol_Main(Game g, int x, int y) {
        super(g, "sol_noir", x, y);
    }
    
    @Override
    public String getItemType() {
        return "Sol_Main";
    }
    
    @Override
    public void evolve(long l) {
        
    }
}
