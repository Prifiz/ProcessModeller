package entities.engine;

import entities.system.SimpleSystem;

public class SimpleModellingEngine implements Engine {
    // on each step asks system if anything changed
    // runs the next step for every registered process
    // calculates processes run order

    private boolean paused = false;
    private boolean stopped = true;

    private long currentTime = 0L;

    private final long STEP_DELTA_TIME = 1000L; // millis

    private SimpleSystem system;

    public SimpleModellingEngine(SimpleSystem system) {
        this.system = system;
    }


    public void run() {
        while (!(stopped || paused)) {
            updateCurrentSystemTime();
            // ask system 4 changes
            //
            // build process run graph
            system.getComputers().forEach(computer -> {
                computer.updateState(currentTime);
            });
        }
    }

    private void updateCurrentSystemTime() {
        currentTime += STEP_DELTA_TIME;
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
