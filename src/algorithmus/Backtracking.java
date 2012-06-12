package algorithmus;

import labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * Backtracking: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Die Klasse Backtracking implementiert ein Labyrinth Lösungsalgorithmus der nach dem Backtracking
 * Verfahren funktioniert. 
 * Dies ist ein einfaches Verfahren, welches an jedem Punkt mit einer einfachen Abfrage den nächsten
 * Weg bestimmt. In unserem Beispiel wird zuerst im Labyrinth nach oben geschaut, wenn dort frei ist, 
 * geht der Backtracking Algorithmus in diese Richtung. Wenn nach oben nicht gegangen werden kann,
 * wird rechts abgefragt, danach unten und zum Schluss links. Sind alle 4 Möglichkeiten nicht möglich,
 * befindet man sich in einer Sackgasse und es wird zurückgegangen bis zum ersten Punkt wo noch weitere 
 * Wege möglich sind.
 * 
 * --------------------------------------------------------------------------------------------- */

public class Backtracking extends Algorithmus{
	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	final int[] STEPX	= {  0, 1, 0,-1 };	
	final int[] STEPY	= { -1, 0, 1, 0 };
	final int OBEN = 0, RECHTS = 1, UNTEN = 2, LINKS = 3;
	private Labyrinth stepByStepLab;
	private Object lock;
	private Runnable r1;
	private Thread t1;
	Runnable r2;
	Thread t2;
	private boolean stepByStep = false;

	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */	
	
	public Labyrinth solveLab(Labyrinth originalLab) {
		setEnde(false);
		setStartTime(System.currentTimeMillis());
		setStepCounter(0);
		markieren(originalLab.getStart()[0], originalLab.getStart()[1],originalLab);
		
		rekursivLab(originalLab, originalLab.getStart()[0], originalLab.getStart()[1]);
		
		setEndTime(System.currentTimeMillis());
		setStepFinalWay(zielWeg(originalLab));
		setEnde(true);
		return originalLab;
	}
	
	public boolean rekursivLab(Labyrinth originalLab, int x, int y){
		int schritt = -1;
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
 
				if (!istZiel(neuX, neuY, originalLab)) {
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
	
	 private static boolean istZiel(int neuX, int neuY, Labyrinth originalLab) { 
		 if(neuX==originalLab.getEnde()[0] && neuY==originalLab.getEnde()[1]){
			 return true;
		 }
		 return false;
	}
	 
	 public int zielWeg(Labyrinth originalLab) {
		 int step = 0;
		 for (int i = 0; i < originalLab.getBreite(); i++) {
			for (int j = 0; j < originalLab.getLaenge(); j++) {
				if (originalLab.getChar(i, j)=='m'){
					step++;
				}
			}
		}
		 
	return step;
	}
	
	public void markieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		setEndTime(System.currentTimeMillis());
		originalLab.setChar(x, y, 'm');

		if (stepByStep){
			synchronized(this){
				originalLab.setChar(x, y, 'm');
			}
			try {
				lock.notify();
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void demarkieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		setEndTime(System.currentTimeMillis());
		originalLab.setChar(x, y, 'x');

		if (stepByStep){
			synchronized(this){
				originalLab.setChar(x, y, 'x');
			}
			try {
				lock.notify();
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void startStepByStep(Labyrinth lab) {
		// TODO Auto-generated method stub
		this.stepByStep = true;
		this.stepByStepLab = lab;
		lock = new Object();
		r1 = new MyRunnableOne();
		t1 = new Thread(r1);
		t1.start();
	}

	@Override
	public Labyrinth nextStep() {
		if (!isEnde()){
			r2 = new MyRunnableTwo();
			t2 = new Thread(r2);
			t2.start();
			long waitMillis = 500; // 0.5 Sekunden
			try {
				t2.join(waitMillis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (t1.isAlive()){
				if (t2.isAlive()|| isEnde()) {
				   System.out.println("Fertig, oder 0.5 Sek abgelaufen.");
				   return null;// Die 5 Sekunden sind um; der Thread läuft noch
				} else {
				   return stepByStepLab;// Thread ist beendet
				}
				
				} else {return stepByStepLab;}
			} else{return stepByStepLab;}
	}

public class MyRunnableOne implements Runnable {

	@Override
	public void run() {
		synchronized (lock) {
			try {
				lock.notify();
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			solveLab(stepByStepLab);
		}
	}
}

public class MyRunnableTwo implements Runnable {

	@Override
	public void run() {
		synchronized (lock) {
			for (int i2 = 0; i2 < 1; i2++) {
				try {
					lock.notify();
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

}