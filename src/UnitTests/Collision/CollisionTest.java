package UnitTests.Collision;
import AI.Npc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Lois Gussenhoven, 2118259 on 16-3-2017.
 */
public class CollisionTest extends JPanel implements ActionListener {


    public static void main(String[] args) {
        JFrame frame = new JFrame("CollisionTest");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(new CollisionTest());
        frame.setVisible(true);
    }

    ArrayList<Npc> npcs = new ArrayList<>();

    public CollisionTest() {
        while (npcs.size() < 1000) {
            Point2D spawnPoint = new Point2D.Double(Math.random() * 800, Math.random() * 800);
            if (canSpawn(spawnPoint))
                npcs.add(new Npc(spawnPoint));
        }
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                for (Npc c : npcs)
                    c.setDestination(new Point2D.Double(e.getX(), e.getY()));

            }
        });

        new Timer(10, this).start();

    }

    public boolean canSpawn(Point2D point)
    {
        for (Npc c : npcs) {
            if (c.getLocation().distance(point) < c.getWidth())
                return false;
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Npc c : npcs)
            c.draw(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Npc c : npcs)
            c.update(npcs);

        repaint();

    }

}
