/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun;

import iut.Game;
import iut.Vector;
import java.awt.Graphics;

/**
 *
 * @author lucas
 */
public class CandyRun extends Game {

    /**
     * @param args the command line arguments
     */
    private static Game manche;
    private Module_Memoire memory;
    
    public static void main(String[] args) {
        manche = new Manche(1300, 1000, "CandyRun");
        manche.play();
        
        
    }

    public CandyRun(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    protected void createItems() {
              
    }

    @Override
    protected void drawBackground(Graphics g) {
        
    }

    @Override
    protected void lost() {
        
    }

    @Override
    protected void win() {
        
    }

    @Override
    protected boolean isPlayerWin() {
        return false;
    }

    @Override
    protected boolean isPlayerLost() {
        return false;
    }

    @Override
    public Vector getGravity() {
        return null;
    }
    
}
