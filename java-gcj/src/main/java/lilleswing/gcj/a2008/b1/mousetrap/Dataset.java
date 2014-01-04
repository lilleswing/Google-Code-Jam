package lilleswing.gcj.a2008.b1.mousetrap;

import java.util.List;

class Dataset {
    public long deckSize;
    public List<Long> indices;

    public Dataset(long deckSize, List<Long> indices) {
        this.deckSize = deckSize;
        this.indices = indices;
    }
}
