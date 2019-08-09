package entities.export_import.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.export_import.EntityImporter;
import entities.system.Computer;
import entities.system.ImportedEntity;

import java.io.IOException;

public class ComputerJsonImporter implements EntityImporter {

    @Override
    public ImportedEntity importEntityFromFile(String filePath) {
        return null;
    }

    // implement some kind of orElse() method

    @Override
    public ImportedEntity importEntityFromString(String serializedString) {
        try {
            ObjectMapper readMapper = new ObjectMapper();
            System.out.println("Deserialized:");
            readMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return readMapper.readValue(serializedString, Computer.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
