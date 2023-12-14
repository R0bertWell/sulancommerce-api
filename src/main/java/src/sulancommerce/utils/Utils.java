package src.sulancommerce.utils;

public class Utils {

    public static String treatPercentString(String sentence, boolean usePercent, boolean useUpper){
        if(useUpper)
            sentence = sentence.toUpperCase();
        if(usePercent)
            sentence = "%"+sentence+"%";

        return sentence;
    }
}
