package org.example;

import static org.example.FileCheckUtils.getValidWorkingDirectory;

import java.nio.file.Path;

public class Main {

  public static void main(String[] args) {

    /*Get origin directory
     * Get destiny directory
     * Load all txt files in origin
     * Randomize each loaded file
     * Create a result file at the destiny directory. Name it with the current date
     * Fill the result taking one line from each randomized file in round-robin*/

    final Path originPath = getValidWorkingDirectory(args);
  }
}
