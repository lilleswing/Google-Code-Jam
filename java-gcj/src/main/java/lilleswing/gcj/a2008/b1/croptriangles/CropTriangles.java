package lilleswing.gcj.a2008.b1.croptriangles;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * TODO(Leswing) This solution is incorrect
 */
public class CropTriangles extends Problem<Case> {

    public String solve(int[][] pointMod) {
        long total = 0;
        for (int x1 = 0; x1 < 3; x1++) {
            for (int x2 = x1; x2 < 3; x2++) {
                for (int x3 = x2; x3 < 3; x3++) {
                    for (int y1 = 0; y1 < 3; y1++) {
                        for (int y2 = y1; y2 < 3; y2++) {
                            for (int y3 = y2; y3 < 3; y3++) {
                                int xmod = (x1 + x2 + x3) % 3;
                                int ymod = (y1 + y2 + y3) % 3;
                                if (xmod == 0 && ymod == 0) {
                                    long ways = pointMod[x1][y1] * pointMod[x2][y2] * pointMod[x3][y3];
                                    total += ways;
                                }
                            }
                        }
                    }
                }
            }
        }
        return "" + total/4;
    }

    @Override
    public String solve(Case aCase) {
        final int[][] pointMod = new int[3][3];
        for (Point p : aCase.trees) {
            final int xmod = (int) (p.x % 3);
            final int ymod = (int) (p.y % 3);
            pointMod[xmod][ymod]++;
        }
        return solve(pointMod);
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final Case aCase = new Case(sc.nextLong(), sc.nextLong(), sc.nextLong(), sc.nextLong(),
                    sc.nextLong(), sc.nextLong(), sc.nextLong(), sc.nextLong());
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        CropTriangles cropTriangles = new CropTriangles();
        cropTriangles.main(args[0]);
    }
}
