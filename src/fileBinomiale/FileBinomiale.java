package fileBinomiale;

import java.util.LinkedList;
import java.util.List;

import cle.Cle;

public class FileBinomiale {
	private List<TournoiBinomiale> tournois = new LinkedList<TournoiBinomiale>();

	public FileBinomiale() {
	}

	public FileBinomiale(List<TournoiBinomiale> binomials) {
		for (TournoiBinomiale t : binomials)
			tournois.add(t);
	}

	// primitives

	public boolean estVide() {
		return tournois.isEmpty();
	}

	public TournoiBinomiale minDeg() {
		if (tournois.isEmpty())
			return null;
		TournoiBinomiale minDeg = tournois.get(0);
		for (int i = 1; i < tournois.size(); i++) {
			if (tournois.get(i).degre() < minDeg.degre())
				minDeg = tournois.get(i);
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
		if (tournois.isEmpty())
			return;
		TournoiBinomiale toRemove = tournois.get(0);
		for (int i = 1; i < tournois.size(); i++)
			if (tournois.get(i).getRacine().getVal().inf(toRemove.getRacine().getVal()))
				toRemove = tournois.get(i);
		FileBinomiale f = toRemove.decapite();
		tournois.remove(toRemove);
		union(f);
	}

	public FileBinomiale ajout(TournoiBinomiale t) {
		return union(TournoiBinomiale.file(t));
	}

	public FileBinomiale union(FileBinomiale f) {
		FileBinomiale copy = new FileBinomiale(f.tournois);

		// l'un des deux ou les deux sont vides
		if (tournois.isEmpty() || f.tournois.isEmpty()) {
			if (tournois.isEmpty() && !f.tournois.isEmpty())
				tournois = copy.tournois;
			return this;
		}

		// les deux files ne sont pas vides
		List<TournoiBinomiale> newT = new LinkedList<TournoiBinomiale>(); // liste de la nouvelle file
		TournoiBinomiale t1, t2, tmp, memory = null;
		List<TournoiBinomiale> pTournois;

		// on parcourt les deux files en meme temps 
		while (minDeg() != null || f.minDeg() != null || memory != null) {
			// on prend les tournois de degre minimum
			t1 = minDeg();
			t2 = f.minDeg();
			
			if (t1 != null || t2 != null) {
				if (t1 != null) {
					tmp = t1;
					pTournois = tournois;
				} else {
					tmp = t2;
					pTournois = f.tournois;
				}

				// cas 1 : on a en memoire l'union de deux tournois de degre min précedents
				if (memory != null) {
					if (memory.degre() == tmp.degre()) {
						memory = memory.union2Tid(tmp);
						pTournois.remove(tmp);
					} else {
						if (memory.degre() < tmp.degre()) {
							newT.add(memory);
							memory = null;
						} else {
							newT.add(tmp);
							pTournois.remove(tmp);
						}
					}
				} 
				// cas 2 : les deux tournois minimum sont non nuls et on a rien en memoire
				else if (t2 != null && tmp != t2) {
					if (t2.degre() == t1.degre()) {
						memory = t2.union2Tid(t1);
						tournois.remove(t1);
						f.tournois.remove(t2);
					} else {
						if (t2.degre() < t1.degre()) {
							newT.add(t2);
							f.tournois.remove(t2);
						} else {
							newT.add(t1);
							tournois.remove(t1);
						}
					}
				}
				// cas 3 : l'un deux des tournois min est nul et on a rien en memoire
				else {
					newT.add(tmp);
					pTournois.remove(tmp);
				}
			} 
			// cas 4 : les deux tournois mins sont nul et on a quelque chose en memoire
			else {
				newT.add(memory);
				memory = null;
			}
		}

		tournois = newT;
		return this;
	}
	

	public void ConsIter(List<Cle> cles) {
		for (Cle c : cles)
			ajout(new TournoiBinomiale(c));
	}

	public String toString() {
		String s = "";
		s = s +"<";
		for (TournoiBinomiale t : tournois) {
			s = s +"T"+ t.degre();
			if (t != tournois.get(tournois.size() - 1))
				s = s +",";
		}
		s = s +">";
		return s;
	}

}
