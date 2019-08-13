package entities.export_import;

import entities.system.ImportedEntity;

public interface EntityImporter {
    ImportedEntity importEntityFromFile(String fileLocation);

    ImportedEntity importEntityFromString(String serializedString);
}
