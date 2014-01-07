/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2013.qualification.tictactomek;

class DataSet {
    private final char[][] board;

    public DataSet(final char[][] board) {
        this.board = board;
    }

    public char[][] getBoard() {
        return board;
    }
}