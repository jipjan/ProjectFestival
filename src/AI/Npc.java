package AI;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import AI.mood.*;
import AI.pathFinding.DistanceGrid;
import AI.pathFinding.GridLocation;

import javax.imageio.ImageIO;

public class Npc {
    private State _state;
    private IMood _I_mood;
    private Point2D _location;
    private Point2D _destination;
    private double _direction = 0;
    private static int _tileSize = 32;
    private DistanceGrid _finalDestination;
    private BufferedImage _sprite;

    public double getWidth() {
        return width;
    }
    private double width = 5;

    public Npc(Point2D _location) {
        this._location = _location;
        _destination = _location;
        try{
        switch((int)Math.floor(Math.random()*10)){
            case 0:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker1.png"));
            break;
            case 1:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker2.png"));
                break;
            case 2:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker3.png"));
                break;
            case 3:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker4.png"));
                break;
            case 4:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker5.png"));
                break;
            case 5:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker6.png"));
                break;
            case 6:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker7.png"));
                break;
            case 7:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker8.png"));
                break;
            case 8:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker9.png"));
                break;
            case 9:_sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker10.png"));
                break;

            default: _sprite = ImageIO.read(this.getClass().getResource("/Bezoeker/Bezoeker1.png"));
        }}
        catch(Exception e){
            e.printStackTrace();

        }
    }

    public Npc(GridLocation gridLocation, IMood IMood)
    {
        //_location = gridLocation.getLocation();
        _I_mood = IMood;
    }

    public void needsUpdate()
    {

    }

    public void SetFinalDestination(DistanceGrid finalDestination)
    {
        _finalDestination = finalDestination;
    }

    public DistanceGrid getFinalDestination()
    {
        return _finalDestination;
    }

    public void setMood(IMood m) {
        _I_mood = m;
    }

    public void setState(State s) {
        _state = s;
    }

    public IMood getMood() {
        return _I_mood;
    }

    public State getState() {
        return _state;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform t = new AffineTransform();
        t.translate(_location.getX(),_location.getY());
        t.rotate(_direction);
        t.translate(-_sprite.getWidth()/ 2, -_sprite.getHeight()/2);

        g2d.drawImage(_sprite,t,null);


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

    public void setDestination(Point2D destination) {
        this._destination = destination;
    }
}
