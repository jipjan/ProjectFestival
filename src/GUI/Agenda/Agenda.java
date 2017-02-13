package GUI.Agenda;

import Events.Event;
import GUI.MyPanel.TableObjectModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Agenda extends JPanel
{
    AbstractTableModel model;

    public AgendaGUI(Event event)
    {
        TableObjectModel guiModel = new TableObjectModel("Podium 1", "Podium 2", "Podium 3");
        //guiModel.setItems();
        this.setModel(guiModel);
    }

}
