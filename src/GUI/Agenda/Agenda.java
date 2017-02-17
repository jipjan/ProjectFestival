package GUI.Agenda;

import GUI.CurrentSetup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Agenda extends JPanel{
JTable table;

    public Agenda(){

        //Event moet later vanuit andere klasse komen

        String [] header={"Time:","Podium 1", "Podium 2", "Podium 3"};
        String [][] data={{"uWotM9"}};

        DefaultTableModel model = new DefaultTableModel(data,header);
        model.removeRow(0);

        JTable table = new JTable(model);
        table.setDefaultRenderer(Object.class, new TableRenderer());
        table.setPreferredScrollableViewportSize(new Dimension(450,500));
        table.setFillsViewportHeight(true);
        JScrollPane js=new JScrollPane(table);
        setRows(model);
        js.setVisible(true);
        add(js);

    }

    private void setRows(DefaultTableModel model)    {

        int i = 12;
        int rowCountEve=24;
        int rowCountMorn=11;
        String rowTime="";

        String dataRow1 = "test";//new JPanel();
        String dataRow2 = "test";//new JLabel("test");
        String dataRow3 = "test";//new JLabel("test");


        for (i = 12;i <= rowCountEve; i++){
            rowTime = i + ":00";
            for(Events.Event e : CurrentSetup.Events)
            {
                switch (e.getPodium()) {
                    case 1:
                        // if je tijd valt binnen de tijden dat het speelt, doe iets er mee, laadt het in de datarow1/datarow2/datarow3 of i.d.
                        //if (e.getTime().getBeginDate())
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
            model.addRow(new Object[]{rowTime,dataRow1,dataRow2,dataRow3});}
        for (i = 1;i <= rowCountMorn; i++){
            rowTime = i + ":00";
            model.addRow(new Object[]{rowTime,dataRow1,dataRow2,dataRow3});
        }
    }

    public static void main(String [] a) {

        JFrame jf=new JFrame();
        Agenda tab= new Agenda();
        jf.setTitle("Table");
        jf.setSize(500, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(tab);
    }

    class TableRenderer implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                                boolean hasFocus, int row, int column) {
            JTextField editor = new JTextField();
            if (value != null)
                editor.setText(value.toString());
            if (column !=0) {
                //editor.add(table);
            }
            editor.setBackground((column != 0) ? Color.white : Color.lightGray);
            return editor;
        }
    }

    }

