package test;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import cle.Cle;
import fileBinomiale.FileBinomiale;

public class Test {
	// Test Area
	public static void main(String[] args) {
		// Tas min
		
		// Test ConsIter FileBinomiale
		Test.testFileBinomialeConsIter("cles_alea");
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
			System.out.println("Test du fichier : "+file.getName());
			System.out.println("Nombre de cles : " + cles.size());
			System.out.println("Test de la complexite temporelle de la fonction ConsIter");
			System.out.println("Start...");
			FileBinomiale f = new FileBinomiale();
			long startTime = System.nanoTime();
			f.ConsIter(cles);
			long endTime = System.nanoTime();
			System.out.println("End ! Time : " + (endTime - startTime) + " ns.");
		}
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
}
