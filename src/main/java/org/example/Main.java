package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.example.implementations.FileCheckerImpl;
import org.example.implementations.FileHandlerImpl;

public class Main {

  private final FileChecker fileChecker = new FileCheckerImpl();
  private final FileHandler fileHandler = new FileHandlerImpl(fileChecker);

  public static void main(String[] args) {
    Main mainInstance = new Main();
    mainInstance.randomizeLists(args);
  }

  public void randomizeLists(String[] args) {

    if (args.length != 2) {
      System.out.println("Please provide two arguments: origin directory and results directory");
      return;
    }

    final Path originPath = fileChecker.getValidWorkingDirectory(args[0]);
    final Path resultsPath = fileChecker.getValidWorkingDirectory(args[1]);

    List<Path> txtFiles = new ArrayList<>(fileHandler.loadTxtFiles(originPath));
    Collections.shuffle(txtFiles);
    List<List<String>> filesLines = new ArrayList<>();

    txtFiles.forEach(file -> filesLines.add(new ArrayList<>(fileHandler.loadLines(file))));
    filesLines.forEach(Collections::shuffle);

    Path resultFile = fileHandler.createFileName(resultsPath);
    writeOneLineFromEachFile(resultFile, filesLines);

    System.out.println("Result file created: " + resultFile);
  }

  private void writeOneLineFromEachFile(Path resultFile, List<List<String>> filesLines) {

    Collection<String> result = new ArrayList<>();

    while (filesLines.stream().anyMatch(list -> !list.isEmpty())) {
      filesLines.forEach(
          list -> {
            if (!list.isEmpty()) {
              result.add(list.remove(0));
            }
          });

      fileHandler.writeLine(resultFile, result);
      result.clear();
    }
  }
}
