package UnitTests.ImportExport;

import Events.*;
import de.jaret.util.date.JaretDate;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static ImportExport.Export.*;
import static ImportExport.Import.ImportJsonObject;
import static ImportExport.Import.ImportObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportExportTest {
    @Test
    public void testExportImport()
    {
        Events a = new Events();
        a.add(new Event("Test", "K3", (short) 5, new Time(new JaretDate(), new JaretDate()), 1));
        ExportFile("test", a);
        Events b = ImportObject("test");
        assertEquals(a, b);
    }

    @Test
    public void testJsonExportImport()
    {
        Events a = new Events();
        a.add(new Event("Test", "K3", (short) 5, new Time(new JaretDate(), new JaretDate()), 1));
        ExportJsonFile("test", a);
        Events b = ImportJsonObject("test", Events.class);
        assertEquals(a, b);
    }
}
