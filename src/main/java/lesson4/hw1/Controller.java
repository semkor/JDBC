package lesson4.hw1;

import java.util.*;

public class Controller {

//--------------------------------------------add-----------------------------------------------------------------------
    public static void put(Storage storage, File file) {
        List<File> files =new ArrayList<>();
        files.add(file);
        saveFile(storage, files);
    }

    public static void putAll(Storage storage, List<File> file) {
        saveFile(storage, file);
    }

//--------------------------------------------delete--------------------------------------------------------------------
    public static void delete(File file) {
        List<File> files =new ArrayList<>();
        files.add(new File(file.getId(),file.getName(),file.getFormat(),file.getSize(),null));
        FileDAO.update(files);
    }

//--------------------------------------------transfer------------------------------------------------------------------
    public static void transferFile(Storage storageFrom, Storage storageTo, long id) {
        saveFile(storageTo, FileDAO.findById(id));
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) {
        saveFile(storageTo, FileDAO.findByIdStorage(storageFrom.getId()));
    }

//--------------------------------------------validate------------------------------------------------------------------
    public static void check (Storage storage, List<File> file) {
        try {
            checkFormat(storage, file);
            checkSize(storage, file);
        } catch (RuntimeException e) {
            System.err.println("Error:" + e.getMessage());
            System.exit(0);
        }
    }

/* проверка  1  -  есть ли в Storage файл с таким  id - нет смысла проверять, так как
                        - file_id - уникальный
                        - у File, только одно поле  Storage */

//проверка 2    -  может ли Storage хранить  файлы такого формата
    private static boolean checkFormat(Storage storage, List<File> file) throws RuntimeException {
        int count = 0;
        String[] check = storage.getFormatsSupported();
        for (File el: file) {
            int countPlace =0;
            for (String els : check) {
                if (el.getFormat().equalsIgnoreCase(els))
                    countPlace++;
            }
            if (countPlace == 0) {
                System.err.println("Storage id - " + storage.getId() + " files cannot be stored in the format - " + el.getFormat() + " for  file id - " + el.getId());
                count++;
            }
        }
        if (count >0)
            throw new RuntimeException("Storage id: " + storage.getId() + " files cannot be stored in the format ");
    return true;
    }


//проверка 3   -  хватит ли размера Storage для перенесения файлов
    private static boolean checkSize(Storage storage, List<File> file) throws RuntimeException {
        long size = storage.getStorageMaxSize();

        long sizeAllFileStorage = 0;
        List<File> filesAll = FileDAO.findByIdStorage(storage.getId());
        for (File el:filesAll){
            sizeAllFileStorage+=el.getSize();
        }

        long sizeFile= 0;
            for(File el:file){
                sizeFile+=el.getSize();
            }

        if ((size-sizeAllFileStorage-sizeFile) < 0)
            throw new RuntimeException("Storage id: " + storage.getId() + " there is not enough space to transfer the file." +
                    " Storage size - " + size + "   The size of all files on storage - " + sizeAllFileStorage +  "   The size of the files we are transferring  - " + sizeFile);
       return true;
    }

//-------------------------------------преобразование / передача на DAO ------------------------------------------------
    private static void saveFile(Storage storage, List<File> file){
        check (storage, file);

        List<File> newFile=new ArrayList<>();
        for(File el: file){
            newFile.add(new File(el.getId(),el.getName(),el.getFormat(),el.getSize(),storage));
        }
        FileDAO.update (newFile);
    }
}