package lilleswing.gcj.a2008.c1.b;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class B extends Problem<Case> {
    private static final int MOD = 2*3*5*7;
    private boolean[] isUgly;

    /**
     *  DP[][]
     *  dp[0][0] = True
     *  dp[r][c] is how many ways we can make r mod 240 using c digits of the number String
     *               Digit
     *  m
     *  o
     *  d
     *
     *  2
     *  4
     *  0
     */
    @Override
    public String solve(Case aCase) {
        int[][] dp = new int[MOD][aCase.numDigits()+1];
        dp[0][0] = 1;
        for(int i = 1; i <= aCase.numDigits(); i++) { // The new String length to be calculating
            for(int j = 0; j < i; j++) { // The index to do the cut at
                final int updatePartial = modNumber(aCase.getSubValue(j, i));
                for (int beforeIndex = 0; beforeIndex < MOD; beforeIndex++) {
                    // Adding
                    int addIndex = modNumber(beforeIndex + updatePartial);
                    dp[addIndex][i] += dp[beforeIndex][j];

                    if (j == 0) { // You can't negate the whole string
                        continue;
                    }

                    // Subtracting
                    int subtractIndex = modNumber(beforeIndex - updatePartial);
                    dp[subtractIndex][i] += dp[beforeIndex][j];
                }
            }
        }
        // display(dp);
        return String.valueOf(totalUgly(dp));
    }

    private long totalUgly(int[][] dp) {
        int lastIndex = dp[0].length - 1;
        long total = 0;
        for(int i = 0; i < MOD; i++) {
            if(isUgly[i]) {
                total += dp[i][lastIndex];
            }
        }
        return total;
    }

    public int modNumber(int n) {
        return (n + MOD) % MOD;
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        sc.nextLine();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final Case aCase = new Case(sc.nextLine());
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
        isUgly = new boolean[MOD];
        int[] uglies = {2,3,5,7};
        Arrays.fill(isUgly, false);
        isUgly[0] = true;
        for(final Integer uglyNum: uglies) {
            int k = 1;
            while(k * uglyNum < isUgly.length) {
                isUgly[k*uglyNum] = true;
                k++;
            }
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.main(args[0]);
    }
}
