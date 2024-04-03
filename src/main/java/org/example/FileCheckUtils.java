package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileCheckUtils {
  private static final String DEFAULT_DIRECTORY = "src/main/resources";

  private FileCheckUtils() {
    throw new IllegalStateException("Utility class");
  }

  static boolean isTxtFile(Path filePath) {

    final String fileName = filePath.getFileName().toString();

    return fileName.endsWith(".txt");
  }

  static Optional<Path> getPathIfExists(String arg) {

    try {

      final Path filePath = Path.of(arg).toRealPath();

      if (!Files.isDirectory(filePath)) {

        System.out.println("Path is not a directory");
        return Optional.empty();
      }

      return Optional.of(filePath);

    } catch (IOException e) {

      System.out.println("Path does not exist");
      return Optional.empty();
    }
  }

  static boolean hasAnyArguments(String[] args) {

    if (args.length == 0) {
      System.out.println("No arguments were passed");
      return false;
    }

    return true;
  }

  public static Path getValidDirectory(String[] args) {

    if (hasAnyArguments(args)) {

      final Optional<Path> filePath = getPathIfExists(args[0]);

      if (filePath.isPresent()) {
        return filePath.get();
      }
    }

    return Path.of(DEFAULT_DIRECTORY);
  }
}
