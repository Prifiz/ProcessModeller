package entities;

import entities.modeller.ModelSystem;

public class SampleModellingEngine implements Engine, Observer {
    // on each step asks system if anything changed
    // runs the next step for every registered process
    // calculates processes run order

    private boolean paused = false;
    private boolean stopped = true;

    private ModelSystem system;

    public SampleModellingEngine(ModelSystem system) {
        this.system = system;
        system.addObserver(this);
    }


    public void run() {
        while (!(stopped || paused)) {
            // ask system 4 changes
            //
            // build process run graph

        }
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

    @Override
    public void handleEvent() {

    }
}
