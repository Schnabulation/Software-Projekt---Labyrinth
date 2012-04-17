package algorithmus;

import labyrinth.Labyrinth;

public class Backtracking extends Algorithmus {

	final static int[] STEPX	= {  0, 1, 0,-1 };	
	final static int[] STEPY	= { -1, 0, 1, 0 };
	static int schritt;
	final static int OBEN = 0, RECHTS = 1, UNTEN = 2, LINKS = 3;
	static int aktX;
	static int aktY;
	
	@Override
	public Labyrinth solveLab(Labyrinth originalLab) {
		markieren(originalLab.getStart()[0], originalLab.getStart()[1],originalLab);
		
		rekursivLab(originalLab, originalLab.getStart()[0], originalLab.getStart()[1]);
		
		return originalLab;
	}
	
	public boolean rekursivLab(Labyrinth originalLab, int x, int y){
		int schritt = -1;
		originalLab.zeichnen();
		int n = originalLab.getBreite();
		
		while (schritt != LINKS){
			schritt ++;	
			int neuX	= x + STEPX[schritt];
			int neuY	= y + STEPY[schritt];
 
			boolean ok = true;
 
			if (neuX < 0 || neuX >= n) ok = false;
			if (neuY < 0 || neuY >= n) ok = false;
			if (ok && originalLab.getChar(neuX, neuY) !='1') ok = false;
 
			if(ok){
				markieren(neuX,neuY,originalLab);
 
				if (!ausgangGefunden(neuX, neuY, originalLab)) {
					// rekursiver Aufruf von FindeLoesung
					if (rekursivLab(originalLab, neuX, neuY)) {
						// Lösung gefunden	
						return true;
					} else {
						demarkieren(neuX,neuY,originalLab);					
					}
				} else return true; 		
			}
			
			
		}
		return false;
	}
	
	 private static boolean ausgangGefunden(int neuX, int neuY, Labyrinth originalLab) {
		 
		 if(neuX==originalLab.getEnde()[0] && neuY==originalLab.getEnde()[1]){
			 return true;
		 }
		 return false;
	}
	
	public void markieren(int x, int y, Labyrinth originalLab){
		originalLab.setChar(x, y, 'm');
		// currentLabyrinth[y][x]='m';
	}
	public void demarkieren(int x, int y, Labyrinth originalLab){
		originalLab.setChar(x, y, 'x');
		// currentLabyrinth[y][x]='x';
	}

}
