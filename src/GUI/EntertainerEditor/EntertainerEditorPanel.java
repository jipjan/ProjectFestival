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

        JLabel duur = new JLabel("Duur");
        c.gridx = 0;
        c.gridy = 1;
        add(duur, c);

        _duur = new JSlider();
        c.gridx = 1;
        add(_duur, c);

        JLabel pop = new JLabel("Populariteit");
        c.gridx = 0;
        c.gridy = 2;
        add(pop, c);

        _populariteit = new JSlider();
        c.gridx = 1;
        add(_populariteit, c);

        JButton save = new JButton("Save");
        save.addActionListener((o) -> updateEvent());

        c.gridx = 1;
        c.gridy = 3;
        add(save, c);
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
