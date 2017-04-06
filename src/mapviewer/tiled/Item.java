package mapviewer.tiled;

/**
 * Created by Tristen on 16-3-2017.
 * Object containing json object data.
 */

public class Item {
    String name;
    double x;
    double y;
    public Item(String nameO, double xO, double yO){
        name=nameO;
        x=xO;
        y=yO;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public String getName(){
        return name;
    }
}
