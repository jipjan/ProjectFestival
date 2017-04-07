package Mapviewer.TiledMapReader;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class MyTester {
    public static void main(String[] args) {
        MyTiledJsonParser parser = new MyTiledJsonParser("./resources/Festivalplanner Map V1.json");
        System.out.println(parser.Map);
    }
}
