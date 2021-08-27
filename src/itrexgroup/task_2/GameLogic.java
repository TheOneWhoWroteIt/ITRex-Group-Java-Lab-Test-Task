package itrexgroup.task_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLogic {

    private final char SYMBOL_POINT = '.';
    private final char SYMBOL_ONE = '1';
    private final char SYMBOL_TWO = '2';
    private final String FILE_NAME = "src/itrexgroup/task_2/INPUT.txt";
    private final int SECONDS_FOR_STEP = 5;

    private Map<Integer, Box[][]> convertDataFromAFileToAMap(String fileName){
        Map<Integer, Box[][]> map = new HashMap<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

            String line = bufferedReader.readLine();
            String[] array = line.split(" ");
            int H = Integer.parseInt(array[0]);
            int M = Integer.parseInt(array[1]);
            int N = Integer.parseInt(array[2]);
            int m = 0;

            Box[][] arrayBox = new Box[M][N];
            while(line != null){

                line = bufferedReader.readLine();
                if(line != null && !line.isEmpty()){

                    for (int i = 0; i < line.length(); i++) {

                        arrayBox[m][i] = new Box(line.charAt(i));

                    }
                    m++;
                    map.put(H, arrayBox);

                }else{
                    m = 0;
                    arrayBox = new Box[M][N];
                    H--;
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return map;
    }

    private  Box[][] findMatrixByLevel(Map<Integer, Box[][]> map, int level){

        return map.get(level);
    }

    private int[] findStartAndFinishBox( Box[][] array, char symbol){
        int[] result = new int[2];

        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j].getValue() == symbol){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    private List<int[]> findNeighboringBox(Box[][] array, int[] box){

        List<int[]> list = new ArrayList<>();
        int M = box[0];
        int N = box[1];

        if(M-1 >= 0){
            if((array[M-1][N].getValue() == SYMBOL_POINT || array[M-1][N].getValue() == SYMBOL_TWO) && !array[M-1][N].isVisit()){
                int[] result = new int[2];
                result[0] = M-1;
                result[1] = N;
                list.add(result);
            }

        }

        if(N+1 < array[0].length){
            if((array[M][N+1].getValue() == SYMBOL_POINT || array[M][N+1].getValue() == SYMBOL_TWO) && !array[M][N+1].isVisit()){
                int[] result = new int[2];
                result[0] = M;
                result[1] = N+1;
                list.add(result);
            }

        }

        if(M+1 < array.length){
            if((array[M+1][N].getValue() == SYMBOL_POINT || array[M+1][N].getValue() == SYMBOL_TWO) && !array[M+1][N].isVisit()){
                int[] result = new int[2];
                result[0] = M+1;
                result[1] = N;
                list.add(result);
            }

        }

        if(N-1 >= 0){
            if((array[M][N-1].getValue() == SYMBOL_POINT || array[M][N-1].getValue() == SYMBOL_TWO) && !array[M][N-1].isVisit()){
                int[] result = new int[2];
                result[0] = M;
                result[1] = N-1;
                list.add(result);
            }

        }

        return list;
    }

    private List<int[]> findPointForExitAndEnter(Box[][] levelStart, Box[][]levelNext ){

        List<int[]> listBox = new ArrayList<>();

        for (int i = 0; i < levelStart.length; i++) {
            for (int j = 0; j < levelStart[0].length ; j++) {
                if((levelStart[i][j].getValue() == SYMBOL_ONE && levelNext[i][j].getValue() == SYMBOL_POINT)
                        || (levelStart[i][j].getValue() == SYMBOL_POINT && levelNext[i][j].getValue() == SYMBOL_POINT)
                        || (levelStart[i][j].getValue() == SYMBOL_POINT && levelNext[i][j].getValue() == SYMBOL_TWO) ){
                    int[] coordinate = new int[2];
                    coordinate[0] = i;
                    coordinate[1] = j;
                    listBox.add(coordinate);

                }

            }

        }
        return listBox;
    }

    private boolean compareCoordinate(int[] first, int[] second){
        return first[0] == second[0] && first[1] == second[1];
    }

    private void setValueForStep(Box[][] box, int[] coordinate, int value){
        box[coordinate[0]][coordinate[1]].setCounStep(value);

    }

    private void updateStepCount(Box[][] box, int[] start){

        List<int[]> list = findNeighboringBox(box, start);
        for (int i = 0; i < list.size() ; i++) {
            int[] coor = list.get(i);
            int temp = box[start[0]][start[1]].getCounStep();
            if((temp + 1) < box[coor[0]][coor[1]].getCounStep()){
                setValueForStep(box, coor, temp + 1);
            }

        }

    }

    private Box[][] createCopyBox(Box[][] box){
        Box[][] result = new Box[box.length][box[0].length];

        for (int i = 0; i <box.length ; i++) {
            for (int j = 0; j < box[i].length; j++) {
                try {
                    result[i][j] = box[i][j].clone();
                } catch (CloneNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return result;

    }

    private int[] findNextBoxForStep(Box[][] array, int[] box){
        int M = box[0];
        int N = box[1];

        if(M-1 >= 0 && !array[M-1][N].isVisit() && (array[M-1][N].getValue() == SYMBOL_POINT || array[M-1][N].getValue() == SYMBOL_TWO)){
            int[] result = new int[2];
            result[0] = M-1;
            result[1] = N;
            return result;


        }

        else if(N+1 < array[0].length && !array[M][N+1].isVisit() && (array[M][N+1].getValue() == SYMBOL_POINT || array[M][N+1].getValue() == SYMBOL_TWO)){
            int[] result = new int[2];
            result[0] = M;
            result[1] = N+1;
            return result;

        }

        else if(M+1 < array.length && !array[M+1][N].isVisit() && (array[M+1][N].getValue() == SYMBOL_POINT || array[M+1][N].getValue() == SYMBOL_TWO)){
            int[] result = new int[2];
            result[0] = M+1;
            result[1] = N;
            return result;

        }

        else if(N-1 >= 0 && !array[M][N-1].isVisit() && (array[M][N-1].getValue() == SYMBOL_POINT || array[M][N-1].getValue() == SYMBOL_TWO)){
            int[] result = new int[2];
            result[0] = M;
            result[1] = N-1;
            return result;

        }
        return null;
    }




    private Box[][] getUpdateBoxMatrixByStepValue(Box[][] box, int[] start, int[] finish) {
        Box[][] result = createCopyBox(box);
        int[] prev = null;
        int[] first = start;
        List<int[]> listPrev = new ArrayList<>();
        int count = 0;

        setValueForStep(result, first, 0);

        while(!compareCoordinate(first, finish)){

            updateStepCount(result, first);
            result[first[0]][first[1]].setVisit(true);
            int[] second = findNextBoxForStep(result, first);

            if(second != null){
                prev = first;
                first = second;
                listPrev.add(prev);
                count++;
            }else{
                second = first;
                first = prev;

                if(count == 1){
                    prev = null;
                    count = 0;
                }
                else{
                    prev = listPrev.get(listPrev.size() - count);
                    count--;
                }

            }
        }

        return  result;

    }

    private int getStepCountFromStartUntilFinish(Box[][] box, int[] start, int[] finish){
        Box[][] temp = createCopyBox(box);
        int result;

        try{
            Box[][] updateBox = getUpdateBoxMatrixByStepValue(temp, start, finish);
            result = updateBox[finish[0]][finish[1]].getCounStep();

        }catch (IndexOutOfBoundsException ex){
            result = -1;
        }

        return result;
    }




    private List<BoxData> getListBoxData() {


        List<BoxData> resultList = new ArrayList<>();

        Map<Integer, Box[][]> map = convertDataFromAFileToAMap(FILE_NAME);
        int[] start = findStartAndFinishBox(findMatrixByLevel(map, map.size() - 1), SYMBOL_ONE);
        int[] finish = findStartAndFinishBox(findMatrixByLevel(map, 0), SYMBOL_TWO);


        int step = 0;

        for (int i = map.size()-1; i >= 0; i--) {

            if (i == 0) {


                Box[][] firstLevelBoxMatrix = map.get(i);
                Box[][] secondLevelBoxMatrix = map.get(i+1);
                BoxData boxData = null;
                List<int[]> listPointEnter = findPointForExitAndEnter(firstLevelBoxMatrix, secondLevelBoxMatrix);
                for (int j = 0; j < listPointEnter.size(); j++) {
                    step = getStepCountFromStartUntilFinish(firstLevelBoxMatrix, listPointEnter.get(j), finish);

                    int[] startCoordinate = {listPointEnter.get(j)[0], listPointEnter.get(j)[1]};
                    int[] finishCoordinate = {finish[0], finish[1] };


                    boxData = new BoxData(i, startCoordinate, finishCoordinate, step);
                    resultList.add(boxData);


                }


            } else if (i == (map.size() - 1)) {


                Box[][] firstLevelBoxMatrix = map.get(i);
                Box[][] secondLevelBoxMatrix = map.get(i - 1);
                BoxData boxData = null;
                List<int[]> listPointExit = findPointForExitAndEnter(firstLevelBoxMatrix, secondLevelBoxMatrix);
                for (int j = 0; j < listPointExit.size(); j++) {
                    step = getStepCountFromStartUntilFinish(firstLevelBoxMatrix, start, listPointExit.get(j));


                    int[] startCoordinate = {start[0], start[1]};
                    int[] finishCoordinate = {listPointExit.get(j)[0], listPointExit.get(j)[1] };


                    boxData = new BoxData(i, startCoordinate, finishCoordinate, step);
                    resultList.add(boxData);


                }



            } else {

                Box[][] one = map.get(i + 1);
                Box[][] two = map.get(i);
                Box[][] three = map.get(i - 1);
                BoxData boxData = null;

                List<int[]> listPointExit = findPointForExitAndEnter(two, three);
                List<int[]> listPointEnter = findPointForExitAndEnter(one, two);
                for (int j = 0; j < listPointEnter.size(); j++) {
                    for (int k = 0; k < listPointExit.size(); k++) {
                        step = getStepCountFromStartUntilFinish(two, listPointEnter.get(j), listPointExit.get(k));



                        int[] startCoordinate = {listPointEnter.get(j)[0], listPointEnter.get(j)[1]};
                        int[] finishCoordinate = {listPointExit.get(k)[0], listPointExit.get(k)[1] };


                        boxData = new BoxData(i, startCoordinate, finishCoordinate, step);
                        resultList.add(boxData);


                    }
                }



            }

        }

        return resultList;

    }


    public int getTime(){
        int time = 0;

        List<BoxData> boxDataList = getListBoxData();
        int level = boxDataList.get(0).getLevel();
        BoxData boxDataTemp = null;
        int step = 0;


        int min = Integer.MAX_VALUE;

        for (int i = 0; i < boxDataList.size(); i++) {
            if(boxDataList.get(i).getLevel() == level){

                if(boxDataList.get(i).getValue() < min && boxDataList.get(i).getValue() != -1 ){
                    min = boxDataList.get(i).getValue();
                    boxDataTemp = boxDataList.get(i);
                    step += boxDataTemp.getValue();
                }
            }
        }

        level--;


        min = Integer.MAX_VALUE;
        while(level > 0){
            for (int i = 0; i <boxDataList.size(); i++) {
                if(compareCoordinate(boxDataTemp.getFinish(), boxDataList.get(i).getStart()) && boxDataList.get(i).getLevel() == level){

                    if(boxDataList.get(i).getValue() < min && boxDataList.get(i).getValue() != -1){
                        min = boxDataList.get(i).getValue();
                        boxDataTemp = boxDataList.get(i);
                        step += boxDataTemp.getValue();
                    }
                }
            }

            level--;

        }

        min = Integer.MAX_VALUE;
        for (int i = 0; i <boxDataList.size(); i++) {
            if(compareCoordinate(boxDataTemp.getFinish(), boxDataList.get(i).getStart()) && boxDataList.get(i).getLevel() == level){

                if(boxDataList.get(i).getValue() < min && boxDataList.get(i).getValue() != -1){
                    min = boxDataList.get(i).getValue();
                    boxDataTemp = boxDataList.get(i);
                    step += boxDataTemp.getValue();
                }
            }
        }


        time = (boxDataList.get(0).getLevel() + step)*SECONDS_FOR_STEP;


        return time;
    }
}
