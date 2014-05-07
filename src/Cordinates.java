import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * This class is for setting and retrieving the positions in the board.
 * @author Rasel
 * Date modified /10/2012
 */
public class Cordinates extends JLabel {
    private int x;      // X position in game.
    private int y;      // Y position in game.

    /**
     * Constructs the x and y positions in game.
     */
    public Cordinates(int x, int y) {
        super("", CENTER);
        this.x = x;
        this.y = y;
        
        setPreferredSize(new Dimension(40, 40));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        setOpaque(true);
    }

    /**
     * Sets the color.
     */
    public void setNumber(int number, boolean userInput) {
        setForeground(userInput ? Color.BLUE : Color.BLACK);
        setText(number > 0 ? number + "" : "");
    }

    
    public int getFieldX() {
        return x;
    }

    
    public int getFieldY() {
        return y;
    }
    
    public int getCor(){
       int x,y,temp[][] = null;
       x=this.getFieldX();
       y=this.getFieldY();
       return temp[x][y];
               
    }

 
}