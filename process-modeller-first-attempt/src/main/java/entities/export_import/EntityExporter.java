package entities.export_import;

import entities.system.ExportedEntity;

public interface EntityExporter {
    void exportEntityToFile(ExportedEntity exportedEntity, String fileLocation);

    String exportEntityToString(ExportedEntity exportedEntity);
}
