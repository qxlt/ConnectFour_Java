public class Game {
    private String[][] gameHolder;
    private final int column;
    private final int row;
    private int availableMoves;
    private static final int MAX_DEPTH = 6;
    private static final String[] sign = new String[] {"√", "X"};

    public Game(){
        column = 7;
        row = 6;
        availableMoves = column * row;
        gameHolder = new String[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++){
                gameHolder[i][j] = "";
            }
        }
    }

    public int getColumn(){
        return column;
    }

    public int getRow(){
        return row;
    }

    public String[][] getGameHolder(){
        return gameHolder;
    }

    public String[] getSign(){
        return sign;
    }

    public int placeDisc(int column, String value){
        int loc = is_occupied(column);
        if(loc == -1) return -1;
        gameHolder[loc][column] = value;
        availableMoves--;
        return loc;
    }

    public void reverseDisc(int column) {
        for (int row = 0; row < this.row; row++) {
            if (!gameHolder[row][column].isEmpty()) {
                gameHolder[row][column] = "";
                availableMoves++;
                break;
            }
        }
    }


    public int is_occupied(int column){
        for (int i = 5; i > -1; i--) {
            if(gameHolder[i][column].isEmpty()){
                return i;
            }
        }
        return -1;
    }

    public boolean anyMovesAvailable(){
        return availableMoves>0;
    }

    public boolean is_connected(int row, int column, String value){
        if(check_vertically(row, column, value)||
        check_diagonal_rb_lt(row, column, value)||
        check_diagonal_lb_rt(row, column, value)||
        check_horizontally(row, column, value)){
            return true;
        }
        return false;
    }


    private boolean check_vertically(int row, int column, String value){
        if (row < 3) {
            return gameHolder[row + 1][column].equals(value) &&
                    gameHolder[row + 2][column].equals(value) &&
                    gameHolder[row + 3][column].equals(value);
        } else {
            return false;
        }
    }

    private boolean check_horizontally(int row, int column, String value){
        if(column<=3){
            if(gameHolder[row][column+1].equals(value) &&
                    gameHolder[row][column+2].equals(value)&&
                    gameHolder[row][column+3].equals(value)){
                return true;
            }
        }

        if(column < 5 && column > 0) {
            if (gameHolder[row][column - 1].equals(value) &&
                    gameHolder[row][column + 1].equals(value) &&
                    gameHolder[row][column + 2].equals(value)) {
                return true;
            }
        }

        if(column > 1 && column < 6) {
            if (gameHolder[row][column - 2].equals(value) &&
                    gameHolder[row][column - 1].equals(value) &&
                    gameHolder[row][column + 1].equals(value)) {
                return true;
            }
        }

        if(column >= 3) {
            if (gameHolder[row][column - 3].equals(value) &&
                    gameHolder[row][column - 1].equals(value) &&
                    gameHolder[row][column - 2].equals(value)) {
                return true;
            }
        }
        return false;

    }
    private boolean check_diagonal_rb_lt(int row, int column, String value) {
        // 2<column<7 2<row<6
        if(row > 2 && row < this.row && column < this.column && column > 2){
            if(gameHolder[row-1][column-1].equals(value) &&
                    gameHolder[row-2][column-2].equals(value) &&
                    gameHolder[row-3][column-3].equals(value)) {
                return true;
            }
        }

        // 1<column<6 1<row<5
        if(column > 1 && column < 6 && row < 5 && row > 1 ){
            if(gameHolder[row+1][column+1].equals(value)&&
                    gameHolder[row-1][column-1].equals(value)&&
                    gameHolder[row-2][column-2].equals(value)) {
                return true;
            }
        }

        // 0<column<5 0<row<4
        if(column > 0 && column < 5 && row > 0 && row < 4){
            if(gameHolder[row+2][column+2].equals(value)&&
                    gameHolder[row+1][column+1].equals(value)&&
                    gameHolder[row-1][column-1].equals(value)){
                return true;
            }
        }

        // 0<=column<4 0<row<3
        if (row < 3 && row >= 0 && column >= 0 && column < 4 ) {
            if(gameHolder[row+3][column+3].equals(value)&&
                    gameHolder[row+2][column+2].equals(value)&&
                    gameHolder[row+1][column+1].equals(value)){
                return true;
            }
        }
        return false;

    }
    private boolean check_diagonal_lb_rt(int row, int column, String value){
        // 0<=column<4 2<row<6
        if(column < 4 && column >= 0 && row > 2 && row < this.row){
            if(gameHolder[row-1][column+1].equals(value) &&
                    gameHolder[row-2][column+2].equals(value) &&
                    gameHolder[row-3][column+3].equals(value)) {
                return true;
            }
        }
        // 1<column<5 1<row<5
        if(column > 1 && column < 5 && row > 1 && row < 5){
            if(gameHolder[row+1][column-1].equals(value) &&
                    gameHolder[row-1][column+1].equals(value) &&
                    gameHolder[row-2][column+2].equals(value)) {
                return true;
            }
        }

        // 1<column<6 0<row<4
        if(column > 1 && column < 6 && row > 0 && row < 4){
            if(gameHolder[row+2][column-2].equals(value) &&
                    gameHolder[row+1][column-1].equals(value) &&
                    gameHolder[row-1][column+1].equals(value)) {
                return true;
            }
        }

        // 2<column<7 0<=row<3
        if(column > 2 && column < this.column && row < 3 && row >= 0){
            if(gameHolder[row+1][column-1].equals(value) &&
                    gameHolder[row+2][column-2].equals(value) &&
                    gameHolder[row+3][column-3].equals(value)) {
                return true;
            }
        }
        return false;
    }


    public void Display(){
        System.out.println("---------Connect-4---------");
        System.out.println("---------------------------");
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j <= column; j++) {
                System.out.print("|");
                if(j != 7){
                    if(gameHolder[i][j] != null){
                        System.out.print(gameHolder[i][j]);
                    }
                }
                System.out.print("\t");

            }
            System.out.println("\n----------------------------");
        }
        System.out.println("  1\t  2\t  3\t  4\t  5\t  6\t  7");
    }

    private Pair minimax(int depth, int alpha, int beta, boolean maximizingPlayer) {
        // return invalid value(-1) when one player won or no place available
        if (depth == 0 || evaluateBoard() == 100 || evaluateBoard() == -100 || !anyMovesAvailable()) {
            return new Pair(evaluateBoard(), -1);
        }

        // set up the first value to compare with an invalid value (-1)
        Pair bestMove = new Pair(maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE, -1);

        for (int col = 0; col < getColumn(); col++) {
            if (is_occupied(col) != -1) {
                // Try this move
                placeDisc(col, maximizingPlayer ? sign[1] : sign[0]);
                int score = minimax(depth - 1, alpha, beta, !maximizingPlayer).getKey();
                // Undo move
                reverseDisc(col);

                if (maximizingPlayer && score > bestMove.getKey()) {
                    bestMove = new Pair(score, col);
                    alpha = Math.max(alpha, score);
                } else if (!maximizingPlayer && score < bestMove.getKey()) {
                    bestMove = new Pair(score, col);
                    beta = Math.min(beta, score);
                }

                if (beta <= alpha) {
                    break;
                }
            }
        }

        return bestMove;
    }

    public Pair findBestMove(String player) {
        return minimax(MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, player.equals(sign[1]));
    }

    private int evaluateBoard() {
        int score = 0;

        // Score each cell in the board
        for (int row = 0; row < getRow(); row++) {
            for (int col = 0; col < getColumn(); col++) {
                if (getGameHolder()[row][col].equals(sign[1])) { // AI's disc
                    if (is_connected(row, col, sign[1])) {
                        score += 100;
                    }
                } else if (getGameHolder()[row][col].equals(sign[0])) { // Player's disc
                    if (is_connected(row, col, sign[0])) {
                        score -= 100;
                    }
                }
            }
        }
        return score;
    }

}
