package cle;

// ECHAUFFEMENT

public class Cle {

	private int [] cle = new int [4];
	
	
	//bit de poids faible : indice 3
	public Cle(int i1,int i2,int i3,int i4) {
		this.cle[0] = i1;
		this.cle[1] = i2;
		this.cle[2] = i3;
		this.cle[3] = i4;
	}
	
	//question 1.1
	public boolean inf(Cle c) {
		if(this.getTaille()< c.getTaille()) return true;
		if(this.getTaille() > c.getTaille()) return false;
		for(int i =0; i<this.getTaille();i++) {
			if (this.cle[i] < c.getCle()[i]) return true;
			if (this.cle[i] > c.getCle()[i]) return false;
		}
		return false;
	}
	
	
	//question 1.2
	public boolean eg(Cle c) {
		if(this.getTaille() != c.getTaille()) {
			return false;
		}
		else {
			for (int i= 0;i<this.getTaille();i++) {
				if(this.cle[i] !=  c.getCle()[i]) return false;
			}
		}
		return true;
	}
	
	public int [] getCle() {
		return this.cle;
	}
	
	public void setCle(Cle c) {
		this.cle = c.cle;
	}
	
	public int getTaille() {
		return this.cle.length;
	}
	
	public String toString() {
		return ""+this.cle[0]+ ""+this.cle[1]+""+this.cle[2]+""+this.cle[3];
	}
}
