package com.w1ll1am.htt_test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class logger {
    public final static Logger logger = Logger.getLogger("HTT_TEST.log");
    private static FileHandler fh;

    public static void setupLogger(String file) {
        try {
            logger.setUseParentHandlers(false);

            Logger globalLogger = Logger.getLogger("global");
            Handler[] handlers = globalLogger.getHandlers();
            for (Handler handler : handlers) {
                globalLogger.removeHandler(handler);
            }

            logger.setLevel(Level.INFO);
            fh = new FileHandler(System.getProperty("user.dir") + "/" + file, true);

            Logformatter formatter = new Logformatter();
            fh.setFormatter(formatter);

            logger.addHandler(fh);
        } catch (Exception e) {
            System.out.println("Unable to setup logger: Exception: " + e.getMessage());
        }
    }
}

class Logformatter extends Formatter {
    private static final DateFormat df = new SimpleDateFormat("[dd/MM/yyyy hh:mm:ss]");

    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);

        builder.append(df.format(new Date(record.getMillis()))).append(" - ");
        builder.append(function.SorrundWithChar("[-]", record.getSourceClassName().toString()));
        builder.append("["+record.getSourceMethodName()).append("] - ");
        builder.append(function.SorrundWithChar("[-]", record.getLevel().toString()));
        builder.append(formatMessage(record));
        builder.append("\n");

        return builder.toString();
    }

    public String getHead(Handler h) {
        return super.getHead(h);
    }

    public String getTail(Handler h) {
        return super.getTail(h);
    }
}