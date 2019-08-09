package entities.export_import.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.export_import.EntityExporter;
import entities.system.ExportedEntity;

public class ComputerJsonExporter implements EntityExporter {

    @Override
    public void exportEntityToFile(ExportedEntity exportedEntity, String filePath) {

    }

    @Override
    public String exportEntityToString(ExportedEntity exportedEntity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(exportedEntity);
        } catch (JsonProcessingException ex) {
            System.out.println("Error: " + ex.getMessage());
            return "";
        }
    }
}
