public class PriorityFinalizedHeuristic extends Heuristic {
    @Override
    public int compare(Arc o1, Arc o2) {
        if (o1.getRightHand().getDomainSize() == 1 && o2.getRightHand().getDomainSize() != 1) {
            return -1;
        }
        else if (o1.getRightHand().getDomainSize() != 1 && o2.getRightHand().getDomainSize() == 1) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
