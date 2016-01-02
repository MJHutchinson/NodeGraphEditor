package net.mjhutchinson.nodegrapheditor;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Created by Michael Hutchinson on 02/01/2016 at 15:04.
 */
public class Globals {

    public static float worldScaleFactor = 1.0f;

    public static Point2D worldPosition = new Point2D(0.0f, 0.0f);

    public static float majorGridlineSpacing = 125.0f;
    public static float majorGridlineWidth = 1.0f;
    public static float minorGridlineSpacing = 25.0f;
    public static float minorGridlineWidth = 0.5f;

    public static class Colors{
        public static final Color BACKGROUND = new Color(0.222,0.222,0.222,1);
        public static final Color GRIDLINES = new Color(0.160,0.160,0.160,1);
        public static final Color NODE_BACKGROUND = new Color(0.8,0.8,0.8,0.7);
        public static final Color NODE_BACKGROUND_2 = new Color(0.5,0.5,0.5,0.7);
        public static final Color NODE_OUTLINE = new Color(0.14, 0.14, 0.14, 1);
        public static final Color NODE_TEXT = new Color(0.14, 0.14, 0.14, 1);
    }


}
