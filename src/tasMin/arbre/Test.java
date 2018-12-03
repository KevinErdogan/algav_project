package tasMin.arbre;

import java.util.ArrayList;
import java.util.List;

import cle.Cle;

public class Test {
	public static void main(String[] args) {
	
		Cle c0 = new Cle(0,0,0,0);
		Cle c1 = new Cle(0,0,0,1);
		Cle c2 = new Cle(0,0,1,0);
		Cle c3 = new Cle(0,0,1,1);
		Cle c4 = new Cle(0,1,0,0);
		Cle c5 = new Cle(0,1,0,1);
		Cle c6 = new Cle(0,1,1,0);
		Cle c7 = new Cle(0,1,1,1);
		Cle c8 = new Cle(1,0,0,0);
		
		Noeud n = new Noeud(c5);
		ArbreBinaire b = new ArbreBinaire(n);
		b.Ajout(c0);
		b.Ajout(c1);
		b.Ajout(c2);
		b.Ajout(c3);
		b.Ajout(c4);
		b.Ajout(c2);
		b.Ajout(c0);
		
		//System.out.println(b.hauteur(b.getRacine()));
		b.printArbre();
	    b.SupprMin();
	    System.out.println();
	    System.out.println("SUPPR\n");
		b.printArbre();
		
		b.SupprMin();
		System.out.println();
		System.out.println("SUPPR\n");
		b.printArbre();
		
		
		System.out.println("\nConsIter\n");
		List <Cle> consIterliste = new ArrayList <Cle>();
		consIterliste.add(c8);
		consIterliste.add(c7);
		consIterliste.add(c6);
		consIterliste.add(c5);
		consIterliste.add(c4);
		consIterliste.add(c3);
		consIterliste.add(c2);
		consIterliste.add(c1);
		consIterliste.add(c0);
		
		ArbreBinaire b1 = b.ConsIter(consIterliste);
		b1.printArbre();
		
		
		System.out.println("\nUnion\n");
		ArbreBinaire union = b.Union(b1);
		union.printArbre();
	}
}
