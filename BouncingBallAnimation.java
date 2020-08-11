/**
 * @author Mohamad Elesawi <esawi442@gmail.com>
 * @since 2019-03-14
 */

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A Bouncing Ball Animation .
 */
public class BouncingBallAnimation {
    /**
     * Main method of BouncingBallAnimation.
     * new GUI object that creates a screen with given dimensions.
     * a bouncing ball inside the screen.
     *
     * @param args .
     */
    public static void main(String[] args) {
        int bound = 200;
        GUI gui = new GUI("Bouncing Ball Animation", bound, bound);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(90, 0, 30, java.awt.Color.BLACK);
        Velocity velocity = Velocity.fromAngleAndSpeed(45, 10);
        ball.setVelocity(velocity);
        while (true) {
            ball.setBounds(bound, bound, 0, 0);
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
