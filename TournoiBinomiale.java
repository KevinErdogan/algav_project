package fileBinomiale;

import java.util.InputMismatchException;
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
		if(fils.isEmpty())
			return 1;
		return 1+fils.getFirst().degre();
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
	
	public boolean estVide(){
		return racine==null;
	}
	
	public int degre() {
		if(racine==null) return 0;
		return racine.degre();
	}
	
	public TournoiBinomiale union2Tid(TournoiBinomiale t) throws InputMismatchException {
		if(t.degre()!=degre())
			throw new InputMismatchException();
			
		TournoiBinomiale newT = new TournoiBinomiale();
		newT.racine = t.racine;
		newT.racine.fils.addFirst(racine);
		return newT;
	}
	
	public FileBinomiale decapite() {
		if(degre()==1) return null;
		LinkedList<Noeud> noeuds = racine.fils;
		TournoiBinomiale[] tournois = new TournoiBinomiale[noeuds.size()];
		for(int i=0;i<noeuds.size();i++) 
			tournois[i] = new TournoiBinomiale(noeuds.get(i));
		return new FileBinomiale(tournois);
	}
	
	public static FileBinomiale file(TournoiBinomiale t) {
		return new FileBinomiale(t);
	}
	
}
