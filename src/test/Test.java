package test;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import avl.AVL;
import cle.Cle;
import fileBinomiale.FileBinomiale;
import fileBinomiale.TournoiBinomiale;
import md5.Md5;
import tasMin.arbre.ArbreBinaire;
import tasMin.tableau.Tableau;

public class Test {
	// Test Area
	public static void main(String[] args) {
		
		/*TestConsIter Tas binaire	
		//Test.testTasArbreConsIter("cles_alea");
		//Test.testTasTableauConsIter("cles_alea");
		 */
		 
		
		//TestUnion Tas binaire	
	//	Test.testTasArbreUnion("cles_alea");
		//Test.testTasTableauUnion("cles_alea");
		 
		/* Test Ajout
		FileBinomiale f = new FileBinomiale();
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,0,1)));
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,0,1)));
		TournoiBinomiale t1 = (new TournoiBinomiale(new Cle(0,0,1,0))).union2Tid(new TournoiBinomiale(new Cle(0,1,0,1)));
		f = f.ajout(t1);
		TournoiBinomiale t3 = ((new TournoiBinomiale(new Cle(0,0,1,0))).union2Tid(new TournoiBinomiale(new Cle(0,1,0,1)))).union2Tid((new TournoiBinomiale(new Cle(0,0,1,0))).union2Tid(new TournoiBinomiale(new Cle(0,1,0,1)))).union2Tid(((new TournoiBinomiale(new Cle(0,0,1,0))).union2Tid(new TournoiBinomiale(new Cle(0,1,0,1)))).union2Tid((new TournoiBinomiale(new Cle(0,0,1,0))).union2Tid(new TournoiBinomiale(new Cle(0,1,0,1)))));
		f= f.ajout(t3);
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,0,1)));
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,0,1)));
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,0,1)));
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,0,1)));
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,0,1)));
		f= f.ajout(new TournoiBinomiale(new Cle(0,0,1,1)));
		System.out.println(f);
		*/
		// Test ConsIter FileBinomiale
		//Test.testFileBinomialeConsIter("cles_alea");
		Test.AVLShakespeare("Shakespeare");
		//Test.testFileBinomialeUnion("cles_alea");
		//Test.testFileBinomialeConsIter("cles_alea");
		
		//Test.testTasMinSupprMin("cles_alea");
		//Test.testTasMinAjout("cles_alea");
		//Test.testTasMinShakespeareConsIter("Shakespeare");
	}
	
	//Q6.12
	public static void AVLShakespeare(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		Md5 hash = new Md5();
		AVL tree = new AVL();
		LinkedList<String> uniqueWords = new LinkedList<String>();
		for(File file : txtFiles) {
			System.out.println(file.getName());
			List<String> words = Test.readWordFile(repertoryName+"/"+file.getName());
			long startTime = System.nanoTime();
			for(String w : words) {
				Cle c = hash.md5(w);
				if(tree.getRacine()==null) {
					uniqueWords.addLast(w);
					tree.setRacine(tree.insert(tree.getRacine(),c));
				}
				else if(!tree.recherche(tree.getRacine(), c)){
					uniqueWords.addLast(w);
					tree.setRacine(tree.insert(tree.getRacine(),c));
				}
				
			}
			long endTime = System.nanoTime();
			System.out.println(/*"End ! Time : " */+ (endTime - startTime) + " ns.");				
		}
			System.out.println(uniqueWords.size());

	}
	
	
	public static void testTasTableauConsIter(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+file.getName());
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction ConsIter");
			System.out.println("Start...");
			Tableau t = new Tableau();
			long startTime = System.nanoTime();
			t.ConsIter(cles);
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		}
	}
	
	public static void testTasArbreConsIter(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+file.getName());
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction ConsIter");
			System.out.println("Start...");
			ArbreBinaire a = new ArbreBinaire();
			long startTime = System.nanoTime();
			a.ConsIter(cles);
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		}
	}
	
	public static void testTasArbreUnion(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+file.getName());
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction Union");
			System.out.println("Start...");
			ArbreBinaire a1 = new ArbreBinaire().ConsIter(cles);
			ArbreBinaire a2 = new ArbreBinaire().ConsIter(cles);
			a2.ConsIter(cles);
			long startTime = System.nanoTime();
			a1.Union(a2);
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		}
	}
	
	public static void testTasTableauUnion(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+file.getName());
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction Union");
			System.out.println("Start...");
			Tableau t1 = new Tableau().ConsIter(cles);
			Tableau t2 = new Tableau().ConsIter(cles);
			long startTime = System.nanoTime();
			t1.Union(t2);
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		}
	}
	
	public static void testFileBinomialeConsIter(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+file.getName());
			//System.out.println("Test du fichier : "+file.getName());
			System.out.print(/*"Nombre de cles : "*/ + cles.size()+" ");
			//System.out.println("Test de la complexite temporelle de la fonction ConsIter");
			//System.out.println("Start...");
			FileBinomiale f = new FileBinomiale();
			long startTime = System.nanoTime();
			f.ConsIter(cles);
			long endTime = System.nanoTime();
			System.out.println(/*"End ! Time : " */+ (endTime - startTime) + " ns.");
		}
	}
	
	public static void testFileBinomialeUnion(String repertoryName) {
		/*File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});*/
		//for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+"jeu_1_nb_cles_50000.txt");
			List<Cle> cles2 = Test.readKeyFile(repertoryName+"/"+"jeu_2_nb_cles_50000.txt");
		//	System.out.println("Test du fichier : "+file.getName());
		/*	System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction ConsIter");
			System.out.println("Start...");*/
			FileBinomiale f = new FileBinomiale();
			FileBinomiale f2 = new FileBinomiale();
			f.ConsIter(cles);
			f.ConsIter(cles2);
			long startTime = System.nanoTime();
			f.union(f2);
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		//}
	}
	
	
	//Q.1.4

	public static void testTasMinSupprMin(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		
		for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+file.getName());
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction SupprMin");
			System.out.println("Start...");
			Tableau t = new Tableau().ConsIter(cles);
			long startTime = System.nanoTime();
			t.SupprMin();
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		}
	}
	
	public static void testTasMinAjout(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		
		for(File file : txtFiles) {
			List<Cle> cles = Test.readKeyFile(repertoryName+"/"+file.getName());
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction SupprMin");
			System.out.println("Start...");
			Tableau t = new Tableau().ConsIter(cles);
			long startTime = System.nanoTime();
			t.Ajout(new Cle(1,0,0,1));
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		}
	}
	
	public static void testTasMinShakespeareConsIter(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		int cmp =0;
		long av=0;
		Md5 hash = new Md5();
		
		for(File file : txtFiles) {
			List<String> words = Test.readWordFile(repertoryName+"/"+file.getName());
			List<Cle> cles = new ArrayList<Cle>();
			for(String w : words) {
				cles.add(hash.md5(w));
			}
			
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction ConsIter");
			System.out.println("Start...");
			Tableau t1 = new Tableau();
			long startTime = System.nanoTime();
			t1.ConsIter(cles);
			long endTime = System.nanoTime();
			av += (endTime - startTime);
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
			cmp++;
		}
		
		
		
		System.out.println("Moyenne " + av/cmp);
	}
	
	public static void testTasMinShakespeareUnion(String repertoryName) {
		File rep = new File(repertoryName);
		File[] txtFiles = rep.listFiles(new FilenameFilter(){
		  public boolean accept(File dir, String name) {
		    return name.endsWith(".txt");
		  }
		});
		int cmp =0;
		long av=0;
		Md5 hash = new Md5();
		
		for(File file : txtFiles) {
			List<String> words = Test.readWordFile(repertoryName+"/"+file.getName());
			List<Cle> cles = new ArrayList<Cle>();
			for(String w : words) {
				cles.add(hash.md5(w));
			}
			
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction Union");
			System.out.println("Start...");
			
			Tableau t1 = new Tableau().ConsIter(cles);
			Tableau t2 = new Tableau().ConsIter(cles);
			long startTime = System.nanoTime();
			t1.Union(t2);
			long endTime = System.nanoTime();
			av += (endTime - startTime);
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
			cmp++;
		}
		System.out.println("Moyenne " + av/cmp);
	}
	
	
	
	public static List<Cle> readKeyFile(String path) {
		List<Cle> cles = new LinkedList<Cle>();
		try {
			FileReader f = new FileReader(new File(path));
			char buffer0x[] = new char[2];
			String s = "";
			int turn = 3;
			long values[] = new long[] { 0, 0, 0, 0 };
			char a = 0;
			while (f.read(buffer0x) > 0) {
				while ((a = ((char) f.read())) != '\n' && s.length() <= 32)
					s = s + a;
				while (s.length() >= 8) {
					values[turn] = Long.parseLong(s.substring(s.length() - 8, s.length()), 16);
					turn--;
					s = s.substring(0, s.length() - 8);
				}
				if (turn > -1)
					values[turn] = Long.parseLong(s, 16);
				cles.add(new Cle(values[0], values[1], values[2], values[3]));
				values[0] = 0;
				values[1] = 0;
				values[2] = 0;
				values[3] = 0;
				s = "";
				turn = 3;
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cles;
	}
	
	public static List<String> readWordFile(String path){
		List<String> words = new LinkedList<String>();
		try {
			FileReader f = new FileReader(new File(path));
			String s = "";
			int b;
			char a = 0;
			while ((b=f.read()) > 0) {
				a = (char) b;
				if(a!='\n') { 
					s=s+a;
				}else {
					words.add(new String(s));
					s="";
				}
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
}
