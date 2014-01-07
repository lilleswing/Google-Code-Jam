/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2013.qualification.treasure;

import java.util.List;

class Chest implements Comparable<Chest> {
    private final int chestNum;
    private String openKey;
    private List<String> containedKeys;
    private boolean open;

    public Chest(int chestNum, String openKey, List<String> containedKeys) {
        this.chestNum = chestNum;
        this.openKey = openKey;
        this.containedKeys = containedKeys;
        open = false;
    }

    @Override
    public boolean equals(Object o) {
        return ((Chest)o).chestNum == this.chestNum;
    }

    public String getOpenKey() {
        return openKey;
    }

    public int getChestNum() {
        return chestNum;
    }

    public List<String> getContainedKeys() {
        return containedKeys;
    }

    @Override
    public int compareTo(Chest o) {
        return this.chestNum - o.getChestNum();
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

    public void unopen() {
        open = false;
    }
}