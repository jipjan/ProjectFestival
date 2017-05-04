package NewAI.BaseClasses;

import org.dyn4j.collision.manifold.Manifold;
import org.dyn4j.collision.narrowphase.Penetration;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.CollisionListener;
import org.dyn4j.dynamics.contact.ContactConstraint;

/**
 * Created by Jaap-Jan on 3-5-2017.
 */
public class MyCollisionListener implements CollisionListener {

    @Override
    public boolean collision(Body body, BodyFixture bodyFixture, Body body1, BodyFixture bodyFixture1) {
        return false;
    }

    @Override
    public boolean collision(Body body, BodyFixture bodyFixture, Body body1, BodyFixture bodyFixture1, Penetration penetration) {
        return false;
    }

    @Override
    public boolean collision(Body body, BodyFixture bodyFixture, Body body1, BodyFixture bodyFixture1, Manifold manifold) {
        return false;
    }

    @Override
    public boolean collision(ContactConstraint contactConstraint) {
        return false;
    }
}
