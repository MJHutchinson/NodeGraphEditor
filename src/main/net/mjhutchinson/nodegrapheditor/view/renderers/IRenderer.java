package net.mjhutchinson.nodegrapheditor.view.renderers;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Michael Hutchinson on 03/01/2016 at 11:52.
 */
public interface IRenderer<T> {

    void render(T data, Point2D point2D, GraphicsContext gc);

}
