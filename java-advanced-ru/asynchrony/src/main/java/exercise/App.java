package exercise;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String pathResult) {
        CompletableFuture<String> context1 = CompletableFuture.supplyAsync(() -> {
            Path pathFile1 = Paths.get(path1).toAbsolutePath().normalize();

            if (!Files.exists(pathFile1)) {
                try {
                    throw new NoSuchFileException("Файл 1 не существует ");
                } catch (NoSuchFileException e) {
                    throw new RuntimeException(e);
                }
            }

            String fileContext1;

            try {
                fileContext1 = Files.readString(pathFile1);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка на пути чтения 1 файла", e);
            }
            return fileContext1;
        });

        CompletableFuture<String> context2 = CompletableFuture.supplyAsync(() -> {
            Path pathFile2 = Paths.get(path2).toAbsolutePath().normalize();

            if (!Files.exists(pathFile2)) {
                try {
                    throw new NoSuchFileException("Файл 2 не существует");
                } catch (NoSuchFileException e) {
                    throw new RuntimeException(e);
                }
            }

            String fileContext2;

            try {
                fileContext2 = Files.readString(pathFile2);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка на пути чтения 2 файла", e);
            }
            return fileContext2;
        });

        return context1.thenCombine(context2, (f1, f2) -> {
            Path resultPath = Paths.get(pathResult).toAbsolutePath().normalize();
            if (!Files.exists(resultPath)) {
                try {
                    Files.createFile(resultPath);
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка при создании итогового файла", e);
                }
            }

            String contextResult = f1 + f2;

            try {
                Files.writeString(resultPath, contextResult, StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при записи итогового файла", e);
            }
            return "Файлы успешно объединены!";
        }).exceptionally(ex -> {
            System.out.println("Ooops! - " + ex.getMessage());
            return "WRONG RESULT";
        });
    }


    public static CompletableFuture<Long> getDirectorySize(String path) {
        return CompletableFuture.supplyAsync(() -> {
            Path pathDirectory = Paths.get(path).toAbsolutePath().normalize();
            long size = 0;

            if (!Files.exists(pathDirectory) || !Files.isDirectory(pathDirectory)) {
                throw new RuntimeException("Указанный путь не существует или не является директорией: " + path);
            }

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(pathDirectory)) {
                for (var entry : stream) {
                    if (Files.isRegularFile(entry)) {
                        size += Files.size(entry);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return size;
        });
    }


    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/dest.txt"
        );
        CompletableFuture<Long> size = getDirectorySize("src/main/resources");

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(result, size);

        allFutures.thenRun(() -> {
            try {
                System.out.println("Результат: " + result.get());
                System.out.println("Размер директории: " + size.get() + " байт");
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).join();
        // END
    }
}

