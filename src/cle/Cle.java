package cle;

// ECHAUFFEMENT

public class Cle {

	private int [] cle = new int [4];
	
	
	//bit de poids faible : indice 3
	public Cle(int i1,int i2,int i3,int i4) {
		cle[0] = i1;
		cle[1] = i2;
		cle[2] = i3;
		cle[3] = i4;
	}
	
	//question 1.1
	public boolean inf(Cle c) {
		for(int i =0; i<4;i++) {
			if (cle[i] < c.getCle()[i]) return true;
		}
		return false;
	}
	
	
	//question 1.2
	public boolean eg(Cle c) {
		for (int i= 0;i<4;i++) {
			if(cle[i] !=  c.getCle()[i]) return false;
		}
		return true;
	}
	
	public void setCle(Cle c) {
		this.cle = c.cle;
	}
	
	public String toString() {
		return ""+this.cle[0]+ ""+this.cle[1]+""+this.cle[2]+""+this.cle[3];
	}
}
