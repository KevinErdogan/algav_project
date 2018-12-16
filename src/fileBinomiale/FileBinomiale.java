package fileBinomiale;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import cle.Cle;

public class FileBinomiale {
	private List<TournoiBinomiale> tournois = new LinkedList<TournoiBinomiale>();
	
	public FileBinomiale() {}
	
	public FileBinomiale(List<TournoiBinomiale> binomials) {
		for(TournoiBinomiale t : binomials)
			tournois.add(t);
	}
	
	// primitives

	public boolean estVide() {
		return tournois.isEmpty();
	}
	
	public TournoiBinomiale minDeg() {
		if(tournois.isEmpty()) return null;
		TournoiBinomiale minDeg = tournois.get(0);
		for(int i=1; i<tournois.size();i++) {
			if(tournois.get(i).degre() < minDeg.degre())
				minDeg=tournois.get(i);
		}
		return minDeg;
	}
	
	public FileBinomiale reste() {
		TournoiBinomiale degMin = minDeg();
		tournois.remove(degMin);
		FileBinomiale reste = new FileBinomiale(tournois);
		tournois.add(degMin);
		return reste;
	}
	
	public void ajoutMin(TournoiBinomiale t) {
		tournois.add(t);
	}
	
	// fonctions fondamentales
	
	public void supprMin() {
		if(tournois.isEmpty()) return;
		TournoiBinomiale toRemove = tournois.get(0);
		for(int i =1;i<tournois.size();i++) 
			if(tournois.get(i).getRacine().getVal().inf(toRemove.getRacine().getVal())) 
				toRemove=tournois.get(i);
		FileBinomiale f = toRemove.decapite();
		tournois.remove(toRemove);
		union(f);
	}
	
	public void ajout(TournoiBinomiale t) {
		union(TournoiBinomiale.file(t));
	}
	
	public FileBinomiale union(FileBinomiale f) {
		FileBinomiale copy = new FileBinomiale(f.tournois);
		List<TournoiBinomiale> newT = new LinkedList<TournoiBinomiale>();
		TournoiBinomiale t1, t2, memory=null;
		
		while(!tournois.isEmpty() && !copy.tournois.isEmpty()){
			t1 = minDeg();
			t2 = copy.minDeg();
			if(memory!=null) {
				if(memory.degre()==t1.degre() && memory.degre()==t2.degre()) {
					newT.add(memory);
					memory=t1.union2Tid(t2);
					tournois.remove(t1);
					copy.tournois.remove(t2);
				}else if(memory.degre()==t1.degre()) {
					memory = memory.union2Tid(t1);
					tournois.remove(t1);
				}else if(memory.degre()==t2.degre()){
					memory = memory.union2Tid(t2);
					copy.tournois.remove(t2);
				}else {
					newT.add(memory);
					memory=null;
				}
			}else if(	t1.degre() < t2.degre()) {
					newT.add(t1);
					tournois.remove(t1);
			}
			else if(t1.degre() > t2.degre()){
				newT.add(t2);
				copy.tournois.remove(t2);
			} else {
				memory = t1.union2Tid(t2);
				tournois.remove(t1);
				copy.tournois.remove(t2);
			}
		}
		tournois = newT;
		return this;
	}
	
	public void ConsIter(List<Cle> cles) {
		for(Cle c : cles)
			ajout(new TournoiBinomiale(c));
	}

}
