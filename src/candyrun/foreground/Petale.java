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
public class Petale extends GameItem{

    private int speed;
    private int compteur =0;
    private int speedX = 1;
    private int compteurX = 0;
    private Manche manche;
    
    private boolean isFreeze;
    
    public Petale(Game g, double _x, double _y, int speed) {
        super(g, "Petales", _x, _y);
        this.manche = (Manche) g;
        
        this.speed = speed;
        super.moveXY(0, - (super.getHeight()-super.getGame().getHeight()));
        
        this.isFreeze = false;
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
        return "Petales";
    }

    @Override
    public void evolve(long l) {
        if (!this.manche.isIsFreeze()){
        
            compteur += 1*speed;
            compteurX += l;

            if(compteurX >= 10000){
                if (speedX == 1){
                    speedX = -1;
                }else{
                    speedX = 1;
                }
                this.compteurX = 0;
            }

            if(super.getTop() == 0){
                super.moveXY(0, -(super.getHeight()-super.getGame().getHeight()));
                compteur = 0;
            }

            super.moveXY(speedX, speed);
        }
        else
        {
            this.moveXY(0, 0);
        }
    }
    
}
