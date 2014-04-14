/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2008.b1.numbersets;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lilleswing.gcj.util.Problem;
import lilleswing.gcj.util.datastructures.UnionFind;
import lilleswing.gcj.util.math.PrimeSieve;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NumberSets extends Problem<DataSet> {

    private List<Long> primes;

    @Override
    public void preCompute() {
        primes = PrimeSieve.sieve(1000001);
    }

    @Override
    public String solve(DataSet aDataSet) {
        long upperBound = aDataSet.getUpper() - aDataSet.getLower();
        final Set<Long> elements = createElements(aDataSet.getLower(), aDataSet.getUpper());

        final UnionFind<Long> unionFind = new UnionFind<>(elements);
        for(long prime: primes) {
            if(prime < aDataSet.getMinPrime()) {
                continue;
            }
            if(prime > upperBound) {
                break;
            }
            final List<Long> innerValues = aDataSet.getInterior(prime);
            for(long value: innerValues) {
                unionFind.union(innerValues.get(0), value);
            }
        }
        return "" + unionFind.size();
    }

    private Set<Long> createElements(long lower, long upper) {
        final Set<Long> set = Sets.newHashSet();
        for(long i = lower; i<= upper; i++) {
            set.add(i);
        }
        return set;
    }

    @Override
    public List<DataSet> parse(String data) {
        Scanner sc = new Scanner(data);
        final List<DataSet> dataSets = Lists.newArrayList();
        final int numCases = sc.nextInt();
        for(int i = 0; i < numCases; i++ ) {
            long lower = sc.nextLong();
            long upper = sc.nextLong();
            long minPrime = sc.nextLong();
            final DataSet aDataSet = new DataSet(lower, upper, minPrime);
            dataSets.add(aDataSet);
        }
        return dataSets;
    }

    public static void main(String[] args) {
        NumberSets numberSets = new NumberSets();
        numberSets.main(args[0]);
    }
}