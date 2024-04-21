import java.io.Console;

/**
 * A TicTacToe Game
 * @author Joonas Sipilä
 * @version 1.0
 */
public class TicTacToe {   

    /**
     * determines symbol for blank space in board
     */
    public static char symbol = '-';
    /**
     * determines if player has won. default false
     */
    public static boolean isPlayerWin = false;
     /**
     * determines if bot has won. default false
     */
    public static boolean isBotWin = false;

    /**
     * this is the main method
     * @param args main method
     */
    public static void main(String [] args) {
        System.out.println("");

        /**
         * asks user for size of the board
         */
        int boardSize = askboardsize();

        /**
        * creates new empty board
        */
        char[][] currentboard = newboard(boardSize);

        //switches turns between player and bot
        boolean isPlayerturn = true;

        /**
        * checks whose turn it is
        */
        checkturn(boardSize, currentboard, isPlayerturn);
    }

    /**this method determines whose turn it is and changes turns
     * @param boardSize size of the board
     * @param currentboard board with x's and o's saved to it
     * @param isPlayerturn determines whether an x or o will be added to the table. switches turns between player and bot.
    */
    public static void checkturn(int boardSize, char[][] currentboard, boolean isPlayerturn) {    

        //if players turn, symbol is x. switches to bots turn. calls playerturn method.
        if(isPlayerturn == true) {
            symbol = 'x';
            System.out.println("\n" +"Your Turn");
            isPlayerturn = false;
            userturn(boardSize, currentboard, isPlayerturn);
    
        //if bots turn, symbol is o. switches to players turn. calls botturn method.
        } else if(isPlayerturn == false) {
            symbol = 'o';
            System.out.println("\n" +"Bots Turn");
            isPlayerturn = true;
            botturn(boardSize, currentboard, isPlayerturn);
        }
    }
    
    //Asks boards size, board must be at least 3x3.
    /**
     * This method asks user what size they want for the board
     * @return size of the board
     */
    public static int askboardsize() {
        Console c = System.console();
        int boardSize = 0;
        while(boardSize < 3) {

            //Displays ascii-style welcome message to user. Explains some info briefly.
            //Saves users choice of boards size.
            System.out.println("|      Welcome to TicTacToe!      |");
            System.out.println("|     Please Enter Board Size     |");
            System.out.println("|   Board must be at least 3x3.   |");
            System.out.println("| Your symbol:[x] Bots symbol:[o] |");
            boardSize = Integer.parseInt(c.readLine());

            /**
            * checks if user wants more in-depth instructions.
            */
            wantinfo();

            //checks whether board is at least 3 in size.
            if(boardSize < 3) {
                System.out.println("\n" + "You entered size " +boardSize +". Board must be at least 3x3 in size \n");
            } else {
                System.out.println("\n" + "You entered size " +boardSize +". Lets begin.\n");

                //informs user about how many symbols they need in a row in order to win.
                if(boardSize < 10) {
                    System.out.println("With boardsize " +boardSize +" You need 3 symbols in a row to win.\n");
                } else {
                    System.out.println("With boardsize " +boardSize +" You need 5 symbols in a row to win.\n");
                }
            }
        }
        //returns determined size back to main method.
        return boardSize;
    }

    /**
     * asks user if they would like to see more in-depth instructions and prints instructions if told so
     */
    public static void wantinfo() { 
        Console c = System.console();   
        System.out.println("Would you like to see more instructions?");
        System.out.println("          | 1 = Yes | 2 = No |          ");
        int moreInfo = Integer.parseInt(c.readLine());

        //displays more info in ascii-style if user presses 1.
        if (moreInfo == 1) {
            System.out.println("");
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("                                    | INFORMATION! |                                    ");
            System.out.println("              You were asked what size you would like for your board to be              ");
            System.out.println(" If you chose your boardsize to be 10 or higher, your mission is to get 5 X's in a row. ");
            System.out.println("  If you chose your boardsize to be 9 or lower, your mission is to get 3 X's in a row.  ");
            System.out.println("");
            System.out.println("      You will be playing against a bot. Your symbol is [x] and bots symbol is [o]      ");
            System.out.println("               First player to get 3 or 5 symbols in a row wins the game.               ");
            System.out.println(" Filling the board with your symbols before either player has won will result in a tie. ");
            System.out.println("");
            System.out.println("                  You will start the game and pass turns with the bot.                  ");
            System.out.println("   Current situation of the board will be displayed after each move you or bot makes.   ");
            System.out.println("");
            System.out.println("                                    |  IMPORTANT!  |                                    ");
            System.out.println("Please notice that while horizontal and vertical lines both are checked for 3/5 in a row");
            System.out.println("in diagonal lines only 3 in a row check is implemented, even with board over 10 in size.");
            System.out.println("");
            System.out.println("     This is purely result of lack of time and understanding while making the game.     ");
            System.out.println("         This shouldn't affect too much on the gameplay once you're aware of it.        ");
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("");

        //doesn't display info is user presses 2.
        } else if (moreInfo == 2) {
            System.out.println("");
        //asks again if user didn't press 1 or 2.
        } else {
            wantinfo();
        }
    }

    /**
     * creates new board, prints it and sends it back to main method.
     * @param boardSize size of the board
     * @return new board gets saved as char[][] currentboard that will be changed after each turn
     */
    public static char[][] newboard(int boardSize) {
        char[][] board = new char[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                board[i][j] = '-';
            }
        }
        
        //prints new board.
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
        //sends it back to main method with new name "currentboard"
        return board;
    }


    /**
     * asks user to give coordinates for their symbol
     * @param boardSize size of the board
     * @param currentboard board with given symbols saved to it
     * @param isPlayerturn is it players or bots turn
     */
    public static void userturn(int boardSize, char[][]currentboard, boolean isPlayerturn) {
    Console c = System.console();
        int userH = -1;
        int userV = -1;
        System.out.println("");

        //asks for vertical symbol. userH and userV are messed up and other way around...
        while(userV > boardSize -1 || userV < 0) {
            System.out.println("Pick HORIZONTAL Coordinate");
            System.out.println("Coordinates must be between 0 and " +(boardSize -1));
            userV = Integer.parseInt(c.readLine());
        }
        System.out.println("");

        //asks for vertical symbol. userH and userV are messed up and other way around...
        while(userH > boardSize -1 || userH < 0) {
            System.out.println("Pick VERTICAL Coordinate");
            System.out.println("Coordinates must be between 0 and " +(boardSize -1));
            userH = Integer.parseInt(c.readLine());
        }

        //informs user what coordinates they choose.
        System.out.println("\nYou picked coordinates " +userH +" and " +userV +".");
        checksymbol(userH, userV, boardSize, currentboard, isPlayerturn);
    }

    //bots turn. generates random number in range.
    /**
     * generates random coordinates for bots symbol.
     * @param boardSize size of the board
     * @param currentboard board with given symbols saved to it
     * @param isPlayerturn is it players or bots turn
     */
    public static void botturn(int boardSize, char[][] currentboard, boolean isPlayerturn) {
        int userH = 0 + (int)(Math.random() * boardSize);
        int userV = 0 + (int)(Math.random() * boardSize);
        checksymbol(userH, userV, boardSize, currentboard, isPlayerturn);
    }

    /**
     * this method makes sure coordinates are within range and at a free slot
     * @param userH vertical coordinate of the symbol
     * @param userV horizontal coordinate of the symbol
     * @param boardSize size of the board
     * @param currentboard board with given symbols saved to it
     * @param isPlayerturn is it players or bots turn
     */
    public static void checksymbol (int userH, int userV, int boardSize, char[][] currentboard, boolean isPlayerturn) {
        
        while(true) {

            //Check if the position on the board the user entered is empty (has a -) or not
            if(currentboard[userH][userV] != '-') {
                
                //informs player if their coordinates arent in range.
                //doesn't show warning if it is bots turn in order to prevent spam.
                if(isPlayerturn == false) {
                    System.out.println("-------------------------------------------");
                    System.out.println("Slot already has a symbol in it. Try again.");
                    System.out.println("-------------------------------------------");
                    //takes user back to coordinate selection if given coordinates are incorrect.
                    userturn(boardSize, currentboard, isPlayerturn);

                } else {
                    //forces bot to choose new coordinates if given coordinates are incorrect.
                    botturn(boardSize, currentboard, isPlayerturn);
                    System.out.println("Bot chose coordinates " +userH +" and " +userV);
                }
            //if symbols position is valid, break the loop.
            } else { 
                break;
            }			
        }    

        //saves given coordinates to current board.
        currentboard[userH][userV] = symbol;

        //calls checkwin method.
        checkwin(boardSize, currentboard, isPlayerturn);
        //calls printboard method.
        printBoard(currentboard, boardSize, isPlayerturn);
    }
     
    /**
     * this method prints board with all given symbols in it.
     * @param currentboard board with given symbols saved to it
     * @param boardSize size of the board
     * @param isPlayerturn is it players or bots turn
     */
    public static void printBoard(char[][] currentboard, int boardSize, boolean isPlayerturn) {
        System.out.println("Board:\n");
        for(int i = 0; i < boardSize; i++) {
            //The inner for loop prints out each row of the board
            for(int j = 0; j < boardSize; j++) {
                System.out.print(currentboard[i][j]);
            }
            //This statement makes a new line so that each line is on its own new row.
            System.out.println();
        }
        //calls checkturn method to swith turns.
        checkturn(boardSize, currentboard, isPlayerturn);
    }

    /**
     * this method directs integers to other methods that are responsible for checking whether bot or player has 3 symbols in a row
     * @param boardSize size of the board
     * @param currentboard board with given symbols saved to it.
     * @param isPlayerturn is it players or bots tuen
     */
    public static void checkwin(int boardSize, char[][] currentboard, boolean isPlayerturn) {

        int triplex = 0;
        int tripleo = 0;
        int i = 0;
        int j = boardSize -1;

        //calls method that checks horizontal lines.
        checkHorizontal(triplex, tripleo, currentboard, i, j, boardSize);

        //calls method that checks vertical lines.
        checkVertical(triplex, tripleo, currentboard, i, j, boardSize);

        //calls multiple methods that check parts of diagonal lines.
        checkDiagsRight1(currentboard, boardSize);
        checkDiagsRight2(currentboard, boardSize);
        checkDiagsLeft1(currentboard, boardSize);
        checkDiagsLeft2(currentboard, boardSize);

        //check if board is full when neither of players have won.
        isboardfull(i, j, boardSize, currentboard);
    }

    /**
     * checks if the board is full of users/bots symbols. if board is full, calls tie-method.
     * @param i iterates through the loop
     * @param j works as a stopping point for int i's iteration
     * @param boardSize size of the board
     * @param currentboard board with given symbols saved to it
     */
    public static void isboardfull(int i, int j, int boardSize, char[][] currentboard) {
        int k = 0;
        int grid = 0;
        int gridsize = boardSize * boardSize;

        for(i = 0; i <= j; i++) {
            for(k = 0; k < boardSize; k++) {
                if (currentboard[i][k] != '-') {
                    grid ++;
                    if (grid == gridsize) {
                    tie(currentboard, boardSize);
                    }
                }
            }
        }
    }

    /**
     * checks whether horizontal lines have 3 same symbols in a row.
     * @param triplex value will rise if x's are noticed in the board
     * @param tripleo value will rise if o's are noticed in the board
     * @param currentboard board with given symbols saved to it
     * @param i works as a coordinate
     * @param j works as a coordinate
     * @param boardSize size of the board
     */
    public static void checkHorizontal(int triplex, int tripleo, char[][] currentboard, int i, int j, int boardSize) {
        for(int l = 0; l < boardSize; l++) {
            //this breaks the calculation of o's and x's once one line has been checked.
            //this prevents the game from making false win statements.
            triplex = 0;
            tripleo = 0;
            for(int k = 0; k < boardSize; k++) {
                //is board is less than 10 in size, 3 symbols in a row is enough to win.
                if (boardSize < 10) {
                    if(currentboard[l][k] == 'x') {
                        triplex++;
                        if(triplex ==3 && tripleo ==3){
                            //if code detects 3 x's in a row, it calls for playerwon method.
                            sameTime(currentboard, boardSize);
                            break;
                        } else if(triplex == 3) {
                            playerwon(currentboard, boardSize);
                            break;
                        }
                    } else if(currentboard[l][k] == 'o') {
                        tripleo++;
                        if(tripleo == 3) {
                            botwon(currentboard, boardSize);
                            break;
                        }
                    //resets values.
                    } else {
                        triplex = 0;
                        tripleo = 0;  
                    }      
                //if board size is 10 or more, 5 symbols in a row is enough to win.
                } else {
                    if(currentboard[l][k] == 'x') {
                        triplex++;
                        if(triplex ==5 && tripleo ==5){
                            //if code detects 3 x's in a row, it calls for playerwon method.
                            sameTime(currentboard, boardSize);
                            break;
                        } else if(triplex == 5) {
                            playerwon(currentboard, boardSize);
                            break;
                        }
                    } else if(currentboard[l][k] == 'o') {
                        tripleo++;
                        if(tripleo == 5) {
                            botwon(currentboard, boardSize);
                            break;
                        }
                    //resets values.
                    } else {
                        triplex = 0;
                        tripleo = 0; 
                    }       
                }
            }
        }
    }

    /**
     * checks whether vertical lines have 3 same symbols in a row.
     * @param triplex value will rise if x's are noticed in the board
     * @param tripleo value will rise if o's are noticed in the board
     * @param currentboard board with given symbols saved to it
     * @param i works as a coordinate
     * @param j works as a coordinate
     * @param boardSize size of the board
     */
    public static void checkVertical(int triplex, int tripleo, char[][] currentboard, int i, int j, int boardSize) {
        for(int k = 0; k < boardSize; k++) {
            //is board is less than 10 in size, 3 symbols in a row is enough to win.
            if(boardSize < 10) {
                //this breaks the calculation of o's and x's once one line has been checked.
                //this prevents the game from making false win statements.
                triplex = 0;
                tripleo = 0;
                for(int l = 0; l < boardSize; l++) {

                    if(currentboard[l][k] == 'x') {
                        triplex++;
                        if(triplex ==3 && tripleo ==3){
                            //if code detects 3 x's in a row, it calls for playerwon method.
                            sameTime(currentboard, boardSize);
                            break;
                        } else if(triplex == 3) {
                            playerwon(currentboard, boardSize);
                            break;
                        }
                    } else if(currentboard[l][k] == 'o') {
                        tripleo++;
                        //if code detects 3 o's in a row, it calls for botwon method.
                        if(tripleo == 3) {
                            botwon(currentboard, boardSize);
                            break;
                        }
                    //resets values.
                    } else {
                        triplex = 0;
                        tripleo = 0;
                    }
                }
                //if board size is 10 or more, 5 symbols in a row is enough to win.
            } else {
                //this breaks the calculation of o's and x's once one line has been checked.
                //this prevents the game from making false win statements.
                triplex = 0;
                tripleo = 0;
                for(int l = 0; l < boardSize; l++) {

                    if(currentboard[l][k] == 'x') {
                        triplex++;
                        if(triplex ==5 && tripleo ==5){
                            //if code detects 3 x's in a row, it calls for playerwon method.
                            sameTime(currentboard, boardSize);
                            break;
                        } else if(triplex == 5) {
                            playerwon(currentboard, boardSize);
                            break;
                        }
                    } else if(currentboard[l][k] == 'o') {
                        tripleo++;
                        //if code detects 3 o's in a row, it calls for botwon method.
                        if(tripleo == 5) {
                            botwon(currentboard, boardSize);
                            break;
                        }
                    //resets values.
                    } else {
                        triplex = 0;
                        tripleo = 0;
                    }
                }
            }
        }
    }

    /**
     * checks whether lines from lower half of diagonal lines from bottom left corner to upper right corner have 3 same symbols in a row.
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void checkDiagsRight1(char[][] currentboard, int boardSize) {
        String markstring = "";
        int maxdiag = boardSize;

        int col = 0;
        int row = 0;

        while(true) {
            for(int i = 0; i <maxdiag; i++) {
                markstring += currentboard[row][col];
                row++;
                col++;
            }
            if(markstring.contains("xxx")) {
                playerwon(currentboard, boardSize);
                break;
            } else if(markstring.contains("ooo")) {
                botwon(currentboard, boardSize);
                break;
            } else {
                maxdiag--;
                col = 0;
                row = boardSize - maxdiag;
                markstring = "";
            }
            if(maxdiag ==2){
                break;
            }
        }
    }

    /**
     * checks whether lines from upper half of diagonal lines from bottom left corner to upper right corner have 3 same symbols in a row.
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void checkDiagsRight2(char[][] currentboard, int boardSize) {
        String markstring = "";
        int maxdiag = boardSize;

        int col = 0;
        int row = 0;

        while(true) {
            for(int i = 0; i <maxdiag; i++) {
                markstring += currentboard[row][col];
                row++;
                col++;
            }
            if(markstring.contains("xxx")) {
                playerwon(currentboard, boardSize);
                break;
            } else if(markstring.contains("ooo")) {
                botwon(currentboard, boardSize);
                break;
            } else {
                maxdiag--;
                row = 0;
                col = boardSize - maxdiag;
                markstring = "";
            }
            if(maxdiag ==2){
                break;
            }
        }
    }

    /**
     * checks whether lines from lower half of diagonal lines from bottom right corner to upper left corner have 3 same symbols in a row.
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void checkDiagsLeft1(char[][] currentboard, int boardSize) {
        String markstring = "";
        int maxdiag = boardSize;

        int col = 0;
        int row = boardSize -1;

        while(true) {
            for(int i = 0; i <maxdiag; i++) {
                markstring += currentboard[row][col];
                row--;
                col++;
            }
            if(markstring.contains("xxx")) {
                playerwon(currentboard, boardSize);
                break;
            } else if(markstring.contains("ooo")) {
                botwon(currentboard, boardSize);
                break;
            } else {
                maxdiag--;
                row = boardSize -1;
                col = boardSize - maxdiag;
                markstring = "";
            }
            if(maxdiag ==2){
                break;
            }
        }
    }

    /**
     * checks whether lines from upper half of diagonal lines from bottom right corner to upper left corner have 3 same symbols in a row.
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */    
    public static void checkDiagsLeft2(char[][] currentboard, int boardSize) {
        String markstring = "";
        int maxdiag = boardSize;

        int col = 0;
        int row = boardSize -1;

        while(true) {
            for(int i = 0; i <maxdiag; i++) {
                markstring += currentboard[row][col];
                row--;
                col++;
            }
            if(markstring.contains("xxx")) {
                playerwon(currentboard, boardSize);
                break;
            } else if(markstring.contains("ooo")) {
                botwon(currentboard, boardSize);
                break;
            } else {
                maxdiag--;
                col = 0;
                row = maxdiag;
                markstring = "";
            }
            if(maxdiag ==2){
                break;
            }
        }
    }

    /**
     * displays a message to user if they won
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void playerwon(char[][] currentboard, int boardSize) {
        System.out.println("\n<[CONGRATULATIONS!]>");
        System.out.println("<[You Won The Game]>\n");
        //prints the final board of the game.
        lastboard(currentboard, boardSize);
        gameEnded();
    }

    /**
     * displays a message to user if bot won
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void botwon(char[][] currentboard, int boardSize) {
        System.out.println("\n<[Better Luck Next Time]>");
        System.out.println("<[  Bot Won The Game.  ]>\n");
        //prints the final board of the game.
        lastboard(currentboard, boardSize);
        gameEnded();
    }

    /**
     * displays a message to user if the board is full of symbols but neither player has won.
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void tie(char[][] currentboard, int boardSize) { 
        System.out.println("\n<[Board is Full]>");
        System.out.println("<[ It is a Tie ]>\n");
        //prints the final board of the game.
        lastboard(currentboard, boardSize);
        gameEnded();
    }
    
    /**
     * displays a message to user if they won, and bot won after users winning turn
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void sameTime(char[][] currentboard, int boardSize) { 
        System.out.println("\n<[CONGRATULATIONS!]>");
        System.out.println("<[You Won The Game]>\n");
        //prints the final board of the game.
        lastboard(currentboard, boardSize);
        gameEnded();
    }

    /**
     * displays a message for the user and exits the game once it has ended.
     */
    public static void gameEnded() {
        System.out.println("\nGame has Ended.  ");
        System.out.println("Thank you for playing!\n");

        System.exit(1);
    }

    /**
     * prints the last board of the game after win or tie condition has happened
     * @param currentboard board with given coordinates saved to it
     * @param boardSize size of the board
     */
    public static void lastboard(char[][] currentboard, int boardSize) {
        System.out.println("Final Board:\n");
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                System.out.print(currentboard[i][j]);
            }
            System.out.println("");
        }
    }
}
