package org.example.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.example.FileChecker;

public final class FileCheckerImpl implements FileChecker {

  private final String defaultDirectory;

  public FileCheckerImpl(String defaultDirectory) {
    this.defaultDirectory = defaultDirectory;
  }

  private FileCheckerImpl() {
    throw new IllegalStateException("Utility class");
  }

  public boolean isTxtFile(Path filePath) {

    final String fileName = filePath.getFileName().toString();

    return fileName.endsWith(".txt");
  }

  public Path getPathIfExistsOrDefaultDirectory(String arg) {

    try {

      final Path filePath = Path.of(arg).toRealPath();

      if (!Files.isDirectory(filePath)) {

        System.out.println(
            "Given path is not a directory. Working with default directory: " + defaultDirectory);
        return Path.of(defaultDirectory);
      }

      return filePath;

    } catch (IOException e) {

      System.out.println(
          "Given path does not exist. Working with default directory: " + defaultDirectory);
      return Path.of(defaultDirectory);
    }
  }

  public Path getValidWorkingDirectory(String path) {

    if (path != null) {
      return getPathIfExistsOrDefaultDirectory(path);
    }

    return Path.of(defaultDirectory);
  }
}
