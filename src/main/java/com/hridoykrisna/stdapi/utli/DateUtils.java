package com.hridoykrisna.stdapi.utli;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public static Date getExpirationTime(Long expirationHour){
        Date date = new Date();
        Long expirationMills = TimeUnit.HOURS.toMillis(expirationHour);
        return new Date(expirationMills+date.getTime());
    }
}
