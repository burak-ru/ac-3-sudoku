public class Arc {
    private Field leftHand;
    private Field rightHand;

    /**
     * Constructor of the Arc class
     * @param leftHand left hand side of the arc
     * @param rightHand right hand side of the arc
     */
    public Arc(Field leftHand, Field rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public Field getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Field leftHand) {
        this.leftHand = leftHand;
    }

    public Field getRightHand() {
        return rightHand;
    }

    public void setRightHand(Field rightHand) {
        this.rightHand = rightHand;
    }
}
