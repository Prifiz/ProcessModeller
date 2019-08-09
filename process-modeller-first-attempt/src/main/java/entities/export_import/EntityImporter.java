package entities.export_import;

import entities.system.ImportedEntity;

public interface EntityImporter {
    ImportedEntity importEntityFromFile(String filePath);
    ImportedEntity importEntityFromString(String serializedString);
}
