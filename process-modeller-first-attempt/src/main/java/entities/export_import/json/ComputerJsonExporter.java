package entities.export_import.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.export_import.EntityExporter;
import entities.system.ExportedEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ComputerJsonExporter implements EntityExporter {

    @Override
    public void exportEntityToFile(ExportedEntity exportedEntity, String fileLocation) {
        Path filePath = Paths.get(fileLocation);
        try {
            Files.write(
                    filePath,
                    exportEntityToString(exportedEntity).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.WRITE);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String exportEntityToString(ExportedEntity exportedEntity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(exportedEntity);
        } catch (JsonProcessingException ex) {
            System.out.println("Error: " + ex.getMessage());
            return "";
        }
    }
}
