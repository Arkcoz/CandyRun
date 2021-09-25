/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.score;

import candyrun.Manche;
import iut.Game;
import iut.GameItem;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Chiffre extends GameItem {
    
    private ArrayList<String> spriteChiffre;
    private int num;
    private boolean ajouterAuJeu;
    private Temps temps;
    private Manche manche;
    
    public Chiffre(Game g, double _x, double _y, Temps t) {
        super(g, "Score_0", _x, _y);
        
        this.ajouterAuJeu = false;
        this.temps = t;
        this.manche = (Manche) g;
        
        this.spriteChiffre = new ArrayList <>();
        this.num = 0;
        
        for(int i =0; i<10; i++){
            this.spriteChiffre.add("Score_"+i);
        }
        
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
        return "Chiffre";
    }

    @Override
    public void evolve(long l) {
        if (this.ajouterAuJeu == false){
            this.getGame().addItem(this);
            this.ajouterAuJeu = true;
        }
        
        this.changeSprite(spriteChiffre.get(num));
        
        if (this.temps.isDelete()){
            this.manche.remove(this);
        }
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
    
    
}

