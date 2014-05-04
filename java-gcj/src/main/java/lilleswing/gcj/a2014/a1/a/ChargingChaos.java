package lilleswing.gcj.a2014.a1.a;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ChargingChaos extends Problem<Case> {
    @Override
    public String solve(Case aCase) {
        boolean[] flips = new boolean[aCase.getSize()];
        boolean possible = dfs(aCase, 0, flips);
        if (!possible) {
            return "NOT POSSIBLE";
        }
        return String.valueOf(cardinality(flips));
    }

    private Object cardinality(boolean[] flips) {
        int count = 0;
        for(int i = 0; i < flips.length; i++) {
            if(flips[i]) {
                count++;
            }
        }
        return count;
    }

    public boolean dfs(Case aCase, int index, boolean[] flips) {
        if(index == aCase.getSize()) {
            return true;
        }
        if(matches(aCase, index)) {
            boolean retval = dfs(aCase, index + 1, flips);
            if(retval) {
                return true;
            }
        }
        flip(aCase, index, flips);
        if(matches(aCase, index)) {
            boolean retval = dfs(aCase, index+1, flips);
            if(retval) {
                return true;
            }
        }
        unflip(aCase, index, flips);
        return false;
    }

    private void unflip(Case aCase, int index, boolean[] flips) {
        aCase.flip(index);
        flips[index]  = false;
    }

    private void flip(Case aCase, int index, boolean[] flips) {
        aCase.flip(index);
        flips[index] = true;
    }

    private boolean matches(Case aCase, int index) {
        List<boolean[]> outlets = aCase.getOutlets();
        List<boolean[]> devices = aCase.getDevices();
        for (int i = 0; i < outlets.size(); i++) {
            boolean[] outlet = outlets.get(i);
            boolean[] device = devices.get(i);
            if (outlet[index] != device[index]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        final int numCases = sc.nextInt();
        for(int i = 0; i< numCases; i++) {
            int n = sc.nextInt();
            int l = sc.nextInt();
            List<boolean[]> outlets = Lists.newArrayList();
            for(int j = 0; j < n; j++) {
                String outlet = sc.next();
                boolean[] bitSet = createBitSet(outlet);
                outlets.add(bitSet);
            }
            List<boolean[]> devices = Lists.newArrayList();
            for(int j = 0; j < n; j++) {
                String outlet = sc.next();
                boolean[] bitSet = createBitSet(outlet);
                devices.add(bitSet);
            }
            Case aCase = new Case(outlets, devices, l);
            cases.add(aCase);
        }
        return cases;
    }

    private boolean[] createBitSet(String binaryString) {
        boolean[] bitSet = new boolean[binaryString.length()];
        for(int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            if(c == '1') {
                bitSet[i] = true;
            }
        }
        return bitSet;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Problem problem = new ChargingChaos();
        problem.main(args[0]);
    }
}

class Case {
    private List<boolean[]> outlets;
    private List<boolean[]> devices;
    private int size;
    public Case(List<boolean[]> outlets, List<boolean[]> devices, int size) {
        this.outlets = outlets;
        this.devices = devices;
        Collections.sort(outlets, new BSetComparator());
        Collections.sort(devices, new BSetComparator());
        this.size = size;
    }

    public List<boolean[]> getOutlets() {
        return outlets;
    }

    public List<boolean[]> getDevices() {
        return devices;
    }

    public void flip(int index) {
        for(boolean[] outlet: outlets) {
            outlet[index] =  !outlet[index];
        }
        Collections.sort(outlets, new BSetComparator());
    }

    public int getSize() {
        return size;
    }
}

class BSetComparator implements Comparator<boolean[]> {
    @Override
    public int compare(boolean[] o1, boolean[] o2) {
        for(int i = 0; i < o1.length; i++) {
            if(o1[i] && !o2[i]) {
                return 1;
            }
            if(o2[i] && !o1[i]) {
                return -1;
            }
        }
        return 0;
    }
}
