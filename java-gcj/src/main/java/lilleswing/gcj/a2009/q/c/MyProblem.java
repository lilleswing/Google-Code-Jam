package lilleswing.gcj.a2009.q.c;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lilleswing.gcj.util.Problem;

public class MyProblem extends Problem<Case> {

    String pattern = "welcome to code jam";

    @Override
    public String solve(Case aCase) {
        BigInteger dp[] = new BigInteger[pattern.length() + 1];
        dp[0] = BigInteger.ONE;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = BigInteger.ZERO;
        }
        for(int i = 0; i < aCase.data.length(); i++) {
            final String c = aCase.data.substring(i, i+1);
            shiftDP(dp, c);
        }
        BigInteger value = dp[dp.length-1];
        value = value.mod(new BigInteger("10000"));
        return String.format("%04d", value.longValue());
    }

    private void shiftDP(BigInteger[] dp, String c) {
        for (int i = 0; i < pattern.length();  i++) {
            if (c.equals(pattern.substring(i, i+1))) {
                dp[i+1] = dp[i].add(dp[i+1]);
            }
        }
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        int n = sc.nextInt();
        sc.nextLine();
        List<Case> cases = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cases.add(new Case(sc.nextLine()));
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Problem problem = new MyProblem();
        problem.main(args[0]);
    }
}

class Case {
    String data;

    public Case(String data) {
        this.data = data.trim();
    }
}
