

/*Выполните ревью кода. Найдите логические ошибки. Прокомментируйте с различных точек зрения: архитектура, организация кода, нейминг, и т.д.
Можете ли вы предложить более оптимальное решение с точки зрения быстродействия / ресурсов?
Можно оставлять комментарии прямо в тексте, можно написать заключение отдельно, можно также править код или написать новый.*/



package test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Преобразовать файл так, чтобы выходной файл содержал только цифровые символы из исходного
 */
public class Main {

    public static void main(String[] args) throws IOException {
        StringBuffer result = new StringBuffer();
        var path = Path.of("test.txt");
        try (BufferedReader r = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = r.readLine()) != null) {
                readLine(result, line);
            }
        } catch (IOException e) {
            throw new WrongFileFormatException();
        }

        Files.writeString(Path.of("result.txt"), result.toString());
    }

    private static void readLine(StringBuffer accum, String s) {
        Optional<Character> chr = Optional.empty();
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) > '0') || (s.charAt(i) < '9')) {
                chr = Optional.of(s.charAt(i));
            }
        }
        chr.ifPresent(accum::append);
    }

    public static class WrongFileFormatException extends RuntimeException {

        public WrongFileFormatException() {
            super("File of a wrong format");
        }
    }
}


