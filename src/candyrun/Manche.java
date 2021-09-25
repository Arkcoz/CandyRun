/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun;

import candyrun.bonbon.VagueDeBonbon;
import candyrun.score.Temps;
import candyrun.background.Background;
import candyrun.background.FondTemps;
import candyrun.foreground.Arbre;
import candyrun.foreground.Barriere;
import candyrun.foreground.MenuDebut;
import candyrun.foreground.Petale;
import candyrun.sol.Plateforme;
import candyrun.sol.Sol;
import candyrun.sol.Sol_Main;
import iut.Audio;
import iut.Game;
import iut.Vector;
import java.awt.Graphics;

/**
 *
 * @author lucas
 */
public class Manche extends Game{
    private Sol sol;
    private Background background;
    private Joueur joueur;
    private VagueDeBonbon vagueBonbon;
    
    private Barriere barriere;
    private Plateforme plateforme;
    
    private Petale petale;
    private FondTemps fondTemps;
    private Temps temps;
    private Arbre arbre;
    private Jauge jauge;
    
    private boolean isFreeze;
    
    private Audio sonMusique = new Audio("Musique1");
    
    public Manche(int width, int height, String title) {
        super(width, height, title);
        
        this.sol = new Sol_Main(this,0,950);
        this.background = new Background(this, 0, 0);
        this.joueur = new Joueur(this, 750, 800);
        this.vagueBonbon = new VagueDeBonbon(this,0,0);
        this.barriere = new Barriere(this,500,766-57);
        this.plateforme = new Plateforme(this, 420, 766,this.barriere);
        this.petale = new Petale(this, -600, 0, 2);
        this.fondTemps = new FondTemps(this,1000,0);
        this.temps = new Temps(this, 500,500);
        this.arbre = new Arbre(this,-200,457);
        this.jauge = new Jauge(this,1060,800);
        
        this.isFreeze = false;
        
        startMusic();
        
    }

    @Override
    protected void createItems() {
        
        this.addItem(new MenuDebut(this,0,0));
        
    }

    @Override
    protected void drawBackground(Graphics grphcs) {
        
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

    public Joueur getJoueur() {
        return joueur;
    }

    public Plateforme getPlateforme() {
        return plateforme;
    }

    public boolean isIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(boolean isFreeze) {
        this.isFreeze = isFreeze;
    }
    
    public void stopMusic() {
       //this.sonMusique = null;
    }
    
    public void startMusic(){
        //this.sonMusique.start();
    }

    public Temps getTemps() {
        return temps;
    }
    
    public void quitter(){
        this.die();
        this.exit();
    }
    
    public void recommencer(){
        this.joueur.reset();
        this.temps.reset();
        this.vagueBonbon.reset();
        this.plateforme.reset();
        this.background.reset();
        this.isFreeze = false;
        
        
        
    }

    public Sol getSol() {
        return sol;
    }
    
    public Background getBackgroundd() {
        return background;
    }

    public VagueDeBonbon getVagueBonbon() {
        return vagueBonbon;
    }

    public Barriere getBarriere() {
        return barriere;
    }

    public Petale getPetale() {
        return petale;
    }

    public FondTemps getFondTemps() {
        return fondTemps;
    }

    public Arbre getArbre() {
        return arbre;
    }

    public Jauge getJauge() {
        return jauge;
    }
    
    
}
