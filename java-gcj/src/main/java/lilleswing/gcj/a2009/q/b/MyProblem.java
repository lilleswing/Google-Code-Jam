package lilleswing.gcj.a2009.q.b;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

public class MyProblem extends Problem<Case> {

    private LinkedBlockingQueue lowercase;

    @Override
    public String solve(Case aCase) {
        aCase.markSinks();
        aCase.floodFill();
        return aCase.display(lowercase);
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final int t = sc.nextInt();
        List<Case> myCases = new ArrayList<>();
        for (int runs = 0; runs < t; runs++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            Case myCase = new Case(w, h);
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    myCase.addCell(r, c, sc.nextInt());
                }
            }
            myCases.add(myCase);
        }
        return myCases;
    }

    @Override
    public void preCompute() {
        lowercase = new LinkedBlockingQueue();
        for (char c = 'a'; c <= 'z'; c++) {
            lowercase.offer("" + c);
        }
    }

    public static void main(String[] args) {
        MyProblem problem = new MyProblem();
        problem.main(args[0]);
    }
}

class Case {
    int h;
    int w;
    Cell[][] board;

    public Case(int w, int h) {
        this.h = h;
        this.w = w;
        this.board = new Cell[h][w];
    }

    public void addCell(int r, int c, int h) {
        this.board[r][c] = new Cell(r, c, h);
    }

    public void markSinks() {
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                List<Cell> neighbors = neighbors(r, c);
                int height = board[r][c].height;
                boolean isSink = true;
                for (Cell cell : neighbors) {
                    if (cell.height < height) {
                        isSink = false;
                    }
                }
                if (isSink) {
                    board[r][c].sink = UUID.randomUUID().toString();
                }
            }
        }
    }

    public List<Cell> neighbors(int r, int c) {
        List<Cell> cells = Lists.newArrayList();
        if (r - 1 >= 0) {
            cells.add(board[r - 1][c]);
        }
        if (c - 1 >= 0) {
            cells.add(board[r][c - 1]);
        }
        if (c + 1 < w) {
            cells.add(board[r][c + 1]);
        }
        if (r + 1 < h) {
            cells.add(board[r + 1][c]);
        }
        return cells;
    }

    public void floodFill() {
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                follow(r, c);
            }
        }
    }

    private Cell downHill(final Cell myCell, final List<Cell> neighbors) {
        int minHeight = myCell.height;
        Cell bestCell = null;
        for (final Cell neighbor : neighbors) {
            if (neighbor.height < minHeight) {
                minHeight = neighbor.height;
                bestCell = neighbor;
            }
        }
        return bestCell;
    }

    private void follow(int r, int c) {
        Cell cell = board[r][c];
        List<Cell> path = new ArrayList();
        while (Strings.isNullOrEmpty(cell.sink)) {
            path.add(cell);
            List<Cell> neighbors = neighbors(r, c);
            final Cell downhill = downHill(cell, neighbors);
            if (downhill == null) {
                throw new RuntimeException("Can't have null if not sink");
            }
            r = downhill.row;
            c = downhill.column;
            cell = downhill;
        }
        for (final Cell pathCell : path) {
            pathCell.sink = cell.sink;
        }
    }

    public String display(Queue<String> labels) {
        labels = new LinkedBlockingQueue<>(labels);
        final Map<String, String> labelMap = new HashMap<>();
        final String[][] displayBoard = new String[this.h][this.w];
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                final String sink = board[r][c].sink;
                if (!labelMap.containsKey(sink)) {
                    labelMap.put(sink, labels.poll());
                }
                final String label = labelMap.get(sink);
                displayBoard[r][c] = label;
            }
        }
        return makeString(displayBoard);
    }

    private String makeString(String[][] displayBoard) {
        final StringBuilder sb = new StringBuilder();
        for (int r = 0; r < h; r++) {
            final StringBuilder row = new StringBuilder();
            for (int c = 0; c < w; c++) {
                row.append(displayBoard[r][c] + " ");
            }
            sb.append(row.toString().trim() + "\n");
        }
        return "\n" + sb.toString().trim();
    }
}

class Cell {
    int row;
    int column;
    int height;
    String sink;

    public Cell(int r, int c, int height) {
        this.row = r;
        this.column = c;
        this.height = height;
    }
}
