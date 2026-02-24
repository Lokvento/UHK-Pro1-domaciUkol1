package pro1;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File inputDir = new File("input");
        File outputDir = new File("output");

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File[] files = inputDir.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    System.out.println(f.getName());
                    FileManager fm = new FileManager(f.getPath());
                    var lines = fm.read();
                    var parsed = fm.parse(lines);

                    String outputPath = new File(outputDir, f.getName()).getPath();

                    fm.Output(parsed, outputPath);
                }
            }
        } else {
            System.out.println("The 'input' directory does not exist or is empty.");
        }
    }
}