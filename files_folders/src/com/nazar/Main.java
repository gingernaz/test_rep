package com.nazar;

import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        Path oddFiles = Paths.get("E:\\test_rep\\data\\odd");
        Path evenFiles = Paths.get("E:\\test_rep\\data\\even");
        Path resultingPath = Paths.get("E:\\test_rep\\data\\result");

        moveFilesToDir(oddFiles, resultingPath, true);
        moveFilesToDir(evenFiles, resultingPath, false);

        //convert
        Path page = Paths.get("E:\\test_rep\\data\\result\\1.jpg");
        Path result = Paths.get("E:\\test_rep\\data\\result.pdf");
        convertToPDF(resultingPath, result);
    }

    public static void convertToPDF(Path sourceDir, Path destinationPDF) {
        try {
            ConvertJPEG converter = new ConvertJPEG(destinationPDF);
            converter.init();
            DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir);
            for (Path file: stream) {
                converter.setSource(file);
                converter.convert();
            }
            converter.close();
        } catch (Exception e) {
            System.err.println(e);
        }
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
            System.err.println(e);
        }
    }

}
