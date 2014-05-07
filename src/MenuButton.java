import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;



/**
 * This class controls all user actions from Buttons.
 *
 * @author Rasel
 * Date modified 23/11/2012
 */
public class MenuButton implements ActionListener {
    private GameEngine game;

    /**
     * Constructor
     */
    public MenuButton(GameEngine game) {
        this.game = game;
    }

    /**
     * Performs action according to user interaction
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Easy":
                game.easyGame();
                break;
            case "Normal":
                game.normalGame();
                break;
            case "Hard":
                game.hardGame();
                break;
            case "Undo":
                game.undo();
                break;
            case "Redu":
                game.redu();
                break;
            case "Check":
                game.checkGame();
                break;
            case "Help":
                game.Help(true);
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                game.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
                break;
        }
    }
}