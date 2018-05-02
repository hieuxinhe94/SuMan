package com.hieuxinhe.suman.Utils;

import com.hieuxinhe.suman.Constants.LocateLanguageConstants;

import java.util.Locale;

public class SystemHelper {

    public static Locale ConvertLocateStringToLocate(String localeText)
    {
        switch (localeText)
        {
            case LocateLanguageConstants.Chinese: return  Locale.CHINA;


            case LocateLanguageConstants.Japan: return  Locale.JAPANESE;
        }
        return Locale.ENGLISH;
    }
}
