package entities.system.logger;

public interface LoggedEntity { // Loggable? Some concrete interface for logger attachment
    void attachLogger(Logger logger);
    void notifyAllLoggers();
}
