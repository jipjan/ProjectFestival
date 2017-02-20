package tileLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by thijs_000 on 20-Feb-17.
 */
public class TiledTileMap
{
    public TiledTileMap()
    {
        try
        {
            //map inladen
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
