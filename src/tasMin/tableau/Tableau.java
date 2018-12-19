package tasMin.tableau;

import java.util.ArrayList;
import java.util.List;

import cle.Cle;


//pere (i-1)/2
//gauche 2*i+1
//droite 2*i+2

public class Tableau {
	
	private List<Cle> list; //liste car taille inconnue,  si Cle [] alors taille invariable -> pas pratique pour l'ajout
	
	public Tableau() {
		list = new ArrayList<Cle>();
	}//
	
	public Tableau(List <Cle> cles) {	
		this.list = cles;
	}
	
	/*----------------- LES 4 FONCTIONS FONDAMENTALES ----------------- */
	
	//SUPPRIME LE MINIMUM
	public void SupprMin() {
		//cas de base
		if(list.size() == 0) return;
		if(list.size() == 1) list.remove(0);
		else {
			int last_i = list.size()-1; //indice du dernier élément
			swap(list,0,last_i); // on échange les clés du premier et dernier élément
			list.remove(last_i); // on supprime le dernier élément 
			descendre(0); // 0 car c'est la racine
		}
	}
	
	private void descendre(int i) {
		int g = 2*i+1; //fils gauche
		int d = 2*i+2; //fils droit
		int min = g; //init minimum
		int t = list.size();
		if( g < t && d < t) { // condition pour ne pas dépasser la taille du tas
			if(list.get(d).inf(list.get(g))) { // fils droit plus petit que fils gauche
				min = d;
			}
			
			if(list.get(min).inf(list.get(i))) { //si la clé à la position i est plus petit que le minimum
				swap(list,i,min); //échange i <-> min
				descendre(min); //on continue récursivement jusqu'à ce qu'il n'y ait plus de fils plus petit pour i 
			}
		}
	}
	
	public void Ajout(Cle c) {
		list.add(c); //ajout à la fin du tableau
	    Ajout(list.size()-1);
	}
	
	private void Ajout(int i) {
		Cle p = list.get((i-1)/2); //on récupère le parent de l'élément indice i
		Cle curr = list.get(i); //copie de l'élément indice i
		
		if(i>0 && curr.inf(p)) { //l'élément indice i pas la racine et inf à son parent
			swap(list,i,(i-1)/2); //échange
			Ajout((i-1)/2); //récursion : on remonte tant que parent plus grand
		}
		else {
			return;
		}		
	}
	
	public Tableau ConsIter(List<Cle> cles) {
	
		Tableau tab = new Tableau();

		for (int i = 0; i<cles.size(); i++) { 
	        tab.Ajout(cles.get(i)); 
		}
		
		return tab;
	}
	
	//retourne l'union de t1 et this
	public Tableau Union(Tableau t1) {
		//cas de base
		if(t1.list.size() == 0) return this;
		if(list.size() == 0) return t1;

		//nouveau tas
		Tableau union = new Tableau();
		List <Cle> l = new ArrayList<Cle>();
		l.addAll(list);
		l.addAll(t1.list);
		union.list = l;
		
		//pour chaque noeud qui ont des fils 
		for (int i = union.list.size()/ 2 - 1; i >= 0; i--) { 
	        union.descendre(i); 
		}
		return union;
	}

	
	/*-------------------------------------------------------------- */
	
	public void printList() {
		for(Cle c : list) {
			System.out.print(c +" ");
		}
		System.out.println();
	}
	
	//inverse la valeur des clés aux indices i1 et i2
	private void swap(List <Cle> list,int i1,int i2) {
		Cle c1 = list.get(i1);
		Cle c2 = list.get(i2);
		list.set(i2,c1);
		list.set(i1,c2);
	}
	


}
