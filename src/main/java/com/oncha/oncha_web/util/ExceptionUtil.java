package com.oncha.oncha_web.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

    public static String getStackTrace(Exception e) {
        StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
        return error.toString();
    }

}
