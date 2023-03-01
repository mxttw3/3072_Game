import java.util.Scanner;

public class Menu {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static void main(String[] args) throws Exception {
        boolean[] achivements = new boolean[4];
        achivements = menuPrincipal(achivements);
    }

    public static boolean[] menuPrincipal(boolean[] achivements) {
    //Variables menu principal
        Scanner read = new Scanner(System.in);
        int[][] board = new int[4][4];
        boolean error = false;
        int bucle = 0, SquareNum = 0, whatRandom = 3, subMenu = 0, control = 0;
        boolean[] difficultiesPlayed = new boolean[3];

    //Clean screen
    for (int i = 0; i < 69; i++) {
        System.out.println();
    }

    System.out.println("""
    ██████╗  ██████╗ ███████╗██████╗      ██████╗  █████╗ ███╗   ███╗███████╗
    ╚════██╗██╔═████╗╚════██║╚════██╗    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝
     █████╔╝██║██╔██║    ██╔╝ █████╔╝    ██║  ███╗███████║██╔████╔██║█████╗  
     ╚═══██╗████╔╝██║   ██╔╝ ██╔═══╝     ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  
    ██████╔╝╚██████╔╝   ██║  ███████╗    ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗
    ╚═════╝  ╚═════╝    ╚═╝  ╚══════╝     ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝\n""");

    int menu = Game.INTControl("""
        |─────────────────|
        | 1. Play         |
        | 2. Achivements  |
        | 3. Exit         |
        |─────────────────|""", 1, 3);

    if(menu >=1 && menu <=2){

        switch (menu) {
            case 1 ->{
                do{
                    //Clean screen
                    for (int i = 0; i < 69; i++) {
                        System.out.println();
                    }
                    System.out.println("""        
                        ██████╗ ██╗███████╗███████╗██╗ ██████╗██╗   ██╗██╗  ████████╗██╗   ██╗
                        ██╔══██╗██║██╔════╝██╔════╝██║██╔════╝██║   ██║██║  ╚══██╔══╝╚██╗ ██╔╝
                        ██║  ██║██║█████╗  █████╗  ██║██║     ██║   ██║██║     ██║    ╚████╔╝ 
                        ██║  ██║██║██╔══╝  ██╔══╝  ██║██║     ██║   ██║██║     ██║     ╚██╔╝  
                        ██████╔╝██║██║     ██║     ██║╚██████╗╚██████╔╝███████╗██║      ██║   
                        ╚═════╝ ╚═╝╚═╝     ╚═╝     ╚═╝ ╚═════╝ ╚═════╝ ╚══════╝╚═╝      ╚═╝\n""");
                    subMenu = Game.INTControl("""
                        |─────────────────|
                        | 1. Demon        |
                        | 2. Medium       |
                        | 3. Easy peasy   |
                        | 4. Exit         |
                        |─────────────────|""", 1, 5);
                    switch (subMenu) {
                        case 1 ->{
                            SquareNum = 3;
                            difficultiesPlayed[0] = true;
                        }
                        case 2 ->{
                            SquareNum = 4;
                            difficultiesPlayed[1] = true;
                        }
                        case 3 ->{
                            SquareNum = 5;
                            difficultiesPlayed[2] = true;
                        }
                        case 4 ->{
                            menuPrincipal(achivements);
                        }
                        case 5 ->{
                            int EasterEgg = Game.INTControl("""
                            |─────────────────────────────────────|
                            |    YOU DISCOVERED THE EASTER EGG    |
                            | WITH WHAT NUMBER YOU WANT TO START? |
                            |               2 to 5                |
                            |─────────────────────────────────────|""", 2, 5);
                            whatRandom = EasterEgg;
                            achivements[3]=true;
                    }
                }
                }while(subMenu == 5);

                board = new int[SquareNum][SquareNum];
                do{
                    //(Vector BOARD, MAXIMO (1=1suma - 2=2sumas - 3=3sumas...), TRUE=POTENCIA 2 FALSE=SUMA 5, NUMERO DE CASILLAS)
                    if (bucle == 0) {
                        board = Game.start(board, whatRandom, SquareNum);
                        bucle = 1;
                    }
                    board = Game.ChooseMOV(board);
                    if(Game.hasLost(board)){
                        achivements[1]=true;
                        // ENSEÑAR TABLERO
                        int maxChars = 0;
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length; j++) {
                                String str = String.format("%d", board[i][j]);
                                if (str.length() > maxChars) {
                                    maxChars = str.length();
                                }
                            }
                        }
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length; j++) {
                                System.out.printf("%-" + (maxChars + 1) + "d", board[i][j]);
                            }
                            System.out.println();
                        }
                        System.out.println("""
                            |───────────|
                            | GAME OVER |
                            |───────────|
                            -> INTRO TO CONTINUE""");
                        read.nextLine();
                        achivements[1]=true;
                        return menuPrincipal(achivements);
                    }else if(Game.hasWon(board, whatRandom) && control == 0){
                        achivements[0]=true;
                        //ENSEÑAR TABLERO
                        int maxChars = 0;
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length; j++) {
                            String str = String.format("%d", board[i][j]);
                                if (str.length() > maxChars) {
                                    maxChars = str.length();
                                    }
                                }
                                }
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length; j++) {
                                System.out.printf("%-" + (maxChars + 1) + "d", board[i][j]);
                                }
                                System.out.println();
                            }
                        
                        achivements[0]=true;
                        System.out.println("What do you want to do now?");
                        int menuGanar = Game.INTControl("""
                            |───────────────────────|
                            | 1. Continue Playing   |
                            | 2. Exit               |
                            |───────────────────────|""", 1, 2);
                        if (menuGanar == 1) {
                            System.out.println("Good Luck");
                            control = 1;
                        }else {
                            return menuPrincipal(achivements);
                        }
                    }
                    board = Game.metodoRandom(board, whatRandom, SquareNum);
                }while(!error);
            }
            case 2 ->{
                int contar=0;
                achivements = Game.achivements(achivements);
                for (int i = 0; i < difficultiesPlayed.length; i++) {
                    if (difficultiesPlayed[i]) {
                        contar++;
                }
            }
            if (contar == 3) {
                achivements[3]=true;
            }
        }
        }
        return menuPrincipal(achivements);
    }else {
        return achivements;
    }
    }
}
