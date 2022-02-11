package aJDBC.lesson4.hw1;

import java.util.*;

public class Controller {

    //--------------------------------------------add-----------------------------------------------------------------------
    public static void put(Storage storage, File file) {
        List<File> files = new ArrayList<>();
        files.add(file);
        Utills.saveFile(storage, files);
    }

    public static void putAll(Storage storage, List<File> file) {
        Utills.saveFile(storage, file);
    }

    //--------------------------------------------delete--------------------------------------------------------------------
    public static void delete(File file) {
        List<File> files = new ArrayList<>();
        files.add(new File(file.getId(), file.getName(), file.getFormat(), file.getSize(), null));
        FileDAO.update(files);
    }

    //--------------------------------------------transfer------------------------------------------------------------------
    public static void transferFile(Storage storageFrom, Storage storageTo, long id) {
        Utills.saveFile(storageTo, FileDAO.findById(id));
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) {
        Utills.saveFile(storageTo, FileDAO.findByIdStorage(storageFrom.getId()));
    }
}