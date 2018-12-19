package tasMin.arbre;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cle.Cle;

public class ArbreBinaire {
	
	private Noeud racine;
	public ArbreBinaire() {
		racine = null;
	}
	
	public ArbreBinaire(Noeud racine) {
		this.racine = racine;
	}	
	
	/*----------------- LES 4 FONCTIONS FONDAMENTALES ----------------- */
	
	public void Ajout(Cle c) {
		
		// cas 1 :  ajout dans un arbre vide
		if(racine == null) {
			racine = new Noeud(c);
		}
		else{
			
			/*
			 * cas 2 : on ajoute au dernier noeud le nouveau noeud a gauche si l'arbre est complet 
			 * ou si fils gauche incomplet, ou a droite  si le fils gauche est complet
			 */  
			AjoutDernierNoeud(c,racine,null);
			
			/*
			 * pour la dernière clé ajoutée, on compare la clé de son parent avec la sienne, si elle est plus grande
			 * alors on les échange et ainsi de suite jusqu'à ce qu'on arrive à la racine
			 */
			Noeud d = dernierNoeud(racine);
			Noeud p = d.getParent();
			
			while(p != null) {
				if(d.getVal().inf(p.getVal())) {
					swap(d,p);
					d= p;
					p = d.getParent();
				}
				else {
					break;
				}
			}
		}
		
	}
	
	//Ajout de la clé au dernier noeud qui ne se trouve pas à la hauteur h 
	private void AjoutDernierNoeud(Cle c,Noeud n,Noeud p) {
		if(n == null) {
			n = new Noeud(c);
			
			n.setParent(p);
			if(p.getGauche() == null) p.setGauche(n);
			else p.setDroit(n);
			return;
		}
		if(n.estComplet() || !(n.getGauche().estComplet())) {
			AjoutDernierNoeud(c,n.getGauche(),n);
		}
		else {
			AjoutDernierNoeud(c,n.getDroit(),n);
		}
	}
	
	public void SupprMin() {
		
		//cas de base
		if(racine == null) return;
		if(taille(racine) == 1) racine = null;
		
		else {
		Noeud d = dernierNoeud(racine);
		Noeud p = racine;
		
		//on échange les clés de la racine et de la derniere feuille
		swap(d,p);
		
		//on enlève le dernier noeud de l'arbre
		if(d == d.getParent().getGauche()) {
			d.getParent().setGauche(null);
		}
		else {
			d.getParent().setDroit(null);
		}
		d.setParent(null);
		

		descendre(p); // p : la racine
		
		}
	}
	
	
	/*
	 * en partant de la racine (nouvelle valeur donc pas le minimum), on fait le descendre dans ses fils,
	 * on choisit le plus petit de ses fils
	 */
	private void descendre(Noeud n) {
		
		//trouver le fils avec la clé la plus petite
		Noeud min;
		Noeud g = n.getGauche();
		Noeud d = n.getDroit();
		
		
		if(g != null && d !=null) {
			if(g.getVal().inf(d.getVal()) || g.getVal().eg(d.getVal())) {
				min = g;
			}
			else min = d;
		}
		else if (g != null && d ==null) {
			min = g;
		}
		else if (g == null && d !=null) {
			min = d;
		}
		else return;
		//comparaison avec le noeud actuel
		if(min.getVal().eg(n.getVal())) return;
		if(min.getVal().inf(n.getVal())) {
			swap(n,min);
			descendre(min);
		}
	}
	
	
	public ArbreBinaire ConsIter(List <Cle> cles) {
		
		List <Noeud> noeuds = new ArrayList<Noeud>();
		
		//liste des noeuds du nouveau tas
				for (Cle c : cles) {
					noeuds.add(new Noeud(c));
				}
				
				//pere (i-1)/2
				//gauche 2*i+1
				//droite 2*i+2
				//creation de l'arborescence du tas (set : fils gauche, fils droit et pere)
				int taille = noeuds.size();
				for(int i =0; i<taille;i++) {
					Noeud curr = noeuds.get(i);		
					
					if((2*i+1) < taille && (2*i+2) < taille) {
						Noeud g = noeuds.get(2*i+1);
						Noeud d = noeuds.get(2*i+2);
						curr.setGauche(g);
						curr.setDroit(d);
					}
					else if((2*i+1) < taille) {
						Noeud g = noeuds.get(2*i+1);
						curr.setGauche(g);
					}
					else if((2*i+2) < taille) {
						Noeud d = noeuds.get(2*i+2);
						curr.setDroit(d);
					}
					if((i-1)/2 > 0) {
						Noeud p = noeuds.get((i-1)/2);
						curr.setParent(p);
					}
				}
				
				/*
				 *pour chaque noeud qui ont des fils : si leur clé est plus petite alors on les fait descendre dans
				 *l'arbre en commencant par le dernier noeud ayant des fils
				 */
				for(int i = (noeuds.size()/2)-1;i>=0;i--){
					descendre(noeuds.get(i));
				}
				
				return new ArbreBinaire(noeuds.get(0));
	}
	
	
	public ArbreBinaire Union(ArbreBinaire a) {
		List<Cle> l1 = this.getCles(); //O(n)
		List<Cle> l2 = a.getCles(); //O(m)
		
		List<Cle> union = new ArrayList<Cle>();
		union.addAll(l1);
		union.addAll(l2);
	
		return ConsIter(union);
	}
	
	// ---------- PRIMITIVES
	
	//O(log n)
	public int taille(Noeud n) {
		if (n == null) return 0;
		else return (taille(n.getGauche())+ 1 + taille(n.getDroit()));
	}
	
	
	//O(log n)
	public int hauteur(Noeud n) {
		if (n == null) return 0;
		else return (1+ Math.max(hauteur(n.getGauche()),hauteur(n.getDroit())));
	}
	
	
	//Echange la valeur des clés de 2 noeuds
	public void swap(Noeud n1, Noeud n2) {
		Cle tmp = n1.getVal();
		n1.setVal(n2.getVal());
		n2.setVal(tmp);
	}
	
	
	//O(n) : a la BFS
	public void printArbre() {
		LinkedList<Noeud> file = new LinkedList<Noeud>();
		if(this.racine != null) {
			file.addFirst(this.racine);
			while(!file.isEmpty()) {
				Noeud n = file.getFirst();
				System.out.println(n.getVal());
				file.removeFirst();
				if(n.getGauche() != null) {
					file.add(n.getGauche());
				}
				if(n.getDroit() != null) {
					file.add(n.getDroit());
				}
			}
		}
		else {
			System.out.println("Arbre vide");
		}
	}
	
	
	//BFS
	public List<Cle> getCles(){
		List <Cle> list = new ArrayList<Cle>();
		LinkedList<Noeud> file = new LinkedList<Noeud> ();
		if(this.racine != null) {
			file.addFirst(this.racine);
			while(!file.isEmpty()) {
				Noeud n = file.getFirst();
				list.add(n.getVal());
				file.removeFirst();
				if(n.getGauche() != null) {
					file.add(n.getGauche());
				}
				if(n.getDroit() != null) {
					file.add(n.getDroit());
				}
			}
		}
		return list;
	}
	
	
	//retourne le dernier noeud ajouté : O(log n )
	public Noeud dernierNoeud(Noeud n) {
		if(n.getGauche() == null && n.getDroit() == null) {
			return n;
		}
		if(n.estComplet()) {
			return dernierNoeud(n.getDroit());
		}
		else {
			if(n.getGauche().estComplet()) {
				if(n.getDroit() != null) {
					if(!(n.getDroit().estComplet())) return dernierNoeud(n.getDroit());
				}
			}
			return dernierNoeud(n.getGauche());
		}
	}
	
	
	// ---------- GETTERS
	
	public Noeud getRacine() {
		return this.racine;
	}	  
}
