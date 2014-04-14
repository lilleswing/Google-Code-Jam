package lilleswing.gcj.a2014.qualification.b;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class CookieClickerAlpha extends Problem<Case> {
    private final DecimalFormat decimalFormat = new DecimalFormat("0.0000000");

    @Override
    public String solve(Case aCase) {
        BigDecimal bestGoal = aCase.getX().divide(new BigDecimal(2.0), MathContext.DECIMAL128);
        BigDecimal rate = new BigDecimal(2);
        BigDecimal time = BigDecimal.ZERO;
        while(true) {
            BigDecimal timeToFact = aCase.getC().divide(rate, MathContext.DECIMAL128);
            BigDecimal newRate = rate.add(aCase.getF());
            BigDecimal newTime = time.add(timeToFact);
            BigDecimal newGoal = newTime.add(aCase.getX().divide(newRate, MathContext.DECIMAL128));
            if(newGoal.compareTo(bestGoal) >= 0) {
                return decimalFormat.format(bestGoal);
            } else {
                time = newTime;
                bestGoal = newGoal;
                rate = newRate;
            }
        }
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            BigDecimal c = new BigDecimal(sc.nextDouble());
            BigDecimal f = new BigDecimal(sc.nextDouble());
            BigDecimal x = new BigDecimal(sc.nextDouble());
            Case aCase = new Case(c,f,x);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        CookieClickerAlpha cookieClickerAlpha = new CookieClickerAlpha();
        cookieClickerAlpha.main(args[0]);
    }
}

class Case {
    private BigDecimal c;
    private BigDecimal f;
    private BigDecimal x;
    public Case(BigDecimal c, BigDecimal f, BigDecimal x) {
        this.c = c;
        this.f = f;
        this.x = x;
    }

    BigDecimal getC() {
        return c;
    }

    void setC(BigDecimal c) {
        this.c = c;
    }

    BigDecimal getF() {
        return f;
    }

    void setF(BigDecimal f) {
        this.f = f;
    }

    BigDecimal getX() {
        return x;
    }

    void setX(BigDecimal x) {
        this.x = x;
    }
}
