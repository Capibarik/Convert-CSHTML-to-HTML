package org.example;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Класс со статическими методами для поиска cshtml-файлов
 */
public class SearchCSHTML {
    /**
     * Метод по двум параметрам: абсолютному пути папки и имени файла,- ищет внутри исходной директории файл с именем {@code file_name} и возвращает путь до ПАПКИ с таким же именем, что и у искомого файла.
     * Например, в папке {@code C:\Folder1} нужно найти файл с именем {@code File2.file} (этот файл может находиться в подпапках), тогда метод может вернуть следующий абсолютный путь:
     * {@code C:\Folder1\Folder2\Folder3\File2}
     *
     * @param folder_path имя папки, в которой будет происходить поиск файла {@code file_name}
     * @param file_name   искомый файл
     * @return абсолютный путь до файла без расширения (т. е. папку)
     * @throws FileNotFoundException исключение выбрасывается в таких же случаях, как и в {@link #getFilePath}
     */
    public static String getFolderPath(String folder_path, String file_name)
            throws FileNotFoundException {
        String extension = getExtension(file_name);
        return stringsSubtract(
                getFilePath(folder_path, file_name),
                extension);
    }

    /**
     * Метод на основе двух параметров: абсолютного пути до папки и шаблона имени файла,- находит абсолютный путь первого файла, подошедшего под заданный шаблон
     *
     * @param folder_path     абсолютный путь до папки
     * @param filename_filter шаблон для поиска файла по нему (например, при ".sln" найдется первый попавшийся файл с расширением ".sln")
     * @return абсолютный путь до файла
     * @throws FileNotFoundException исключение выбрасывается, когда:
     *                               не найдена папка,
     *                               вместо папки был передан файл,
     *                               файл по шаблону {@code filename_filter} не был найден
     */
    public static String getFilePath(String folder_path, String filename_filter)
            throws FileNotFoundException {
        File project_folder = new File(folder_path);
        if (!project_folder.exists())
            throw new FileNotFoundException("Папка не найдена");
        else if (project_folder.isFile())
            throw new FileNotFoundException("Был передан файл вместо папки");
        String project_file_path = searchingForPathOfProject(project_folder, filename_filter);
        if (project_file_path.isEmpty())
            throw new FileNotFoundException("Файл не был найден");
        return project_file_path;
    }

    /**
     * Рекурсивный метод, который использует DFS для поиска первого файла, подошедшего под шаблон {@code filename_filter}
     *
     * @param start_file      директория, в которой будет производиться поиск
     * @param filename_filter заданный шаблон
     * @return абсолютный путь до найденного файла, если такой существует, иначе пустая строка
     */
    public static String searchingForPathOfProject(File start_file, String filename_filter) {
        File[] list_files = start_file.listFiles();
        if (list_files != null) {
            for (File inner_file : list_files) {
                if (inner_file.isFile()) {
                    String file_name = inner_file.getName();
                    if (file_name.endsWith(filename_filter))
                        return inner_file.getAbsolutePath();
                } else {
                    String result = searchingForPathOfProject(inner_file, filename_filter);
                    if (!result.isEmpty())
                        return result;
                }
            }
        }
        return "";
    }

    /**
     * Метод для осуществления вычитания строк
     *
     * @param s1 строка, из которой будет вычитаться строка {@code s2}
     * @param s2 строка, которая будет вычитаться из строки {@code s1}
     * @return разность строк {@code s1} и {@code s2}
     */
    public static String stringsSubtract(String s1, String s2) {
        int diff = s1.length() - s2.length();
        if (diff < 0) return "";
        else return s1.substring(0, diff);
    }

    /**
     * Метод для получения расширения файла
     * @param file_path строка (абсолютный путь, относительный путь, имя файла и т. п.)
     * @return расширение файла, если оно есть, иначе пустая строка
     */
    public static String getExtension(String file_path) {
        StringBuffer extension = new StringBuffer();
        for (int i = file_path.length() - 1; i >= 0; i--) {
            char ch = file_path.charAt(i);
            extension.append(ch);
            if (ch == '.') {
                extension.reverse();
                return extension.toString();
            } else if (ch == '\\')
                return "";

        }
        return "";
    }
}
