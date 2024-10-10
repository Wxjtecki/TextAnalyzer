//Autor Kamil Pajączkowski
import java.io.*;
import java.util.*;

public class TextAnalyzer {

    public static void main(String[] args) {
        // Ścieżka do pliku tekstowego
        String filePath = "path_to_your_file.txt";

        try {
            String text = readFile(filePath);
            System.out.println("Tekst został wczytany z pliku.");

            int wordCount = countWords(text);
            int sentenceCount = countSentences(text);
            Map<String, Integer> wordFrequency = countWordFrequency(text);

            // Wyświetlanie wyników
            System.out.println("Ilość słów: " + wordCount);
            System.out.println("Ilość zdań: " + sentenceCount);
            System.out.println("Najczęściej używane słowa: " + wordFrequency);

            // Generowanie raportu
            generateReport(wordCount, sentenceCount, wordFrequency, "report.txt");
            System.out.println("Raport został zapisany do pliku report.txt.");

        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wczytywania pliku: " + e.getMessage());
        }
    }

    // Funkcja do wczytywania pliku
    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    // Funkcja do liczenia słów
    public static int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    // Funkcja do liczenia zdań
    public static int countSentences(String text) {
        String[] sentences = text.split("[.!?]");
        return sentences.length;
    }

    // Funkcja do liczenia wystąpień słów
    public static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        String[] words = text.toLowerCase().split("\\s+");

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", ""); // Usuwanie znaków specjalnych
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        return wordFrequency;
    }

    // Funkcja do generowania raportu
    public static void generateReport(int wordCount, int sentenceCount, Map<String, Integer> wordFrequency, String outputPath) throws IOException {
        FileWriter writer = new FileWriter(outputPath);
        writer.write("Raport analizy tekstu\n");
        writer.write("=====================\n");
        writer.write("Ilość słów: " + wordCount + "\n");
        writer.write("Ilość zdań: " + sentenceCount + "\n");
        writer.write("Najczęściej używane słowa:\n");

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
        }

        writer.close();
    }
}
