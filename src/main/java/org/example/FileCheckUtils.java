package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileCheckUtils {
  private static final String DEFAULT_DIRECTORY = "src/main/resources";

  private FileCheckUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static boolean isTxtFile(Path filePath) {

    final String fileName = filePath.getFileName().toString();

    return fileName.endsWith(".txt");
  }

  public static Path getPathIfExistsOrDefaultDirectory(String arg) {

    try {

      final Path filePath = Path.of(arg).toRealPath();

      if (!Files.isDirectory(filePath)) {

        System.out.println(
            "Given path is not a directory. Working with default directory: " + DEFAULT_DIRECTORY);
        return Path.of(DEFAULT_DIRECTORY);
      }

      return filePath;

    } catch (IOException e) {

      System.out.println(
          "Given path does not exist. Working with default directory: " + DEFAULT_DIRECTORY);
      return Path.of(DEFAULT_DIRECTORY);
    }
  }

  public static boolean hasAnyArguments(String[] args) {

    if (args.length == 0) {
      System.out.println("No arguments were passed");
      return false;
    }

    return true;
  }

  public static Path getValidWorkingDirectory(String[] args) {

    if (hasAnyArguments(args)) {
      return getPathIfExistsOrDefaultDirectory(args[0]);
    }

    return Path.of(DEFAULT_DIRECTORY);
  }
}
