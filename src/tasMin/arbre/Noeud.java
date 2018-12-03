package tasMin.arbre;

import cle.Cle;

public class Noeud {
	
	private Cle val ;
	private Noeud gauche,droit,parent;
	
	public Noeud (Cle val) {
		this.val = val;
		this.gauche = null;
		this.droit = null;
		this.parent = null;
	}
	
	public Cle getVal() {
		return this.val;
	}

	public Noeud getGauche() {
		return gauche;
	}

	public Noeud getDroit() {
		return droit;
	}

	public Noeud getParent() {
		return parent;
	}

	public void setVal(Cle val) {
		this.val = val;
	}

	public void setGauche(Noeud gauche) {
		this.gauche = gauche;
	}

	public void setDroit(Noeud droit) {
		this.droit = droit;
	}

	public void setParent(Noeud parent) {
		this.parent = parent;
	}
	
	public boolean estComplet() {
		return this.getNbFilsGauche() == this.getNbFilsDroit();
	}
	
	public int getNbFilsGauche() {
		return getNbFilsGauche(this);
	}
	
	private int getNbFilsGauche(Noeud n) {
		if(n == null) return 0;
		if(n.gauche != null) return 1+ getNbFilsGauche(n.gauche);
		else return 0;
	}
	
	public int getNbFilsDroit() {
		return getNbFilsDroit(this);
	}
	
	private int getNbFilsDroit(Noeud n) {
		if(n == null) return 0;
		if(n.droit != null) return 1+ getNbFilsDroit(n.droit);
		else return 0;
	}
}
