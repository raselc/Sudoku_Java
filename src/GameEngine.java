import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Put the values in the board, contains the solution, the user input,help 
 * and check the game.
 * @author Rasel
 * Date modified 26/11/2012
 */
public final class GameEngine extends Observable {
    private int[][] solution;      // Generates solution.
    private int[][] game;          // Generates game with user input.
    private boolean[][] check;     // Checks the validity of game.
    private int selectedNumber;    // Selected number by user.
    private boolean help =false;   // Help turned on or off.
    

    /**
     * Constructor
     */
    public GameEngine() {
        easyGame();
        normalGame();
        hardGame();
        check = new boolean[9][9];
    }

    /**
     * Makes the new easy game
    */
    public void easyGame() {
        
        solution = genSolution(new int[9][9],1);
        game = generateGame(copy(solution));
        setChanged();
        notifyObservers(UpdateEvent.EASY_GAME);
    }
    /**
     * Makes the new normal game
    */
    public void normalGame(){
        solution = genSolution(new int[9][9],2);
        game = generateGame(copy(solution));
        setChanged();
        notifyObservers(UpdateEvent.NORMAL_GAME);
        
    }
    
    /**
     * Makes the new hard game
    */
    public void hardGame(){
        solution = genSolution(new int[9][9],3);
        game = generateGame(copy(solution));
        setChanged();
        notifyObservers(UpdateEvent.HARD_GAME);
        
    }
    /**
     * Sets help on or off
     *  
    */
    public void Help(boolean helpp) {
        this.help = helpp;
        setChanged();
        notifyObservers(UpdateEvent.HELP);
    }
    
    /**
     * Returns whether selected number is at a given position.
    */
    public boolean SelectedHelpNumber(int x, int y) {
        return solution[x][y] == selectedNumber ;
    }
    /*
     * Returns whether help is on or off
    */
    
    public boolean isHelp() {
        return help;
    }

    /**
     * Checks user input against the solution
    */
    public void checkGame() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                check[x][y] = game[x][y] == solution[x][y];
            }
        }
        setChanged();
        notifyObservers(UpdateEvent.CHECK);
    }
    /**
     * goes back 1 step
     */
    
    public void redu(){
        
        setChanged();
        notifyObservers(UpdateEvent.REDU);
    }
    /*
     * goes front 1 step
     */
    public void undo(){
        setChanged();
        notifyObservers(UpdateEvent.UNDO);
    }
    /**
     * Sets selected number to user input.
     *
     */
    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
        setChanged();
        notifyObservers(UpdateEvent.SELECTED_NUMBER);
    }

    /**
     * Returns number selected user.
     */
    public int getSelectedNumber() {
        return selectedNumber;
    }
    
    /**
     * Sets the given number on the specific position in the board.
     */
    public void setNumber(int x, int y, int number) {
        game[x][y] = number;
    }

    /**
     * Gets the number of given position.
     */
    public int getNumber(int x, int y) {
        return game[x][y];
    }

    /**
     * Returns whether user input is valid of given position.
     */
    public boolean IsValid(int x, int y) {
        return check[x][y];
    }

    /**
     * Returns whether given number is on x axis for given game.
     */
    private boolean IsXaxis(int[][] game, int xaxis, int number) {
        for (int x = 0; x < 9; x++) {
            if (game[xaxis][x] == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether given number is on y axis for given game.
     */
    private boolean IsYaxis(int[][] game, int yaxis, int number) {
        for (int y = 0; y < 9; y++) {
            if (game[y][yaxis] == number) {
                return false;
            }
        }
        return true;
    }
    /**
     * Returns whether the given number is in block for given game.
     *
     */
    private boolean isPossibleBlock(int[][] game, int x, int y, int number) {
        int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int xx = x1; xx < x1 + 3; xx++)  {
            for (int yy = y1; yy < y1 + 3; yy++){
                if (game[xx][yy] == number)
                    return false;
            }
        }
        return true;
    }

     /**
     * Returns next number for the solution
     */
    private int getNextNumber(int[][] game, int x, int y, List<Integer> numbers) {
        while (numbers.size() > 0) {
            int number = numbers.remove(0);
            if (IsXaxis(game, x, number) && IsYaxis(game, y, number)&& isPossibleBlock(game, x, y, number)) {
                return number;
            }
        }
        return -1;
    }
    /*
     * Returns a number for a board and also generates the board
     */
    private int[][] genBoard(int[][] game, int index) {
        if (index > 80)
            return game;

        int x = index % 9;
        int y = index / 9;

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        while (numbers.size() > 0) {
            int number = getNextNumber(game, x, y, numbers);
            if (number == -1)
                return null;

            game[x][y] = number;
            int[][] tmpGame = genBoard(game, index + 1);
            if (tmpGame != null)
                return tmpGame;
            game[x][y] = 0;
        }

        return null;
    }
    /**
     * Game solutions.
    */
    private int[][] genSolution(int[][] game, int dif) {
        
        if (dif ==1){
            game= genBoard(new int [9][9],0);
        }
        else if(dif==2){
            game= genBoard(new int [9][9],0);
        }
        else if (dif ==3){
            game= genBoard(new int [9][9],0);
        } 
        return game;
    }

    /**
     * Generates game from solution [for the user].
     */
    private int[][] generateGame(int[][] game) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions);
        return generateGame(game, positions);
    }

    /**
     * Generates game from solution.
     */
    private int[][] generateGame(int[][] game, List<Integer> positions) {
        while (positions.size() > 0) {
            int position = positions.remove(0);
            int x = position % 9;
            int y = position / 9;
            int temp = game[x][y];
            game[x][y] = 0;

            if (!checkGame(game)) {
                game[x][y] = temp;
            }
        }

        return game;
    }

    /**
     * Checks whether game is valid.
     */
    private boolean checkGame(int[][] game) {
        return validGame(game, 0, new int[] { 0 });
    }

    /**
     * Checks whether game is valid.
     */
    private boolean validGame(int[][] game, int index, int[] temp) {
        if (index > 80) {
            return ++temp[0] == 1;
        }

        int x = index % 9;
        int y = index / 9;

        if (game[x][y] == 0) {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                numbers.add(i);
            }

            while (numbers.size() > 0) {
                int number = getNextNumber(game, x, y, numbers);
                if (number == -1) {
                    break;
                }
                game[x][y] = number;

                if (!validGame(game, index + 1, temp)) {
                    game[x][y] = 0;
                    return false;
                }
                game[x][y] = 0;
            }
        } else if (!validGame(game, index + 1, temp)) {
            return false;
        }

        return true;
    }

    /**
     * Copies the solution.
    */
    private int[][] copy(int[][] game) {
        int[][] copy = new int[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                copy[y][x] = game[y][x];
            }
        }
        
        return copy;
    }

}