package ImportExport;

import com.google.gson.Gson;

import java.io.*;

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
        } catch(ClassNotFoundException c) {
            c.printStackTrace();
        }
        return e;
    }

    public static <T> T ImportJsonObject(String fileLocation, Class<T> typeClass) {
        Gson obj = new Gson();
        T e = null;
        try {
            e = obj.fromJson(new FileReader(fileLocation), typeClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return e;
    }
}
