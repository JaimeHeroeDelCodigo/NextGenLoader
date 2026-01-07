import org.example.nextgenloader.files.FileManagement;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;


public class TestFileManagement {


    @Test
    public void testSearch() {

        Path outputPath = Paths.get("");
        String result = FileManagement.searchPathOfFile(outputPath,"");

        assert !result.isEmpty();
    }

}
