package lilleswing.gcj.a2008.c1.c;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

public class Case {
    final int MOD = 1000000007;
    final TreeMap<Integer, Integer> map = Maps.newTreeMap();
    final List<Integer> limits = Lists.newArrayList();
    public Case(long n, int m, long x, long y, long z, List<Long> a) {
        final List<Long> tLimits = Lists.newArrayList();
        for(int i = 0; i < n; i++) {
            tLimits.add(a.get(i % m));
            a.set(i % m, ((x * a.get(i % m)) + (y * (i + 1))) % z);
        }
        final List<Long> tLimitValues = Lists.newArrayList(Sets.newHashSet(tLimits));
        Collections.sort(tLimitValues);
        final Map<Long, Integer> indexMap = Maps.newHashMap();
        for(int i = 0; i < tLimitValues.size(); i++) {
            indexMap.put(tLimitValues.get(i), i);
        }
        for (final Long tLimitValue: tLimitValues) {
            limits.add(indexMap.get(tLimitValue));
        }
    }

    public int getSequences() {
        for(Integer limit: limits) {
            System.out.println(map);
            final int toAdd = getToAdd(limit);
            final int originalValue = getOriginalValue(limit);
            final int unitySequence = 1;
            int total = modAdd(unitySequence, modAdd(toAdd, originalValue));
            map.put(limit, total);
        }
        System.out.println(map);
        int sequences = 0;
        for (final Integer value: map.values()) {
            sequences = modAdd(sequences, value);
        }
        return sequences;
    }

    private int getToAdd(Integer limit) {
        final Iterator<Integer> iterator = map.keySet().iterator();
        if(!iterator.hasNext()) {
            return 0;
        }
        int value = iterator.next();
        int toAdd = 0;
        while (value < limit) {
            toAdd = modAdd(toAdd, map.get(value));
            if(!iterator.hasNext()) {
                return toAdd;
            }
            value = iterator.next();
        }
        return toAdd;
    }

    private int getOriginalValue(Integer limit) {
        if(map.containsKey(limit)) {
            return map.get(limit);
        }
        return 0;
    }

    public int modAdd(final int a, final int b) {
        return (a + b) % MOD;
    }

}
