package algorithmus;

import java.util.List;
import java.util.Vector;

import labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * AStar: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Die Klasse AStar implementiert ein Labyrinth Lösungsalgorithmus der nach dem A*
 * Verfahren funktioniert. 
 * Der A* Algorithmus startet beim Startpunkt und analysiert von diesem alle erreichbaren Nachbarfelder.
 * Er speichert diese in einer "offenen" Liste zusammen mit einer abschätzung des Abstandes bis
 * zum Ziel. Das Ziel ist dem Algorithmus bekannt und er berechnet den Abstand dann in unserem Beispiel
 * entweder mit der Manhattan Distanz, oder der Luftlinie.
 * Unsere Version des A* Algorithmus kann zusätzlich noch unterscheiden, ob er den bisherigen Weg 
 * vom Start zum analysierenden Punkt mit einberechnet, oder nur auf den geschätzten Abstand 
 * zum Ziel schaut.
 * Ohne den bisherigen Weg zu berücksichtigen findet der A* nicht zwingend den idealen Weg, aber es
 * die Unterschiede sind dennoch spannend anzusehen. 
 * Beim offiziellen A* Algorithmus, also mit einbeziehen des bisherigen Weges, wird IMMER der idealste
 * Weg gefunden.
 * --------------------------------------------------------------------------------------------- */

public class AStar extends Algorithmus {
	
	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	private String abstandTyp;
	private boolean entfernungStart;
	private List<AStarKnoten> listeOffen;
	private List<AStarKnoten> listeGeschlossen;
	
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
	 * Methode: AStar (Konstruktor)
	 *  
	 * Author: Reto Huber
	 * 
	 * dem Konstruktor wird übergeben um welche Art A* Algorithmus es sich handelt. 
	 * Also ob er mit der Manhattan Distanz oder der Luftlinie den Abstand berechnen soll und
	 * ob der bisherige Weg vom Start zum aktuellen Punkt berücksichtigt wird, oder nicht.
	 * 
	 * --------------------------------------------------------------------------------------------- */
	public AStar(String abstandTyp, boolean entfernungStart){
		this.abstandTyp = abstandTyp;
		this.entfernungStart = entfernungStart;
	}
	
	
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
		setStartTime(System.currentTimeMillis());
		setStepCounter(0);
		
		//hier wird die Methode aufgerufen die den eigentlichen Algorithmus enthält.
		doWork(originalLab);
		
		setEndTime(System.currentTimeMillis());
		setStepFinalWay(zielWeg(originalLab));
		setEnde(true);
		return originalLab;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: doWork
	 *  
	 * Author: Reto Huber
	 * 
	 * doWork ist für die eigentliche Arbeit des Algorithmus zuständig. Sie löst das Labyrinth 
	 * mit dem A* Algorithmus.
	 * 
	 * --------------------------------------------------------------------------------------------- */
	public boolean doWork(Labyrinth lab){
		listeOffen = new Vector<AStarKnoten>();
		listeGeschlossen = new Vector<AStarKnoten>();
		AStarKnoten knoten = new AStarKnoten(null,lab.getStart()[0],lab.getStart()[1],abstand(lab.getStart()[0],lab.getStart()[1],lab.getEnde()),entfernungStart);
		
		listeOffen.add(knoten);
		
		while (!listeOffen.isEmpty()){	//solange offene Liste nicht leer ist
			AStarKnoten kno = getBester(listeOffen);
			listeOffen.remove(kno);
			Vector<AStarKnoten> moeglich = new Vector<AStarKnoten>();
			
			int aktX = kno.getX();
			int aktY = kno.getY();
			
			try{
				if(lab.getChar(aktX-1, aktY)=='1'){			//links
					AStarKnoten kno2 = new AStarKnoten(kno,aktX-1,aktY,abstand(aktX-1,aktY,lab.getEnde()),entfernungStart);
					moeglich.add(kno2);
					markierenOffen(aktX-1,aktY, lab);
				}
			} catch (ArrayIndexOutOfBoundsException aie){}  //Feld zu ende
			try{
				if(lab.getChar(aktX+1, aktY)=='1'){			//rechts
					AStarKnoten kno2 = new AStarKnoten(kno,aktX+1,aktY,abstand(aktX+1,aktY,lab.getEnde()),entfernungStart);
					moeglich.add(kno2);					
					markierenOffen(aktX+1,aktY, lab);
				}
			} catch (ArrayIndexOutOfBoundsException aie){}  //Feld zu ende
			try{
				if(lab.getChar(aktX, aktY-1)=='1'){			//oben
					AStarKnoten kno2 = new AStarKnoten(kno,aktX,aktY-1,abstand(aktX,aktY-1,lab.getEnde()),entfernungStart);
					moeglich.add(kno2);
					markierenOffen(aktX,aktY-1, lab);
				}
			} catch (ArrayIndexOutOfBoundsException aie){}  //Feld zu ende
			try{
				if(lab.getChar(aktX, aktY+1)=='1'){			//unten
					AStarKnoten kno2 = new AStarKnoten(kno,aktX,aktY+1,abstand(aktX,aktY+1,lab.getEnde()),entfernungStart);
					moeglich.add(kno2);
					markierenOffen(aktX,aktY+1, lab);
				}
			} catch (ArrayIndexOutOfBoundsException aie){}  //Feld zu ende
			
			for(AStarKnoten n : moeglich){
				if (n.getX()==lab.getEnde()[0] && n.getY()==lab.getEnde()[1]){
					AStarKnoten k = n;
					while (k != null){
						markierenFertig(k.getX(), k.getY(), lab);
						k=k.getParent();
					}
					
					//am ziel
					return true;
				}
				listeOffen.add(n);
			}
			
			listeGeschlossen.add(kno);
			markierenGeschlossen(kno.getX(),kno.getY(), lab);
		} return false; //kein Ende gefunden
		
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: getBester
	 *  
	 * Author: Reto Huber
	 * 
	 * gibt den Knoten mit dem kürzesten geschätztem Gesamtwert aus einer Liste heraus zurück.
	 * --------------------------------------------------------------------------------------------- */
	public AStarKnoten getBester(List<AStarKnoten> list){
		AStarKnoten bester = null;
		for (AStarKnoten n:list) {
			if (bester == null || n.getF()<bester.getF() ){
				bester = n;
			}
		}
		return bester;
	}
	
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: abstand
	 *  
	 * Author: Reto Huber
	 * 
	 * Ruft die entpsrechende Methode zur Berechnung des Abstandes auf, je nachdem ob der Algorithmus
	 * mit der Manhattan Distanz oder Luftlinie arbeitet.
	 * --------------------------------------------------------------------------------------------- */
	public float abstand(int x, int y, int[] ziel){
		if (abstandTyp.equals("Luftlinie")){
			return abstandLuftLinie(x,y,ziel);
		} else if (abstandTyp.equals("Manhattan")){
			return abstandManhattan(x,y,ziel);
		}else{
			return 0;
		}
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: abstandLuftlinie
	 *  
	 * Author: Reto Huber
	 * 
	 * berechnet den Abstand eines Punktes zum Ziel anhand der Luftlinie.
	 * --------------------------------------------------------------------------------------------- */
	public float abstandLuftLinie(int x, int y, int[] ziel){
		int a = Math.abs(ziel[0] - x);
		int b = Math.abs(ziel[1] - y);
		return (float)Math.sqrt((a*a) + (b*b));
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: abstandManhattan
	 *  
	 * Author: Reto Huber
	 * 
	 * berechnet den Abstand eines Punktes zum Ziel anhand der Manhattan Distanz.
	 * --------------------------------------------------------------------------------------------- */
	public float abstandManhattan(int x, int y, int[] ziel){
		int a = Math.abs(ziel[0] - x);
		int b = Math.abs(ziel[1] - y);
		return a+b;
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
	 * Methode: markierenFertig
	 *  
	 * Author: Reto Huber
	 * 
	 * Markiert einen Punkt im Labyrinth "grün". Also als Punkt für den finalen Weg.
	 * 
	 * --------------------------------------------------------------------------------------------- */
	public void markierenFertig(int x, int y, Labyrinth originalLab){
		originalLab.setChar(x, y, 'm');
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: markierenOffen
	 *  
	 * Author: Reto Huber
	 * 
	 * Markiert einen Punkt im Labyrinth "dunkelgrau". Also nicht als Punkt für den finalen Weg, aber
	 * als Punkt der zur Analyse bereit steht. Somit ist er in der offenen Liste.
	 * --------------------------------------------------------------------------------------------- */
	public void markierenOffen(int x, int y, Labyrinth originalLab){
		originalLab.setChar(x, y, 'o');
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: markierenGeschlossen
	 *  
	 * Author: Reto Huber
	 * 
	 * Markiert einen Punkt im Labyrinth "hellgrau". Also nicht als Punkt für den finalen Weg, aber
	 * als Punkt der besucht wurde und vollständig analysiert. Somit ist er in der geschlossenen Liste.
	 * --------------------------------------------------------------------------------------------- */
	public void markierenGeschlossen(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		setEndTime(System.currentTimeMillis());
		originalLab.setChar(x, y, 'g');

		/*
		 * dieser Abschnitt ist für den StepByStep Modus. Wenn dieser aktiv ist, 
		 * wird hier der zweite Thread aktiviert, so dass es eine Pause gibt und
		 * es erst beim nächsten Klick auf den "NextStep"-Button, oder wenn die Wartezeit
		 * abgelaufen ist, weiter geht.
		 */
		if (stepByStep){
			synchronized(this){
				originalLab.setChar(x, y, 'g');
			}
			try {
				lock.notify();
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: toString
	 *  
	 * Author: Reto Huber
	 * 
	 * Die toString Methode wurde überschrieben, so dass sie die Art des A*-Algorithmus zurück gibt.
	 * Dies wird unter anderem für die Erstellung der Statistiken im GUI benötigt.
	 * 
	 * --------------------------------------------------------------------------------------------- */
 	
	public String toString() {
		String classname = this.getClass().getName();
		int mid = classname.lastIndexOf ('.') + 1;
		String entfernung = "";
		if(entfernungStart){
			entfernung = "mit Weg";
		}
		return (classname.substring(mid)+" " + abstandTyp + " " + entfernung);
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
				e.printStackTrace();
			}
			if (t1.isAlive()){
				if (t2.isAlive() || isEnde()) {
				   System.out.println("Fertig, oder 0.5 Sek abgelaufen.");
				   return null;// Die 0.5 Sekunden sind um; der Thread läuft noch
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
						e.printStackTrace();
					}
				}
			}
		}

	}


}
