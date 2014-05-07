import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


/**
 * This class draws the menu buttons and reacts to updates from the model.
 *
 * @author Rasel
 * Date modified 23/11/2012
 */
public class MenuLayout extends JPanel implements Observer {
    JButton btnEasy,btnNormal,btnHard,btnRedu,btnUndo, btnCheck, btnExit,btnHelp; 
    ButtonGroup btnGrpNumbers;          
    JToggleButton[] btnNumbers;     

    /**
     * Constructs the panel and arranges all components.
     */
    public MenuLayout() {
        
        super(new BorderLayout());

        JPanel menuAlign = new JPanel();
        menuAlign.setLayout(new BoxLayout(menuAlign, BoxLayout.PAGE_AXIS));
        add(menuAlign, BorderLayout.NORTH);

        JPanel menuNumbers = new JPanel();
        menuNumbers.setLayout(new BoxLayout(menuNumbers, BoxLayout.PAGE_AXIS));
        menuNumbers.setBorder(BorderFactory.createTitledBorder(" Numbers "));
        menuAlign.add(menuNumbers);
        
        JPanel menuNum = new JPanel(new FlowLayout(FlowLayout.LEADING));
        menuNumbers.add(menuNum);
        
        JPanel menuNum1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        menuNumbers.add(menuNum1);
        
        JPanel menuNum2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        menuNumbers.add(menuNum2);

        btnGrpNumbers = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
        for (int i = 0; i < 3; i++) {
            btnNumbers[i] = new JToggleButton("" + (i+1));
            btnNumbers[i].setPreferredSize(new Dimension(40, 40));
            btnNumbers[i].setFocusable(false);
            btnGrpNumbers.add(btnNumbers[i]);
            menuNum.add(btnNumbers[i]);
        }
        
        for (int i = 3; i < 6; i++) {
            btnNumbers[i] = new JToggleButton("" + (i+1));
            btnNumbers[i].setPreferredSize(new Dimension(40, 40));
            btnNumbers[i].setFocusable(false);
            btnGrpNumbers.add(btnNumbers[i]);
            menuNum1.add(btnNumbers[i]);
        }
        for (int i = 6; i < 9; i++) {
            btnNumbers[i] = new JToggleButton("" + (i+1));
            btnNumbers[i].setPreferredSize(new Dimension(40, 40));
            btnNumbers[i].setFocusable(false);
            btnGrpNumbers.add(btnNumbers[i]);
            menuNum2.add(btnNumbers[i]);
        }
        
        
        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        menuAlign.add(pnlOptions);
        
        JPanel pnlOptions1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        menuAlign.add(pnlOptions1);
        
        JPanel pnlOptions2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        menuAlign.add(pnlOptions2);

        btnEasy = new JButton("Easy");
        btnEasy.setFocusable(false);
        pnlOptions.add(btnEasy);
        
        btnNormal = new JButton("Normal");
        btnNormal.setFocusable(false);
        pnlOptions.add(btnNormal);
        
        btnHard = new JButton("Hard");
        btnHard.setFocusable(false);
        pnlOptions.add(btnHard);

        btnCheck = new JButton("Check");
        btnCheck.setFocusable(false);
        pnlOptions1.add(btnCheck);
        
        btnUndo = new JButton("Undo");
        btnUndo.setFocusable(false);
        pnlOptions1.add(btnUndo);
        
        btnRedu = new JButton("Redu");
        btnRedu.setFocusable(false);
        pnlOptions1.add(btnRedu);
        
        btnHelp = new JButton("Help");
        btnHelp.setFocusable(false);
        pnlOptions1.add(btnHelp);
        
        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        pnlOptions2.add(btnExit);
    }

    /**
     * Sends updates
     */
    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateEvent)arg) {
            case EASY_GAME:
                btnGrpNumbers.clearSelection();
                break;
            case NORMAL_GAME:
                btnGrpNumbers.clearSelection();
                break;
            case HARD_GAME:
                btnGrpNumbers.clearSelection();
                break;
            case UNDO:
                btnGrpNumbers.clearSelection();
                break;
            case REDU:
                btnGrpNumbers.clearSelection();
                break;
            case CHECK:
                btnGrpNumbers.clearSelection();
                break;
        }
    }

    /**
     * Incorporate controller to all components.
    */
    public void setController(MenuButton buttonController) {
        btnEasy.addActionListener(buttonController);
        btnNormal.addActionListener(buttonController);
        btnHard.addActionListener(buttonController);
        btnUndo.addActionListener(buttonController);
        btnRedu.addActionListener(buttonController);
        btnCheck.addActionListener(buttonController);
        btnExit.addActionListener(buttonController);
        btnHelp.addActionListener(buttonController);
        for (int i = 0; i < 9; i++) {
            btnNumbers[i].addActionListener(buttonController);
        }
    }
}