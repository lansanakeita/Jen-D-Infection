package jeu_Inffection; 
import java.util.ArrayList;


public class State{
	
	/**
	 * Les Attributs de la Classe
	 */
	protected int nbligne; 
	protected int nbcolonne; 
	protected int grille[][] ; 
	protected int joueur; 	

	/**
	 * Creation du constructeur
	 */
	public State (int nbligne, int nbcolonne) {
		this.nbligne = nbligne; 
		this.nbcolonne = nbcolonne; 
		this.grille=new int [nbligne][nbcolonne]; 
		this.joueur = 1; 
	}
	
	/**
	 * La copie du constructeur 
	 */
	public State (State state) {
		this(state.nbligne,state.nbcolonne);
		for (int i=0; i< this.nbligne; i++){
			for (int j=0; j<this.nbcolonne; j++){
				  this.grille[i][j]=state.grille[i][j] ; 
			   }
		}
	}
	
	/**
	 * Les Getters et Setter permettant acceder aux attributs hors de la Classe 
	 * getJoueur() represente le Joueur Courant
	 */
	public int getJoueur() {
		return this.joueur;
	}
	
	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	
	public int[][] getGrille() {
		return grille;
	}

	public void setGrille(int grille[][]) {
		this.grille = grille;
	}

	
	
	/**
	 * Methode permettant d'afficher la grille de jeu avec des traits de séparation
	 */
	public void affiche(){
		for (int i=0; i< this.nbligne; i++){
			for (int j=0; j<this.nbcolonne; j++){
				System.out.print(" | " + this.grille[i][j]); 
			}
			System.out.println(" | "); 
		}
		System.out.println(); 
	}

	/**
	 * Creation de la Méthode getMove qui stocke tous les mouvements possibles dans les quatres directions 
	 * en eplacement 
	 * Et en Duplication  
	 * 
	 */

	// Methode pour le deplacement : la lettre t represente le pion
	public ArrayList<Move> getMove(int joueur){
 		ArrayList<Move> move = new ArrayList<Move>();
 		for (int i=0; i< this.nbligne; i++){
			for (int j=0; j<this.nbcolonne; j++){
			
				if(this.grille[i][j]==joueur){
					
					// test pour descendre en deplacement
					if (i<this.nbligne-2 && this.grille[i+2][j]==0){
						move.add(new Move(i,j,i+2,j,true)); }
					
						// test pour monter en deplacement
						if (i>1 && this.grille[i-2][j]==0){
							move.add(new Move(i,j,i-2,j,true)); 
						}
	
						// test pour aller  a droite en deplacement
						if (j<this.nbcolonne-2 && this.grille[i][j+2]==0){
							move.add(new Move(i, j, i, j+2, true));
						}

						// test pour aller au gauche en deplacement
						 if (j>1 && this.grille[i][j-2]==0){
							move.add(new Move(i,j,i,j-2,true)); 
						}			
						
						// test pour descendre en duplication
						 if (i<this.nbligne-1 && this.grille[i+1][j]==0){
							move.add(new Move(i,j,i+1,j,false)); 
						}
	
						// test pour monter en duplication 
						 if (i>1 && this.grille[i-1][j]==0){
							move.add(new Move(i,j,i-1,j,false));
						}
	
						// test pour aller  a droite	en duplication
						if (j<this.nbcolonne-1 && this.grille[i][j+1]==0){
								move.add(new Move(i, j, i, j+1, false));
						}
						 
						// parcours pour aller au gauche en duplication
						 if (j>1 && this.grille[i][j-1]==0){
							 move.add(new Move(i,j,i,j-1,false));
						}
					}
				}
		} 
		return move; 
	}  
	
	/**
	 * Methode permettant de vérifié si le Jeu est terminé 
	 * le jeu est terminé si les 2 deux joueurs n'ont plus de mouvement possible
	 */
	public boolean isFinished(){
				if ((this.getMove(1).isEmpty() || this.getMove(2).isEmpty())){
					return true;  
				}
				else {
					return false; 
				}
	} 
	
	/**
	 * Méthode permettant de determiner le  nombre de pion pour chaque joueur 
	 * et de calculer le Score final
	 */
	public float getScore (int joueur){
		float nbrePion=0,nbreTotal=0; 
		
		for(int i = 0; i <this.nbligne; i++){
			for (int j = 0; j <this.nbcolonne; j++){
				
				if(this.grille[i][j]!=0){
					nbreTotal ++; 
				}
				
				if(this.grille[i][j]==joueur){
					nbrePion ++; 
				}
			}
		}
		return nbrePion/nbreTotal; 
	} 

	/**
	 * La Methode play prend la liste des mouvements disponibles pour les utilisés
	 * on a 2 types de deplacement : Le deplacement de et la duplication 
	 * true = deplacement simple et false = duplication 
	 * si on doit utilisé un deplacement simple la position de départ doit être vide et l'arrivé doit être égal au joueur 
	 * si on a une duplication : la position de départ et arrivée doivent être égale au joueur
	 * on a egalement une variable enemi qui represente le joueur ennemi ou adversaire 
	 * si la position d'arrivée est égale a enemi alors le pion nous appartient et c'est le même principe pour le pion adverse aussi 
	 */
	// la methode Play
	public State play(Move mouv) {
		State state = new State(this);
		int enemi = this.joueur == 1? 2: 1;
		state.grille[mouv.getArriveeX()][mouv.getArriveeY()]=this.joueur;
		
		if(mouv.isDeplacement()==true) {
			state.getGrille()[mouv.getDepartX()][mouv.getDepartY()]=0;	
		}
			
		else {
				// pour descendre avec duplication 
			if (mouv.getArriveeX()<this.nbligne-1 && this.grille[mouv.getArriveeX()+1][mouv.getArriveeY()]==enemi){
				state.grille[mouv.getArriveeX()+1][mouv.getArriveeY()]=this.joueur;
				}
				// pour monter avec duplication 
			 if (mouv.getArriveeX()>1 && this.getGrille()[mouv.getArriveeX()-1][mouv.getArriveeY()]==enemi){
				state.grille[mouv.getArriveeX()-1][mouv.getArriveeY()]=this.joueur;
			}
				// pour aller  a droite	avec duplication
			 if (mouv.getArriveeY()<this.nbcolonne-1 && this.getGrille()[mouv.getArriveeX()][mouv.getArriveeY()+1]==enemi){
				state.grille[mouv.getArriveeX()][mouv.getArriveeY()+1]=this.joueur; 
			}
				// pour aller au gauche avec duplication
			 if (mouv.getArriveeY()>1 && this.getGrille()[mouv.getArriveeX()][mouv.getArriveeY()-1]==enemi){
				state.grille[mouv.getArriveeX()][mouv.getArriveeY()-1]=this.joueur;
			}			
		}	
		state.joueur = enemi;
		return state;
	}	
	
	/**
	 * Methode permettant d'affiche le Score pour les 2 joueurs
	 * et de désigner le joueur qui a plus de pion comme étant le gagnat de la partie  
	 */
	public void afficheScore() {
		System.out.println("                              ");
		System.out.println("le score du Joueur 1 est: " + getScore(1));
		System.out.println("le score du Joueur 2 est: " + getScore(2));
		System.out.println("                                ");
		if(getScore(1)> getScore(2)) {
			System.out.println(" Le Joueur 1 est le Gagnant ! ");
		}
		else {
			System.out.println(" Le Joueur 2 est le Gagnant !");
		}
	}

	
	
}
