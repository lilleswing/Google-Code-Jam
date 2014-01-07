/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2013.qualification.treasure;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;

public class Treasure extends Problem<Dataset> {
    private static final String IMPOSSIBLE = "IMPOSSIBLE";

    @Override
    public String solve(final Dataset dataset) {
        if(!dataset.preValidate()) {
            return IMPOSSIBLE;
        }
        final List<Integer> order = Lists.newArrayList();
        boolean openedThisRound;
        do {
            openedThisRound = false;
            for (Chest chest : dataset.getChests()) {
                if (!dataset.canOpen(chest, dataset.getStartKeys())) {
                    continue;
                }
                dataset.openChest(chest);
                if (dataset.isGood()) {
                    order.add(chest.getChestNum() + 1);
                    openedThisRound = true;
                    break;
                } else {
                    dataset.unopenChest(chest);
                }
            }
        } while (dataset.hasOpenChests() && openedThisRound);

        if(!dataset.getChests().isEmpty()) {
            return IMPOSSIBLE;
        }
        return order.toString().replaceAll("\\[" ,"").replaceAll("\\]","").replaceAll(",","");
    }

    @Override
    public List<Dataset> parse(final String data) {
        final Scanner sc = new Scanner(data);
        final List<Dataset> dataSets = Lists.newArrayList();
        final int numCases = sc.nextInt();
        for(int i = 0; i < numCases; i++ ) {
            final int numKeys = sc.nextInt();
            final int numChests = sc.nextInt();
            final List<String> startKeys = Lists.newArrayList();
            for(int j = 0; j < numKeys; j++) {
                startKeys.add("" + sc.nextInt());
            }
            final List<Chest> chests = Lists.newArrayList();
            for(int j = 0; j < numChests; j++) {
                final String openKey = "" + sc.nextInt();
                final int numContainedKeys = sc.nextInt();
                final List<String> containedKeys = Lists.newArrayList();
                for(int k = 0; k < numContainedKeys; k++) {
                    containedKeys.add("" + sc.nextInt());
                }
                chests.add(new Chest(j, openKey, containedKeys));
            }
            dataSets.add(new Dataset(startKeys, chests));
        }
        return dataSets;
    }

    @Override
    public void precompute() {
    }

    public static void main(String[] args) {
        Treasure treasure = new Treasure();
        treasure.main(args[0]);
    }
}