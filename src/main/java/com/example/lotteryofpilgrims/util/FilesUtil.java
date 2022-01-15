package com.example.lotteryofpilgrims.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FilesUtil {

    private FilesUtil() {
    }

    public static final Path root = Paths.get(".").normalize().toAbsolutePath();
    public static final String path = root + "/src/main/resources/files/";
    public static int count = 1;

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {

        String oldFileName = +count - 1 + ".tmp";
        String newFileName = +count + ".tmp";

        // delete old tmp file , then write the new one
        deleteFile(oldFileName);
        File file = new File(path + newFileName);
        multipartFile.transferTo(file);
        count++;
        return file;
    }

    public static void deleteFile(String fileName) {
        File file = new File(path + fileName);
        if (file.exists() && file.isFile()) {
            log.info("start deleting file {}",fileName);
            file.delete();
        }
    }

}
