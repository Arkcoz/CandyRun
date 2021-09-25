/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.bonbon;

import candyrun.Manche;
import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Pomme extends BoxGameItem {

    private Manche manche;
    private VagueDeBonbon vdb;
    
    public Pomme(Game g, int x, int y, VagueDeBonbon vdb) {
        super(g, "pomme", x, y);
        this.manche = (Manche) g;
        this.vdb = vdb;
    }

    @Override
    public void collideEffect(GameItem gi) {
        if (gi.getItemType()=="Player"){
            this.getGame().remove(this);
        }
        
        if(this.getTop() >= this.getGame().getHeight()){
            this.getGame().remove(this);
        }
    }

    @Override
    public String getItemType() {
        return "Pomme";
    }

    @Override
    public void evolve(long l) {
        if (this.manche.isIsFreeze()){
            this.moveXY(0, 0);
        }
        else{
            this.moveXY(0, 3);
        }
        
        if (this.vdb.isDelete()){
            this.manche.remove(this);
        }
    }
    
}
