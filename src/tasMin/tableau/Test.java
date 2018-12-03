package tasMin.tableau;

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
		
		Tableau tab = new Tableau();
		tab.Ajout(c2);
		tab.Ajout(c5);
		tab.Ajout(c4);
		tab.Ajout(c4);
		tab.Ajout(c4);
		tab.Ajout(c3);
		tab.Ajout(c1);
		tab.Ajout(c0);
		
		tab.printList();
		tab.SupprMin();
		tab.printList();
		
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
		
		Tableau newtab = tab.ConsIter(consIterliste);
		System.out.println("----CONSITER");
		newtab.printList();
		
		tab.Union(newtab);
		
		Tableau union = tab.Union(newtab);
		union.printList();
		
	}


}
