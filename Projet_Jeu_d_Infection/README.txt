LANSANA KEITA 



Classes : 
State Classe Contient : 
	- Création et Affichhage de la grille
	- La méthode getMove
	- La méthode isFinish
	- la méthode getScore
	- La méthode play 

	
Move contient: 
	- les positions de departs et d'arrivées des joueurs plus le type de déplacement à effectuer 


Intelligence contient : 
	- la méthode MinMax 
	- La méthode AlphaBeta 
	- La méthode decision permettant de faire le choix de la méthode à executer


Classes Executable : 
	-Main.java

Main.java :
	
	Compilation depuis la racine  : javac -d build jeu_Inffection/*.java
	Exécution : java -cp build jeu_Inffection.Main args[0] args[1] args[2] args[3] args[4] args[5]

	La classe teste si :
		le coup d'avance 
		le jeu avec Minmax ou
		AlphaBeta
	sont fonctionnels
