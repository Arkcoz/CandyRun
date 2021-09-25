/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.curseur;

import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Curseur_Pause extends Curseur {


    
    public Curseur_Pause(Game g, double _x, double _y) {
        super(g, "curseur", _x, _y);
    }


    @Override
    public String getItemType() {
        return "Curseur Pause";
    }

    @Override
    public void evolve(long l) {
                
        //PARTIE PRINCIPALE
        int deltaPosX = 0;
        int deltaPosY = 0;
        switch (this.getEtat()){
            case 0 : 
                deltaPosX = 241 - this.getLeft();
                deltaPosY = 522 - this.getTop();
            break;
            
            case 1 : 
                deltaPosX = 241 - this.getLeft();
                deltaPosY = 646 - this.getTop();
            break;
            
            case 2 :
                deltaPosX = 241 - this.getLeft();
                deltaPosY = 771 - this.getTop();    
            break;
        }
        
        this.moveXY(deltaPosX,deltaPosY);
    }
  
    
}
