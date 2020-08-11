/**
 * @author Mohamad Elesawi <esawi442@gmail.com>
 * @since 2019-03-14
 **/

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * MultipleFramesBouncingBallsAnimation class.
 * <p>
 * A Multiple Bouncing  Balls inside multiple Frames Animation.
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * Main method of MultipleBouncingBallsAnimation.
     * a new GUI object creates a screen with dimensions then draws balls with certain sizes that are given as arguments
     * from the command line ,the balls get their velocity according to their size ,big size = low velocity,
     * small size = high velocity. the balls bounce in two frames , a small yellow and a larger grey rectangles
     * with different dimensions: the grey rectangle range from (50,50) to (500,500). and the yellow rectangle from
     * (450,450) to (600,600) the first half of the balls (arguments) bounce inside the first frame,
     * and the second half of the balls bounce inside the second frame
     *
     * @param args : each ball's radius.
     **/
    public static void main(String[] args) {
        int width = 600;
        int height = 600;
        //Creates a window titled "Multiple bouncing balls Animation", with given height and width.
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", width, height);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        // grey rectangle coordinates width and height
        int bigFrameX1 = 50, bigFrameY1 = 50, bigFrameX2 = 500, bigFrameY2 = 500, bigFrameHeight = 450,
                bigFrameWidth = 450;

        /* yellow rectangle coordinates width and height , (the "i" variable is placed out of the loop so we wil be
        able to get the current position in the command arguments to the second array) */
        int smallerFrameX1 = 450, smallerFrameY1 = 450, smallerFrameX2 = 600, smallerFrameY2 = 600,
                smallerFrameHeight = 150, smallerFrameWidth = 150, i;
        //if there are no arguments
        if (args.length == 0) {
            System.out.println("there is no balls to draw!");
            return;
        }
        //if there is an odd number of balls
        if ((args.length) % 2 != 0) {
            System.out.println("please put an even number of balls .");
            return;
        }
        // setting each array size to half of the arguments length.
        Ball[] bigFrameBalls = new Ball[args.length / 2];

        Ball[] smallFrameBalls = new Ball[args.length / 2];

        // filling the first array with balls.
        for (i = 0; i < bigFrameBalls.length; i++) {

            bigFrameBalls[i] = Ball.generateRandomBall(Integer.parseInt(args[i]), bigFrameWidth, bigFrameHeight,
                    bigFrameX1, bigFrameY2, 0);
        }
        //the current position in the command arguments , so we could keep counting without mistakes.
        int placment = i;

        // filling the second array with balls.
        for (int j = 0; j < smallFrameBalls.length; j++) {

            smallFrameBalls[j] = Ball.generateRandomBall(Integer.parseInt(args[placment]), smallerFrameWidth,
                    smallerFrameHeight, smallerFrameX1, smallerFrameY2, 0);
            // increasing the placment to get the next radius.
            placment++;
        }

        // sorting each array from the biggest radius to the smallest.

        bigFrameBalls = Ball.sortBallArray(bigFrameBalls);

        smallFrameBalls = Ball.sortBallArray(smallFrameBalls);

        /** the setArrayVelocity function loop starts from the second member of the array, so we will give the first
         member its velocity and continue ( i used 2 diffrent ways to set velocity, the first with sorting the
         array and giving each member the precius velocity + a random number, and the second is division as u can see
         here . **/
        int dx = 150 / bigFrameBalls[0].getSize();
        bigFrameBalls[0].setVelocity(dx, dx);

        int dy = 150 / smallFrameBalls[0].getSize();

        smallFrameBalls[0].setVelocity(dy, dy);

        // setting each ball in both array's velocity

        bigFrameBalls = Ball.setArrayVelocity(bigFrameBalls, 1);

        smallFrameBalls = Ball.setArrayVelocity(smallFrameBalls, 1);


        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //draw the first frame(grey rectangle) on the screen.
            d.setColor(Color.GRAY);
            d.fillRectangle(bigFrameX1, bigFrameY1, bigFrameX2 - bigFrameX1, bigFrameY2 - bigFrameY1);

            //draw the second frame(grey rectangle) on the screen.
            d.setColor(Color.YELLOW);
            d.fillRectangle(smallerFrameX1, smallerFrameY1, smallerFrameX2 - smallerFrameX1,
                    smallerFrameY2 - smallerFrameY1);

            // draw the right balls in the right frame , and have them moves according to their velocity.
            MultipleBouncingBallsAnimation art = new MultipleBouncingBallsAnimation(
                    bigFrameX2, bigFrameY2, bigFrameX1, bigFrameY1);
            MultipleBouncingBallsAnimation art2 = new MultipleBouncingBallsAnimation(
                    smallerFrameX2, smallerFrameY2, smallerFrameX1, smallerFrameY1);

            art.drawBouncingBalls(bigFrameBalls, d);
            art2.drawBouncingBalls(smallFrameBalls, d);

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

}
