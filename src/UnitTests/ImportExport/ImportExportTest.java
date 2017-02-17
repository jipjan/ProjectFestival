package UnitTests.ImportExport;

import ImportExport.Export;
import Events.*;
import ImportExport.Import;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static ImportExport.Export.*;
import static ImportExport.Import.ImportObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportExportTest {
    @Test
    public void testExportImport()
    {
        Events a = new Events();
        a.add(new Event("Test", (short) 5, new Time(new Date(), new Date())));
        ExportFile("test", a);
        Events b = ImportObject("test");
        assertEquals(a, b);
    }
}
