/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2013.qualification.tictactomek;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// TODO(Leswing) Solve Method

public class TicTacTomek extends Problem<DataSet> {
    private static final String O_WON = "O won";
    private static final String X_WON = "X won";
    private static final String DRAW = "Draw";
    private static final String NOT_OVER = "Game has not completed";
    private static final String UNKNOWN = "Unknown";

    @Override
    public String solve(final DataSet dataSet) {
        final char[][] board = dataSet.getBoard();
        // check horizontal
        for(int i = 0; i < 4; i++) {
            if(!check(board[i]).equals(UNKNOWN)) {
                return check(board[i]);
            }
        }
        // check vertical
        for(int i = 0; i < 4; i++) {
            final char[] four = new char[4];
            for(int j = 0; j < 4; j++) {
                four[j] = board[j][i];
            }
            if(!check(four).equals(UNKNOWN)) {
                return check(four);
            }
        }

        // check diagonal
        char[] diag1 = {board[0][0], board[1][1], board[2][2], board[3][3]};
        char[] diag2 = {board[0][3], board[1][2], board[2][1], board[3][0]};
        if(!check(diag1).equals(UNKNOWN)) {
            return check(diag1);
        }
        if(!check(diag2).equals(UNKNOWN)) {
            return check(diag2);
        }
        if(isComplete(board)) {
            return NOT_OVER;
        }
        return DRAW;
    }

    private boolean isComplete(char[][] board) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public String check(final char[] four){
        int x = 0;
        int o = 0;
        for(int i = 0; i < four.length; i++) {
            if(four[i] == '.') {
                return UNKNOWN;
            } else if(four[i] == 'X') {
                x++;
            } else if(four[i] == 'O') {
                o++;
            } else if(four[i] == 'T') {
                x++;
                o++;
            }
        }
        if(o == 4) {
            return O_WON;
        }
        if(x == 4) {
            return X_WON;
        }
        return UNKNOWN;
    }

    @Override
    public List<DataSet> parse(final String data) {
        final Scanner sc = new Scanner(data);
        final List<DataSet> dataSets = Lists.newArrayList();
        final int numCases = sc.nextInt();
        for(int i = 0; i< numCases; i++) {
            final char[][] board = new char[4][4];
            for(int j = 0; j < 4; j++) {
                final String line = sc.nextLine();
                for(int k = 0; k < 4; k++) {
                    board[j][k] = line.charAt(k);
                }
            }
            sc.nextLine();
            dataSets.add(new DataSet(board));
        }
        return dataSets;
    }

    @Override
    public void precompute() {
    }

    public static void main(final String[] args) {
        final TicTacTomek ticTacTomek = new TicTacTomek();
        ticTacTomek.main(args[0]);
    }
}