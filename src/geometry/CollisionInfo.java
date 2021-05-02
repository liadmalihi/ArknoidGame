//ID:318948809
package geometry;

import interfaces.Collidable;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Collidable collisionObject;
    private Point point;

    /**
     * Instantiates a new Collision info.
     *
     * @param c     the collision object
     * @param point the point
     */
    public CollisionInfo(Collidable c, Point point) {
        this.collisionObject = c;
        this.point = point;
    }

    /**
     * Collision point point.
     * return the point at which the collision occurs.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Collision object collidable.
     * return the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
