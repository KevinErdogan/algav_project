package cle;

// ECHAUFFEMENT

public class Cle {

	private long [] cle;
	
	public Cle() {
		cle = new long[] {0,0,0,0};
	}
	
	//bit de poids faible : indice 3
	public Cle(long i1,long i2,long i3,long i4) {
		cle = new long[4];
		cle[0] = i1;
		cle[1] = i2;
		cle[2] = i3;
		cle[3] = i4;
	}
	
	//question 1.1
	public boolean inf(Cle c) {
		for(int i =0; i<4;i++) 
			if (cle[i] != c.cle[i]) 
				return cle[i] < c.cle[i];
		return false;
	}
	
	
	//question 1.2
	public boolean eg(Cle c) {
		for (int i= 0;i<4;i++) {
			if(cle[i] !=  c.cle[i])
				return false;
		}
		return true;
	}
	
	public long [] getCle() {
		return cle;
	}
	
	public String toString() {
		return ""+cle[0]+" "+cle[1]+" "+cle[2]+" "+cle[3];
	}
}
