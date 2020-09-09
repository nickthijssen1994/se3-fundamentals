/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import fun3kochfractalfx.FUN3KochFractalFX;
import timeutil.TimeStamp;

import java.util.ArrayList;

/**
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */
public class KochManager {

    private KochFractal koch;
    private ArrayList<Edge> edges;
    private FUN3KochFractalFX application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;

    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<Edge>();
        this.koch = new KochFractal(this);
        this.application = application;
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
        application.setTextNrEdges("" + koch.getNrOfEdges());
        application.setTextCalc(tsCalc.toString());
        drawEdges();
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
