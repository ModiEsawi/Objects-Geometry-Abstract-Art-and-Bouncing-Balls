/**
 * @author Mohamad Elesawi <esawi442@gmail.com>
 * @since 2019-03-14
 */

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * MultipleBouncingBallsAnimation class.
 */

/**
 * creating multiple bouncing balls inside the screen.
 **/
public class MultipleBouncingBallsAnimation {

    private double rightBoundrey;
    private double topBoundrey;
    private double leftBoundrey;
    private double bottomBoundrey;

    /**
     * setting bounds.
     *
     * @param right  - x max limit.
     * @param left   - x min limit.
     * @param top    - y max limit.
     * @param bottom - y min limit.
     */

    public MultipleBouncingBallsAnimation(double right, double top,
                                          double left, double bottom) {

        this.leftBoundrey = left;
        this.bottomBoundrey = bottom;
        this.rightBoundrey = right;
        this.topBoundrey = top;
    }

    /**
     * setting the bounds and drawing the balls, getting the balls movement with the help of the "moveOneStep" function.
     *
     * @param allBalls : the array of balls that need to be drawn.
     * @param d        : the surface to draw the balls on
     */

    public void drawBouncingBalls(Ball[] allBalls, DrawSurface d) {
        for (int i = 0; i < allBalls.length; i++) {
            allBalls[i].setBounds(this.rightBoundrey, this.topBoundrey,
                    this.leftBoundrey, this.bottomBoundrey);
            allBalls[i].moveOneStep();
            allBalls[i].drawOn(d);

        }
    }

    /**
     * Main method of MultipleBouncingBallsAnimation.
     * a new GUI object creates a screen with dimensions then draws balls with certain sizes
     * that are given as arguments from the command line ,the balls get their velocity according to their
     * size ,big size = low velocity ,small size = high velocity.
     *
     * @param args : each argument represents the ball's radius.
     */
    public static void main(String[] args) {
        int height = 800;
        int width = 800;

        //Creates a window titled "Multiple bouncing balls Animation", with given height and width.
        GUI gui = new GUI("Multiple bouncing balls Animation", width, height);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        Ball[] allBalls = new Ball[args.length]; // the array of the balls

        // if there are no arguments
        if (args.length == 0) {
            System.out.println("No Balls to draw!");
        }
        // filling the array with balls
        for (int i = 0; i < args.length; ++i) {
            allBalls[i] = Ball.generateRandomBall(Integer.parseInt(args[i]), width, height, 0, 0, 1);
        }
        // sorting the array from the biggest radius to the smallest.
        allBalls = Ball.sortBallArray(allBalls);

        // the first ball in the array will always have the lowest velocity,so we can set (1,1) change in the x
        //and y coordinates to be the lowest velocity
        allBalls[0].setVelocity(1, 1);

        //setting the velocity of each ball in the array , then setting its bounds.
        allBalls = Ball.setArrayVelocity(allBalls, 1);
        MultipleBouncingBallsAnimation art = new MultipleBouncingBallsAnimation(
                800, 800, 0, 0);

        //drawing the balls on the screen and moving them according to their velocity.
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            art.drawBouncingBalls(allBalls, d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}

