package entities.system;

public interface LoggedEntity { // Loggable? Some concrete interface for logger attachment
    void attachLogger(Logger logger);
    void notifyAllLoggers();
}
