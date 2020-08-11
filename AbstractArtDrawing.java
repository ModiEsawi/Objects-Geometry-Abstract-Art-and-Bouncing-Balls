/**
 * @author Mohamad Elesawi <esawi442@gmail.com>
 * @since 2019-03-14
 **/

import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * An Abstract Art Drawing .
 * draws lines segments in the screen.
 */
public class AbstractArtDrawing {

    /**
     * Drawing random lines-segments in ths screen.
     * using biuoop package , and in particular biuoop.GUI and
     * biuoop.DrawSurface classes.
     */

    public void drawRandomLines() {
        int linesNumber = 10; // numbers of lines to draw
        int width = 400;
        int height = 300;
        int pointRadius = 3;
        // an array to store all lines in

        Line[] linesArray = new Line[linesNumber];

        /* Create a window with title "Random Lines Example"
         which is certain pixels wide and  high. */

        GUI gui = new GUI("Random Lines Example", width, height);
        DrawSurface d = gui.getDrawSurface();
        // filling the array with lines
        for (int i = 0; i < linesNumber; ++i) {
            linesArray[i] = Line.generateRandomLine();
            d.setColor(Color.BLUE); // midPoint's color.
            d.fillCircle((int) linesArray[i].middle().getX(), (int) linesArray[i].middle().getY(),
                    pointRadius); //draw the midPoint of the line with radius = 3.
            d.setColor(Color.BLACK); // line's color.
            linesArray[i].drawLine(linesArray[i], d); //draw the line.
        }
        // checking each line if its Intersecting with any line in the array
        for (int i = 0; i < linesArray.length; i++) {
            for (int j = 0; j < linesArray.length; j++) {
                if (linesArray[i].isIntersecting(linesArray[j]) && j != i) {
                    //getting the intersection point.
                    Point intersection = linesArray[i].intersectionWith(linesArray[j]);
                    d.setColor(Color.RED); // intersection point's color
                    d.fillCircle((int) intersection.getX(), (int) intersection.getY(),
                            pointRadius);  // drawing the point with radius = 3.
                }
            }
        }
        gui.show(d); // show the outcome ( the lines and they're intersection on the screen
    }

    /**
     * Main method: Initialize and run the AbstractArtDrawing class.
     *
     * @param args ,arguments from the command line.
     */

    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}

