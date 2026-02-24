package pro1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;

public class FileManager {
    String path;

    public FileManager(String path) {
        this.path = path;
    }

    public ArrayList<String> read() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public String normalize(String line) {
        return line.replaceAll("\\s*[:;=,]+", ",");
    }
    private String[] parseRow(String line) {
        line = normalize(line.trim());

        String[] parts = line.split(",", 2);

        if (parts.length < 2) {
            return new String[0];
        }

        String name = parts[0].trim();
        String data = parts[1].trim();

        return new String[]{name, data};
    }

    public ArrayList<String> parse(ArrayList<String> lines) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = parseRow(lines.get(i));

            if (parts.length == 2) {
                String name = parts[0];
                String data = parts[1];

                try {
                    Fraction fraction = Fraction.parse(data);
                    result.add(name + "," + fraction.toString());
                } catch (Exception e) {
                    result.add(lines.get(i));
                }
            } else {
                result.add(lines.get(i));
            }
        }
        return result;
    }

    public void Output(ArrayList<String> lines, String path) {

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(path));) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
