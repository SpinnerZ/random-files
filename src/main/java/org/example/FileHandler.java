package org.example;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public interface FileHandler {

  List<Path> loadTxtFiles(Path originPath);

  List<String> loadLines(Path txtFile);

  Path createFileName(Path dir);

  void writeLine(Path file, Collection<String> line);
}
