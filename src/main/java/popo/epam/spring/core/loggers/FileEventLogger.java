package popo.epam.spring.core.loggers;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import popo.epam.spring.core.beans.Event;
import popo.epam.spring.core.exeptions.FileNotWritable;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Log4j2
@RequiredArgsConstructor
public class FileEventLogger implements EventLogger {

    @NonNull private String filename;
    private File file;

    private void init() throws FileNotWritable, IOException {
        this.file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.canWrite()) {
            throw new FileNotWritable();
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", Charset.defaultCharset(), true);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
