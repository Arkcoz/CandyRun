/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.sol;

import candyrun.Manche;
import candyrun.foreground.Barriere;
import iut.Game;

/**
 *
 * @author lucas
 */
public class Plateforme extends Sol{
    
    private Barriere barriere;
    private Manche manche;
    private int moveClock;
    private boolean gauche;
    private int vitesse;
    private boolean derriere;
    
    private int compteur;
    
    public Plateforme(Game g, int x, int y, Barriere b) {
        super(g, "plateforme_0", x, y);
        
        this.manche = (Manche) g;
        this.moveClock = 1000;
        this.gauche = false;
        this.barriere = b;
        this.vitesse = 5;
        this.derriere = false;
        this.compteur = 0;
        
        
    }
    
    @Override
    public String getItemType() {
        return "Plateforme";
    }
    
    @Override
    public void evolve(long l) {
        
        if(this.manche.getJoueur().getEtat()!=0){
            this.derriere = true;
        }else{
            this.derriere = false;
        }
        
        if(!this.manche.isIsFreeze()){
            if (this.derriere){
                if (this.compteur != 10){
                    this.compteur += 1;
                    this.changeSprite("plateforme_"+this.compteur);
                    this.barriere.changeSprite("barriere_"+this.compteur);
                }
            }else{
                if(this.compteur != 0){
                    this.compteur -= 1;
                    this.changeSprite("plateforme_"+this.compteur);
                    this.barriere.changeSprite("barriere_"+this.compteur);
                }
            }


            //PARTIE DEPLACEMENT
            this.moveClock -= l;
            if(this.moveClock<=0){
                if (gauche){
                    this.gauche = false;
                }else{
                    this.gauche = true;
                }
                this.moveClock = 1000;
            }

            if (gauche){
                this.vitesse = -5;
            }else{
                this.vitesse = 5;
            }

            this.moveXY(vitesse, 0);
        }

    }
    
    public int getVitesse() {
        return vitesse;
    }
    
    public void reset(){
        this.derriere = false;
    }

    public boolean isDerriere() {
        return derriere;
    }

    public void setDerriere(boolean derriere) {
        this.derriere = derriere;
    }

    
    
}
