package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

public class LaunchHTML {
    public static void main(String[] args)
            throws IOException {
        // Текущий каталог: "Translate CSHTML to HTML"
        String path_to_config = System.getenv("MyAppVar");
        String[] ini_data = readDataIni(path_to_config);
        String path_to_file = translation(processedData(ini_data));
        String application = ini_data[3];
        boolean isExecuted = Boolean.parseBoolean(ini_data[4]);
        execute(path_to_file, application, isExecuted);
    }
    @Deprecated
    private static String[] readData(String config_file_path)
            throws FileNotFoundException {
        Scanner in = new Scanner(new File(config_file_path));
        String project_path = in.nextLine();
        String cshtml_file_name = in.nextLine();
        String output_folder_path = in.nextLine();
        return new String[]{project_path, cshtml_file_name, output_folder_path};
    }
    public static String[] readDataIni(String config_file_path)
            throws IOException {
        FileInputStream file_stream = new FileInputStream(config_file_path);
        InputStreamReader reader = new InputStreamReader(file_stream, StandardCharsets.UTF_8);
        Properties p = new Properties(); p.load(reader);
        String project_path = p.getProperty("project_folder_path");
        String cshtml_file_name = p.getProperty("target_file_path");
        String output_folder_path = p.getProperty("output_folder_path");
        String application = p.getProperty("application");
        String isExecuted = p.getProperty("execute");
        return new String[]{project_path, cshtml_file_name, output_folder_path, application, isExecuted};
    }

    private static String[] processedData(String[] data_ini)
            throws FileNotFoundException {
        String project_path = data_ini[0];
        String cshtml_file_name = data_ini[1];
        String output_folder_path = data_ini[2];
        String project_folder_path = SearchCSHTML.getFolderPath(project_path, ".sln");
        String cshtml_file_path = SearchCSHTML.getFilePath(project_folder_path + "\\Views", cshtml_file_name);
        String cshtml_layout_path = SearchCSHTML.getFilePath(project_folder_path + "\\Views", "_Layout.cshtml");
        return new String[]{cshtml_layout_path, cshtml_file_path, output_folder_path};
    }

    private static String translation(String[] files_for_translation)
            throws IOException {
        String layout = files_for_translation[0];
        String file_body = files_for_translation[1];
        String output_folder_path = files_for_translation[2];
        String path_to_file = TranslateToHTML.translateFile(layout, file_body, output_folder_path);
        return path_to_file;
    }
    private static void execute(String path_to_file, String application, boolean isExecuted)
            throws IOException {
        if (!path_to_file.isEmpty()) {
            if (isExecuted) {
                Runtime rt = Runtime.getRuntime();
                rt.exec("cmd.exe /C start " + application + " \"" + path_to_file + "\"");
                System.out.println("File has been launched");
            }
        }
        else System.out.println("File has NOT been formed");
    }
}