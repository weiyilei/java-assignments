import javafx.application.Application;
import javafx.stage.Stage;
import static org.assertj.core.api.Assertions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class TestFileWriter {

        @Rule
        public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
        public void testFileExist(){
            Class<? extends Class> aClass1 = FileWriter.class.getClass();
            Method[] methods = aClass1.getMethods();
            Method method = null;
            String path = "C:\\test\\test.txt";
            String content = "test";
            for(Method i : methods) {
                    if (i.getName().equals("writeFile")) {
                            try {
                                    i.invoke(path, content);
                            } catch (Exception e) {
                                    assertEquals("file already exists", e.getMessage());
                            }
                    }
            }
    }

    @Test
        public void testFileNotExist() throws IOException {
            Class<? extends Class> aClass1 = FileWriter.class.getClass();
            Method[] methods = aClass1.getMethods();
            Method method = null;
            String content = "test";
            File output = temporaryFolder.newFolder("test1").toPath().resolve("test1.txt").toFile();
        for(Method i : methods) {
            if (i.getName().equals("writeFile")) {
                try {
                    i.invoke(output.getPath(), content);
                } catch (Exception e) {
                }
            }
        }
            assertThat(output)
                    .hasContent("test")
                    .hasExtension("txt")
                    .hasParent(temporaryFolder.getRoot().toPath().resolve("test1").toString());
    }
        }
