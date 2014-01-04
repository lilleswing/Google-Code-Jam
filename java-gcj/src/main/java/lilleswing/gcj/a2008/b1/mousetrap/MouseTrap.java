package lilleswing.gcj.a2008.b1.mousetrap;

import lilleswing.gcj.util.Problem;

import java.util.List;

/**
 * TODO(Leswing)
 */
public class MouseTrap extends Problem<Dataset> {

    @Override
    public String solve(Dataset aDataset) {
        return aDataset.placeCards();
    }

    @Override
    public List<Dataset> parse(List<String> data) {
        return null;
    }

    @Override
    public void precompute() {
    }
}
