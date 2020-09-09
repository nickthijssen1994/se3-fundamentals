package calculate;

import gui.Controller;
import timeutil.TimeStamp;

import java.util.ArrayList;

public class KochManager {

	private KochFractal koch;
	private ArrayList<Edge> edges;
	private Controller controller;
	private TimeStamp tsCalc;
	private TimeStamp tsDraw;

	public KochManager(Controller controller) {
		this.edges = new ArrayList<Edge>();
		this.koch = new KochFractal(this);
		this.controller = controller;
		this.tsCalc = new TimeStamp();
		this.tsDraw = new TimeStamp();
	}

	public void changeLevel(int nxt) {
		edges.clear();
		koch.setLevel(nxt);
		tsCalc.init();
		tsCalc.setBegin("Begin calculating");
		koch.generateLeftEdge();
		koch.generateBottomEdge();
		koch.generateRightEdge();
		tsCalc.setEnd("End calculating");
		controller.setTextNrEdges("" + koch.getNrOfEdges());
		controller.setTextCalc(tsCalc.toString());
		drawEdges();
	}

	public void drawEdges() {
		tsDraw.init();
		tsDraw.setBegin("Begin drawing");
		controller.clearKochPanel();
		for (Edge e : edges) {
			controller.drawEdge(e);
		}
		tsDraw.setEnd("End drawing");
		controller.setTextDraw(tsDraw.toString());
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}
}
