/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2013.qualification;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// TODO(Leswing) Solve Method

public class TicTacTomek extends Problem<DataSet> {

    @Override
    public String solve(final DataSet dataSet) {
        return null;
    }

    @Override
    public List<DataSet> parse(final List<String> data) {
        final Iterator<String> iterator = data.iterator();
        final List<DataSet> dataSets = Lists.newArrayList();
        final int numCases = Integer.parseInt(iterator.next());
        for(int i = 0; i< numCases; i++) {
            final Scanner sc = new Scanner(iterator.next());
            final char[][] board = new char[4][];
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    board[j][k] = sc.next(".").charAt(0);
                }
            }
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