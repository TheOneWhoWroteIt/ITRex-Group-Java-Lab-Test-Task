package itrexgroup.task_1;

public class TextEditor {

    private final static String CE = "ce";
    private final static String CI = "ci";
    private final static String CK = "ck";
    private final static String C = "c";
    private final static String SE = "se";
    private final static String SI = "si";
    private final static String K = "k";


    private String removeCFromTheText(String text){

        String timeLine_1 = text.replaceAll(CE, SE);
        String timeLine_2 = timeLine_1.replaceAll(CI, SI);
        String timeLine_3 = timeLine_2.replaceAll(CK, K);

        return timeLine_3.replaceAll(C, K);

    }
}
