package org.example;

import java.nio.file.Path;

public class Main {

  public static void main(String[] args) {

    final Path filePath = FileCheckUtils.getValidDirectory(args);
  }
}
