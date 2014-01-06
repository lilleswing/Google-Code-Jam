/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.a2013.qualification.treasure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * For isGood, create graph where each key is a node
 * Each chest is multiple edges from opening key to every new key
 * For each key that we need to prove we can get to
 *  Find shortest path from any starting to the one we need to get to
 *  (Think I can do this with modified djiskra)
 */
public class Dataset {
    private final List<Chest> chests;
    private List<String> startKeys;

    public Dataset(List<String> startKeys, List<Chest> chests) {
        this.startKeys = startKeys;
        this.chests = chests;
    }

    public boolean canOpen(Chest chest, final List<String> keys) {
        if(chest.isOpen()) {
            return false;
        }
        if(!chests.contains(chest)) {
            return false;
        }
        if(!keys.contains(chest.getOpenKey())) {
            return false;
        }
        return true;
    }

    public void openChest(Chest chest) {
        startKeys.remove(chest.getOpenKey());
        startKeys.addAll(chest.getContainedKeys());
        chest.open();
    }

    public void unopenChest(final Chest chest) {
        startKeys.add(chest.getOpenKey());
        for(String key: chest.getContainedKeys()) {
            this.startKeys.remove(key);
        }
        chest.unopen();
    }

    /**
     * Determine if there are enough keys
     */
    public boolean preValidate() {
        final Map<String, Integer> neededKeys = Maps.newHashMap();
        final Map<String, Integer> availableKeys = Maps.newHashMap();
        for(String key: startKeys) {
            if(!availableKeys.containsKey(key)) {
                availableKeys.put(key, 0);
            }
            availableKeys.put(key, availableKeys.get(key)+1);
        }
        for(Chest chest: chests) {
            if(!neededKeys.containsKey(chest.getOpenKey())) {
                neededKeys.put(chest.getOpenKey(), 0);
            }
            neededKeys.put(chest.getOpenKey(), neededKeys.get(chest.getOpenKey()) + 1);
            for (String key : chest.getContainedKeys()) {
                if (!availableKeys.containsKey(key)) {
                    availableKeys.put(key, 0);
                }
                availableKeys.put(key, availableKeys.get(key) + 1);
            }
        }

        for(Map.Entry<String, Integer> needed: neededKeys.entrySet()) {
            final int available = availableKeys.get(needed.getKey());
            if(available < needed.getValue()) {
                return false;
            }
        }
        return true;
    }


    /**
     * This DFS is too slow figure out how to speed it up
     * @param startKeys
     * @param neededKeys
     * @return
     */
    public boolean touchAllKeys(final List<String> startKeys, final Set<String> neededKeys) {
        if(neededKeys.isEmpty()) {
            return true;
        }
        for(Chest chest: chests) {
            if(!canOpen(chest, startKeys)) {
                continue;
            }
            final List<String> newStartKeys = Lists.newArrayList(startKeys);
            newStartKeys.remove(chest.getOpenKey());
            newStartKeys.addAll(chest.getContainedKeys());
            final Set<String> newNeededKeys = Sets.newHashSet(neededKeys);
            newNeededKeys.removeAll(chest.getContainedKeys());
            if(!betterKeySet(startKeys, newStartKeys, neededKeys, newNeededKeys)) {
                continue;
            }
            chest.open();
            boolean touched = touchAllKeys(newStartKeys, newNeededKeys);
            chest.unopen();

            if (touched) {
                return true;
            }
        }
        return false;
    }

    private boolean betterKeySet(List<String> startKeys,
                                 List<String> newStartKeys,
                                 Set<String> neededKeys,
                                 Set<String> newNeededKeys) {
        boolean neededChange = !newNeededKeys.containsAll(neededKeys);
        boolean startChange = false;
        for(String key: newStartKeys) {
            int oldFrequency = Collections.frequency(startKeys, key);
            int newFrequency = Collections.frequency(newStartKeys, key);
            if(newFrequency > oldFrequency) {
                startChange = true;
                break;
            }
        }
        return neededChange || startChange;
    }

    /**
     *  true iff we can access every type of key
     *  Uses a DFS to see if we can get to every key
     */
    public boolean isGood() {
        final Set<String> neededKeys = Sets.newHashSet();
        for(Chest chest: chests) {
            neededKeys.add(chest.getOpenKey());
        }
        neededKeys.removeAll(startKeys);
        final boolean result = touchAllKeys(Lists.newArrayList(startKeys), neededKeys);
        return result;
    }

    public List<String> getStartKeys() {
        return startKeys;
    }

    public List<Chest> getChests() {
        return chests;
    }

    public boolean hasOpenChests() {
        for(Chest chest: chests) {
            if(!chest.isOpen()) {
                return true;
            }
        }
        return false;
    }
}