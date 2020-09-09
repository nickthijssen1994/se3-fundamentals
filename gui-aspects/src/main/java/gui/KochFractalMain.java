package gui;

import calculate.Edge;
import calculate.KochManager;
import calculate.Side;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KochFractalMain extends Application {

    private static final int THRESHOLD = 100_000;
    private final int kpWidth = 500;
    private final int kpHeight = 500;
    private final WritableImage image = new WritableImage(kpWidth, kpHeight);
    // Zoom and drag
    private double zoomTranslateX = 0.0;
    private double zoomTranslateY = 0.0;
    private double zoom = 1.0;
    private double startPressedX = 0.0;
    private double startPressedY = 0.0;
    private double lastDragX = 0.0;
    private double lastDragY = 0.0;
    // Koch manager
    // TO DO: Create class KochManager in package calculate
    private KochManager kochManager;
    // Current level of Koch fractal
    private int currentLevel = 6;
    // Labels for level, nr edges, calculation time, and drawing time
    private Label labelLevel;
    private Label labelNrEdges;
    private Label labelNrEdgesText;
    private Label labelCalc;
    private Label labelCalcText;
    private Label labelDraw;
    private Label labelDrawText;
    private Label labelProgressLeft;
    private ProgressBar progressBarLeft;
    private Label labelProgressLeftText;
    private Label labelProgressBottom;
    private ProgressBar progressBarBottom;
    private Label labelProgressBottomText;
    private Label labelProgressRight;
    private ProgressBar progressBarRight;
    private Label labelProgressRightText;
    // Koch panel and its size
    private Canvas kochPanel;
    // counter for snapshot and its threshold (fixes rendering issue)
    private int counter = 0;

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Define grid pane
        GridPane grid;
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // For debug purposes
        // Make de grid lines visible
        // grid.setGridLinesVisible(true);

        // Drawing panel for Koch fractal
        kochPanel = new Canvas(kpWidth, kpHeight);
        grid.add(kochPanel, 0, 3, 22, 1);
        clearKochPanel();

        // Labels to present number of edges for Koch fractal
        labelNrEdges = new Label("Nr edges:");
        labelNrEdgesText = new Label();
        grid.add(labelNrEdges, 0, 0, 4, 1);
        grid.add(labelNrEdgesText, 3, 0, 22, 1);

        // Labels to present time of calculation for Koch fractal
        labelCalc = new Label("Calculating:");
        labelCalcText = new Label();
        grid.add(labelCalc, 0, 1, 4, 1);
        grid.add(labelCalcText, 3, 1, 22, 1);

        // Labels to present time of drawing for Koch fractal
        labelDraw = new Label("Drawing:");
        labelDrawText = new Label();
        grid.add(labelDraw, 0, 2, 4, 1);
        grid.add(labelDrawText, 3, 2, 22, 1);

        // Label to present current level of Koch fractal
        labelLevel = new Label("Level: " + currentLevel);
        grid.add(labelLevel, 0, 6);

        // Button to increase level of Koch fractal
        Button buttonIncreaseLevel = new Button();
        buttonIncreaseLevel.setText("Increase Level");
        buttonIncreaseLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                increaseLevelButtonActionPerformed(event);
            }
        });
        grid.add(buttonIncreaseLevel, 3, 6);

        // Button to decrease level of Koch fractal
        Button buttonDecreaseLevel = new Button();
        buttonDecreaseLevel.setText("Decrease Level");
        buttonDecreaseLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                decreaseLevelButtonActionPerformed(event);
            }
        });
        grid.add(buttonDecreaseLevel, 5, 6);

        // Button to fit Koch fractal in Koch panel
        Button buttonFitFractal = new Button();
        buttonFitFractal.setText("Fit Fractal");
        buttonFitFractal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fitFractalButtonActionPerformed(event);
            }
        });
        grid.add(buttonFitFractal, 7, 6);

        Button buttonCancel = new Button();
        buttonCancel.setText("Cancel");
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancelButtonActionPerformed(event);
            }
        });
        grid.add(buttonCancel, 9, 6);

        labelProgressLeft = new Label("Progress Left:");
        grid.add(labelProgressLeft, 0, 7);
        progressBarLeft = new ProgressBar();
        grid.add(progressBarLeft, 3, 7);
        labelProgressLeftText = new Label("Nr Edges: 0");
        grid.add(labelProgressLeftText, 5, 7, 22, 1);

        labelProgressBottom = new Label("Progress Bottom:");
        grid.add(labelProgressBottom, 0, 8);
        progressBarBottom = new ProgressBar();
        grid.add(progressBarBottom, 3, 8);
        labelProgressBottomText = new Label("Nr Edges: 0");
        grid.add(labelProgressBottomText, 5, 8, 22, 1);

        labelProgressRight = new Label("Progress Right:");
        grid.add(labelProgressRight, 0, 9);
        progressBarRight = new ProgressBar();
        grid.add(progressBarRight, 3, 9);
        labelProgressRightText = new Label("Nr Edges: 0");
        grid.add(labelProgressRightText, 5, 9, 22, 1);

        // Add mouse clicked event to Koch panel
        kochPanel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                kochPanelMouseClicked(event);
            }
        });

        // Add mouse pressed event to Koch panel
        kochPanel.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                kochPanelMousePressed(event);
            }
        });

        // Add mouse dragged event to Koch panel
        kochPanel.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                kochPanelMouseDragged(event);
            }
        });

        // Create Koch manager and set initial level
        resetZoom();
        kochManager = new KochManager(this);
        kochManager.changeLevel(currentLevel);

        // Create the scene and add the grid pane
        Group root = new Group();
        Scene scene = new Scene(root, kpWidth + 50, kpHeight + 260);
        root.getChildren().add(grid);

        // Define title and assign the scene for main window
        primaryStage.setTitle("Koch Fractal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        kochManager.stopThreadPool();
        Platform.exit();
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
            gc.setLineWidth(4.0);
        } else if (currentLevel <= 5) {
            gc.setLineWidth(1.5);
        } else {
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
        labelNrEdgesText.setText(text);
    }

    public void setTextCalc(String text) {
        labelCalcText.setText(text);
    }

    public void setTextDraw(String text) {
        labelDrawText.setText(text);
    }

    public void requestDrawEdges() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                kochManager.drawEdges();
            }
        });
    }

    private void increaseLevelButtonActionPerformed(ActionEvent event) {
        if (currentLevel < 11) {
            currentLevel++;
            labelLevel.setText("Level: " + currentLevel);
            kochManager.changeLevel(currentLevel);
        }
    }

    private void decreaseLevelButtonActionPerformed(ActionEvent event) {
        if (currentLevel > 1) {
            currentLevel--;
            labelLevel.setText("Level: " + currentLevel);
            kochManager.changeLevel(currentLevel);
        }
    }

    private void fitFractalButtonActionPerformed(ActionEvent event) {
        resetZoom();
        kochManager.drawEdges();
    }

    private void cancelButtonActionPerformed(ActionEvent event) {
        resetZoom();
        kochManager.cancelRunningTasks();
    }

    private void kochPanelMouseClicked(MouseEvent event) {
        if (Math.abs(event.getX() - startPressedX) < 1.0 && Math.abs(event.getY() - startPressedY) < 1.0) {
            double originalPointClickedX = (event.getX() - zoomTranslateX) / zoom;
            double originalPointClickedY = (event.getY() - zoomTranslateY) / zoom;
            if (event.getButton() == MouseButton.PRIMARY) {
                zoom *= 2.0;
            } else if (event.getButton() == MouseButton.SECONDARY) {
                zoom /= 2.0;
            }
            zoomTranslateX = (int) (event.getX() - originalPointClickedX * zoom);
            zoomTranslateY = (int) (event.getY() - originalPointClickedY * zoom);
            kochManager.drawEdges();
        }
    }

    private void kochPanelMouseDragged(MouseEvent event) {
        zoomTranslateX = zoomTranslateX + event.getX() - lastDragX;
        zoomTranslateY = zoomTranslateY + event.getY() - lastDragY;
        lastDragX = event.getX();
        lastDragY = event.getY();
        kochManager.drawEdges();
    }

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

    public ProgressBar getProgressBar(Side side) {
        switch (side) {
            case LEFT:
                return progressBarLeft;
            case BOTTOM:
                return progressBarBottom;
            case RIGHT:
                return progressBarRight;
            default:
                throw new IllegalStateException("Unexpected value: " + side);
        }
    }

    public Label getProgressLabel(Side side) {
        switch (side) {
            case LEFT:
                return labelProgressLeftText;
            case BOTTOM:
                return labelProgressBottomText;
            case RIGHT:
                return labelProgressRightText;
            default:
                throw new IllegalStateException("Unexpected value: " + side);
        }
    }
}
