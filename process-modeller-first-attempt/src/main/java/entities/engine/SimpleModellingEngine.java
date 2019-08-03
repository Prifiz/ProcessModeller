package entities.engine;

import entities.system.SimpleSystem;

public class SimpleModellingEngine implements LimitedStepsEngine {
    // on each step asks system if anything changed
    // runs the next step for every registered process
    // calculates processes run order

    private boolean paused = false;
    private boolean stopped = false;

    private long currentTime = 0L;

    private long stepDeltaTime = 1000L; // millis

    private SimpleSystem system;

    private int stepsPassed = 0;


    public SimpleModellingEngine(SimpleSystem system) {
        this.system = system;
    }

    public SimpleModellingEngine withCustomDeltaTime(long deltaTimeMillis) {
        this.stepDeltaTime = deltaTimeMillis;
        return this;
    }

    private void mainLoop() {
        updateCurrentSystemTime();
        // ask system 4 changes
        //
        // build process run graph
        system.getComputers().forEach(computer -> {
            computer.updateState(currentTime);
        });
    }

    public void run() {
        while (!(stopped || paused)) {
            mainLoop();
        }
    }

    @Override
    public void run(int stepsLimit) {
        while (!(stopped || paused || stepsPassed >= stepsLimit)) {
            mainLoop();
            stepsPassed++;
        }
    }

    private void updateCurrentSystemTime() {
        currentTime += stepDeltaTime;
    }

    public void start() {

    }

    public void resume() {

    }

    public void stop() {
        if (!stopped) {
            stopped = true;
        }
    }

    public void pause() {
        if (!paused) {
            paused = true;
        }
    }
}
