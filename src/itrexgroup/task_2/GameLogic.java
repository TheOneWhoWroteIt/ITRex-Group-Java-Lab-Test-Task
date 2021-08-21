package itrexgroup.task_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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

    private int[] findNextBox(Box[][] array, int[] box){
        int[] result = new int[2];
        int M = box[0];
        int N = box[1];

        if(M-1 >= 0){
            if((array[M-1][N].getValue() == SYMBOL_POINT || array[M-1][N].getValue() == SYMBOL_TWO) && !array[M-1][N].isVisit()){
                result[0] = M-1;
                result[1] = N;
            }

        }

        if(N+1 < array[0].length){
            if((array[M][N+1].getValue() == SYMBOL_POINT || array[M][N+1].getValue() == SYMBOL_TWO) && !array[M][N+1].isVisit()){
                result[0] = M;
                result[1] = N+1;
            }

        }

        if(M+1 < array.length){
            if((array[M+1][N].getValue() == SYMBOL_POINT || array[M+1][N].getValue() == SYMBOL_TWO) && !array[M+1][N].isVisit()){
                result[0] = M+1;
                result[1] = N;
            }

        }

        if(N-1 >= 0){
            if((array[M][N-1].getValue() == SYMBOL_POINT || array[M][N-1].getValue() == SYMBOL_TWO) && !array[M][N-1].isVisit()){
                result[0] = M;
                result[1] = N-1;
            }

        }

        return result;
    }

    private int[] findPointForExitAndEnter(Box[][] levelEnd, Box[][]levelNext ){
        int[] result = new int[2];

        for (int i = 0; i < levelEnd.length; i++) {
            for (int j = 0; j < levelEnd[0].length ; j++) {
                if(levelEnd[i][j].getValue() == SYMBOL_POINT && levelNext[i][j].getValue() == SYMBOL_POINT){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
