

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class of program.
 *
 * @author Rasel
 * Date modified /9/2012
 */
public class Main extends JFrame {
    private static Main Main;
    
    public Main() {
        super("Main");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setTitle("Rasel's Sudoku");
        GameEngine game = new GameEngine();

        MenuButton buttonController = new MenuButton(game);
        MenuLayout buttonPanel = new MenuLayout();
        buttonPanel.setController(buttonController);
        add(buttonPanel, BorderLayout.EAST);

        GameLayout sudokuPanel = new GameLayout();
        GameController sudokuController = new GameController(sudokuPanel, game);
        sudokuPanel.setGame(game);
        sudokuPanel.setController(sudokuController);
        add(sudokuPanel, BorderLayout.CENTER);

        game.addObserver(buttonPanel);
        game.addObserver(sudokuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    /**
     * Main entry point of program.
     */
    public static void main(String[] args) {
        // Use System Look and Feel
        try 
            { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
            {}
        Main = new Main();
        
    }
}