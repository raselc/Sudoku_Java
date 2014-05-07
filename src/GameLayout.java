import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Draws the game board and reacts to updates 
 *
 * @author Rasel
 * Date modified 23/11/2012
 */
public class GameLayout extends JPanel implements Observer {
    // Color constant for help cells.
    private static final Color ColorHelp = new Color(135,206,235);
     private Cordinates[][] fields;  
     private JPanel[][] layout;      
    

    /**
     * Constructs the grid, adds sub grid and add fields
     */
    public GameLayout() {
        super(new GridLayout(3, 3));

        layout = new JPanel[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                layout[y][x] = new JPanel(new GridLayout(3, 3));
                layout[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(layout[y][x]);
            }
        }

        fields = new Cordinates[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x] = new Cordinates(x, y);
                layout[y / 3][x / 3].add(fields[y][x]);
            }
        }
    }
    /**
     * Adds controller
     */
    public void setController(GameController gameController) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                layout[y][x].addMouseListener(gameController);
            }
        }
    }

    /**
     * Send update notifications.
     */
    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateEvent)arg) {
            case EASY_GAME:
                setGame((GameEngine)o);
                break;
            case NORMAL_GAME:
                setGame((GameEngine)o);
                break;
            case HARD_GAME:
                setGame((GameEngine)o);
                break;
            case UNDO:
                setUndo((GameEngine)o);
                break;
            case REDU:
                setRedu((GameEngine)o);
            case CHECK:
                 setGameCheck((GameEngine)o);
                break;
            case HELP:
                setHelp((GameEngine)o);
            case SELECTED_NUMBER:
                 break;
        }
    }
    
    /**
     * Highlights the numbers of the selected number of game for the help.
     */
    private void setHelp(GameEngine game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x].setBackground(Color.WHITE);
                if (game.isHelp() && game.SelectedHelpNumber(x, y))
                    fields[y][x].setBackground(ColorHelp);
            }
        }
    }

    /**
     * Sets the box according to the game.
     */
    public void setGame(GameEngine game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x].setBackground(Color.WHITE);
                fields[y][x].setNumber(game.getNumber(x, y), false);
            }
        }
    }

    /**
     * Sets fields validity according the game.
     */
    private void setGameCheck(GameEngine game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x].setBackground(Color.WHITE);
                if (fields[y][x].getForeground().equals(Color.BLUE)) {
                    fields[y][x].setBackground(game.IsValid(x, y) ? Color.GREEN : Color.RED);
                }
            }
        }
     }
    /*
     * sets the previous value
     */
    private void setRedu(GameEngine game){
        int x,y;
        x= this.getX();
        y=this.getY();
        fields[y][x].setNumber(2, true);
        
               
    }
    /*
     * set the current value
     */
    private void setUndo(GameEngine a ){
        int x= a.getSelectedNumber();
        int y =a.getSelectedNumber();
        fields[y][x].setNumber(22, true);
    }

  
    
}