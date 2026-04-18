package HomeWork2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/text.txt";
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Файл не найден ");
            return;
        }
        List<Student> students = readStudentsFromFile(filePath);
        if (students.isEmpty()) {
            System.out.println("Не удалось загрузить данные о студентах");
            return;
        }
        System.out.println("Заполненная коллекция студентов");
        students.forEach(student -> {
            System.out.println(student);
            student.getBooks().forEach(book -> System.out.println("  - " + book));
            System.out.println();
        });

        System.out.println("После стримов");
        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год: " + year),
                        () -> System.out.println("Не найдено")
                );
    }

    private static List<Student> readStudentsFromFile(String filename) {

        Map<String, List<Book>> studentBooksMap = new LinkedHashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                if (line.trim().isEmpty())
                    continue;
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String studentName = parts[0];
                    String bookTitle = parts[1];
                    String bookAuthor = parts[2];
                    int pages = Integer.parseInt(parts[3]);
                    int year = Integer.parseInt(parts[4]);

                    Book book = new Book(bookTitle, bookAuthor, pages, year);

                    studentBooksMap.computeIfAbsent(studentName, k -> new ArrayList<>()).add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return Collections.emptyList();
        }

        List<Student> students = new ArrayList<>();
        for (Map.Entry<String, List<Book>> entry : studentBooksMap.entrySet()) {
            students.add(new Student(entry.getKey(), entry.getValue()));
        }

        return students;
    }
}
