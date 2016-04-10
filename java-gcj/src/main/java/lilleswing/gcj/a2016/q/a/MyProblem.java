package lilleswing.gcj.a2016.q.a;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;
import org.apache.commons.io.IOUtils;


public class MyProblem extends Problem<Case> {
    @Override
    public String solve(Case aCase) {
        return aCase.solve();
    }

    @Override
    public List<Case> parse(String data) {
        Scanner sc = new Scanner(data);
        int t = sc.nextInt();
        sc.nextLine();
        List<Case> cases = new ArrayList<>();
        for(int i = 0; i < t; i++) {
            cases.add(new Case(sc.nextBigInteger()));
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
    BigInteger m;
    BigInteger ten;
    Set<Integer> set;
    public Case(BigInteger bigInteger) {
        this.m = bigInteger;
        set = new HashSet<>();
        ten = new BigInteger("10");
    }

    public String solve() {
        if(m.equals(BigInteger.ZERO)) {
            return "INSOMNIA";
        }
        addVals();
        BigInteger startVal = m;
        while (set.size() != 10) {
            m = m.add(startVal);
            addVals();
        }
        return m.toString();
    }

    private void addVals() {
        BigInteger k = m;
        while(!k.equals(BigInteger.ZERO)) {
            set.add(k.mod(ten).intValue());
            k = k.divide(ten);
        }
    }
}
