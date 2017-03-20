package ImportExport;

import com.google.gson.Gson;

import java.io.*;

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

    public static void ExportJsonFile(String fileLocation, Object obj) {
        Gson gson = new Gson();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
            writer.write(gson.toJson(obj));
            writer.close();
            System.out.println("Json data is saved in " + fileLocation);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}