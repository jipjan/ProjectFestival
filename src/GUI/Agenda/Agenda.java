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
        SimpleDateFormat time = new SimpleDateFormat("HH");
        Date d = null;
        long duration1 = 0;
        long duration2 = 0;
        long duration3 = 0;
        String oldname1="";
        String oldname2="";
        String oldname3="";



        for (i = 0; i <= 24; i++) {
            dataRow1 = "";
            dataRow2 = "";
            dataRow3 = "";
            rowTime = i + ":00";
            try {
                d = time.parse(String.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Events.Event e : CurrentSetup.Events)
            {
                switch (e.getPodium()) {
                    case 1: {
                        if (duration1 != 0) {
                            dataRow1 = oldname1;
                            dataRow1 = dataRow1 + e.getName();
                            duration1--;
                        } else if (timeCompare(d, e.getTime()))
                            dataRow1 = dataRow1 + e.getName();
                        oldname1 = dataRow1;
                        duration1 = e.getDuration();
                        System.out.println("else if case 1");
                        break;
                    }
                    case 2: {
                        if (duration2 != 0) {
                            dataRow2 = oldname2;
                            dataRow2 = dataRow2 + e.getName();
                            duration2--;
                            System.out.println("Herhaling case 2");
                        } else if (timeCompare(d, e.getTime()))
                            dataRow2 = dataRow2 + e.getName();
                        oldname2 = dataRow2;
                        duration2 = e.getDuration();
                        System.out.println("else if case 2");
                        break;
                    }
                    case 3: {
                        if (duration3 != 0) {
                            dataRow3 = oldname3;
                            dataRow3 = dataRow3 + e.getName();
                            duration3--;
                        } else if (timeCompare(d, e.getTime()))
                            dataRow3 = dataRow3 + e.getName();
                        oldname3 = dataRow3;
                        duration3 = e.getDuration();
                        System.out.println("else if case 3");
                        break;
                    }
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
