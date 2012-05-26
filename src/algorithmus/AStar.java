package algorithmus;

import java.util.List;
import java.util.Vector;

import labyrinth.Labyrinth;

public class AStar extends Algorithmus {
	
	private List<AStarKnoten> listeOffen;
	private List<AStarKnoten> listeGeschlossen;

	@Override
	public Labyrinth solveLab(Labyrinth originalLab) {
		setStartTime(System.currentTimeMillis());
		markieren(originalLab.getStart()[0], originalLab.getStart()[1],originalLab);
		
		doWork(originalLab);
		//din CODE! Falls au rekursiv, bruchts evtl au e neui Methode, siehe Backtracking.
		
		setEndTime(System.currentTimeMillis());
		return originalLab;
	}
	
	public void doWork(Labyrinth lab){
		listeOffen = new Vector<AStarKnoten>();
		listeGeschlossen = new Vector<AStarKnoten>();
		AStarKnoten knoten = new AStarKnoten(null,lab.getStart()[0],lab.getStart()[1],abstandLuftLinie(lab.getStart()[0],lab.getStart()[1],lab.getEnde()));
		System.out.println("Luftlinie: " + abstandLuftLinie(lab.getStart()[0],lab.getStart()[1],lab.getEnde()));
		System.out.println("Manhattan: " + abstandManhattan(lab.getStart()[0],lab.getStart()[1],lab.getEnde()));
		
		listeOffen.add(knoten);
		
		while (listeOffen.isEmpty()){	//solange offene Liste nicht leer ist
			AStarKnoten kno = getBester(listeOffen);
			listeOffen.remove(kno);
			Vector<AStarKnoten> moeglich = new Vector<AStarKnoten>();
			//markierenOffen(kno.getX(), kno.getY(), lab);
			
		}
		
	}
	
	public AStarKnoten getBester(List<AStarKnoten> list){
		AStarKnoten bester = null;
		for (AStarKnoten n:list) {
			if (bester == null || n.getH()<bester.getH() ){
				bester = n;
			}
		}
		return bester;
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
		originalLab.zeichnen();
		// currentLabyrinth[y][x]='m';
	}
	public void demarkieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'x');
		originalLab.zeichnen();
		// currentLabyrinth[y][x]='x';
	}
	public void markierenOffen(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'x');
		originalLab.zeichnen();
		// currentLabyrinth[y][x]='x';
	}
	public void markierenGeschlossen(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'y');
		originalLab.zeichnen();
		// currentLabyrinth[y][x]='x';
	}

}
