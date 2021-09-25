/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.score;

import candyrun.Manche;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class DoublePoint extends GameItem{

    private boolean ajouterAuJeu;
    private Manche manche;
    private Temps temps;
    
    public DoublePoint(Game g, double _x, double _y, Temps temps) {
        super(g, "Score_doublepoint", _x, _y);
        this.manche = (Manche) g;
        this.temps = temps;
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
        return "Double point";
    }

    @Override
    public void evolve(long l) {
        if (this.ajouterAuJeu == false){
            this.getGame().addItem(this);
            this.ajouterAuJeu = true;
        }
        
        if (this.temps.isDelete()){
            this.manche.remove(this);
        }
    }
    
}
