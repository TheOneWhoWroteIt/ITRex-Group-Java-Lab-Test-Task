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
    private final static String E = "e";
    private final static String A = "a";
    private final static String AN = "an";
    private final static String TH = "th";


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

    private String removeDoubleLetter(String text){
        String timeLine_1 = text.replaceAll(OO, U);
        String timeLine_2 = timeLine_1.replaceAll(EE, I);

        return convertsDoubleLettersIntoOne(timeLine_2);
    }

    private String removeTheLetterEAtTheEndOfWord(String text){
        StringBuilder result = new StringBuilder();

        String[] array = text.split(" ");
        for(String s : array){
            if((s.length() > 1) && (E.equals(String.valueOf(s.charAt(s.length()-1))))){

                result.append(s, 0, s.length()-1).append(" ");

            }else{
                result.append(s).append(" ");
            }
        }
        return result.toString();
    }

    private String removeArticles(String text){
        StringBuilder result = new StringBuilder();

        String[] array = text.split(" ");
        for(String s : array){
            if(!s.equals(A) && !s.equals(AN) && !s.equals(TH)){
                result.append(s).append(" ");
            }
        }
        return result.toString();
    }

    public String editText(String text){
        String timeLine_1 = removeCFromTheText(text);
        String timeLine_2 = removeDoubleLetter(timeLine_1);
        String timeLine_3 = removeTheLetterEAtTheEndOfWord(timeLine_2);
        return removeArticles(timeLine_3);
    }
}
