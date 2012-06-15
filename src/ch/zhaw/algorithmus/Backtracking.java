package ch.zhaw.algorithmus;

import ch.zhaw.labyrinth.Labyrinth;

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

	/* ---------------------------------------------------------------------------------------------
	 * Methode: solveLab
	 *  
	 * Author: Reto Huber
	 * 
	 * solveLab wird vom Handler aufgerufen um das Labyrinth sofort lösen zu lassen. Es wird also 
	 * als Rückgabewert das gelöste Labyrinth erwartet. Zuerst werden die für die Statistik benötigten
	 * Variablen für Zeit und Schritte gesetzt, dann das Lösen an sich gestartet und am Schluss wieder
	 * die Statistik variablen angepasst und das gelöste Labyrinth zurückgegeben.
	 * Auch der StepByStep Modus greifft auf diese Methode zu.
	 * --------------------------------------------------------------------------------------------- */
	public Labyrinth solveLab(Labyrinth originalLab) {
		setEnde(false);
		setStartTime(System.currentTimeMillis());
		setStepCounter(0);
		markieren(originalLab.getStart()[0], originalLab.getStart()[1],originalLab);
		
		//hier wird die Methode aufgerufen die den eigentlichen Algorithmus enthält.
		rekursivLab(originalLab, originalLab.getStart()[0], originalLab.getStart()[1]);
		
		setEndTime(System.currentTimeMillis());
		setStepFinalWay(zielWeg(originalLab));
		setEnde(true);
		return originalLab;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: rekursivLab
	 *  
	 * Author: Reto Huber
	 * 
	 * rekursivLab ist für die eigentliche Arbeit des Algorithmus zuständig. Sie wird rekursiv
	 * aufgerufen und löst dadurch das Labyrinth mit dem Backtracking Algorithmus.
	 * Die Methode wird rekursiv aufgerufen, bis das Ziel gefunden wurde.
	 * 
	 * --------------------------------------------------------------------------------------------- */
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
					// rekursiver Aufruf von rekursivLab
					if (rekursivLab(originalLab, neuX, neuY)) {
						// Lösung gefunden	
						return true;
					} else {
						//Sackgasse und demarkieren
						demarkieren(neuX,neuY,originalLab);					
					}
				} else return true; 		
			}
			
			
		}
		return false;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: istZiel
	 *  
	 * Author: Reto Huber
	 * 
	 * Diese Hilfsmethode prüft, ob der Algorithmus bereits am Ziel angekommen ist.
	 * --------------------------------------------------------------------------------------------- */
 	
	 private static boolean istZiel(int neuX, int neuY, Labyrinth originalLab) { 
		 if(neuX==originalLab.getEnde()[0] && neuY==originalLab.getEnde()[1]){
			 return true;
		 }
		 return false;
	}
	 
		/* ---------------------------------------------------------------------------------------------
		 * Methode: zielWeg
		 *  
		 * Author: Reto Huber
		 * 
		 * zielWeg wird am Schluss aufgerufen, nachdem der Backtracking Algorithmus das Ziel gefunden hat
		 * und gibt die Länge des gefundenen Weges zurück.
		 * --------------------------------------------------------------------------------------------- */
	 	
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
	 
		/* ---------------------------------------------------------------------------------------------
		 * Methode: markieren
		 *  
		 * Author: Reto Huber
		 * 
		 * Markiert einen Punkt im Labyrinth "grün". Also als Punkt für den finalen Weg.
		 * --------------------------------------------------------------------------------------------- */
	 	 
	
	public void markieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		setEndTime(System.currentTimeMillis());
		originalLab.setChar(x, y, 'm');

		/*
		 * dieser Abschnitt ist für den StepByStep Modus. Wenn dieser aktiv ist, 
		 * wird hier der zweite Thread aktieviert, so dass es eine Pause gibt und
		 * es erst beim nächsten Klick auf den "NextStep"-Button, oder wenn die Wartezeit
		 * abgelaufen ist, weiter geht.
		 */
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
	

	/* ---------------------------------------------------------------------------------------------
	 * Methode: demarkieren
	 *  
	 * Author: Reto Huber
	 * 
	 * Markiert einen Punkt im Labyrinth "rot". Also nicht als Punkt für den finalen Weg, aber
	 * als Punkt der besucht wurde und in einer Sackgasse endete.
	 * --------------------------------------------------------------------------------------------- */
 	 
	public void demarkieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		setEndTime(System.currentTimeMillis());
		originalLab.setChar(x, y, 'x');
		
		/*
		 * dieser Abschnitt ist für den StepByStep Modus. Wenn dieser aktiv ist, 
		 * wird hier der zweite Thread aktieviert, so dass es eine Pause gibt und
		 * es erst beim nächsten Klick auf den "NextStep"-Button, oder wenn die Wartezeit
		 * abgelaufen ist, weiter geht.
		 */
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

	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: startStepByStep
	 *  
	 * Author: Reto Huber
	 * 
	 * Hier wird alles für den StepByStep Modus vorbereitet. Auch der automatische Schritt
	 * für Schritt Modus (mit Angabe von Geschwindigkeit) wird damit gestartet.
	 * Unser Schritt für Schritt Modus haben wir so eingerichtet, dass die normale solveLab
	 * Methode aufgerufen wird. Dass nicht das ganze Labyrinth sofort gelöst wird, regeln zwei
	 * Threads die sich gegenseitig durch wait und notify abwechseln. Thread macht einen Schritt 
	 * im Labyrinth weiter, Thread 2 macht nichts, ausser zu warten und somit den anderen zu unterbrechen.
	 * 
	 * In dieser Methode wird der Thread 1 eröffnet und gestartet.
	 * --------------------------------------------------------------------------------------------- */
 	
	public void startStepByStep(Labyrinth lab) {
		// TODO Auto-generated method stub
		this.stepByStep = true;
		this.stepByStepLab = lab;
		lock = new Object();
		r1 = new MyRunnableOne();
		t1 = new Thread(r1);
		t1.start();
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: nextStep
	 *  
	 * Author: Reto Huber
	 * 
	 * In der nextStep Methode wird nun bei jedem Aufruf ein neuer Thread2 erstellt, welcher dem wartenden
	 * Thread1 das ok gibt um einen Schritt zu machen und dann wieder beendet wird, sobald Thread 1 wieder
	 * im wartenden Zustand ist.
	 * 
	 * --------------------------------------------------------------------------------------------- */
 	
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

	/* ---------------------------------------------------------------------------------------------
	 * MyRunnableOne: Beschreibung der Klasse
	 * ---------------------------------------------
	 * 
	 * Diese Klasse ist für den Thread1 des StepByStep Modus. Sie startet die Methode solveLab
	 * und wartet dann nach jedem markieren oder demarkieren (also nach jedem Schritt) auf ein
	 * neues notify, welche sie von dem Thread2 bekommt, welcher in der nextStep Methode gestartet
	 * wird.
	 * 
	 * --------------------------------------------------------------------------------------------- */

	public void run() {
		synchronized (lock) {
			try {
				lock.notify();
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//starten des Lösungsalgorithmus
			solveLab(stepByStepLab);
		}
	}
}

public class MyRunnableTwo implements Runnable {

	/* ---------------------------------------------------------------------------------------------
	 * MyRunnableTwo: Beschreibung der Klasse
	 * ---------------------------------------------
	 * 
	 * Diese Klasse ist für den Thread2 des StepByStep Modus. Sie dient lediglich dazu, den 
	 * Thread1 einen Schritt weiter zu bringen, indem sie ihn durch notify aus dem Wartezustand
	 * bringt.
	 * 
	 * --------------------------------------------------------------------------------------------- */

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