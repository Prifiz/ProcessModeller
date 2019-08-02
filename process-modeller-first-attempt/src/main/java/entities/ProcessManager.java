package entities;

public interface ProcessManager<S extends StorageDevice> {
    void registerProcess(AbstractProcess<S> process);
}
