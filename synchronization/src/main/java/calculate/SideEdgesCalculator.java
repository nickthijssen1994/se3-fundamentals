package calculate;

import java.util.ArrayList;

public class SideEdgesCalculator implements Runnable {

	private Side side;
	private ArrayList<Edge> sideEdges;
	private KochManager kochManager;
	private KochFractal kochFractal;

	public SideEdgesCalculator(Side side, KochManager kochManager) {
		this.side = side;
		this.sideEdges = new ArrayList<>();
		this.kochManager = kochManager;
		this.kochFractal = new KochFractal(this);
	}

	public void setLevel(int level) {
		kochFractal.setLevel(level);
	}

	public void addEdge(Edge edge) {
		sideEdges.add(edge);
	}

	@Override
	public void run() {

		sideEdges.clear();
		System.out.println(side.toString() + " Calculator Started");
		switch (side) {
			case LEFT:
				kochFractal.generateLeftEdge();
				break;
			case BOTTOM:
				kochFractal.generateBottomEdge();
				break;
			case RIGHT:
				kochFractal.generateRightEdge();
				break;
		}
		System.out.println(side.toString() + " Calculator Finished");
		kochManager.addEdges(sideEdges);
	}

	public void stop() {
		this.kochFractal.cancel();
	}
}
