package itrexgroup.task_2;

public class Box {

    private boolean visit;
    private final char value;

    public Box(char value) {
        this.value = value;
        this.visit = false;
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

    @Override
    public String toString() {
        return "Box{" +
                "visit=" + visit +
                ", value=" + value +
                '}';
    }
}
