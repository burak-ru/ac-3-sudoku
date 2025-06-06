import java.util.Comparator;

public abstract class Heuristic implements Comparator<Arc> {
    @Override
    public abstract int compare(Arc o1, Arc o2);
}
