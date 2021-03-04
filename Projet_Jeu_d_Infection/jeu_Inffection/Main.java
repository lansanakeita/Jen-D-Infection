package jeu_Inffection;

import java.util.ArrayList;

public class Main{
	public static void main(String[] args) {
		
		/**
		 * Initialisation de la Classe State et Affichage de la Grille
		 * args[0] et args[1] definissent la taille de notre grille (Ligne et Colonne)
		 * state.getGrille()[0][0] : represente la première ligne et la première colonne
		 * Integer.parseInt(args[0])-1 : represente la dernière Ligne
		 * Integer.parseInt(args[1])-1 : represente la dernière colonne
		 */
		State state = new State(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		state.getGrille()[0][0] = 1;
		state.getGrille()[Integer.parseInt(args[0])-1][Integer.parseInt(args[1])-1] = 2;   
		state.affiche(); 
		
		/**
		 * Initialisation de la Classe Intelligence qui permet d'utiliser notre MinMax et la Methode AlphaBeta
		 * ia1 represente le premier joueur et il est désigné par le Chiffre 1 
		 * ia2 represente le deuxième joueur et il est désigné par le Chiffre 2 
		 * Integer.parseInt(args[2] : represente la profondeur du Joueur 1 
		 * Integer.parseInt(args[3] : represente la profondeur du Joueur 2
		 * Integer.parseInt(args[4] : represente le nombre de coup d'avance
		 * Boolean.parseBoolean(args[5]) : represente le choix entre MinMax et AlphaBeta
		 */
		Intelligence ia1 = new Intelligence (1, Integer.parseInt(args[2]), Boolean.parseBoolean(args[5]));  	
		Intelligence ia2 = new Intelligence (2, Integer.parseInt(args[3]), Boolean.parseBoolean(args[5]));  
		int coupAvance = Integer.parseInt(args[4]);
		
		while (!state.isFinished()) {
			
			/**
			 * Test des Coups d'avances du Joueur 2 afin d'quilibrer le jeu
			 */
			
			
			 if (coupAvance!= 0){
				 System.out.println(coupAvance);
				 state.joueur = 2;
				 coupAvance -= 1;
			}
			
			
			/**
			 * Apres avoir joué les coups d'avances la partie débute
			 */
			// on joue apres celui du coup d'avance
		
			if (state.getJoueur() == 1) {
				state = state.play(ia1.decision(state));
			} else {
				state = state.play(ia2.decision(state));
			}
			state.affiche();
		}
			/**
			 * Affichage du Score
			 */
			System.out.print("                        ");
			state.afficheScore();


			/**
			 * Affiche du nombre de noeuds parcourus par les 2 joueurs
			 * si le choix est égal a true on affiche pour le MinMax 
			 * sinn on affiche pour alphaBeta
			 */
			if (ia1.choix == true && ia2.choix == true  ){
				System.out.print(" Le nombre de Noeuds parcourus par le J1 est "+ ia1.getCptMinMax() + " , Le nombre de Noeuds parcourus par le J2 est " + ia2.getCptMinMax());
				System.out.print("                        ");
			}
			else{
				System.out.print(" Le nombre de Noeuds parcourus par le J1 est "+ ia1.getCptAlphaBeta() + " , Le nombre de Noeuds parcourus par le J2 est " + ia2.getCptAlphaBeta());
				System.out.print("                        ");
			}
	}
	
}
