package lilleswing.gcj.a2014.qualification.d;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class D extends Problem<Case> {


    public static int deceitWar(List<Double> naomi, List<Double> ken, int nScore) {
        if(naomi.size() == 0) {
            return nScore;
        }

        if(naomi.get(0) < ken.get(0)) {
            naomi.remove(0);
            ken.remove(ken.size() - 1);
        } else {
            naomi.remove(0);
            ken.remove(0);
            nScore ++;
        }

        return deceitWar(naomi, ken, nScore);

    }

    private int war(final List<Double> naomi, final List<Double> ken) {
        if(naomi.size() == 0) {
            return 0;
        }

        int nextKenLarger = -1;

        for(int i = 0; i < ken.size(); ++i) {
            if(ken.get(i) > naomi.get(0)) {
                nextKenLarger = i;
                break;
            }
        }

        if(nextKenLarger >= 0) {
            naomi.remove(0);
            ken.remove(nextKenLarger);
            return war(naomi, ken);
        } else {
            return naomi.size();
        }
    }

    @Override
    public String solve(Case aCase) {
        final int war = war(Lists.newArrayList(aCase.getNaomi()), Lists.newArrayList(aCase.getKen()));
        final int deceit = deceitWar(Lists.newArrayList(aCase.getNaomi()), Lists.newArrayList(aCase.getKen()), 0);
        return String.format("%d %d", deceit, war);
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final int num_blocks = sc.nextInt();
            final List<Double> naomi = Lists.newArrayList();
            for (int i = 0; i < num_blocks; i++) {
                naomi.add(sc.nextDouble());
            }
            final List<Double> ken = Lists.newArrayList();
            for (int i = 0; i < num_blocks; i++) {
                ken.add(sc.nextDouble());
            }
            final Case aCase = new Case(naomi, ken);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        D d = new D();
        d.main(args[0]);
    }
}

class Case {
    private List<Double> naomi;
    private List<Double> ken;
    public Case(final List<Double> naomi, final List<Double> ken) {
        this.naomi = naomi;
        this.ken = ken;
        Collections.sort(naomi);
        Collections.sort(ken);
    }

    List<Double> getNaomi() {
        return naomi;
    }

    void setNaomi(List<Double> naomi) {
        this.naomi = naomi;
    }

    List<Double> getKen() {
        return ken;
    }

    void setKen(List<Double> ken) {
        this.ken = ken;
    }
}
