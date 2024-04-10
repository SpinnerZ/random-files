package org.example;

import java.nio.file.Path;
import java.util.List;

public interface FileHandler {

  List<Path> loadTxtFiles(Path originPath);

  List<String> loadLines(Path txtFile);
}
