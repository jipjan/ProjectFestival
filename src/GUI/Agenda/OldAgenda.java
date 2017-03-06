package GUI.Agenda;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OldAgenda extends JTable {
    DefaultTableModel model = new DefaultTableModel();
    int rowCount=23;

    public OldAgenda() {
        setModel(model);
        setPodia();
        setRows();
        makeRoster();
    }

    private void setPodia()
    {
        int podiaNummer = 3;
        int i = 1;
        String podiaString="";
        for (i = 1; i <= podiaNummer; i++){
            podiaString = "Podium " + i;
            model.addColumn(podiaString);}
    }

    private void setRows()    {
        int i = 1;
        int rowCount=23;
        String rowTime="";
        //Start 6 uur en eindigt 6 uur
        for (i = 1;i <= rowCount; i++){
             rowTime = i + ":00";
             model.addRow(new Object[]{rowTime});}
    }
    private void makeRoster() {

    }
    }


