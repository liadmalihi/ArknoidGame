//ID:318948809
package game;

import geometry.CollisionInfo;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game.Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Instantiates a new Game.Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add collidable.
     * add the given collidable to the environment
     *
     * @param c the c-Interfaces.Collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove collidable.
     * the function remove this collidable
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Gets closest collision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r;
        Collidable close = null;
        Point point;
        CollisionInfo info;
        Point pCloser = trajectory.getEnd();
        double distance = 0, check;
        //loop of all the collidable
        for (Collidable c : collidables) {
            r = c.getCollisionRectangle();
            //check if the point intersection with the rectangle
            if (r.intersectionPoints(trajectory).size() > 0) {
                point = trajectory.closestIntersectionToStartOfLine(r);
                check = point.distance(trajectory.getStart());
                //check which point are closer to first line
                if (distance == 0 || distance > check) {
                    distance = check;
                    pCloser = point;
                    close = c;
                }
            }
        }
        //If there is no nearby point
        if (close == null) {
            return null;
        }
        info = new CollisionInfo(close, pCloser);
        return info;
    }
}
