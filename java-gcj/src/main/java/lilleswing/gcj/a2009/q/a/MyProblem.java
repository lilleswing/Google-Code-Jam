package lilleswing.gcj.a2009.q.a;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lilleswing.gcj.util.Problem;

public class MyProblem extends Problem<Case> {
    @Override
    public String solve(Case aCase) {
        final Pattern pattern = Pattern.compile(aCase.pattern);
        int total = 0;
        for (final String word : aCase.words) {
            final Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                total += 1;
            }
        }
        return String.valueOf(total);
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner scanner = new Scanner(data);
        final int l = scanner.nextInt();
        final int d = scanner.nextInt();
        final int n = scanner.nextInt();
        scanner.nextLine();
        final List<String> words = new ArrayList<>();
        for (int i = 0; i < d; i++) {
            final String s = scanner.nextLine();
            words.add(s);
        }
        final List<Case> cases = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final String pattern = scanner.nextLine().replaceAll("\\(", "[").replaceAll("\\)", "]");
            Case aCase = new Case(words, pattern);
            cases.add(aCase);
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
    List<String> words;
    String pattern;

    public Case(final List<String> words,
                final String pattern) {
        this.words = words;
        this.pattern = pattern;
    }
}
