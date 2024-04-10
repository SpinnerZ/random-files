package org.example.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.example.FileChecker;

public final class FileCheckerImpl implements FileChecker {

  public boolean isTxtFile(Path filePath) {

    final String fileName = filePath.getFileName().toString();

    return fileName.endsWith(".txt");
  }

  public Optional<Path> getPathIfExists(String arg) {

    if (arg == null) {
      return Optional.empty();
    }

    try {

      final Path filePath = Path.of(arg).toRealPath();

      if (!Files.isDirectory(filePath)) {

        System.out.println("Given directory was not found.");
        System.exit(1);
      }

      return Optional.of(filePath);

    } catch (IOException e) {
      return Optional.empty();
    }
  }

  public Path getValidWorkingDirectory(String directory) {

    Optional<Path> path = getPathIfExists(directory);

    if (path.isEmpty()) {
      System.out.println("Invalid directory: " + directory);
      System.exit(1);
    }

    return path.get();
  }
}
