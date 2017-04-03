package AI;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;


import AI.mood.*;

public class Npc {
    private State _state;
    private Mood _mood;
    private Point2D _location;
    private Point2D _destination;
    private double _direction = 0;
    private static int _tileSize = 32;
    private DistanceGrid _finalDestination;

    public double getWidth() {
        return width;
    }
    private double width = 5;

    public Npc(Point2D _location) {
        this._location = _location;
        _destination = _location;
    }

    public Npc(GridLocation gridLocation, Mood mood)
    {
        _location = gridLocation.getLocation();
        _mood = mood;
    }

    public void SetFinalDestination(DistanceGrid finalDestination)
    {
        _finalDestination = finalDestination;
    }

    public DistanceGrid getFinalDestination()
    {
        return _finalDestination;
    }

    public void setMood(Mood m) {
        _mood = m;
    }

    public void setState(State s) {
        _state = s;
    }

    public Mood getMood() {
        return _mood;
    }

    public State getState() {
        return _state;
    }

    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.pink);
        g2d.fill(new Ellipse2D.Double(_location.getX(), _location.getY(), width, width));
    }

    public Point2D getLocation() {
        return _location;
    }

    public void update(ArrayList<Npc> npcs) {
        double deltaX = _destination.getX() - _location.getX();
        double deltaY = _destination.getY() - _location.getY();
        double newDirection = Math.atan2(deltaY, deltaX);
        double deltaDirection = newDirection - _direction;

        while (deltaDirection < -Math.PI)
            deltaDirection += 2 * Math.PI;

        while (deltaDirection > Math.PI)
            deltaDirection -= 2 * Math.PI;

        if (deltaDirection > 0.1)
            _direction += 0.1;

        else if (deltaDirection < -0.1)
            _direction -= 0.1;

        else
            _direction = newDirection;

        Point2D newLocation = new Point2D.Double(
                _location.getX() + 1 * Math.cos(_direction),
                _location.getY() + 1 * Math.sin(_direction)
        );

        boolean collision = false;
        for (Npc c : npcs) {
            if (c == this)
                continue;

            if (c.getLocation().distance(newLocation) < width)
                collision = true;

        }

        if (!collision)
            _location = newLocation;

        else
            _direction += 0.2;

    }

    public void setDestination(Point2D _destination) {
        this._destination = _destination;
    }
}
