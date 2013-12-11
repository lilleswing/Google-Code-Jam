/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2008.b1.numbersets;

class Case {
    private long minPrime;
    private long upper;
    private long lower;

    public Case(long lower, long upper, long minPrime) {
        this.lower = lower;
        this.upper = upper;
        this.minPrime = minPrime;
    }

    long getMinPrime() {
        return minPrime;
    }

    void setMinPrime(long minPrime) {
        this.minPrime = minPrime;
    }

    long getUpper() {
        return upper;
    }

    void setUpper(long upper) {
        this.upper = upper;
    }

    long getLower() {
        return lower;
    }

    void setLower(long lower) {
        this.lower = lower;
    }
}