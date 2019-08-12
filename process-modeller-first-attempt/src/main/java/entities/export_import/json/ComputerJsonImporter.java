package entities.export_import.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.export_import.EntityImporter;
import entities.system.Computer;
import entities.system.ImportedEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComputerJsonImporter implements EntityImporter {

    @Override
    public ImportedEntity importEntityFromFile(String fileLocation) {
        try {
            Path filePath = Paths.get(fileLocation);
            String content = new String(Files.readAllBytes(filePath));
            return importEntityFromString(content);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    // implement some kind of orElse() method or null object pattern
    @Override
    public ImportedEntity importEntityFromString(String serializedString) {
        try {
            ObjectMapper readMapper = new ObjectMapper();
            readMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return readMapper.readValue(serializedString, Computer.class);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
