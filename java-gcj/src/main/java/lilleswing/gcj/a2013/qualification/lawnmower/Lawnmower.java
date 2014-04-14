package lilleswing.gcj.a2013.qualification.lawnmower;


import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Lawnmower extends Problem<Dataset> {
    private static final String YES = "YES";
    private static final String NO = "NO";

    @Override
    public String solve(Dataset dataset) {
        final int[][] lawn = dataset.getLawn();
        final int[] rowMax = dataset.getRowMaxes();
        final int[] colMax = dataset.getColMaxes();

        for(int i = 0; i < lawn.length; i++) {
            for(int j = 0; j < lawn[0].length; j++) {
                int val = lawn[i][j];
                if(val < rowMax[i] && val < colMax[j]) {
                    return NO;
                }
            }
        }
        return YES;
    }

    @Override
    public List<Dataset> parse(final String data) {
        final Scanner sc = new Scanner(data);
        final List<Dataset> dataSets = Lists.newArrayList();
        final int numCases = sc.nextInt();
        for (int i = 0; i < numCases; i++) {
            final int height = sc.nextInt();
            final int width = sc.nextInt();
            int[][] lawn = new int[height][width];
            for(int h = 0; h < height; h++) {
                for(int w = 0; w < width; w++) {
                    lawn[h][w] = sc.nextInt();
                }
            }
            dataSets.add(new Dataset(lawn));
        }
        return dataSets;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Lawnmower lawnmower = new Lawnmower();
        lawnmower.main(args[0]);
    }
}
