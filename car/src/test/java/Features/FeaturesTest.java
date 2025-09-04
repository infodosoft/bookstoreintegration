package Features;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FeaturesTest {

    @Test
    public void testParallel() {
        Results results = Runner.path("classpath:Features")
            .tags("~@noApix")
            .tags("~@networkLogin")
            .parallel(5);
        FeaturesTest.generateReport(results.getReportDir());
     //   assertEquals(0, results.getFailCount(), results.getErrorMessages());

    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "myKarate");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }

}
