package lilleswing.gcj.a2014.a1.c;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;

public class ProperShuffle extends Problem<Case> {

    @Override
    public String solve(Case aCase) {
        return aCase.naiveClassifier();
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        final int numCases = sc.nextInt();
        for (int i = 0; i < numCases; i++) {
            int n = sc.nextInt();
            final List<Integer> numbers = Lists.newArrayList();
            for(int j = 0; j < n; j++) {
                numbers.add(sc.nextInt());
            }
            Case aCase = new Case(numbers);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Problem problem = new ProperShuffle();
        problem.main(args[0]);
    }
}

class Case {
    private static final String BAD = "BAD";
    private static final String GOOD = "GOOD";
    private static final double NAIVE_GOOD = 500;
    private static final double NAIVE_BAD = 472;
    private static final double NAIVE_CUTOFF = (NAIVE_BAD + NAIVE_GOOD)/2.0;


    private List<Integer> numbers;
    public Case(List<Integer> numbers) {
        this.numbers = numbers;
    }

    List<Integer> getNumbers() {
        return numbers;
    }

    void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String naiveClassifier() {
        double count = 0;
        for(int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            if(number <= i) {
                count++;
            }
        }
        if(count > NAIVE_CUTOFF) {
            return GOOD;
        }
        return BAD;
    }
}
