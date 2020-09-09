/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import gui.KochFractalMain;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KochManager {

	private final ExecutorService threadPool = Executors.newFixedThreadPool(3);
	private ArrayList<Edge> edges = new ArrayList<>();
	private KochFractalMain application;
	private TimeStamp tsCalc = new TimeStamp();
	private TimeStamp tsDraw = new TimeStamp();
	private int finishedTasksCounter = 0;
	private List<SideEdgesCalculationTask> tasks = new ArrayList<>();

	public KochManager(KochFractalMain application) {
		this.application = application;
	}

	public void changeLevel(int level) {

		cancelRunningTasks(); // Necessary when Increase/Decrease button is pressed during calculation

		tasks.clear();
		for (Side side : Side.values()) {
			SideEdgesCalculationTask task = new SideEdgesCalculationTask(side);
			task.setLevel(level);
			application.getProgressBar(side).progressProperty().bind(task.progressProperty());
			application.getProgressLabel(side).textProperty().bind(task.messageProperty());
			task.setOnSucceeded(t -> {
				notifyTaskIsFinished();
			});
			tasks.add(task);
		}

		edges.clear();
		System.out.println();
		System.out.println("Calculation Started");
		tsCalc.init();
		tsCalc.setBegin("Begin calculating");

		for (SideEdgesCalculationTask task : tasks) {
			threadPool.submit(task);
		}
	}

	private void stopTasks() {
		tsCalc.setEnd("End calculating");
		System.out.println("Calculation Finished");
		System.out.println();
		for (SideEdgesCalculationTask task : tasks) {
			try {
				edges.addAll(task.get());
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		application.setTextNrEdges(Integer.toString(edges.size()));
		application.setTextCalc(tsCalc.toString());
		application.requestDrawEdges();
	}

	private void notifyTaskIsFinished() {
		finishedTasksCounter++;
		if (finishedTasksCounter == 3) {
			stopTasks();
		}
	}

	public void drawEdges() {
		tsDraw.init();
		application.clearKochPanel();
		tsDraw.setBegin("Begin drawing");
		for (Edge e : edges) {
			application.drawEdge(e);
		}
		tsDraw.setEnd("End drawing");
		application.setTextDraw(tsDraw.toString());
	}

	public void cancelRunningTasks() {
		for (SideEdgesCalculationTask task : tasks) {
			if (task.isRunning()) {
				task.cancel();
			}
		}
		finishedTasksCounter = 0;
	}

	public void stopThreadPool() {
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
			}
		}
		catch (InterruptedException ie) {
			threadPool.shutdownNow();
		}
	}
}
