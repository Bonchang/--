package controller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class CFolderRefresh {
    public static void refreshFolder(String path) {
        try {
            Path folder = new File(path).toPath();
            Files.list(folder).findFirst().ifPresent(Path::getParent);
        } catch (Exception e) {
            throw new RuntimeException("폴더 새로고침 실패", e);
        }
    }

}
