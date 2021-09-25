/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun.bonbon;

import candyrun.Manche;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class VagueDeBonbon extends GameItem {
    
    private long timerSpawnMinInit;
    private long timerSpawnMaxInit;
    private long timerSpawn;                //Temps entre chaque spawn de bonbon
    private long timerManche;
    private boolean mancheFini;
    private Manche manche;
    private boolean delete;
    
    private long timerSpawnPomme;
    private long timerSpawnMalus;
    
    private boolean recommencer;
    private long timerRecommencer;
    
    public VagueDeBonbon(Game g, double _x, double _y) {
        super(g, "transparent", _x, _y);
        this.manche = (Manche) g;
        this.timerSpawnMinInit = 450;
        this.timerSpawnMaxInit = 500;
        this.timerSpawn = timerSpawnMaxInit;
        this.timerManche = 180000;
        this.mancheFini = false;
        this.delete = false;
        
        this.recommencer = false;
        this.timerRecommencer = 0;
        
        this.timerSpawnPomme = 60000;
        this.timerSpawnMalus = 45000;
        
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
        return "Vague Bonbon";
    }

    @Override
    public void evolve(long l) {
    
        
        
        if ((!this.manche.isIsFreeze()) && (!this.recommencer)){
            this.timerSpawn -= l;
            this.timerManche -= l;
            this.timerSpawnPomme -= l;
            this.timerSpawnMalus -= l; 

            int xRMD = 1 + (int)(Math.random() * ((1000-64 - 1) + 1));

            if ((timerSpawn <= 0) && (!mancheFini)){

                if (this.timerSpawnPomme<=0){
                    this.getGame().addItem(new Pomme(this.getGame(),xRMD,-900,this));
                    this.timerSpawnPomme = 60000;
                    this.timerSpawn = timerSpawnMinInit + (int)(Math.random() * ((timerSpawnMaxInit - 1) + 1));    
                }else if(this.timerSpawnMalus <= 0){
                    this.getGame().addItem(new Bonbon_Violet(this.getGame(),xRMD,-900,this));
                    this.timerSpawnMalus = 45000;
                    this.timerSpawn = timerSpawnMinInit + (int)(Math.random() * ((timerSpawnMaxInit - 1) + 1));   
                }else{
                    this.getGame().addItem(new Bonbon(this.getGame(),xRMD,-900,this));
                    this.timerSpawn = timerSpawnMinInit + (int)(Math.random() * ((timerSpawnMaxInit - 1) + 1));    
                }
                
            }

            if ((timerManche <= 150050) && (timerManche >= 150000)){
                this.timerSpawnMinInit -= 20;  
            }

            if ((timerManche <= 120050) && (timerManche >= 120000)){
                this.timerSpawnMinInit -= 20;  
            }

            if ((timerManche <= 90050) && (timerManche >= 90000)){
                this.timerSpawnMinInit -= 20;  
            }
            if ((timerManche <= 60050) && (timerManche >= 60000)){
                this.timerSpawnMinInit -= 20;  
            }
            if ((timerManche <= 30050) && (timerManche >= 30000)){
                this.timerSpawnMinInit -= 20;  
            }

            if (timerManche <= 0){
                this.mancheFini = true; 
            }
        }
        
        if (this.recommencer){
            this.timerRecommencer += l;
            if(timerRecommencer >= 30){
                this.delete = false;
                this.recommencer = false;
                this.timerRecommencer = 0;
            }
        }
    }

    public void reset(){
        this.delete = true;
        this.timerSpawnMinInit = 450;
        this.timerSpawnMaxInit = 500;
        this.timerSpawn = timerSpawnMaxInit;
        this.timerManche = 180000;
        this.mancheFini = false;
        this.recommencer = true;
        this.timerSpawnPomme = 60000;
        this.timerSpawnMalus = 45000;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setRecommencer(boolean recommencer) {
        this.recommencer = recommencer;
    }

    public void setTimerRecommencer(long timerRecommencer) {
        this.timerRecommencer = timerRecommencer;
    }

    public boolean isRecommencer() {
        return recommencer;
    }
    
    
}
