import java.util.*;

public class Game {
  private Sudoku sudoku;

  Game(Sudoku sudoku) {
    this.sudoku = sudoku;
  }

  public void showSudoku() {
    System.out.println(sudoku);
  }

  /**
   * Implementation of the AC-3 algorithm
   *
   * @return true if the constraints can be satisfied, else false
   */
  public boolean solve() {
    List<Arc> arcs = new ArrayList<>();
    Queue<Arc> queue = new PriorityQueue<>(new SimpleHeuristic());
    int count = 0; // to compare complexity next
    // create arcs for each field and add all to arcs list and queue
    for (Field[] fields : sudoku.getBoard()) {
      for (Field f : fields) {
        f.addArcs();
        arcs.addAll(f.getArcs());
      }
    }
    queue.addAll(arcs);

    while (!queue.isEmpty()) {
      count++; // counting every operation
      Arc arc = queue.poll();
      int check = arc.getLeftHand().getDomainSize(); // to check later if left domain size is changed
      revise(arc);
      if (arc.getLeftHand().getDomainSize() == 0) {
        return false; // inconsistency found
      }
      if (check != arc.getLeftHand().getDomainSize()) {
        // add every arc that has the same right hand as the main arcs left hand to queue if it's not already there
        for (Arc a : arcs) {
          if (arc.getLeftHand() == a.getRightHand() && !queue.contains(a)) {
            queue.add(a);
          }
        }
      }
    }
    System.out.println("Complexity: " + count);
    return true; // all constraints satisfied
  }

  /**
   * Implementation of revise operation
   *
   * @param arc gets revised
   */
  public void revise(Arc arc) {
    List<Integer> leftDomain = arc.getLeftHand().getDomain();
    List<Integer> rightDomain = arc.getRightHand().getDomain();
    List<Integer> valuesToRemove = new ArrayList<>();
    for (int l : leftDomain) {
      boolean found = false;
      for (int r : rightDomain) {
        if (l != r) {
          found = true;
          break;
        }
      }
      if (!found) {
        valuesToRemove.add(l);
      }
    }
    // Remove values from left hand domain that don't satisfy the constraint with right hand domain
    for (Integer val : valuesToRemove) {
      arc.getLeftHand().removeFromDomain(val);
    }
  }

  /**
   * Checks the validity of a sudoku solution
   *
   * @return true if the sudoku solution is correct
   */
  public boolean validSolution() {
    // in sudoku each field should have 1 possible result and if domain size of a field is not 1, then it's not valid
    for (Field[] fields : sudoku.getBoard()) {
      for (Field f : fields) {
        if (f.getDomainSize() != 1) {
          return false;
        }
      }
    }
    return true;
  }
}
