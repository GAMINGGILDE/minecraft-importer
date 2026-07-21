package de.gilde.statsimporter.importer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class ImportCoordinatorTest {

    @Test
    void statsDirectoryUsesPaper261LevelLayout() {
        Path levelDirectory = Path.of("server").resolve("custom-level-name");

        assertEquals(
                levelDirectory.resolve("players").resolve("stats"),
                ImportCoordinator.statsDirectory(levelDirectory)
        );
    }
}
