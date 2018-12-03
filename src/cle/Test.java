package cle;

public class Test {
	
public static void main(String[] args) {
	Cle c1 = new Cle(1,5,3,1);
	Cle c2 = new Cle(2,5,3,1);
	
	System.out.println("C1 == C2  ? " +c1.eg(c2));
	System.out.println("C1 < C2  ? " +c1.inf(c2));
	
	Cle c3 = new Cle(1,5,3,1);
	
	System.out.println("C1 == C3  ? " +c1.eg(c3));
	System.out.println("C1 < C3  ? " +c1.inf(c3));

 }
}
