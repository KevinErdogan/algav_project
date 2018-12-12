package fileBinomiale;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import cle.Cle;

public class FileBinomiale {
	private LinkedList<TournoiBinomiale> tournois = new LinkedList<TournoiBinomiale>();
	
	public FileBinomiale() {}
	
	public FileBinomiale(TournoiBinomiale...binomials) {
		for(TournoiBinomiale t : binomials)
			tournois.add(t);
	}
	
	// primitives
	
	public boolean estVide() {
		return tournois.isEmpty();
	}
	
	public TournoiBinomiale minDeg() {
		if(tournois.isEmpty()) return null;
		return tournois.getLast();
	}
	
	public FileBinomiale reste() {
		TournoiBinomiale t[] = new TournoiBinomiale[tournois.size()-1];
		for(int i=0; i<tournois.size()-1;i++) 
			t[i] = tournois.get(i);
		return new FileBinomiale(t);
	}
	
	public FileBinomiale ajoutMin(TournoiBinomiale t) {
		tournois.addLast(t);
		return this;
	}
	
	// fonctions fondamentales
	
	public void SupprMin() {
		int toRemove=0;
		int min = tournois.getFirst().degre();
		for(int i=1;i<tournois.size();i++) {
			if(tournois.get(i).degre() < min) {
				min=tournois.get(i).degre();
				toRemove=i;
			}
		}
		tournois.remove(toRemove);
	}
	
	public void ajout(TournoiBinomiale t) {
		FileBinomiale.union(this, TournoiBinomiale.file(t));
	}
	
	public static FileBinomiale union(FileBinomiale f1, FileBinomiale f2) {
		return null;
	}
	
	public static FileBinomiale ConsIter(List<TournoiBinomiale> tournois) {
		FileBinomiale f = new FileBinomiale();
		for(TournoiBinomiale t : tournois)
			f.ajout(t);;
		return f;
	}
	
	// Test Area
	
	public static void main(String[] args) {
		try {
			FileReader f = new FileReader(new File("cles_alea/jeu_1_nb_cles_100.txt"));
			LinkedList<Cle> cles = new LinkedList<Cle>();
			char useless[] = new char[2]; // 0x
			char buffer[] = new char[31]; // nbr hexa
			char retour[] = new char[1]; // '\n'
			while(f.read(useless) > 0) {
				f.read(buffer);
				f.read(retour);
				if(retour[0] != '\n') {
					char buffer2[] = new char[32];
					for(int i=0;i<31;i++)
						buffer2[i] = buffer[i];
					buffer2[31]=retour[0];
					long l1 = Long.parseLong(new String(buffer2, 2, 8), 16);
					long l2 = Long.parseLong(new String(buffer2, 8, 8), 16);
					long l3 = Long.parseLong(new String(buffer2, 16, 8), 16);
					long l4 = Long.parseLong(new String(buffer2, 24, 8), 16);
					cles.add(new Cle(l1, l2, l3, l4));
					
					f.read(retour);
				}else {
					long l1 = Long.parseLong("0"+new String(buffer, 2, 7), 16);
					long l2 = Long.parseLong(new String(buffer, 7, 8), 16);
					long l3 = Long.parseLong(new String(buffer, 15, 8), 16);
					long l4 = Long.parseLong(new String(buffer, 23, 8), 16);
					cles.add(new Cle(l1, l2, l3, l4));
				}
			}
			f.close();
			for(int j=0;j<cles.size();j++)
				System.out.println(cles.get(j).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
