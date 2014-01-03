package lilleswing.gcj.a2008.b1.mousetrap;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class Dataset {
    public long deckSize;
    public List<Long> indices;

    public Dataset(long deckSize, List<Long> indices) {
        this.deckSize = deckSize;
        this.indices = indices;
    }

    public String placeCards() {
        final List<Integer> deck = createDeck();
        final List<Long> cards = getCardsAtIndexes(deck);
        return display(cards);
    }

    private String display(List<Long> cards) {
        final StringBuilder builder = new StringBuilder();
        for (long card : cards) {
            builder.append(card + " ");
        }
        return builder.toString().trim();
    }

    public List<Integer> createDeck() {
        final Set<Long> leftIndices = Sets.newHashSet(this.indices);
        final List<Integer> deck = Lists.newArrayListWithCapacity((int) deckSize);
        deck.set(0, 1);
        int index = 1;
        int card = 2;
        while (!leftIndices.isEmpty()) {
            if (leftIndices.contains(index)) {
                leftIndices.remove(index);
            }
            index = (int) ((index + card - 1) % deckSize);
            deck.set(index, card);
        }
        return deck;
    }

    public List<Long> getCardsAtIndexes(final List<Integer> deck) {
        final List<Long> cards = Lists.newArrayList();
        for (long index : indices) {
            cards.add(Long.valueOf(deck.get((int) index)));
        }
        return cards;
    }
}
