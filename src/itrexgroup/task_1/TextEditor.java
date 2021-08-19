package itrexgroup.task_1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor {

    private final String CE = "ce";
    private final String CI = "ci";
    private final String CK = "ck";
    private final String C = "c";
    private final String SE = "se";
    private final String SI = "si";
    private final String K = "k";
    private final String OO = "oo";
    private final String EE = "ee";
    private final String U = "u";
    private final String I = "i";
    private final String REGEX = "(\\D)\\1+";
    private final String E = "e";
    private final String A = "a";
    private final String AN = "an";
    private final String TH = "th";


    private String removeCFromTheText(String text){

        return text.replaceAll(CE, SE).replaceAll(CI, SI).replaceAll(CK, K).replaceAll(C, K);

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

        return convertsDoubleLettersIntoOne(text.replaceAll(OO, U).replaceAll(EE, I));
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

        return removeArticles(removeTheLetterEAtTheEndOfWord(removeDoubleLetter(removeCFromTheText(text))));
    }
}
