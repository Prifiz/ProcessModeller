package entities.engine;

public interface RewindableEngine extends Engine {

    void plusTime(int time);
    void minusTime(int time);
}
