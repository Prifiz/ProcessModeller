package entities.export_import;

import entities.system.ExportedEntity;

public interface EntityExporter {
    void exportEntity(ExportedEntity exportedEntity);
    String exportEntityToString(ExportedEntity exportedEntity);
}
