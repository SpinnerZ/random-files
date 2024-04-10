package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.implementations.FileCheckerImpl;
import org.example.implementations.FileHandlerImpl;

public class Main {

  private static final String DEFAULT_DIRECTORY = "src/main/resources";

  private final FileChecker fileChecker = new FileCheckerImpl(DEFAULT_DIRECTORY);
  private final FileHandler fileHandler = new FileHandlerImpl(fileChecker);

  public static void main(String[] args) {
    Main mainInstance = new Main();
    mainInstance.randomizeLists(args);
  }

  public void randomizeLists(String[] args) {
    /* Create a result file at the results directory. Name it with the current date
     * Fill the result taking one line from each randomized file in round-robin*/

    final Path originPath = fileChecker.getValidWorkingDirectory(args[0]);
    final Path resultsPath = fileChecker.getValidWorkingDirectory(args[1]);

    final List<Path> txtFiles = fileHandler.loadTxtFiles(originPath);
    final List<List<String>> filesLines = new ArrayList<>();

    txtFiles.forEach(file -> filesLines.add(fileHandler.loadLines(file)));
    filesLines.forEach(Collections::shuffle);
  }
}
