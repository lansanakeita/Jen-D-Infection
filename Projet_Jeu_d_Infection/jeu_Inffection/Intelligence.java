package jeu_Inffection; 

	public  class Intelligence {
		
		/**
		 * Les Attributs de la Classe 
		 * cptMinMax permet de compter le nombre de noeud parcourus par MinMAx
		 * cptAlphaBeta permet de compter le nombre de noeud parcourus par Alpha
		 */
		protected int player;
		protected int profondeur;
		protected boolean choix; 
		protected int cptMinMax = 0;
		protected int cptAlphaBeta = 0;

		/**
		 * Constructeur de la Classe 
		 */

		public Intelligence(int player, int profondeur, boolean choix) {
		this.player = player;
		this.profondeur = profondeur;
		this.choix = choix; 
		}
		
		/**
		 * Les Getters permettant d'acceder aux attrubuts hors de la Classe
		 */
		public int getCptMinMax() {
			return this.cptMinMax;
		}
	
		public int getCptAlphaBeta() {
			return this.cptAlphaBeta;
		}
		
		/**
		 * La methode minMax prend en paramètre en State et une profondeur 
		 * on verifie d'abord si la profondeur est différente de 0 car si c'est le cas le jeu est immédiatement interrompu
		 * si la profondeur n'est pas nulle on verifie tous les noeuds possibles afin de trouver le meilleur coup a joué  
		 */
		
		public float minMax(State state, int d) {
			this.cptMinMax++;
			Move mv = new Move();
			float b;
			State nouveauState = new State(state); 
			
			if (d == 0 || state.isFinished()) {
				return state.getScore(this.player);
			}
			else 
				if (state.getJoueur() == this.player) {
					b = Float.NEGATIVE_INFINITY;
					
					for (Move mouvement : state.getMove(state.getJoueur())) {
						nouveauState = state.play(mouvement);
						
						float m = minMax(nouveauState, d - 1);
						if (m > b) {
							b=m;
							mv = mouvement;
						}
					}
				} 
				else {
					b = Float.POSITIVE_INFINITY;
			
					for (Move mouvement : state.getMove(state.getJoueur())) {
						 nouveauState = state.play(mouvement);
						
						 float m = minMax(nouveauState, d - 1);
						if (m < b) {
							b=m;
						mv = mouvement;
						}
					}
				}
			return b;
		}


		/**
		 La methode AlphaBeta prend en paramètre un State, 2 variables Alpha et Beta puis une profondeur 
		 * on verifie d'abord si la profondeur est différente de 0 car si c'est le cas le jeu est immédiatement interrompu
		 * si la profondeur n'est pas nulle on verifie les noeuds et dès qu'on rencontre un coup max on joue sans verifier le reste des noeuds   
		 */
		
		public float alphaBeta(State state, float alpha, float beta, int d) {
			this.cptAlphaBeta ++; 
			
			if (d == 0 || state.isFinished()) {
				return state.getScore(this.player);
			}
			else 
				if (state.getJoueur() == this.player) {
					
					for (Move mouvement : state.getMove(state.getJoueur())) {
						State nouveauState = state.play(mouvement);
						alpha = Math.max(alpha, alphaBeta(nouveauState,alpha, beta, d-1)); 
						if (alpha >= beta) {
							return alpha; 
						}
					}
					return alpha; 
				}
				else {
					
					for (Move mouvement : state.getMove(state.getJoueur())) {
						State nouveauState = state.play(mouvement);
						beta = Math.min(beta, alphaBeta(nouveauState,alpha, beta, d-1)); 
						if (alpha >= beta) {
							return beta; 
						}
					}
					return beta; 
				}
			
		}


		/**
		 * La Methode Decision prend un state en parametre et permet de joueur la partie avec MinMax ou AlphaBeta 
		 */
		public Move decision(State state) {
			float coup = Float.NEGATIVE_INFINITY;
			State nouveauState = new State(state); 
			Move move = null;
			
			/**
			 * si le choix est true on regarde dans la liste de mouvement possible et on commence à jouer avec minMax
			 */
			if(this.choix){
				for (Move mouvement : state.getMove(state.getJoueur())) {
					nouveauState = state.play(mouvement);
					float v = minMax(nouveauState, this.profondeur - 1);
				
					if (v > coup) {
						coup = v;
						move = mouvement;
				}
			}
			return move;
	
			}
			
			/**
			 * si le choix est false on regarde dans la liste de mouvement possible et onjoue avec AlphaBeta
			 */
			else{
				for (Move mouvement : state.getMove(state.getJoueur())) {
					nouveauState = state.play(mouvement);
					float v = alphaBeta(nouveauState, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, this.profondeur - 1);
				
					if (v > coup){
						coup = v;
						move = mouvement;
				}
			}
			return move; 	
	
			}
		}	
}