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
public abstract class Curseur extends GameItem {
    
    private int etat;
    
    public Curseur(Game game, String string, double d, double d1) {
        super(game, string, d, d1);
        this.etat = 0;
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
        return "Curseur";
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
}
