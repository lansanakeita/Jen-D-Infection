package jeu_Inffection; 

public class Move{
	
	/**
	 * Les Attributs de la Classe Move
	 * X represente la Ligne 
	 * Y represente la Colonne 
	 * departX et departY : representent le point de départ du joueur 
	 * arriveeX et arriveeY : represente le oint de d'arrivé du joueur
	 * notre boolean permet de determiner si un déplacement simple ou duplication 
	 */
	private int departX; 
	private int departY;
	private int arriveeX;  
	private int arriveeY; 	
	private boolean deplacement; 

	/**
	 * Le Constructeur de la Classe 
	 */
	public Move(int departX, int departY, int arriveeX, int arriveeY, boolean deplacement) {
	this.departX = departX;
	this.departY = departY;
	this.arriveeX = arriveeX;
	this.arriveeY = arriveeY;
	this.deplacement = deplacement;
}
	
	/**
	 * Les Getters et Setters permettant d'acceder aux attributs hors de la classe 
	 */
	public int getDepartX() {
		return departX;
	}


	public int getDepartY() {
		return departY;
	}

	public int getArriveeX() {
		return arriveeX;
	}

	public int getArriveeY() {
		return arriveeY;
	}

	public boolean isDeplacement() {
		return deplacement;
	}

/**
 * Constructeur Vide 
 */
public Move() {
	
}


}