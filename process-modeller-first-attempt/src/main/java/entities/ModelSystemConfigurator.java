package entities;

import entities.modeller.ModelSystem;

public class ModelSystemConfigurator {

    private ModelSystem system;

    public ModelSystemConfigurator(ModelSystem system) {
        this.system = system;
    }

    public void configure() {
        this.system.addComputer(new Computer("totally new and empty"));
    }
}
