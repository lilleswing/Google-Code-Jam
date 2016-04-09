package lilleswing.gcj.current.a;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import com.google.common.collect.Lists;
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
        problem.main("/Users/leswing/Documents/projects/Google-Code-Jam/java-gcj/src/main/java/lilleswing/gcj/current/a/A-large.in");
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
