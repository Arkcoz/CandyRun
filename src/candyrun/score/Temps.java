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
public class Temps extends GameItem {
    
    private Manche manche;
    private boolean delete; 
    
    private long temps;
    private int secondes;
    private int minutes;

    private Chiffre minute1;
    private Chiffre minute2;
    private DoublePoint doublePoint;
    private Chiffre seconde1;
    private Chiffre seconde2;
    
    private long tempsTotal;    //Temps écoulé en milliseconde depuis le début
    
    public Temps(Game g, double _x, double _y) {
        super(g, "transparent", _x, _y);
        
        this.manche = (Manche) g;
        this.delete = false; 
        
        this.temps = 0;
        this.secondes = 0;
        this.minutes = 0;
        
        this.minute1 = new Chiffre(g,1050,500,this);
        this.minute2 = new Chiffre(g,1085,500,this);
        this.doublePoint = new DoublePoint(g,1128,498,this);
        this.seconde1 = new Chiffre(g,1165,500,this);
        this.seconde2 = new Chiffre(g,1200,500,this);
        
        
        
        
        this.tempsTotal = 0;
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
        return "Temps";
    }

    @Override
    public void evolve(long l) {
        if (!this.manche.isIsFreeze()){
            this.temps += l;
            if (temps >= 1000){
                this.secondes += 1;
                this.temps -= 1000;
                if(secondes >= 60){
                    this.minutes +=1;
                    this.secondes -=60;
                }
            }

            this.minute1.setNum(minutes/10);
            this.minute2.setNum(minutes%10);
            this.seconde1.setNum(secondes/10);
            this.seconde2.setNum(secondes%10);
            
            this.tempsTotal += l;
        }
    }

    public long getTempsTotal() {
        return tempsTotal;
    }
    
    public void delete(){
        this.delete = true;
        this.manche.remove(this);
    }

    public boolean isDelete() {
        return delete;
    }
    
    
    //METHODE REMISE A NIVEAU
    public void reset(){
        this.temps = 0;
        this.secondes = 0;
        this.minutes = 0;
        this.tempsTotal = 0;
    }

    public void setLeTemps() {
        this.tempsTotal = 119000;
        this.minutes = 1;
        this.secondes = 59;
        
    }

    
    
    public void start(){
        this.getGame().addItem(minute1);
        this.getGame().addItem(minute2);
        this.getGame().addItem(doublePoint);
        this.getGame().addItem(seconde1);
        this.getGame().addItem(seconde2);
    }
}
