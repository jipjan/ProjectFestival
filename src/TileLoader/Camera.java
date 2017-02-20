import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Camera
        implements MouseListener, MouseMotionListener, MouseWheelListener
{
    Point2D centerPoint = new Point2D.Double(0.0D, 0.0D);
    double zoom = 1.0D;
    double rotation = 0.0D;
    Point2D lastMousePos;
    JPanel panel;

    Camera(JPanel panel)
    {
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
    }

    public AffineTransform getTransform(int windowWidth, int windowHeight)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(windowWidth / 2, windowHeight / 2);
        tx.scale(this.zoom, this.zoom);
        tx.translate(this.centerPoint.getX(), this.centerPoint.getY());
        tx.rotate(this.rotation);
        return tx;
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e)
    {
        this.lastMousePos = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e)
    {
        if (SwingUtilities.isMiddleMouseButton(e))
        {
            this.centerPoint = new Point2D.Double(this.centerPoint.getX() - (this.lastMousePos.getX() - e.getX()) / this.zoom, this.centerPoint.getY() - (this.lastMousePos.getY() - e.getY()) / this.zoom);

            this.lastMousePos = e.getPoint();
            this.panel.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {}

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        this.zoom *= (1.0F - e.getWheelRotation() / 25.0F);
        this.panel.repaint();
    }
}
