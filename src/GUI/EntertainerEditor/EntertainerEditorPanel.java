package GUI.EntertainerEditor;


import GUI.ColoredJPanel;
import Events.Event;

import javax.swing.*;
import java.awt.*;

public class EntertainerEditorPanel extends ColoredJPanel{

    private JTable _linkedTable;
    private Event _event;
    private JTextField _name;
    private JSlider _duur;
    private JSlider _populariteit;

    public EntertainerEditorPanel(JTable t) {
        super(new GridLayout(0, 2));

        _linkedTable = t;

        add(new JLabel("Naam"));
        add(_name = new JTextField());

        add(new JLabel("Duur"));
        add(_duur = new JSlider());
        add(new JLabel("Populariteit"));
        add(_populariteit = new JSlider());

        JButton save = new JButton("Save");
        save.addActionListener((o) -> updateEvent());
        add(save);
    }

    private void updateEvent() {
        _event.setName(_name.getText());
        _event.setDuration(_duur.getValue());
        _event.setPopularity((short)_populariteit.getValue());
        _linkedTable.tableChanged(null);
    }

    public void setEvent(Event e){
        _event = e;
        _name.setText(_event.getName());
        _duur.setValue((int) _event.getDuration());
        _populariteit.setValue(_event.getPopularity());
        System.out.println(e.getName());
    }
}
