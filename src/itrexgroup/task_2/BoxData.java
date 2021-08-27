package itrexgroup.task_2;

import java.util.Arrays;

public class BoxData {

    private int level;
    private int[] start;
    private int[] finish;
    private int value;

    public BoxData(int level, int[] start, int[] finish, int value) {
        this.level = level;
        this.start = start;
        this.finish = finish;
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getFinish() {
        return finish;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BoxData{" +
                "level=" + level +
                ", start=" + Arrays.toString(start) +
                ", finish=" + Arrays.toString(finish) +
                ", value=" + value +
                '}';
    }
}
