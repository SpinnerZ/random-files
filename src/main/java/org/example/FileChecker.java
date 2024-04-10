package org.example;

import java.nio.file.Path;
import java.util.Optional;

public interface FileChecker {

  boolean isTxtFile(Path filePath);

  Optional<Path> getPathIfExists(String arg);

  Path getValidWorkingDirectory(String path);
}
