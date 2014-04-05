package com.nazar;

import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Path oddFiles = Paths.get("E:\\test_rep\\data\\odd");
        Path evenFiles = Paths.get("E:\\test_rep\\data\\even");
        Path resultingPath = Paths.get("E:\\test_rep\\data\\result");

        moveFilesToDir(oddFiles, resultingPath, true);
        moveFilesToDir(evenFiles, resultingPath, false);
    }

    public static void copyFiles(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    public static void moveFilesToDir(Path sourceDir, Path targetDir, Boolean odd) {
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir);
            int i = 2;
            if (odd) i = 1;
            for (Path file: stream) {
                Path target = Paths.get(targetDir + "\\" + i + ".jpg");
                copyFiles(file, target);
                i = i + 2;
            }
        } catch (Exception e) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(e);
        }
    }

}
