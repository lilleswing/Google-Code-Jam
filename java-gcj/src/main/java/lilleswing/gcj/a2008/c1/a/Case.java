package lilleswing.gcj.a2008.c1.a;


import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class Case {
    private final int k;
    private final int p;
    private final int l;
    private final List<Integer> frequencies;

    public Case(int p, int k, int l, List<Integer> frequencies) {
        this.p = p;
        this.k = k;
        this.l = l;
        Collections.sort(frequencies);
        this.frequencies = Lists.reverse(frequencies);
    }

    public long minStrokes() {
        long keyStrokes = 0;
        final List<Integer> keys = initializeKeys();
        int numFrequency = 0;
        while(numFrequency != frequencies.size()) {
            for (int i = 0; i < keys.size(); i++) {
                if (numFrequency == frequencies.size()) {
                    break;
                }
                int letterOccurrences = frequencies.get(numFrequency++);
                int numLetterOnKey = keys.get(i);
                numLetterOnKey++;
                keys.set(i, numLetterOnKey);
                keyStrokes += numLetterOnKey * letterOccurrences;
            }
        }
        return keyStrokes;
    }

    private List<Integer> initializeKeys() {
        final List<Integer> keys = Lists.newArrayList();
        for(int i = 0; i < this.k; i++) {
            keys.add(0);
        }
        return keys;
    }
}
