package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jacco on 13/03/2017.
 */
public class ColoredJPanel extends JPanel {
    public ColoredJPanel()
    {
        super();
        setbg();
    }

    public ColoredJPanel(LayoutManager layoutManager)
    {
        super(layoutManager);
        setbg();

    }

    private void setbg()
    {
        setBackground(Color.pink);
    }
}
