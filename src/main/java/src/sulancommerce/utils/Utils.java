package src.sulancommerce.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String treatPercentString(String sentence, boolean usePercent, boolean useUpper){
        if(useUpper)
            sentence = sentence.toUpperCase();
        if(usePercent)
            sentence = "%"+sentence+"%";

        return sentence;
    }

    public static String getExpirationTime(long minutes){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Adicionar 30 minutos
        LocalDateTime newDateTime = currentDateTime.plusMinutes(minutes);

        // Converter para ZonedDateTime com fuso horário "-04:00"
        ZonedDateTime zonedDateTime = ZonedDateTime.of(newDateTime, ZoneId.of("-04:00"));

        // Formatar a data no formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        return zonedDateTime.format(formatter);
    }
}
