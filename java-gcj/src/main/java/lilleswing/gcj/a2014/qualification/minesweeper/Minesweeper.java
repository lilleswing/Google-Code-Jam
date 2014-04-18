package lilleswing.gcj.a2014.qualification.minesweeper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lilleswing.gcj.util.Problem;

import java.util.*;

/**
 * NOTE(Leswing) Only works for small input
 * Can probably reduce problems to 5/5 if enough mines (trivial if fewer)
 * and then use the cached solutions
 */
public class Minesweeper extends Problem<Case> {
    private Map<Point, Map<Integer, String>> gridCache;
    private static final int CACHE_SIZE = 7;

    @Override
    public String solve(Case aCase) {
        final Point p = new Point(aCase.getR(), aCase.getC());
        final Map<Integer, String> boards = gridCache.get(p);
        if(boards.containsKey(aCase.getM())) {
            return "\n" + boards.get(aCase.getM());
        }
        return "\nImpossible";
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final Case aCase = new Case(sc.nextInt(), sc.nextInt(), sc.nextInt());
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
        gridCache = Maps.newHashMap();
        for (int r = 1; r <= CACHE_SIZE; r++) {
            for (int c = 1; c <= CACHE_SIZE; c++) {
                Board board = new Board(r, c);
                final Point point = new Point(r, c);
                gridCache.put(point, board.getGrids());
            }
        }
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.main(args[0]);
    }
}

class Case {
    private int r;
    private int c;
    private int m;
    public Case(int r, int c, int m) {
        this.r = r;
        this.c = c;
        this.m= m;
    }

    int getR() {
        return r;
    }

    void setR(int r) {
        this.r = r;
    }

    int getC() {
        return c;
    }

    void setC(int c) {
        this.c = c;
    }

    int getM() {
        return m;
    }

    void setM(int m) {
        this.m = m;
    }
}

class Board {
    private int r, c;
    private boolean[][] grid;
    private Map<Integer, String> grids;
    private Set<String> gridCache;

    public Board(int r, int c) {
        this.r = r;
        this.c = c;
        grids = Maps.newHashMap();
        gridCache = Sets.newHashSet();
        grid = new boolean[r][c];
        grid[0][0] = true;
        saveBoard();
        final List<Point> moves = expand(new Point(0,0));
        dfs(moves);
    }

    private List<Point> expand(Point point) {
        final List<Point> adj = genAdj(point.r, point.c);
        final List<Point> next = Lists.newArrayList();
        for(final Point adjPoint: adj) {
            if(!grid[adjPoint.r][adjPoint.c]) {
                grid[adjPoint.r][adjPoint.c] = true;
                next.add(adjPoint);
            }
        }
        return next;
    }

    private boolean saveBoard() {
        int mines = 0;
        final StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(i == 0 && j == 0) {
                    stringBuilder.append("c");
                } else if(!grid[i][j]) {
                    stringBuilder.append("*");
                    mines++;
                } else {
                    stringBuilder.append(".");
                }
            }
            stringBuilder.append("\n");
        }
        final String boardString = stringBuilder.toString().trim();
        if(gridCache.contains(boardString)) {
            return false;
        }
        gridCache.add(stringBuilder.toString().trim());
        if(!grids.containsKey(mines)) {
            grids.put(mines, stringBuilder.toString().trim());
            //System.out.println(String.format("%s,%s,%s", r, c, mines));
            //System.out.println(stringBuilder.toString());
        }
        return true;
    }

    public void dfs(final List<Point> moves) {
        if(!saveBoard()) {
            return;
        }
        for(final Point move: moves) {
            final List<Point> adj = genAdj(move.r, move.c);
            final List<Point> flips = getFlips(adj);
            if(!flips.isEmpty()) {
                flip(flips, true);
                dfs(flips);
                flip(flips, false);
            }
        }
    }

    private void flip(List<Point> flips, final boolean status) {
        for(final Point point: flips) {
            grid[point.r][point.c] = status;
        }
    }

    private List<Point> getFlips(List<Point> adj) {
        final List<Point> flips = Lists.newArrayList();
        for(final Point adjPoint: adj) {
            if(!grid[adjPoint.r][adjPoint.c]) {
                flips.add(adjPoint);
            }
        }
        return flips;
    }

    public List<Point> genAdj(int nowR, int nowC) {
        final List<Point> points = Lists.newArrayList();
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                final int newR = nowR+i;
                final int newC = nowC+j;
                if(newR >= 0 && newR < r && newC >= 0 && newC < c) {
                    points.add(new Point(newR, newC));
                }

            }
        }
        return points;
    }

    public Map<Integer, String> getGrids() {
        return grids;
    }
}

class Point {
    public int r;
    public int c;
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        return Objects.equals(this.r, other.r) && Objects.equals(this.c, other.c);
    }
}
