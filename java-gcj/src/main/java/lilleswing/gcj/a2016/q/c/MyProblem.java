package lilleswing.gcj.a2016.q.c;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;

/**
 * NOTE(LESWING) does not work
 */
public class MyProblem extends Problem<Case> {


    @Override
    public String solve(Case aCase) {
        String template = "11%s11%s11%s11%s11";
        final List<String> coins = Lists.newArrayList();
        for (int i = 0; i < aCase.nbits - 10; i++) {
            for (int j = 0; j < aCase.nbits - 10 - i; j++) {
                for (int k = 0; k < aCase.nbits -10 - i - j; k++) {
                    int l = aCase.nbits - 10 - i - j - k;
                    String output = String.format(template, zeroStr(i), zeroStr(j), zeroStr(k), zeroStr(l));
                    coins.add(output + " 3 2 5 2 7 2 3 2 11");
                    aCase.jcoins--;
                    if (aCase.jcoins == 0) {
                        return stringList(coins);
                    }
                }
            }
        }
        throw new RuntimeException("Need moar jamcoins");
    }

    private String stringList(List<String> coins) {
        final StringBuilder sb = new StringBuilder();
        for (final String coin : coins) {
            sb.append("\n" + coin);
        }
        return sb.toString();
    }

    @Override
    public List<Case> parse(String data) {
        Scanner sc = new Scanner(data);
        int t = sc.nextInt();
        List<Case> cases = Lists.newArrayList();
        for(int i = 0; i < t; i++) {
            cases.add(new Case(sc.nextInt(), sc.nextInt()));
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    private String zeroStr(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Problem problem = new MyProblem();
        problem.main(args[0]);
    }
}

class Case {
    int nbits;
    int jcoins;
    public Case(int n, int jcoins) {
        this.nbits = n;
        this.jcoins = jcoins;
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


