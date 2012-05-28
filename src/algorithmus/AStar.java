package algorithmus;

import java.util.List;
import java.util.Vector;

import labyrinth.Labyrinth;

public class AStar extends Algorithmus {
	
	private String abstandTyp;
	private boolean entfernungStart;
	private List<AStarKnoten> listeOffen;
	private List<AStarKnoten> listeGeschlossen;

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
		System.out.println("zielx: "+ ziel[0]);
		System.out.println("ziely: "+ ziel[1]);
		System.out.println("x: "+ x);
		System.out.println("y: "+ y);
		return a+b;
	}
	
	public void markieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'm');
		//originalLab.zeichnen();
		// currentLabyrinth[y][x]='m';
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
	}
	
//	public void stepByStep(){
//		if (step1.equals("S")) {
//			scanner.nextLine();
//		} else if (step1.equals("A")) {
//			//nichts
//		} else {
//			System.out.println("falsche eingabe");
//			System.exit(0);
//		}
//	}

}
