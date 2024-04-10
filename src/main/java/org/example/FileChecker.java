package org.example;

import java.nio.file.Path;

public interface FileChecker {

  boolean isTxtFile(Path filePath);

  Path getPathIfExistsOrDefaultDirectory(String arg);

  Path getValidWorkingDirectory(String path);
}
