package calculate;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class SideEdgesCalculationTask extends Task<ArrayList<Edge>> implements Observer {

    private Side side;
    private ArrayList<Edge> sideEdges;
    private long nrEdgesToCalculate;
    private KochFractal kochFractal;

    public SideEdgesCalculationTask(Side side) {
        this.side = side;
        sideEdges = new ArrayList<>();
        kochFractal = new KochFractal();
        kochFractal.addObserver(this);
    }

    @Override
    protected ArrayList<Edge> call() throws Exception {
        sideEdges.clear();
        nrEdgesToCalculate = kochFractal.getNrOfEdges() / 3;
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
        return sideEdges;
    }

    public void setLevel(int level) {
        kochFractal.setLevel(level);
    }

    @Override
    public void update() {
        if (isCancelled()) {
            kochFractal.cancel();
            updateMessage("Edges Calculated: " + sideEdges.size() + " CANCELLED");
            updateProgress(sideEdges.size(), nrEdgesToCalculate);
        } else {
            sideEdges.add(kochFractal.getCalculatedEdge());
            updateMessage("Edges Calculated: " + sideEdges.size());
            updateProgress(sideEdges.size(), nrEdgesToCalculate);
        }

        //For Debugging or Visualization
        try {
            Thread.sleep(0, 1);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
