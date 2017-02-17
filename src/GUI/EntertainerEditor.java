package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by thijs_000 on 06-Feb-17.
 */


public class EntertainerEditor extends JFrame
{
    public static void main(String[] args)
    {
        EntertainerEditor editor = new EntertainerEditor();
    }


    public EntertainerEditor()
    {
        super("Entertainer Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel editor = new JPanel(new GridLayout(7,1));

        JPanel name = new JPanel(new FlowLayout());
        editor.add(name);
        JLabel nameLbl = new JLabel("name:", SwingConstants.LEFT);
        name.add(nameLbl);
        JTextField nameText = new JTextField("",20);
        name.add(nameText);

        JPanel time = new JPanel(new FlowLayout());
        editor.add(time);
        JLabel timeLbl = new JLabel("entertain time:", SwingConstants.LEFT);
        time.add(timeLbl);
        String[] comboTimes = { "1 hour", "2 hour", "3 hour", "4 hour", "5 hour" };
        JComboBox timeCombo = new JComboBox(comboTimes);
        timeCombo.setSelectedIndex(0);
        timeCombo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        time.add(timeCombo);


        JPanel popularity = new JPanel(new FlowLayout());
        editor.add(popularity);
        JLabel popLbl = new JLabel("popularity:", SwingConstants.LEFT);
        popularity.add(popLbl);
        JSlider popSlider = new JSlider(0,100,4);
        popularity.add(popSlider);

        JPanel empty1 = new JPanel(new FlowLayout());
        editor.add(empty1);

        JPanel empty2 = new JPanel(new FlowLayout());
        editor.add(empty2);

        JPanel empty3 = new JPanel(new FlowLayout());
        editor.add(empty3);

        JPanel save = new JPanel(new FlowLayout());
        editor.add(save);
        JButton saveBut = new JButton("save");
        saveBut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        save.add(saveBut);


        setContentPane(editor);
        setSize(300, 400);
        setVisible(true);
    }
}
