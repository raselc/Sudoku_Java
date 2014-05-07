import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Controls user actions.
 *
 * @author Rasel
 * Date modified /9/2012
 */
public class GameController implements MouseListener {
    private GameLayout gameLayout;    
    private GameEngine game;           

    /**
     * Constructor, sets game.
     */
    public GameController(GameLayout gameLayout, GameEngine game) {
        this.gameLayout = gameLayout;
        this.game = game;
    }

    /**
     * Deals with mouse events
     */
    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel)e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof Cordinates) {
            Cordinates field = (Cordinates)component;
            int x = field.getFieldX();
            int y = field.getFieldY();

            if (e.getButton() == MouseEvent.BUTTON1 && (game.getNumber(x, y) == 0 || field.getForeground().equals(Color.BLUE))) {
                int number = game.getSelectedNumber();
                if (number == -1) {
                    return;
                }
                game.setNumber(x, y, number);
                field.setNumber(number, true);
            } else if (e.getButton() == MouseEvent.BUTTON3 && !field.getForeground().equals(Color.BLACK)) {
                game.setNumber(x, y, 0);
                field.setNumber(0, false);
            }
           
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    { }
    @Override
    public void mouseEntered(MouseEvent e) 
    { }
    @Override
    public void mouseExited(MouseEvent e) 
    { }
    @Override
    public void mouseReleased(MouseEvent e) 
    { }
}