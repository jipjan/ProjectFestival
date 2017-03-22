package GUI.EntertainerEditor;


import GUI.ColoredJPanel;
import Events.Event;

import javax.swing.*;
import java.awt.*;

public class EntertainerEditorPanel extends ColoredJPanel{

    private JTable _linkedTable;
    private Event _event;
    private JTextField _name, _performer;
    private JSpinner _duur;
    private JSlider _populariteit;

    public EntertainerEditorPanel(JTable t) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        _linkedTable = t;

        JLabel name = new JLabel("Naam");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(name, c);

        _name = new JTextField();
        c.gridx = 1;
        add(_name, c);

        JLabel perf = new JLabel("Performer");
        c.gridx = 0;
        c.gridy = 1;
        add(perf, c);

        _performer = new JTextField();
        c.gridx = 1;
        add(_performer, c);

        JLabel duur = new JLabel("Duur");
        c.gridx = 0;
        c.gridy = 2;
        add(duur, c);

        SpinnerNumberModel m = new SpinnerNumberModel(30, 5, 1440, 5);
        _duur = new JSpinner(m);
        _duur.setEditor(new JSpinner.NumberEditor(_duur));
        c.gridx = 1;
        add(_duur, c);

        JLabel pop = new JLabel("Populariteit");
        c.gridx = 0;
        c.gridy = 3;
        add(pop, c);

        _populariteit = new JSlider();
        _populariteit.setMinimum(1);
        _populariteit.setMaximum(10);
        _populariteit.setPaintTicks(true);
        _populariteit.setPaintLabels(true);
        _populariteit.setMajorTickSpacing(1);
        c.gridx = 1;
        add(_populariteit, c);

        JButton save = new JButton("Save");
        save.addActionListener((o) -> updateEvent());

        c.gridx = 1;
        c.gridy = 4;
        add(save, c);

        setAllEnabled(false);
    }

    private void setAllEnabled(boolean b) {
        for (Component co : getComponents())
            co.setEnabled(b);
    }

    private void updateEvent() {
        _event.setName(_name.getText());
        _event.setPerformer(_performer.getText());
        _event.setDuration((int) _duur.getValue());
        _event.setPopularity((short)_populariteit.getValue());
        _linkedTable.tableChanged(null);
    }

    public void setEvent(Event e){
        if (e == null) return;
        setAllEnabled(true);
        _event = e;
        _name.setText(_event.getName());
        _performer.setText(_event.getPerformer());
        _duur.setValue((int) _event.getDuration());
        _populariteit.setValue(_event.getPopularity());
        System.out.println(e.getName());
    }
}
