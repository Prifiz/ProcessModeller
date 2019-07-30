package entities.modeller;

public class System {

    // stores all the nodes common info (like snapshot)
    private String checksum;

    public boolean isAnythingChanged() {
        return false;
    }

    public void registerChanges() {
        // happens when something in processes changed (actions, process kills, etc. which influence process run logic)
    }

}
