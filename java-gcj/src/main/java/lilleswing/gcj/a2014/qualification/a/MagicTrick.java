package lilleswing.gcj.a2014.qualification.a;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MagicTrick extends Problem<Case> {
    public static void main(String[] args) {
        MagicTrick magicTrick = new MagicTrick();
        magicTrick.main(args[0]);
    }

    @Override
    public String solve(Case aCase) {
        final Set<Integer> first = aCase.getFirstCards().get(aCase.firstIndex());
        final Set<Integer> second = aCase.getSecondCards().get(aCase.secondIndex());
        first.retainAll(second);
        if (first.size() == 1) {
            return String.valueOf(first.iterator().next());
        } else if (first.isEmpty()) {
            return "Volunteer cheated!";
        } else {
            return "Bad magician!";
        }
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final int firstRow = sc.nextInt();
            final List<Set<Integer>> rows1 = Lists.newArrayList();
            for (int i = 0; i < 4; i++) {
                final Set<Integer> row = Sets.newHashSet();
                for(int j = 0; j < 4; j++) {
                    row.add(sc.nextInt());
                }
                rows1.add(row);
            }

            final int secondRow = sc.nextInt();
            final List<Set<Integer>> rows2 = Lists.newArrayList();
            for (int i = 0; i < 4; i++) {
                final Set<Integer> row = Sets.newHashSet();
                for (int j = 0; j < 4; j++) {
                    row.add(sc.nextInt());
                }
                rows2.add(row);
            }

            final Case aCase = new Case();
            aCase.setFirstRow(firstRow);
            aCase.setFirstCards(rows1);
            aCase.setSecondRow(secondRow);
            aCase.setSecondCards(rows2);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }
}

class Case {
    private int firstRow;
    private int secondRow;
    private List<Set<Integer>> firstCards;
    private List<Set<Integer>> secondCards;

    public Case() {
    }

    int firstIndex() {
        return firstRow - 1;
    }

    int secondIndex() {
        return secondRow - 1;
    }

    int getFirstRow() {
        return firstRow;
    }

    void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    int getSecondRow() {
        return secondRow;
    }

    void setSecondRow(int secondRow) {
        this.secondRow = secondRow;
    }

    List<Set<Integer>> getFirstCards() {
        return firstCards;
    }

    void setFirstCards(List<Set<Integer>> firstCards) {
        this.firstCards = firstCards;
    }

    List<Set<Integer>> getSecondCards() {
        return secondCards;
    }

    void setSecondCards(List<Set<Integer>> secondCards) {
        this.secondCards = secondCards;
    }
}
