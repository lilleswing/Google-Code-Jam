package lilleswing.gcj.a2008.c1.b;

public class Case {
    final String s;
    public Case(String s) {
        this.s = s.trim();
    }

    public int numDigits() {
        return s.length();
    }

    public int getSubValue(int lower, int upper) {
        final String strDigit = s.substring(lower, upper);
        return Integer.valueOf(strDigit);
    }
}
