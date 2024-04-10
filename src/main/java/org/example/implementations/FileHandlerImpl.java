package org.example.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.example.FileChecker;
import org.example.FileHandler;

public class FileHandlerImpl implements FileHandler {

  private final FileChecker fileChecker;

  public FileHandlerImpl(FileChecker fileChecker) {
    this.fileChecker = fileChecker;
  }

  public List<Path> loadTxtFiles(Path dir) {

    try (Stream<Path> files = Files.list(dir)) {
      return files.map(Path::getFileName).filter(fileChecker::isTxtFile).toList();
    } catch (IOException e) {
      throw new RuntimeException("Error while reading files from directory", e);
    }
  }

  public List<String> loadLines(Path txtFile) {

    try (Stream<String> lines = Files.lines(txtFile)) {
      return lines.toList();
    } catch (IOException e) {
      System.out.println("Error while reading lines from file " + txtFile.getFileName());
      System.out.println(e.getMessage());
      return new ArrayList<>();
    }
  }
}
