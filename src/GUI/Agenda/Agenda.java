package GUI.Agenda;

import Events.Time;
import GUI.CurrentSetup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Agenda extends JPanel {
    JTable table;

    public Agenda() {

        //Event moet later vanuit andere klasse komen

        String[] header = {"Time:", "Podium 1", "Podium 2", "Podium 3"};
        String[][] data = {{"uWotM9"}};

        DefaultTableModel model = new DefaultTableModel(data, header);
        model.removeRow(0);

        JTable table = new JTable(model);
        table.setDefaultRenderer(Object.class, new TableRenderer());
        table.setPreferredScrollableViewportSize(new Dimension(450, 500));
        table.setFillsViewportHeight(true);
        JScrollPane js = new JScrollPane(table);
        setRows(model);
        js.setVisible(true);
        add(js);

    }

    private void setRows(DefaultTableModel model) {

        int i = 12;
        int rowCountEve = 24;
        int rowCountMorn = 11;
        String rowTime = "";

        String dataRow1 = "";//new JPanel();
        String dataRow2 = "";//new JLabel("test");
        String dataRow3 = "";//new JLabel("test");
        SimpleDateFormat f = new SimpleDateFormat("HH:00");
        SimpleDateFormat time = new SimpleDateFormat("hh");
        Date d = null;
        for (i = 0; i <= 24; i++) {
            dataRow1 = "";
            rowTime = i + ":00";
            try {
                d = time.parse(String.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Events.Event e : CurrentSetup.Events) {
                switch (e.getPodium()) {
                    case 1:
                        if (timeCompare(d, e.getTime()))
                            dataRow1 = e.getName();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
            model.addRow(new Object[]{rowTime, dataRow1, dataRow2, dataRow3});
        }
    }


    public static void timeCheck() {

    }

    public static void main(String[] a) {

        JFrame jf = new JFrame();
        Agenda tab = new Agenda();
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
            if (column != 0) {
                //editor.add(table);
            }
            editor.setBackground((column != 0) ? Color.white : Color.lightGray);
            return editor;
        }
    }

    private static boolean timeCompare(Date d, Time t) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(d);
        int hours = cal.get(Calendar.HOUR_OF_DAY);

        cal.setTime(t.getBeginDate());
        int hourt1 = cal.get(Calendar.HOUR_OF_DAY);

        cal.setTime(t.getEndDate());
        int hourt2 = cal.get(Calendar.HOUR_OF_DAY);
        // t1 < d < t2
        return (hours >= hourt1 && hours <= hourt2);
    }

}

