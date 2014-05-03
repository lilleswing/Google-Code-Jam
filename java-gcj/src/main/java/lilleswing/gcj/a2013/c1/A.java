package lilleswing.gcj.a2013.c1;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class A extends Problem<Case> {
    Set<Character> vowels;

    @Override
    public String solve(Case aCase) {
        int n = aCase.getN();
        String word = aCase.getWord();

        long value = 0;
        int c = 0;
        int r = 0;
        for(int i = 0; i < word.length(); i++) {
            if(!vowels.contains(word.charAt(i))) {
                c++;
            } else {
                c = 0;
            }
            if(c >= n) {
                value += (i - n - r + 2) * (word.length() - i);
                r = i - n + 2;
            }
        }
        return String.valueOf(value);
    }

    private long getValue(int i, int n, String word) {
        if(!allCons(i,n,word)) {
            return 0;
        }
        int startIndex = i-n;
        int endIndex = i;
        int startMult = startIndex + 1;
        int endMult = word.length() - endIndex + 1;
        return startMult * endMult;
    }

    private boolean allCons(int i, int n, String word) {
        int startIndex = i-n;
        int endIndex = i;
        for(int j = startIndex; j < endIndex; j++) {
            if(vowels.contains(word.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final String word = sc.next();
            final int n = sc.nextInt();
            Case aCase = new Case(word, n);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
        vowels = Sets.newHashSet('a','e','i','o','u');
    }

    public static void main(String[] args) {
        A a = new A();
        a.main(args[0]);
    }
}

class Case {

    private final int n;
    private String word;

    public Case(String word, int n) {
        this.word = word;
        this.n = n;
    }

    int getN() {
        return n;
    }

    String getWord() {
        return word;
    }

    void setWord(String word) {
        this.word = word;
    }
}
