/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2008.b1.numbersets;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lilleswing.gcj.util.Problem;
import lilleswing.gcj.util.datastructures.UnionFind;
import lilleswing.gcj.util.math.PrimeSieve;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NumberSets extends Problem<Case> {

    private List<Long> primes;

    @Override
    public void precompute() {
        primes = PrimeSieve.sieve(1000001);
    }

    @Override
    public String solve(Case aCase) {
        long upperBound = aCase.getUpper() - aCase.getLower();
        final Set<Long> elements = createElements(aCase.getLower(), aCase.getUpper());

        final UnionFind unionFind = new UnionFind(elements);
        for(long prime: primes) {
            if(prime < aCase.getMinPrime()) {
                continue;
            }
            if(prime > upperBound) {
                break;
            }
            final List<Long> innerValues = aCase.getInterior(prime);
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
    public List<Case> parse(List<String> data) {
        final Iterator<String> iterator = data.iterator();
        final List<Case> cases = Lists.newArrayList();
        final int numCases = Integer.parseInt(iterator.next());
        for(int i = 0; i < numCases; i++ ) {
            final String[] vals = iterator.next().split(" ");
            long lower = Long.parseLong(vals[0]);
            long upper = Long.parseLong(vals[1]);
            long minPrime = Long.parseLong(vals[2]);
            final Case aCase = new Case(lower, upper, minPrime);
            cases.add(aCase);
        }
        return cases;
    }

    public static void main(String[] args) {
        NumberSets numberSets = new NumberSets();
        numberSets.main(args[0]);
    }
}