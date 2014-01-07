/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2008.b1.numbersets;

import com.google.common.collect.Lists;

import java.util.List;

class DataSet {
    private long minPrime;
    private long upper;
    private long lower;

    public DataSet(long lower, long upper, long minPrime) {
        this.lower = lower;
        this.upper = upper;
        this.minPrime = minPrime;
    }

    public List<Long> getInterior(final long prime) {
        final List<Long> values = Lists.newArrayList();
        final long start = this.lower/prime;
        long value = start * prime;
        while(value < this.lower) {
            value += prime;
        }
        while(value <= this.upper) {
            values.add(value);
            value += prime;
        }
        return values;
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