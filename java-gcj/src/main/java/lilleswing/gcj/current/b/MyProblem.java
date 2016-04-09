package lilleswing.gcj.current.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;
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
        problem.main("/Users/leswing/Documents/projects/Google-Code-Jam/java-gcj/src/main/java/lilleswing/gcj/current/b/sample.in");
    }
}

class Case {
    String s;
    char[] goal = new char[]{'+','-'};
    int goalIndex;
    int startIndex = 0;
    int endIndex;
    boolean isStart = true;
    public Case(final String s) {
        this.s = s;
        this.endIndex = s.length() - 1;

    }

    public String countFlips() {
        while(startIndex <= endIndex) {
            char c = getChar();
            char g = getGoal();
            if (c == g) {
                increment();

            }
        }
    }

    private char getChar() {
        if (isStart) {
            return s.charAt(startIndex);
        }
        return s.charAt(endIndex);
    }

    private void increment() {
        if (isStart) {
            startIndex += 1;
            return;
        }
        endIndex += 1;
        return;
    }

    private boolean isMatch() {
        return getChar() == getGoal();
    }

    private void flipGoal() {
        goalIndex = (goalIndex + 1) % 2;
        isStart = !isStart;
    }

    private char getGoal() {
        return goal[goalIndex];
    }
}

abstract class Problem<T> {
    public abstract String solve(T t);

    public abstract List<T> parse(final String data);

    public abstract void preCompute();

    public void display(final List<String> results, final String filename) {
        final String base = filename.substring(0, filename.lastIndexOf('.'));
        final String outFileName = base + ".out";

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(outFileName)));
            int case_num = 1;
            for (String result : results) {
                final String answer = String.format("Case #%d: %s", case_num, result);
                System.out.println(answer);
                writer.write(answer + "\n");
                case_num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    public void main(String filename) {
        preCompute();
        final String data = readLines(filename);
        final List<T> cases = parse(data);
        final List<String> results = Lists.transform(cases, this::solve);
        display(results, filename);
    }

    private String readLines(final String filename) {
        try {
            final String content = new Scanner(new File(filename)).useDelimiter("\\Z").next();
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}


