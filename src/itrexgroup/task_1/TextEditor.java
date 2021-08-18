package itrexgroup.task_1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor {

    private final static String CE = "ce";
    private final static String CI = "ci";
    private final static String CK = "ck";
    private final static String C = "c";
    private final static String SE = "se";
    private final static String SI = "si";
    private final static String K = "k";
    private final static String OO = "oo";
    private final static String EE = "ee";
    private final static String U = "u";
    private final static String I = "i";
    private final static String REGEX = "(\\D)\\1+";


    private String removeCFromTheText(String text){

        String timeLine_1 = text.replaceAll(CE, SE);
        String timeLine_2 = timeLine_1.replaceAll(CI, SI);
        String timeLine_3 = timeLine_2.replaceAll(CK, K);

        return timeLine_3.replaceAll(C, K);

    }

    private String convertsDoubleLettersIntoOne(String text){

        StringBuilder result = new StringBuilder();

        String[] array = text.split(" ");

        for (String s : array) {
            Pattern p = Pattern.compile(REGEX);
            Matcher m = p.matcher(s);

            if (m.find()) {

                result.append(s.replaceAll(REGEX, "$1")).append(" ");

            } else {
                result.append(s).append(" ");
            }
        }

        return result.toString();
    }

    private String RemoveDoubleLetter(String text){
        String timeLine_1 = text.replaceAll(OO, U);
        String timeLine_2 = timeLine_1.replaceAll(EE, I);

        return convertsDoubleLettersIntoOne(timeLine_2);
    }
}
