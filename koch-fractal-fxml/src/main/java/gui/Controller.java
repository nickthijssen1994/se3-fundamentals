package gui;

import calculate.Edge;
import calculate.KochManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

	private static final int THRESHOLD = 200_000;
	private final int kpWidth = 500;
	private final int kpHeight = 500;
	private final WritableImage image = new WritableImage(kpWidth, kpHeight);
	private double zoomTranslateX = 0.0;
	private double zoomTranslateY = 0.0;
	private double zoom = 1.0;
	private double startPressedX = 0.0;
	private double startPressedY = 0.0;
	private double lastDragX = 0.0;
	private double lastDragY = 0.0;

	private KochManager kochManager;

	private int currentLevel = 1;

	@FXML
	private Label labelNumberOfEdges;
	@FXML
	private Label labelCalculating;
	@FXML
	private Label labelDrawing;
	@FXML
	private Label labelLevel;
	@FXML
	private Canvas kochPanel;

	private int counter = 0;

	@FXML
	public void initialize() {
		resetZoom();
		kochManager = new KochManager(this);
		kochManager.changeLevel(currentLevel);
	}

	public void clearKochPanel() {
		GraphicsContext gc = kochPanel.getGraphicsContext2D();
		gc.clearRect(0.0, 0.0, kpWidth, kpHeight);
		gc.setFill(Color.BLACK);
		gc.fillRect(0.0, 0.0, kpWidth, kpHeight);
		counter = 0;
	}

	public void drawEdge(Edge e) {
		// Graphics
		GraphicsContext gc = kochPanel.getGraphicsContext2D();

		// Adjust edge for zoom and drag
		Edge e1 = edgeAfterZoomAndDrag(e);

		// Set line color
		gc.setStroke(e1.color);

		// Set line width depending on level
		if (currentLevel <= 3) {
			gc.setLineWidth(2.0);
		}
		else if (currentLevel <= 5) {
			gc.setLineWidth(1.5);
		}
		else {
			gc.setLineWidth(1.0);
		}

		// Draw line
		gc.strokeLine(e1.X1, e1.Y1, e1.X2, e1.Y2);

		counter++;
		if (counter >= THRESHOLD) {
			kochPanel.snapshot(null, image);
			counter = 0;
		}
	}

	public void setTextNrEdges(String text) {
		labelNumberOfEdges.setText(text);
	}

	public void setTextCalc(String text) {
		labelCalculating.setText(text);
	}

	public void setTextDraw(String text) {
		labelDrawing.setText(text);
	}

	public void requestDrawEdges() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				kochManager.drawEdges();
			}
		});
	}

	@FXML
	private void increaseLevelButtonActionPerformed(ActionEvent event) {
		if (currentLevel < 12) {
			// resetZoom();
			currentLevel++;
			labelLevel.setText(String.valueOf(currentLevel));
			kochManager.changeLevel(currentLevel);
		}
	}

	@FXML
	private void decreaseLevelButtonActionPerformed(ActionEvent event) {
		if (currentLevel > 1) {
			// resetZoom();
			currentLevel--;
			labelLevel.setText(String.valueOf(currentLevel));
			kochManager.changeLevel(currentLevel);
		}
	}

	@FXML
	private void fitFractalButtonActionPerformed(ActionEvent event) {
		resetZoom();
		kochManager.drawEdges();
	}

	@FXML
	private void kochPanelMouseClicked(MouseEvent event) {
		if (Math.abs(event.getX() - startPressedX) < 1.0 && Math.abs(event.getY() - startPressedY) < 1.0) {
			double originalPointClickedX = (event.getX() - zoomTranslateX) / zoom;
			double originalPointClickedY = (event.getY() - zoomTranslateY) / zoom;
			if (event.getButton() == MouseButton.PRIMARY) {
				zoom *= 2.0;
			}
			else if (event.getButton() == MouseButton.SECONDARY) {
				zoom /= 2.0;
			}
			zoomTranslateX = (int) (event.getX() - originalPointClickedX * zoom);
			zoomTranslateY = (int) (event.getY() - originalPointClickedY * zoom);
			kochManager.drawEdges();
		}
	}

	@FXML
	private void kochPanelMouseDragged(MouseEvent event) {
		zoomTranslateX = zoomTranslateX + event.getX() - lastDragX;
		zoomTranslateY = zoomTranslateY + event.getY() - lastDragY;
		lastDragX = event.getX();
		lastDragY = event.getY();
		kochManager.drawEdges();
	}

	@FXML
	private void kochPanelMousePressed(MouseEvent event) {
		startPressedX = event.getX();
		startPressedY = event.getY();
		lastDragX = event.getX();
		lastDragY = event.getY();
	}

	private void resetZoom() {
		int kpSize = Math.min(kpWidth, kpHeight);
		zoom = kpSize;
		zoomTranslateX = (kpWidth - kpSize) / 2.0;
		zoomTranslateY = (kpHeight - kpSize) / 2.0;
	}

	private Edge edgeAfterZoomAndDrag(Edge e) {
		return new Edge(e.X1 * zoom + zoomTranslateX, e.Y1 * zoom + zoomTranslateY, e.X2 * zoom + zoomTranslateX, e.Y2 * zoom + zoomTranslateY, e.color);
	}
}
