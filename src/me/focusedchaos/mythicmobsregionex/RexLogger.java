package me.focusedchaos.mythicmobsregionex;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.StringWriter;
import java.io.PrintWriter;


public class RexLogger {
    private Logger _logger;
    private String _pluginName;
    private int _maxLogLevel = 0;

    public RexLogger(Logger logger, String pluginName, int maxLogLevel)
    {
        this._logger = logger;
        this._pluginName = pluginName;
        this._maxLogLevel = maxLogLevel;
    }
    public void logDebug(String message, int logLevel)
    {
        if (logLevel < this._maxLogLevel) {
            this._logger.log(Level.INFO, message);
        }
    }
    public void logInfo(String message)
    {
        this._logger.log(Level.INFO, message);
    }
    public void logWarning(String message)
    {
        this._logger.log(Level.WARNING, message);
    }
    public void logSevere(String message)
    {
        this._logger.log(Level.SEVERE, message);
    }
    public void logSevere(Exception ex)
    {
        this._logger.log(Level.SEVERE, ex.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String stackTrace = sw.toString();
        System.out.println(stackTrace);
        this._logger.log(Level.SEVERE, stackTrace);
    }
}
