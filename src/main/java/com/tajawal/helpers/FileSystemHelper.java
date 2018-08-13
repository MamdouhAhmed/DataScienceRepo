package com.tajawal.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileSystemHelper {

    public static List<String> getAllAvailableFiles(File file) {
        List<String> allFiles = new ArrayList<>();
        if (file.isDirectory()) {
            allFiles = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .map(File::getPath)
                    .collect(Collectors.toList());
        } else {
            allFiles.add(file.getPath());
        }
        return allFiles;
    }
}
