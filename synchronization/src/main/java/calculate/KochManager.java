/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import gui.KochFractalMain;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */
public class KochManager {

    private ArrayList<Edge> edges;
    private KochFractalMain application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;

    private ArrayList<SideEdgesCalculator> sideEdgesCalculators;
    private ArrayList<Thread> sideEdgesThreads;

    private int finishedThreadsCounter = 0;

    public KochManager(KochFractalMain application) {
        this.edges = new ArrayList<>();
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
        this.sideEdgesCalculators = new ArrayList<>();
        this.sideEdgesThreads = new ArrayList<>();
        initializeThreads();
    }

    private void initializeThreads() {
        for (Side side : Side.values()) {
            SideEdgesCalculator sideEdgesCalculator = new SideEdgesCalculator(side, this);
            sideEdgesCalculators.add(sideEdgesCalculator);
        }
    }

    public void changeLevel(int level) {
        edges.clear();
        tsCalc.init();

        for (SideEdgesCalculator sideEdgesCalculator : sideEdgesCalculators) {
            sideEdgesCalculator.stop();
            sideEdgesCalculator.setLevel(level);
        }
        sideEdgesThreads.clear();
        for (SideEdgesCalculator sideEdgesCalculator : sideEdgesCalculators) {
            sideEdgesThreads.add(new Thread(sideEdgesCalculator));
        }

        finishedThreadsCounter = 0;
        tsCalc.setBegin("Begin calculating");

        for (Thread sideEdgesThread : sideEdgesThreads) {
            sideEdgesThread.start();
        }
    }

    private void endCalculation() {
        tsCalc.setEnd("End calculating");
        application.setTextNrEdges("" + edges.size());
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

    public synchronized void addEdges(List<Edge> edges) {

        this.edges.addAll(edges);

        finishedThreadsCounter++;
        if (finishedThreadsCounter == 3) {
            endCalculation();
        }
    }
}
