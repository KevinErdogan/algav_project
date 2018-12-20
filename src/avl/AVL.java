package avl;

import cle.Cle;

public class AVL { 
	
	class Noeud { 
		private Cle key;
		private int hauteur; 
		private Noeud gauche, droite; 
		
		Noeud(Cle d) { 
			key = d; 
			hauteur = 1; 
		} 
	}
	
	private Noeud racine=null;
	
	private Noeud rotationDroite(Noeud n) { 
		Noeud g = n.gauche; 
		Noeud T = g.droite; 

		//Rotation
		g.droite = n; 
		n.gauche = T; 

		//mise à jour de la hauteur
		n.hauteur = max(hauteur(n.gauche), hauteur(n.droite)) + 1; 
		g.hauteur = max(hauteur(g.gauche), hauteur(g.droite)) + 1; 

		return g; 
	} 

	private Noeud rotationGauche(Noeud n) { 
		Noeud d = n.droite; 
		Noeud T = d.gauche; 

		//Rotation
		d.gauche = n;  
		n.droite = T; 

		// mise à jour de la hauteur
		n.hauteur = max(hauteur(n.gauche), hauteur(n.droite)) + 1; 
		d.hauteur = max(hauteur(d.gauche), hauteur(d.droite)) + 1; 

		return d; //y est la nouvelle racine 
	} 

	public Noeud insert(Noeud n, Cle key) { 

		//cas de base : insertion dans un ABR
		if (n == null) return (new Noeud(key)); 

		if (key.inf(n.key)) n.gauche = insert(n.gauche, key); 
		else if (n.key.inf(key)) n.droite = insert(n.droite, key); 
		else return n; 

		//mise à jour de la hauteur du noeud
		n.hauteur = 1 + max(hauteur(n.gauche), hauteur(n.droite)); 

		//REEQUILIBRAGE
		
		int balance = getBalance(n); 
	
		if (balance > 1 && key.inf(n.gauche.key)) return rotationDroite(n); 
		if (balance < -1 && n.droite.key.inf(key))return rotationGauche(n); 
		if (balance > 1 && n.gauche.key.inf(key)) { 
			n.gauche = rotationGauche(n.gauche); 
			return rotationDroite(n); 
		} 
		if (balance < -1 && key.inf(n.droite.key)) { 
			n.droite = rotationDroite(n.droite); 
			return rotationGauche(n); 
		} 

		return n; 
	} 


	public static int hauteur(Noeud n) { 
		if (n == null) return 0; 
		return n.hauteur; 
	} 

	public static int max(int a, int b) { 
		return (a > b) ? a : b; 
	} 
	
	public static int getBalance(Noeud n) { 
		if (n == null) return 0;
		return hauteur(n.gauche) - hauteur(n.droite); 
	} 
	
	public static void parcoursPrefixe(Noeud Noeud) { 
		if (Noeud != null) { 
			System.out.print(Noeud.key + " "); 
			AVL.parcoursPrefixe(Noeud.gauche); 
			AVL.parcoursPrefixe(Noeud.droite); 
		} 
	} 
	
	public Noeud getRacine() {
		return racine;
	}
	
	public boolean recherche(Noeud n,Cle c) {
		
		if(n == null) return false;
		if(n.key.eg(c)) return true;
		else{
			Noeud g = n.gauche;
			Noeud d = n.droite;

			if (c.inf(n.key)){
				return recherche(g,c);
			}
			else {
				return recherche(d,c);
			}
		}
	}

	public static void main(String[] args) { 
		AVL tree = new AVL(); 
		
		
		tree.racine = tree.insert(tree.racine, new Cle(0,0,0,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(1,0,0,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(0,1,0,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(0,0,1,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(0,1,1,1)); 
		tree.racine = tree.insert(tree.racine, new Cle(1,1,1,1)); 

		System.out.println();
		AVL.parcoursPrefixe(tree.racine);
		
		System.out.println(tree.recherche(tree.racine, new Cle(1,0,0,1)));
	} 
} 
