package lilleswing.gcj.a2008.a1.a;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class Case {
    private final List<Integer> v1;
    private final List<Integer> v2;
    public Case(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public long  minScalerProduct() {
        Collections.sort(v1);
        final List<Integer> revList = Lists.reverse(v1);
        Collections.sort(v2);
        long total = 0;
        for(int i = 0; i < v1.size(); i++) {
            total += revList.get(i) * v2.get(i);
        }
        return total;
    }
}
