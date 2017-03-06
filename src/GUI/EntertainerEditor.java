package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Events.Event;
import Events.Time;


/**
 * Created by thijs_000 on 06-Feb-17.
 */


public class EntertainerEditor extends JPanel
{

    public static void main(String[] args)
    {
        String testName = "TestName";
        short popularity = 5;
        Time time = new Time(10);
        EntertainerEditor editor = new EntertainerEditor(new Event(testName,popularity,time));

        JFrame testFrame = new JFrame();
        testFrame.setTitle("testFrame");
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        testFrame.add(editor);

        //testFrame.setContentPane(editor);
        testFrame.setVisible(true);

    }

    public void updateEvent(Events.Event event)
    {
        setBackground(Color.pink);
        JPanel name = new JPanel(new FlowLayout());
        name.setBackground(Color.pink);
        add(name);
        JLabel nameLbl = new JLabel("name:", SwingConstants.LEFT);
        name.add(nameLbl);
        JTextField nameText = new JTextField(event.getName(),21);
        name.add(nameText);

        boolean testing = true;
        JPanel time = new JPanel(new FlowLayout());
        time.setBackground(Color.pink);
        add(time);
        JLabel timeLbl = new JLabel("entertain time:", SwingConstants.LEFT);
        time.add(timeLbl);
        JTextField timeText = new JTextField(String.valueOf(event.getTime().getDurationInMinutes()),16);
        time.add(timeText);


        JPanel popularity = new JPanel(new FlowLayout());
        add(popularity);
        JLabel popLbl = new JLabel("popularity:", SwingConstants.LEFT);
        popularity.add(popLbl);
        JSlider popSlider = new JSlider(0,10,event.getPopularity());
        popularity.add(popSlider);
        JLabel popValue = new JLabel(String.valueOf(event.getPopularity()));
        popularity.add(popValue);
        popSlider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                String value = String.valueOf(popSlider.getValue());
                popValue.setText(value);
            }
        });


        JPanel empty1 = new JPanel(new FlowLayout());
        add(empty1);

        JPanel empty2 = new JPanel(new FlowLayout());
        add(empty2);

        JPanel empty3 = new JPanel(new FlowLayout());
        add(empty3);

        JPanel save = new JPanel(new FlowLayout());
        add(save);
        JButton saveBut = new JButton("save");
        saveBut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                event.set_name(nameText.getText());
                event.set_popularity((short) popSlider.getValue());
                event.set_time(new Time(Integer.valueOf(timeText.getText())));
                if(testing == true)
                {
                    System.out.println(event.getName());
                    System.out.println(event.getPopularity());
                    System.out.println(event.getTime().getDurationInMinutes());
                }
            }
        });
        save.add(saveBut);
    }

    public EntertainerEditor(Events.Event event)
    {
        super(new GridLayout(7,1));
        updateEvent( event);
    }
}
