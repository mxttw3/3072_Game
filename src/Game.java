import java.util.Scanner;

public class Game {
    public static int INTControl(String menuTitle, int min, int max) {
        Scanner read = new Scanner(System.in);
        boolean correct = false;
        int menu = 0;
        do {
            System.out.println(menuTitle);
            correct = read.hasNextInt();

            if (!correct) {
                System.out.println("-- Wrong Carather --");
                read.nextLine();
            } else {
                menu = read.nextInt();
                if (menu < min || menu > max) {
                    System.out.println("-- Wrong Number --");
                    correct = false;
                }
                read.nextLine();
            }
        } while (!correct);
        return menu;
    }

    public static int[][] start(int[][] board, int whatRandom, int SquareNum){
        int columnIA = 0, FileIA = 0, NumberIA = 0, bucle = 0;
        do {
            //Mirar que casilla esta vacia
            do {
                columnIA = (int) (Math.random() * SquareNum);
                FileIA = (int) (Math.random() * SquareNum);
            } while (board[FileIA][columnIA] != 0);

            //Numeros aleatorios:
            NumberIA=whatRandom; 
            //Reescribir el tablero 
            board[FileIA][columnIA] = NumberIA;
            bucle = bucle +1;
        } while (bucle == 1);
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

        return board;
    }

    //(Vector BOARD, MAXIMO (1=1suma - 2=2sumas - 3=3sumas...), TRUE=POTENCIA 2 FALSE=SUMA 5, NUMERO DE CASILLAS)
    public static int[][] metodoRandom(int[][] board, int whatRandom, int SquareNum){
        int columnIA = 0, FileIA = 0, NumberIA = 0, randomIndex = 0;
        //Mirar que casilla esta vacia
        do {
            columnIA = (int) (Math.random() * SquareNum);
            FileIA = (int) (Math.random() * SquareNum);
        } while (board[FileIA][columnIA] != 0);

        //Numeros aleatorios:
        randomIndex = (int) (Math.random() *1+2);
        if (randomIndex==1){
            NumberIA=whatRandom;
        }else{
            NumberIA=whatRandom*2;
        }
        //Reescribir el tablero 
        board[FileIA][columnIA] = NumberIA;
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
        return board;
    }

    public static int[][] Movement(int WhatMOV, int[][] board){
        switch (WhatMOV) {
            case 1 ->{
                //Movimiento arriba
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] != 0) {
                            int k = i;
                            while (k > 0 && board[k-1][j] == 0) {
                                board[k-1][j] = board[k][j];
                                board[k][j] = 0;
                                k--;
                            }
                            if(k>0 && board[k-1][j] == board[k][j]){
                                board[k-1][j] = board[k-1][j] + board[k][j];
                                board[k][j] = 0;
                            }
                        }     
                    }
                }
            }

            case 2 ->{
                //Movimiento izquierda
                for (int i = 0; i < board.length; i++) {
                    for (int j = 1; j < board[i].length; j++) {
                        if (board[i][j] != 0) {
                            int k = j;
                            while (k > 0 && board[i][k-1] == 0) {
                                board[i][k-1] = board[i][k];
                                board[i][k] = 0;
                                k--;
                            }
                            if(k>0 && board[i][k-1] == board[i][k]){
                                board[i][k-1] = board[i][k-1] + board[i][k];
                                board[i][k] = 0;
                            }
                        }     
                    }
                }
            }

            case 3 ->{
                //Movimiento abajo
                for (int i = board.length - 2; i >= 0; i--) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] != 0) {
                            int k = i;
                            while (k < board.length - 1 && board[k+1][j] == 0) {
                                board[k+1][j] = board[k][j];
                                board[k][j] = 0;
                                k++;
                            }
                            if(k<board.length - 1 && board[k+1][j] == board[k][j]){
                                board[k+1][j] = board[k+1][j] + board[k][j];
                                board[k][j] = 0;
                            }
                        }     
                    }
                }  
            }

            case 4 ->{
                //Movimiento derecha
                for (int i = 0; i < board.length; i++) {
                    for (int j = board[i].length - 2; j >= 0; j--) {
                        if (board[i][j] != 0) {
                            int k = j;
                            while (k < board[i].length - 1 && board[i][k+1] == 0) {
                                board[i][k+1] = board[i][k];
                                board[i][k] = 0;
                                k++;
                            }
                            if(k<board[i].length - 1 && board[i][k+1] == board[i][k]){
                                board[i][k+1] = board[i][k+1] + board[i][k];
                                board[i][k] = 0;
                            }
                        }     
                    }
                }
            }
        }
        return board;
    }

    public static int[][] ChooseMOV(int[][] board){
        Scanner read = new Scanner(System.in);
        boolean error = true;
        do{
            error = true;
            System.out.println("""
                |────────────|
                | MOVE WITH: |
                | UP    -> W |
                | DOWN  -> S |
                | LEFT  -> A |
                | RIGHT -> D |
                |────────────|""");

            char movementLetter = read.next().charAt(0);
            switch (movementLetter) {
                case 'W':
                case 'w':
                    board = Movement(1, board);
                break;
                case 'A':
                case 'a':
                    board = Movement(2, board);
                break;
                case 'S':
                case 's':
                    board = Movement(3, board);
                break;
                case 'D':
                case 'd':
                    board = Movement(4, board);
                break;
                case default:
                System.out.println("-- Wrong Carather --");
                error = false;
                break;
            }
        }while (!error);
        return board;
    }

    public static boolean[] achivements( boolean[] achivements){
        Scanner read = new Scanner(System.in);
        //Clean screen
        for (int i = 0; i < 69; i++) {
            System.out.println();
        }
        System.out.println("|────────────────────────────────────────|");
        if (achivements[0]==true) {
            System.out.println("| 1. Win a game" + Menu.GREEN +"                  FINISHED" + Menu.RESET + "|");
        }else {
            System.out.println("| 1. Win a game" + Menu.RED +"                INCOMPLETE" + Menu.RESET + "|");
        }
        if (achivements[1]==true) {
            System.out.println("| 2. Lose a game" + Menu.GREEN +"                 FINISHED" + Menu.RESET + "|");
        }else {
            System.out.println("| 2. Lose a game" + Menu.RED +"               INCOMPLETE" + Menu.RESET + "|");
        }
        if (achivements[2]==true) {
            System.out.println("| 3.Play all the difficulties" + Menu.GREEN +"    FINISHED" + Menu.RESET + "|");
        }else {
            System.out.println("| 3.Play all the difficulties" + Menu.RED +"  INCOMPLETE" + Menu.RESET + "|");
        }
        if (achivements[3]==true) {
            System.out.println("| 4. Discover the easter egg" + Menu.GREEN +"     FINISHED" + Menu.RESET + "|");
        }else {
            System.out.println("| 4. Discover the easter egg" + Menu.RED +"   INCOMPLETE" + Menu.RESET + "|");
        }
        System.out.println("|────────────────────────────────────────|");
        System.out.println("-> INTRO TO CONTINUE");
        read.nextLine();
        return achivements;
    }

    public static boolean hasLost(int[][] board){
        boolean lost = false;
        int contar = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j]==0) {
                    contar++;
                }
            }
        }
        if (contar==0) {
            lost = true;
        }
        return lost;
    }

    public static boolean hasWon(int[][] board, int whatRandom){
        boolean won = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                switch(whatRandom){
                    case 2:
                        if (board[i][j]>=2048) {
                            won=true;
                        }
                    break;
                    case 3:
                        if (board[i][j]>=3072) {
                            won=true;
                        }
                    break;
                    case 4:
                        if (board[i][j]>=4096) {
                            won=true;
                        }
                    break;
                    case 5:
                        if (board[i][j]>=5120) {
                            won=true;
                        }
                    break;
            }
        }
        if (won) {
            System.out.println("Congratulations you won the game!!");
            
        }
        }
    return won;
    }
}