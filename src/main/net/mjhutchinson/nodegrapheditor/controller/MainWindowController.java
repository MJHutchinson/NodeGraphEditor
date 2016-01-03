package net.mjhutchinson.nodegrapheditor.controller;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import net.mjhutchinson.nodegrapheditor.Globals;

/**
 * Created by Michael Hutchinson on 02/01/2016 at 14:05.
 */
public class MainWindowController {

    @FXML SplitPane mainSplitPane;
    @FXML Canvas canvas;
    @FXML ScrollPane scrollPane;

    Point2D lastDragPosition = null;

    /**
     * Initialization method for the controller. For the
     * setup of listeners and event filters
     */
    public void initialize(){
        mainSplitPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainSplitPane.setDividerPositions( (1280f / observable.getValue().floatValue()) * 0.15 , 1.0f - ((1280f / observable.getValue().doubleValue()) * 0.15) );
        });

        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            onCanvasScroll(event);
            event.consume();
        });

        draw();
    }

    /**
     * Handler for scroll events on the Canvas
     * @param event the ScrollEvent
     * @see ScrollEvent
     */
    public void onCanvasScroll(ScrollEvent event){
        float previousScaleFactor = Globals.worldScaleFactor;
        Globals.worldScaleFactor += event.getDeltaY()>0 ? 0.1 : -0.1;
        if(Globals.worldScaleFactor < 0.2) Globals.worldScaleFactor = 0.2f;
        if(Globals.worldScaleFactor > 4) Globals.worldScaleFactor = 4.0f;
        Globals.worldPosition = Globals.worldPosition.add((event.getX() / previousScaleFactor) - (event.getX() / Globals.worldScaleFactor), (event.getY() / previousScaleFactor) - (event.getY() / Globals.worldScaleFactor));

        redraw();
    }

    /**
     * Handler for mouse presses on the Canvas
     * @param event the MouseEvent
     * @see MouseEvent
     */
    public void onCanvasMousePressed(MouseEvent event){
        lastDragPosition = new Point2D(event.getX(), event.getY());
    }

    /**
     * Handler for mouse drag events on the Canvas
     * @param event the MouseEvent
     * @see MouseEvent
     */
    public void onCanvasMouseDragged(MouseEvent event){
        if(lastDragPosition != null){
            Globals.worldPosition = new Point2D(Globals.worldPosition.getX() - ((event.getX() - lastDragPosition.getX()) / Globals.worldScaleFactor), Globals.worldPosition.getY() - ((event.getY() - lastDragPosition.getY()) / Globals.worldScaleFactor));
            lastDragPosition = new Point2D(event.getX(), event.getY());
        }

        redraw();
    }

    /**
     * Handler for mouse release events on the Canvas
     * @param event the MouseEvent
     * @see MouseEvent
     */
    public void onCanvasMouseReleased(MouseEvent event){
        lastDragPosition = null;
    }

    /**
     * Handler for mouse click events on the Canvas
     * @param event the MouseEvent
     * @see MouseEvent
     */
    public void onCanvasMouseClicked(MouseEvent event){
        System.out.println("X:" + Float.toString(screenToWorldX((float) event.getX())) + " Y:" + Float.toString(screenToWorldX((float) event.getY())));
    }

    /**
     * Draws the current Node Graph onto the Canvas. If not
     * the first time of drawing, redraw() should be called
     * instead
     * @see Canvas
     * @see GraphicsContext
     * @see MainWindowController#redraw()
     */
    private void draw(){

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //draw background
        gc.setFill(Globals.Colors.BACKGROUND);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //draw gridlines
        gc.setLineWidth(Globals.majorGridlineWidth * Globals.worldScaleFactor);
        gc.setStroke(Globals.Colors.GRIDLINES);
        for(float i = 0; i < canvas.getWidth() / (Globals.majorGridlineSpacing * Globals.worldScaleFactor); i++){
            gc.strokeLine((Globals.majorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getX() * Globals.worldScaleFactor) % (Globals.majorGridlineSpacing * Globals.worldScaleFactor)), 0, (Globals.majorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getX() * Globals.worldScaleFactor) % (Globals.majorGridlineSpacing * Globals.worldScaleFactor)), canvas.getHeight());
        }

        gc.setLineWidth(Globals.minorGridlineWidth * Globals.worldScaleFactor);
        for(float i = 0; i < canvas.getWidth() / (Globals.minorGridlineSpacing * Globals.worldScaleFactor); i++){
            gc.strokeLine((Globals.minorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getX() * Globals.worldScaleFactor) % (Globals.minorGridlineSpacing * Globals.worldScaleFactor)), 0, (Globals.minorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getX() * Globals.worldScaleFactor) % (Globals.minorGridlineSpacing * Globals.worldScaleFactor)), canvas.getHeight());
        }

        gc.setLineWidth(Globals.majorGridlineWidth * Globals.worldScaleFactor);
        for(float i = 0; i < canvas.getHeight() / (Globals.majorGridlineSpacing * Globals.worldScaleFactor); i++){
            gc.strokeLine(0, (Globals.majorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getY() * Globals.worldScaleFactor) % (Globals.majorGridlineSpacing * Globals.worldScaleFactor)), canvas.getWidth(), (Globals.majorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getY() * Globals.worldScaleFactor) % (Globals.majorGridlineSpacing * Globals.worldScaleFactor)));
        }

        gc.setLineWidth(Globals.minorGridlineWidth * Globals.worldScaleFactor);
        for(float i = 0; i < canvas.getWidth() / (Globals.minorGridlineSpacing * Globals.worldScaleFactor); i++){
            gc.strokeLine(0, (Globals.minorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getY() * Globals.worldScaleFactor) % (Globals.minorGridlineSpacing * Globals.worldScaleFactor)), canvas.getWidth(), (Globals.minorGridlineSpacing * Globals.worldScaleFactor) * i - ((Globals.worldPosition.getY() * Globals.worldScaleFactor) % (Globals.minorGridlineSpacing * Globals.worldScaleFactor)));
        }

    }

    /**
     * Redraws the Node Graph on the Canvas
     * @see MainWindowController#draw()
     */
    private void redraw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        draw();
    }

    /**
     * Converts a 2D point from screen coordinates
     * to world coordinates
     * @param point the Point2D to convert from screen coordinates
     * @return the point in world coordinates
     */
    private Point2D screenToWorld(Point2D point){
        return new Point2D(point.getX()/Globals.worldScaleFactor + Globals.worldPosition.getX(), point.getY()/Globals.worldScaleFactor + Globals.worldPosition.getY());
    }

    /**
     * Converts a 2D point from world coordinates
     * to screen coordinates
     * @param point the Point2D to convert from world coordinates
     * @return the point in screen coordinates
     */
    private Point2D worldToScreen(Point2D point){
        return new Point2D((point.getX() - Globals.worldPosition.getX()) * Globals.worldScaleFactor, (point.getY() - Globals.worldPosition.getY()) * Globals.worldScaleFactor);
    }

    private float screenToWorldX(float x){
        return (float) (x/Globals.worldScaleFactor + Globals.worldPosition.getX());
    }

    private float worldToScreenX(float x){
        return (float) ((x - Globals.worldPosition.getX()) * Globals.worldScaleFactor);
    }

    private float screenToWorldY(float y){
        return (float) (y/Globals.worldScaleFactor + Globals.worldPosition.getY());
    }

    private float worldToScreenY(float y){
        return (float) ((y - Globals.worldPosition.getY()) * Globals.worldScaleFactor);
    }

}
