package lilleswing.gcj.current.c;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lilleswing.gcj.util.math.PrimeSieve;
import org.apache.commons.io.IOUtils;


public class MyProblem extends Problem<Case> {

    Set<Long> primes;

    @Override
    public String solve(Case aCase) {
        for (final Long prime: primes) {
            String base2String = Long.toString(prime, 2);
            boolean isCoin = true;
            for (int i = 3; i < 10; i++) {
                int decimalValue = Integer.parseInt(base2String, i);
                if (!primes.contains(decimalValue)) {
                    isCoin = false;
                    break;
                }
            }
            if (isCoin) {
                System.out.println(base2String);
            }
        }
        return "FOO";
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
        primes = Sets.newHashSet(PrimeSieve.sieve(65536));
    }

    public static void main(String[] args) {
        Problem problem = new MyProblem();
        problem.main("/Users/leswing/Documents/projects/Google-Code-Jam/java-gcj/src/main/java/lilleswing/gcj/current/c/sample.in");
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


