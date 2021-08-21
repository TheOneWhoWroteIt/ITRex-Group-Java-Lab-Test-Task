package itrexgroup.task_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameLogic {

    private final static char SYMBOL_POINT = '.';
    private final static char SYMBOL_ONE = '1';
    private final static char SYMBOL_TWO = '2';
    private final static String FILE_NAME = "src/itrexgroup/task_2/INPUT.txt";
    private final static int SECONDS_FOR_STEP = 5;

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
}
