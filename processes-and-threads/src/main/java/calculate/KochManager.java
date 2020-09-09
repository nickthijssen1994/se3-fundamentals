/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import gui.KochFractalMain;
import timeutil.TimeStamp;

import java.util.ArrayList;

/**
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */
public class KochManager {

    private final KochFractal koch;
    private ArrayList<Edge> edges;
    private KochFractalMain application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;

    private int finishedThreadsCounter = 0;

    public KochManager(KochFractalMain application) {
        this.edges = new ArrayList<>();
        this.koch = new KochFractal(this);
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
    }

    public void changeLevel(int nxt) {
        edges.clear();
        koch.setLevel(nxt);
        tsCalc.init();

        startCalculatorThreads();
    }

    private void startCalculatorThreads() {
        finishedThreadsCounter = 0;

        SideEdgesCalculator leftSideEdgesCalculator = new SideEdgesCalculator(Side.LEFT, koch, this);
        SideEdgesCalculator bottomSideEdgesCalculator = new SideEdgesCalculator(Side.BOTTOM, koch, this);
        SideEdgesCalculator rightSideEdgesCalculator = new SideEdgesCalculator(Side.RIGHT, koch, this);

        Thread leftSideEdgesThread = new Thread(leftSideEdgesCalculator);
        Thread bottomSideEdgesThread = new Thread(bottomSideEdgesCalculator);
        Thread rightSideEdgesThread = new Thread(rightSideEdgesCalculator);

        tsCalc.setBegin("Begin calculating");

        leftSideEdgesThread.start();
        bottomSideEdgesThread.start();
        rightSideEdgesThread.start();

    }

    public void incrementFinishedThreadsCounter() {
        finishedThreadsCounter++;
        if (finishedThreadsCounter == 3) {
            endCalculation();
        }
    }

    private void endCalculation() {
        tsCalc.setEnd("End calculating");
        application.setTextNrEdges("" + koch.getNrOfEdges());
        application.setTextCalc(tsCalc.toString());
        application.requestDrawEdges();
    }

    public void drawEdges() {
        tsDraw.init();
        tsDraw.setBegin("Begin drawing");
        application.clearKochPanel();
        for (Edge e : edges) {
            application.drawEdge(e);
        }
        tsDraw.setEnd("End drawing");
        application.setTextDraw(tsDraw.toString());
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }
}
