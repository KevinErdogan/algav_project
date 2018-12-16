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
	
	// Test Area
	
	public static void main(String[] args) {
		LinkedList<Cle> cles = new LinkedList<Cle>();
		try {
			FileReader f = new FileReader(new File("cles_alea/jeu_1_nb_cles_50000.txt"));
			char buffer0x[] = new char[2];
			String s = "";
			int turn=3;
			long values[] = new long[] { 0, 0, 0, 0 };
			char a = 0;
			while(f.read(buffer0x)>0) {
				while((a= ((char) f.read()))!='\n' && s.length()<=32)
					s = s + a;
				while(s.length()>=8) {
					values[turn]=Long.parseLong(s.substring(s.length()-8,s.length()), 16);
					turn--;
					s=s.substring(0, s.length()-8);
				}
				if(turn>-1)
					values[turn]=Long.parseLong(s, 16);
				cles.add(new Cle(values[0], values[1], values[2], values[3]));
				values[0]=0;values[1]=0;values[2]=0;values[3]=0;
				s="";
				turn=3;
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Nombre de cles : "+cles.size());
		System.out.println("Test de la complexite temporelle de la fonction ConsIter");
		FileBinomiale f = new FileBinomiale();
		System.out.println("Start !");
		long startTime = System.currentTimeMillis();
		f.ConsIter(cles);
		long endTime = System.currentTimeMillis();
		System.out.println("End ! Time : "+(endTime-startTime));
	}
}
