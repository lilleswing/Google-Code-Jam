package lilleswing.gcj.a2016.q.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;
import org.apache.commons.io.IOUtils;


public class MyProblem extends Problem<Case> {
    @Override
    public String solve(Case aCase) {
        return aCase.countFlips();
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final int t = sc.nextInt();
        sc.nextLine();
        final List<Case> cases = Lists.newArrayList();
        for(int i = 0; i < t; i++) {
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
    String s;
    char stackState;
    public Case(final String s) {
        this.s = s;
        stackState = s.charAt(0);
    }

    public String countFlips() {
        int total = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != stackState) {
                total++;
                flipStackState();
            }
        }
        if (stackState == '-') {
            total++;
        }
        return String.valueOf(total);
    }

    private void flipStackState() {
        if (stackState == '-') {
            stackState = '+';
            return;
        }
        stackState = '-';
    }
}


