package calculate;

public class SideEdgesCalculator implements Runnable {

	private Side side;
	private KochManager kochManager;
	private KochFractal kochFractal;

	public SideEdgesCalculator(Side side, KochFractal kochFractal, KochManager kochManager) {
		this.side = side;
		this.kochManager = kochManager;
		this.kochFractal = kochFractal;
	}

	@Override
	public void run() {
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
		kochManager.incrementFinishedThreadsCounter();
	}
}
