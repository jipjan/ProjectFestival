package ImportExport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Export {
    public static void ExportFile(String fileLocation, Serializable object) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileLocation);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + fileLocation);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}