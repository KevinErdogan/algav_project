package fileBinomiale;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cle.Cle;

public class TournoiBinomiale {

class Noeud {
	
	private Cle val;
	private LinkedList<Noeud> fils = new LinkedList<Noeud>();
	
	public Noeud(Cle val) {
		this.val=val;
	}
	
	public Noeud(Cle val, List<Noeud> list) {
		this.val=val;
		fils.addAll(list);
	}
	
	public Cle getVal() {
		return val;
	}
	
	public LinkedList<Noeud> getFils(){
		return fils;
	}

	public int degre() {
		return fils.size();
	}
}
	
	private Noeud racine=null;
	
	public TournoiBinomiale() {}
	
	public TournoiBinomiale(Noeud racine) {
		this.racine=racine;
	}
	
	public TournoiBinomiale(Cle cleRacine) {
		racine= new Noeud(cleRacine);
	}
	
	public Noeud getRacine() {
		return racine;
	}
	
	public boolean estVide(){
		return racine==null;
	}
	
	public int degre() {
		if(racine==null) return 0;
		return racine.degre();
	}
	
	public TournoiBinomiale union2Tid(TournoiBinomiale t){
		if(t.degre()!=degre()) {
			System.out.println("Erreur union de deux tournois de degré différent");
			System.exit(1);
		}
			
		TournoiBinomiale newT = new TournoiBinomiale();
		// la racine est la plus petite des deux cles
		if(t.racine.getVal().inf(racine.getVal())) {
			newT.racine = t.racine;
			newT.racine.fils.addFirst(racine);
		}else {
			newT.racine = racine;
			newT.racine.fils.addFirst(t.racine);
		}
		return newT;
	}
	
	public FileBinomiale decapite() {
		if(degre()<1) return null;
		LinkedList<Noeud> noeuds = racine.fils;
		List<TournoiBinomiale> tournois = new ArrayList<TournoiBinomiale>();
		for(int i=0;i<noeuds.size();i++) 
			tournois.add(new TournoiBinomiale(noeuds.get(i)));
		return new FileBinomiale(tournois);
	}
	
	public static FileBinomiale file(TournoiBinomiale t) {
		List<TournoiBinomiale> l = new ArrayList<TournoiBinomiale>();
		l.add(t);
		return new FileBinomiale(l);
	}
	
}
