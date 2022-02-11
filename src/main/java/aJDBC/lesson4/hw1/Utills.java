package aJDBC.lesson4.hw1;

import java.util.*;

public class Utills {

    public static void check(Storage storage, List<File> file) {
        try {
            checkFormat(storage.getId(), storage.getFormatsSupported(), file);
            checkSize(storage.getId(), storage.getStorageMaxSize(), file);

        } catch (RuntimeException e) {
            System.err.println("Error:" + e.getMessage());
            System.exit(0);
        }
    }

/* проверка  1  -  есть ли в Storage файл с таким  id - нет смысла проверять, так как
                        - file_id - уникальный
                        - у File, только одно поле  Storage */

    //проверка 2    -  может ли Storage хранить  файлы такого формата
    public static boolean checkFormat(long storageId, String[] fomatSupported, List<File> file) throws RuntimeException {
        int count = 0;
        for (File el : file) {
            int countPlace = 0;
            for (String els : fomatSupported) {
                if (el.getFormat().equalsIgnoreCase(els))
                    countPlace++;
            }
            if (countPlace == 0) {
                System.err.println("Storage id - " + storageId + " files cannot be stored in the format - " + el.getFormat() + " for  file id - " + el.getId());
                count++;
            }
        }
        if (count > 0)
            throw new RuntimeException("Storage id: " + storageId + " files cannot be stored in the format ");
        return true;
    }


    //проверка 3   -  хватит ли размера Storage для перенесения файлов
    public static boolean checkSize(long storageId, long size, List<File> file) throws RuntimeException {
        long sizeAllFileStorage = 0;
        List<File> filesAll = FileDAO.findByIdStorage(storageId);
        for (File el : filesAll) {
            sizeAllFileStorage += el.getSize();
        }

        long sizeFile = 0;
        for (File el : file) {
            sizeFile += el.getSize();
        }

        if ((size - sizeAllFileStorage - sizeFile) < 0)
            throw new RuntimeException("Storage id: " + storageId + " there is not enough space to transfer the file." +
                    " Storage size - " + size + "   The size of all files on storage - " + sizeAllFileStorage + "   The size of the files we are transferring  - " + sizeFile);
        return true;
    }

    //-------------------------------------преобразование / передача на DAO --------------------------------------------
    public static void saveFile(Storage storage, List<File> file) {
        check(storage, file);

        List<File> newFile = new ArrayList<>();
        for (File el : file) {
            newFile.add(new File(el.getId(), el.getName(), el.getFormat(), el.getSize(), storage));
        }
        FileDAO.update(newFile);
    }
}
