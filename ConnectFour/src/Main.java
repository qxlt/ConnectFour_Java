import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int turn;
    public static void main(String[] args) {
        while(true){
            // set up the template
            System.out.println("Hello! Welcome to Connect Four!");

            // import a scanner
            Scanner obj = new Scanner(System.in);
            Random rand = new Random();
            // initiate the game
            Game newgame = new Game();
            String first_player;
            String second_player;
            String currentplayer;
            String[] players;
            int col;
            int row;

            System.out.println("Please choose(type 1/2)\n1. Play with friend\n2. Play with robot");
            String choice = obj.nextLine();

            // Play with friend
            if(choice.equals("1")){
                // getting players info - First Player Default √ Second Player Default X
                System.out.println("Awesome! Please type player one's name below");
                String player_name_one = obj.nextLine();
                System.out.println("Player Two's Name");
                String player_name_two = obj.nextLine();
                System.out.println("Player One: " + player_name_one + " Symbol: " + newgame.getSign()[0]+ "\nPlayer Two: "
                        + player_name_two + " Symbol: " + newgame.getSign()[1] );

                newgame.Display();

                // decides who goes first
                if(rand.nextBoolean()){
                    first_player = player_name_one;
                    second_player = player_name_two;
                }
                else{
                    first_player = player_name_two;
                    second_player = player_name_one;
                }

                players = new String[] {first_player, second_player};
                // announce who goes first
                System.out.println(first_player+ " goes first...");

                // game will happen until one player wins
                while(true){
                    currentplayer = players[turn % 2];
                    System.out.println(currentplayer+ "'s turn to choose spot: ");
                    while(true){
                        try{
                            col = obj.nextInt()-1;
                            row = newgame.placeDisc(col,newgame.getSign()[0]);
                            break;
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("You cannot place your disc out of the game board. " +
                                    "\nPlease re-select it");
                            newgame.Display();
                        }
                    }
                    newgame.Display();
                    if(newgame.is_connected(row, col, newgame.getSign()[0])) {
                        break;
                    }
                    turn++;
                    currentplayer = players[turn % 2];
                    System.out.println(currentplayer+ "'s turn to choose spot: ");
                    while(true){
                        try{
                            col = obj.nextInt()-1;
                            row = newgame.placeDisc(col,newgame.getSign()[1]);
                            break;
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("You cannot place your disc out of the game board. " +
                                    "\nPlease re-select it");
                            newgame.Display();
                        }
                    }
                    newgame.Display();
                    if(newgame.is_connected(row, col, newgame.getSign()[1])) {
                        turn++;
                        break;
                    }
                    turn++;
                }
                System.out.println(currentplayer + " won! Good Game!");

            }
            // play with robot
            else if (choice.equals("2")) {
                System.out.println("Be Ready to Fight Against the Powerful Robot!");
                players = new String[] {"You", "Robot"};
                newgame.Display();
                while (true) {
                    try {
                        // get player location by asking them - keep validating if it's in the index
                        System.out.println("It's your turn to place the disc");
                        col = obj.nextInt() - 1;
                        row = newgame.placeDisc(col, newgame.getSign()[0]);
                        if (row == -1) {
                            System.out.println("Column is full. Please select another column.");
                            continue;
                        }
                    }catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("You cannot place your disc out of the game board. " +
                                "\nPlease re-select it");
                        continue;
                    }
                    currentplayer = players[turn % 2];
                    turn++;
                    // check if connected
                    if (newgame.is_connected(row, col, newgame.getSign()[0])){
                        System.out.println(currentplayer + " Won!");
                        break;
                    }
                    newgame.Display();
                    System.out.println("Robot's turn to place the disc");
                    col = newgame.findBestMove(newgame.getSign()[1]).getValue();
                    row = newgame.placeDisc(col, newgame.getSign()[1]);
                    currentplayer = players[turn % 2];
                    newgame.Display();
                    if (newgame.is_connected(row, col, newgame.getSign()[1])) {
                        System.out.println(currentplayer + " Won!");
                        break;
                    }
                    turn++;
                    if(!newgame.anyMovesAvailable()) {
                        System.out.println("No More Available Moves. \n-Draw-");
                        break;
                    }

                }


            }
            System.out.println("Would you like have another game? Y to continue");
            obj.nextLine();
            String go_on = obj.nextLine();
            if(!go_on.equalsIgnoreCase("y")){
                System.out.println("Exiting...");
                break;
            }
        }
    }

}