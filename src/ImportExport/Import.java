package ImportExport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Import {
    public static <T extends Serializable> T ImportObject(String fileLocation)
    {
        T e = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileLocation);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (T) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
        }catch(ClassNotFoundException c) {
            c.printStackTrace();
        }
        return e;
    }
}
