import java.util.ArrayList;
import java.util.List;

public class Field {
  private int value = 0;
  private List<Integer> domain;
  private List<Field> neighbours; // A list of all fields that this field is constrained by
  private List<Arc> arcs; // A list of arcs belongs to the field
  private boolean added; // A boolean that checks if arcs are already initialized

  /*
   * ==============
   *  CONSTRUCTORS
   * ==============
   */

  // Constructor in case the field is unknown
  Field() {
    this.domain = new ArrayList<>(9);
    for (int i = 1; i < 10; i++)
      this.domain.add(i);
    this.arcs = new ArrayList<>();
    this.added = false;
  }

  // Constructor in case the field is known, i.e., it contains a value
  Field(int initValue) {
    this.value = initValue;
    this.domain = new ArrayList<>();
    this.domain.add(initValue);
    this.arcs = new ArrayList<>();
    this.added = true;
  }

  /*
   * =================
   *  VALUE FUNCTIONS
   * =================
   */
  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  /*
   * =====================
   *  NEIGHBOUR FUNCTIONS
   * =====================
   */
  public void setNeighbours(List<Field> neighbours) {
    this.neighbours = neighbours;
  }

  public List<Field> getNeighbours() {
    return neighbours;
  }

  public List<Field> getOtherNeighbours(Field b) {
    List<Field> newNeighbours = new ArrayList<>(neighbours);
    newNeighbours.remove(b);
    return newNeighbours;
  }

  /*
   * ==================
   *  ARC FUNCTIONS
   * ==================
   */

  public List<Arc> getArcs() {
    return arcs;
  }

  // Initializes arcs for the field
  public void addArcs() {
    if (!this.added) {
      for (Field f : this.neighbours) {
        this.arcs.add(new Arc(this, f));
      }
    }
  }
  /*
   * ==================
   *  DOMAIN FUNCTIONS
   * ==================
   */

  public List<Integer> getDomain() {
    return domain;
  }

  public int getDomainSize() {
    return domain.size();
  }

   /**
   * Removes the given value from the domain, and possibly assigns the last value to the field.
   *
   * @param value
   * @return true if the value was removed
   */
  public boolean removeFromDomain(int value) {
    boolean b = this.domain.remove(Integer.valueOf(value));

    // If there is only one value left in the domain, sets the value of the field to the last domain value.
    if (domain.size() == 1) {
      setValue(domain.get(0));
    }

    return b;
  }

  /*
   * ================
   *  MISC FUNCTIONS
   * ================
   */

  @Override
  public String toString() {
    return (value==0)? "." : String.valueOf(value);
  }
}
