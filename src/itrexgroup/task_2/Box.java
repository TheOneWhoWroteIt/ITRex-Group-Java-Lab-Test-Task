package itrexgroup.task_2;

public class Box implements Cloneable {

    private boolean visit;
    private final char value;
    private int counStep;

    public Box(char value) {
        this.value = value;
        this.visit = false;
        this.counStep = Integer.MAX_VALUE;
    }



    public boolean isVisit() {
        return visit;
    }

    public char getValue() {
        return value;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public int getCounStep() {
        return counStep;
    }

    public void setCounStep(int counStep) {
        this.counStep = counStep;
    }

    @Override
    public Box clone() throws CloneNotSupportedException {
        return (Box) super.clone();
    }

    @Override
    public String toString() {
        return "Box{" +
                "visit=" + visit +
                ", value=" + value +
                ", counStep=" + counStep +
                '}';
    }
}
