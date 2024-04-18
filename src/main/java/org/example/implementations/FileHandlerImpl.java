package org.example.implementations;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.example.FileChecker;
import org.example.FileHandler;

public class FileHandlerImpl implements FileHandler {

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("dd-MM-yy");
  private final FileChecker fileChecker;

  public FileHandlerImpl(FileChecker fileChecker) {
    this.fileChecker = fileChecker;
  }

  public List<Path> loadTxtFiles(Path dir) {

    try (Stream<Path> files = Files.list(dir)) {
      return files.filter(fileChecker::isTxtFile).toList();
    } catch (IOException e) {
      throw new RuntimeException("Error while reading files from directory", e);
    }
  }

  public List<String> loadLines(Path txtFile) {

    try (Stream<String> lines = Files.lines(txtFile)) {
      return lines.toList();
    } catch (IOException e) {
      System.out.println("Error while reading lines from file " + txtFile.getFileName());
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public Path createFileName(Path dir) {

    return Path.of(dir.toString(), LocalDate.now().format(DATE_TIME_FORMATTER));
  }

  public void writeLine(Path file, Collection<String> lines) {

    Path target = Path.of(file.toString() + ".txt");

    try {
      Files.write(target, lines, CREATE, APPEND);
    } catch (IOException e) {
      System.out.println("Error while writing to file " + file.getFileName());
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void deletePreviousFiles(Path dir) {

    LocalDate today = LocalDate.now();

    try {
      Files.deleteIfExists(Path.of(dir.toString(), today.format(DATE_TIME_FORMATTER) + ".txt"));
      Files.deleteIfExists(
          Path.of(dir.toString(), today.minusDays(1L).format(DATE_TIME_FORMATTER) + ".txt"));
      Files.deleteIfExists(
          Path.of(dir.toString(), today.minusDays(2L).format(DATE_TIME_FORMATTER) + ".txt"));
      Files.deleteIfExists(
          Path.of(dir.toString(), today.minusDays(3L).format(DATE_TIME_FORMATTER) + ".txt"));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void openFile(File file) {

    try {
      Desktop.getDesktop().open(file);
    } catch (IOException e) {
      System.out.println("Error while opening file " + file.getName());
      System.out.println(e.getMessage());
    }
  }
}
