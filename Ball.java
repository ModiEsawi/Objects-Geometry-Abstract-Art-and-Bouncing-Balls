/**
 * @author Mohamad Elesawi <esawi442@gmail.com>
 * @since 2019-03-14
 **/

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * class Ball.
 **/

public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private double rightBoundrey;
    private double topBoundry;
    private double leftBoundry;
    private double bottomBoundry;

    /**
     * Constructor.
     * given the x and y coordinates of the center point of the ball, radius and color.
     *
     * @param x     , ball's center x coordinate point.
     * @param y     , ball's center y coordinate point.
     * @param r     ,  ball's radius.
     * @param color : ball's color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return x-coordinate of the ball's center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y-coordinate of the ball's center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface : draws surface to draw our ball on it.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * set the balls velocity while the velocity is given .
     *
     * @param v : the velocity.
     */

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the balls velocity while given the change in position of the `x` and the `y` coordinates.
     *
     * @param dx : change in position of the x .
     * @param dy : change in position of the y .
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set the frame's boundaries that the ball resides
     * between them.
     *
     * @param right  : frame's  right boundary.
     * @param top    : frame's top boundary.
     * @param left   :  frame's left boundary.
     * @param bottom : frame's bottom boundary.
     */
    public void setBounds(double right, double top, double left,
                          double bottom) {
        this.rightBoundrey = right - this.radius - Math.abs(this.velocity.getdx());
        this.topBoundry = top - this.radius - Math.abs(this.velocity.getdY());
        this.leftBoundry = left + this.radius
                + Math.abs(this.velocity.getdx());
        this.bottomBoundry = bottom + this.radius
                + Math.abs(this.velocity.getdY());
    }

    /**
     * moving the the ball one step according to it's velocity, while taking ball's frame exit into calculation.
     */
    public void moveOneStep() {

        Point point = new Point(this.center.getX(), this.center.getY());
        if ((this.center.getY() >= this.topBoundry && this.velocity.getdY() > 0)
                || (this.center.getY() <= this.bottomBoundry
                && this.velocity.getdY() < 0)) {
            // the velocity causes the ball to go out of the frame, so we will set the ball to be exactly on its
            //boundry
            if (this.center.getY() >= this.topBoundry) {
                point.setY(this.topBoundry + 2 * this.velocity.getdY());
            } else {
                point.setY(this.bottomBoundry + 2 * this.velocity.getdY());
            }
            this.setVelocity(this.velocity.getdx(), -this.velocity.getdY());
        }
        if ((this.center.getX() >= this.rightBoundrey && this.velocity.getdx() > 0)
                || (this.center.getX() <= this.leftBoundry
                && this.velocity.getdx() < 0)) {
            if (this.center.getX() >= this.rightBoundrey) {
                // the velocity causes the ball to go out of the frame, so we will set the ball to be exactly on its
                //boundry
                point.setX(this.rightBoundrey + 2 * this.velocity.getdx());
            } else {
                point.setX(this.leftBoundry + 2 * this.velocity.getdx());
            }
            this.setVelocity(-this.velocity.getdx(), this.velocity.getdY());
        }
        this.center = this.getVelocity().applyToPoint(point);
    }

    /**
     * function to generate a random ball according to the balls bounds , the flag is used to so wont have to create
     * another function that does almost the same thing but adds the needed dx and dy ( for example ,
     * we used it different flags in the MultipleFramesBouncingBallsAnimation class then in the
     * BouncingBallAnimation class.
     *
     * @param radius : the Ball-to-be radius.
     * @param boundX : sets the bound of the x's random point max value
     * @param boundY : sets the bound of the y's random point max value
     * @param addToX : the change in the x coordinates
     * @param addToY : the change in the y coordinate
     * @param flag   :  sets wither to update the coordinates according to the function who sent it.
     * @return a new Ball.
     */


    public static Ball generateRandomBall(int radius, int boundX, int boundY, int addToX, int addToY, int flag) {
        Random rand = new Random();
        Color color = new Color(rand.nextInt(0xFFFFFF)); // random color
        int x = rand.nextInt(boundX) + 1; // get integer in range 1 - X bound
        int y = rand.nextInt(boundY) + 1; // get integer in range 1 - Y bound
        // returns the new ball
        if (flag == 1) {
            return new Ball((double) x, (double) y, radius, color);
        } else { // returns the new ball with updated X and Y according to the velocity
            return new Ball((double) x + addToX, (double) y + addToY, radius, color);
        }
    }

    /**
     * sorting the given array of balls , using bubble sort.
     *
     * @param ballsArray : the array to sort.
     * @return the same array but sorted from the highest radius to the lowest.
     */
    public static Ball[] sortBallArray(Ball[] ballsArray) {
        for (int k = 0; k < ballsArray.length; k++) {
            for (int j = 0; j < ballsArray.length - k - 1; j++) {
                // comparing radius'es
                if (ballsArray[k].getSize() < ballsArray[k + 1].getSize()) {
                    Ball tempBall = ballsArray[k];
                    ballsArray[k] = ballsArray[k + 1];
                    ballsArray[k + 1] = tempBall;
                }
            }
        }
        return ballsArray;
    }

    /**
     * setting the velocity of each ball in the array, the first ball will always be the lragest and its already
     * set to have the slowest speed.
     *
     * @param ballsArray :  the array to set its balls velocity.
     * @param flag       :      decides which way to set the velocity,
     *                   (both ways will are working in a way that the larger the ball the slower its speed and
     *                   the smallest the ball, the faster its speed
     * @return the array with its balls velocity set.
     */
    public static Ball[] setArrayVelocity(Ball[] ballsArray, int flag) {
        Random random = new Random();
        for (int k = 1; k < ballsArray.length; k++) {
            // balls with same size will get the same velocity.
            if (ballsArray[k].getSize() == ballsArray[k - 1].getSize()) {
                ballsArray[k].setVelocity(ballsArray[k - 1].getVelocity());
                continue;
            }
            // balls with radius bigger then 50 will have the same velocity
            if (ballsArray[k].getSize() > 50) {
                ballsArray[k].setVelocity(2, 2);
                continue;
            }
            if (flag == 0) {
                int dy = random.nextInt(12) + (int) ballsArray[k - 1].getVelocity().getdx() + 1;
                //symmetrical change in y and x axes.
                ballsArray[k].setVelocity(dy, dy);
            } else if (flag == 1) {
                if (ballsArray[k].getSize() <= 50) {
                    //symmetrical change in y and x .
                    int dx = 150 / ballsArray[k].getSize();
                    ballsArray[k].setVelocity(dx, dx);
                }
            }
        }
        return ballsArray;
    }
}