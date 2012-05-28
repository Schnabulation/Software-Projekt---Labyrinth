package algorithmus;

import java.util.List;
import java.util.Vector;

import labyrinth.Labyrinth;

public class AStar extends Algorithmus {
	
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

	public AStar(String abstandTyp, boolean entfernungStart){
		this.abstandTyp = abstandTyp;
		this.entfernungStart = entfernungStart;
	}
	@Override
	public Labyrinth solveLab(Labyrinth originalLab) {
		setStartTime(System.currentTimeMillis());
		//markieren(originalLab.getStart()[0], originalLab.getStart()[1],originalLab);
		doWork(originalLab);
		
		//din CODE! Falls au rekursiv, bruchts evtl au e neui Methode, siehe Backtracking.
		
		setEndTime(System.currentTimeMillis());
		setEnde(true);
		return originalLab;
	}
	
	public boolean doWork(Labyrinth lab){
		listeOffen = new Vector<AStarKnoten>();
		listeGeschlossen = new Vector<AStarKnoten>();
		AStarKnoten knoten = new AStarKnoten(null,lab.getStart()[0],lab.getStart()[1],abstand(lab.getStart()[0],lab.getStart()[1],lab.getEnde()),entfernungStart);
		
		listeOffen.add(knoten);
		
		while (!listeOffen.isEmpty()){	//solange offene Liste nicht leer ist
			AStarKnoten kno = getBester(listeOffen);
			listeOffen.remove(kno);
			Vector<AStarKnoten> moeglich = new Vector<AStarKnoten>();
			//markierenOffen(kno.getX(), kno.getY(), lab);
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
						markieren(k.getX(), k.getY(), lab);
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
	
	public AStarKnoten getBester(List<AStarKnoten> list){
		AStarKnoten bester = null;
		for (AStarKnoten n:list) {
			if (bester == null || n.getF()<bester.getF() ){
				bester = n;
			}
		}
		return bester;
	}
	
	public float abstand(int x, int y, int[] ziel){
		if (abstandTyp.equals("Luftlinie")){
			return abstandLuftLinie(x,y,ziel);
		} else if (abstandTyp.equals("Manhattan")){
			return abstandManhattan(x,y,ziel);
		}else{
			return 0;
		}
	}
	
	public float abstandLuftLinie(int x, int y, int[] ziel){
		int a = Math.abs(ziel[0] - x);
		int b = Math.abs(ziel[1] - y);
		return (float)Math.sqrt((a*a) + (b*b));
	}
	
	public float abstandManhattan(int x, int y, int[] ziel){
		int a = Math.abs(ziel[0] - x);
		int b = Math.abs(ziel[1] - y);
		return a+b;
	}
	
	public void markieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'm');
		//originalLab.zeichnen();
		// currentLabyrinth[y][x]='m';
		if (stepByStep){
			synchronized(this){
				originalLab.setChar(x, y, 'm');
			}
			try {
				lock.notify();
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
//	public void demarkieren(int x, int y, Labyrinth originalLab){
//		increaseStepCounter();
//		originalLab.setChar(x, y, 'x');
//		originalLab.zeichnen();
//		// currentLabyrinth[y][x]='x';
//	}
	public void markierenOffen(int x, int y, Labyrinth originalLab){
		originalLab.setChar(x, y, 'o');
		//originalLab.zeichnen();
		// currentLabyrinth[y][x]='x';
	}
	public void markierenGeschlossen(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'g');
		//originalLab.zeichnen();
		// currentLabyrinth[y][x]='x';
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
	
	public String toString() {
		String classname = this.getClass().getName();
		int mid = classname.lastIndexOf ('.') + 1;
		String entfernung = "";
		if(entfernungStart){
			entfernung = "mit Weg";
		}
		return (classname.substring(mid)+" " + abstandTyp + " " + entfernung);
	}
	
	@Override
	public void startStepByStep(Labyrinth lab) {
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
			long waitMillis = 1000; // 5 Sekunden
			try {
				t1.join(10);
				t2.join(waitMillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (t1.isAlive()){
				if (t2.isAlive() || isEnde()) {
				   System.out.println("Fertig, oder 1 Sek abgelaufen.");
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
						e.printStackTrace();
					}
				}
			}
		}

	}


}
