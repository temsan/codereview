

/*Выполните ревью кода. Найдите логические ошибки. Прокомментируйте с различных точек зрения: архитектура, организация кода, нейминг, и т.д.
Можете ли вы предложить более оптимальное решение с точки зрения быстродействия / ресурсов?
Можно оставлять комментарии прямо в тексте, можно написать заключение отдельно, можно также править код или написать новый.*/



package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Преобразовать файл так, чтобы выходной файл содержал только цифровые символы из исходного
 */
public class Main {

    public static void main(String[] args) throws IOException {
        var path = Path.of("test.txt");
        String stringResult;
        try (Stream<String> lineStream = Files.newBufferedReader(path).lines()) {
            stringResult = lineStream.map(String::chars)
                    .flatMapToInt(s -> s.filter(chr -> chr >= (int) '0' && chr <= (int) '9'))
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        } catch (IOException e) {
            throw new WrongFileFormatException();
        }

        Files.writeString(Path.of("result.txt"), stringResult);
    }

    public static class WrongFileFormatException extends RuntimeException {

        public WrongFileFormatException() {
            super("File of a wrong format");
        }
    }
}


