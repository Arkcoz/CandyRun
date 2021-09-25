/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candyrun;

import candyrun.bonbon.Bonbon_Violet;
import candyrun.bonbon.Tache_Encre;
import candyrun.foreground.MenuFin;
import candyrun.foreground.MenuPause;
import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Joueur extends BoxGameItem implements KeyListener{

    private Manche manche;
    
    //Sprite
    private ArrayList<String> SpritePlayer;
    private int etat; 
    private int deltaX;
    private int deltaY;
    
    private boolean menuFin;
    
    
    //touches:
    private boolean right;
    private boolean left;
    private boolean space;
    
    //Invincibilité si touché
    private boolean invincible; 
    private long invincibleClock;
    private boolean invisible;
    
    //Vitesse 
    private int vitesseJ;
    private boolean touchePlateforme;
    
    //Saut
    private boolean saut;
    private int timerSautValue;
    private double vitesseSaut;
    private int timerSaut;
    private boolean toucheLeSol;
    private boolean peutSauter;
    
    private boolean regardeAGauche;
    
    private long timerSortirMenu;
    private boolean SortirMenu;
    
    public Joueur(Game g, int x, int y) {
        super(g, "p_left_1", x, y);
        
        this.manche = (Manche) g;
        
        //Sprite
        initialisationSprite();
        this.etat = 0;
        this.deltaX = 0;
        this.deltaY = 0;
        
        this.invincible = false;
        this.invincibleClock =0;
        this.invisible = false;
        
        //Initialisation des variables de déplacement
        this.right = false;
        this.left = false;
        this.space = false;
        
        //saut
        this.saut = false;
        this.timerSautValue = 100;
        this.vitesseSaut = 0;
        this.timerSaut = timerSautValue;
        this.peutSauter = false;
        
        this.toucheLeSol = true;
                
        //Vitesse 
        this.vitesseJ = 0;
        this.touchePlateforme = false;
        
        //Boolean de l'orientation du regard du personnage
        this.regardeAGauche = true;
        
        //Boolean pour savoir si on est dans le menu fin
        this.menuFin = false;
        
        //Gestion du saut en sortant du menu pause
        this.timerSortirMenu = 0;
        this.SortirMenu = false;

    }

    @Override
    public void collideEffect(GameItem gi) {
        this.toucheLeSol=false;
        this.touchePlateforme = false;
        
        if (gi.getItemType().equals("Sol_Main")){
            if (gi.getTop() <= this.getBottom()){
                this.saut = false;
                this.toucheLeSol = true;
                this.timerSaut = timerSautValue;
                this.peutSauter = true;
                this.deltaY = gi.getTop() - this.getBottom();
                this.vitesseSaut = deltaY; 
            }
        }
        
        if (gi.getItemType().equals("Plateforme")){
            if (!this.manche.getPlateforme().isDerriere()){
                if ( (this.getBottom() >= gi.getTop()-5) && (this.getBottom() <= gi.getTop()+5)){
                    this.saut = false;
                    this.toucheLeSol = true;
                    this.timerSaut = timerSautValue;
                    this.peutSauter = true;
                    this.deltaY = gi.getTop() - this.getBottom();
                    this.vitesseSaut = deltaY; 
                    this.touchePlateforme = true;
                }
            }
        }
        
        if(gi.getItemType().equals("Fond temps")){
            this.deltaX = gi.getLeft() - this.getRight();
            this.vitesseJ = deltaX;
            
        }
        
        
        if(!invincible){
            
            //BONBON = GROSSIR
            if(gi.getItemType().equals("Bonbon")){
                if (this.etat <4){
                    this.etat += 1;
                    this.invincible = true;
                }
                else{
                    this.etat = 5;
                }
            }
            
            if(gi.getItemType().equals("Bonbon Violet")){
                if (this.etat <4){
                    this.etat += 1;
                    this.invincible = true;
                    this.getGame().addItem(new Tache_Encre(this.getGame(),0,0));
                }
                else{
                    this.etat = 5;
                }
            }
            
            //POMME = MAIGRIR
            if(gi.getItemType().equals("Pomme")){
                if (this.etat >0){
                    this.etat -= 1;
                    this.invincible = true;
                }
                else{
                    //Rien faire
                }
            }
        }
        
        
        

    }

    @Override
    public String getItemType() {
        return "Player";
    }

    @Override
    public void evolve(long l) {
        
        //POUR ÉVITER DE SAUTER EN SORTANT DU MENU (Exemple : menu pause + continuer)
        this.timerSortirMenu -= l;
        this.SortirMenu = true;
        if(timerSortirMenu <= 0){
            this.SortirMenu = false;
        }

        //GESTION SPRITE
        changementSprite();

        //PARTIE TIMERSAUTVALUE
        switch (etat){
                case 0 : this.timerSautValue = 100;
                       
            break;
                case 1 : this.timerSautValue = 80;
                        
            break;
                case 2 : this.timerSautValue = 60;
                       
            break;
                case 3 : this.timerSautValue = 40;
                
            break;
                case 4 : this.timerSautValue = 20;
                
            break;
                case 5 : this.manche.setIsFreeze(true);
                
            break;
        }

        
        if ((this.etat == 5) && (!this.menuFin)){
            this.manche.addItem(new MenuFin(this.getGame(),0,0));
            this.menuFin = true;
        }
            
        if (!this.manche.isIsFreeze()){
            //PARTIE POSSIBILITÉ DE SAUTER
            if (!this.toucheLeSol){
                this.peutSauter = false;
            }

            //ACTIVATION DU SAUT SI LA BARRE ESPACE EST PRESSÉ
            if(space){
                if((saut == false) && (this.SortirMenu == false)){
                    this.saut = true;
                    this.vitesseSaut = -10;
                }
            }

            //Gestion invincible
            if(this.invincible){
                this.invincibleClock += l;
                if(this.invincibleClock >= 1500){
                    this.invincible = false;
                    this.invincibleClock =0;
                    this.invisible = false;
                }
            }

            //PARTIE DEPLACEMENT DU JOUEUR
            deplacementJoueur(l);

        }
        
    }    
    
    public void keyTyped(KeyEvent e) {
        

    }

    public void keyPressed(KeyEvent e) {
        try{
            switch(e.getKeyCode())
            {
                
                //Aller à Gauche [←]
                case KeyEvent.VK_LEFT:
                        left = true;
                        regardeAGauche = true;
                    break;   
                
                //Aller à Droite [→]
                case KeyEvent.VK_RIGHT:
                        right = true;
                        regardeAGauche = false;
                    break;
                    
                //Sauter [SPACE]
                case KeyEvent.VK_SPACE: 
                    if(peutSauter){
                        space = true;
                    }
                    break;
                    
                //Sauter [↑]
                case KeyEvent.VK_UP: 
                    if(peutSauter){
                        space = true;
                    }
                    break;
                
                //Mettre Etat 0 [A]
                case KeyEvent.VK_A: 
                        etat = 0;
                    break;
                
                //Mettre Etat 1 [Z]
                case KeyEvent.VK_Z: 
                        etat = 1;
                    break;
                
                //Mettre Etat 2 [E]
                case KeyEvent.VK_E: 
                        etat = 2;
                    break;
                 
                //Mettre Etat 3 [R]
                case KeyEvent.VK_R: 
                        etat = 3;
                    break;
                    
                //Mettre Etat 4 [T]
                case KeyEvent.VK_T: 
                        etat = 4;
                    break;    
                    
                     
                //Mettre Temps a 1:50 [Y]
                case KeyEvent.VK_Y: 
                        this.getGame().addItem(new Bonbon_Violet(this.getGame(),50,-50,this.manche.getVagueBonbon()));
                    break;
                    
                //Mettre Temps a 1:50 [U]
                case KeyEvent.VK_U: 
                        this.manche.getTemps().setLeTemps();
                    break;    
                    
                case KeyEvent.VK_ESCAPE:
                    if (!menuFin){
                        this.manche.setIsFreeze(true);
                        this.manche.addItem(new MenuPause(this.getGame(),0,0));
                    }
                    break;
                        
            }
        }
        catch(Exception x){}
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
           
            case KeyEvent.VK_LEFT :
                    left = false;
                break;
                    
            case KeyEvent.VK_RIGHT :
                    right = false;
                break;
                
            case KeyEvent.VK_SPACE :
                    space = false;
                break;
              
            case KeyEvent.VK_UP :
                    space = false;
                break;
        }
    }
    
    public void deplacementJoueur(long l){
        
        //PARTIE DEPLACEMENT GAUCHE-DROITE
        if (left){
            if(this.getLeft()>10){
                switch (etat){
                    case 0  : vitesseJ = -10;
                        break;
                    case 1  : vitesseJ = -8;
                        break;
                    case 2  : vitesseJ = -6;
                        break;
                    case 3  : vitesseJ = -4;
                        break;
                    case 4  : vitesseJ = -2;
                        break;    
                }
                if(!regardeAGauche){
                    this.regardeAGauche = true;
                }
            }else{
                vitesseJ = 0;
            }
        }else if (right){
            if (this.getRight()<this.getGame().getWidth()-310){
                switch (etat){
                    case 0  : vitesseJ = 10;
                        break;
                    case 1  : vitesseJ = 8;
                        break;
                    case 2  : vitesseJ = 6;
                        break;
                    case 3  : vitesseJ = 4;
                        break;
                    case 4  : vitesseJ = 2;
                        break;     
                }
                if(regardeAGauche){
                    this.regardeAGauche = false;
                }
            }else{
                vitesseJ = 0;
            }
        }else {
            vitesseJ = 0;
        }
        
        
        
        //PARTIE SAUT
        if (saut){         
            this.timerSaut -= l;         
            if ((timerSaut<= 0) && (vitesseSaut != 10)){
                this.vitesseSaut +=2;
                this.timerSaut = this.timerSautValue;  
                
            }
            if (left){
                if(this.getLeft()>10){
                    switch (etat){
                        case 0  : vitesseJ = -9;
                            break;
                        case 1  : vitesseJ = -7;
                            break;
                        case 2  : vitesseJ = -5;
                            break;
                        case 3  : vitesseJ = -3;
                            break;
                        case 4  : vitesseJ = -1;
                            break;    
                    }
                }
            }else if (right){
                if (this.getRight()<this.getGame().getWidth()-310){
                    switch (etat){
                        case 0  : vitesseJ = 9;
                            break;
                        case 1  : vitesseJ = 7;
                            break;
                        case 2  : vitesseJ = 5;
                            break;
                        case 3  : vitesseJ = 3;
                            break;
                        case 4  : vitesseJ = 1;
                            break; 
                    }
                }
            }else{
                vitesseJ = 0;
            }
        }
        
        
        
        if (deltaX != 0){
            vitesseJ = deltaX;
        }
            
        
        if (this.touchePlateforme){
            this.vitesseJ += this.manche.getPlateforme().getVitesse();
        }
        
        //PARTIE SUR LE SOL 
        if ((!saut) && (toucheLeSol)){
            //vitesseSaut = deltaY dans collidEffect des types "Sol" (Plateforme et Main_Sol)
        }
        
        //PARTIE CHUTE D'UNE PLATEFORME
        if ((!saut) && (!toucheLeSol)){
            this.timerSaut -= l;         
            if ((timerSaut<= 0) && (vitesseSaut <= 10)){
                this.vitesseSaut +=4;
                this.timerSaut = this.timerSautValue;        
            }  
        }
        
        
        
        //DEPLACEMENT FINAL
        this.moveXY(vitesseJ, vitesseSaut);
        
    }
    
    public void initialisationSprite(){
        SpritePlayer = new ArrayList<>();
        SpritePlayer.add(0,"p_right_1");
        SpritePlayer.add(1,"p_right_2");
        SpritePlayer.add(2,"p_right_3");
        SpritePlayer.add(3,"p_right_4");
        SpritePlayer.add(4,"p_right_5");
        SpritePlayer.add(5,"p_right_6");
        SpritePlayer.add(6,"p_left_1");
        SpritePlayer.add(7,"p_left_2");
        SpritePlayer.add(8,"p_left_3");
        SpritePlayer.add(9,"p_left_4");
        SpritePlayer.add(10,"p_left_5");
        SpritePlayer.add(11,"p_left_6");
        
    }
    
    public void changementSprite(){
        if (!this.manche.isIsFreeze()){    
            if (invincible){
                switchEtat();
            }

            if (invisible){
                this.changeSprite("invisible" + etat);
            }else{
                if (regardeAGauche){
                    this.changeSprite(SpritePlayer.get(this.etat + 6));
                }else {
                    this.changeSprite(SpritePlayer.get(this.etat));
                }
            }
        }
    }
    
    private void switchEtat() {
        if (this.invisible){
            this.invisible = false;
        }else{
            this.invisible = true;
        }
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setMenuFin(boolean menuFin) {
        this.menuFin = menuFin;
    }
    
    public void reset (){
        this.etat = 0;
        this.menuFin = false;
    }

    public void setTimerSortirMenu(long timerSortirMenu) {
        this.timerSortirMenu = timerSortirMenu;
    }
    
    
}
