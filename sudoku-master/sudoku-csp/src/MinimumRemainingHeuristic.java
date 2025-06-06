public class MinimumRemainingHeuristic extends Heuristic {
    @Override
    public int compare(Arc o1, Arc o2) {
        if (o1.getRightHand().getDomainSize() < o2.getRightHand().getDomainSize()) {
            return -1;
        }
        else if (o1.getRightHand().getDomainSize() > o2.getRightHand().getDomainSize()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
