package aJDBC.lesson4.hw1;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
//----------------------------------DAO ----------------------------------------------------------
        String[] format = {"DOC", "JPEG", "XML"};
        List<File> file = new ArrayList<>();
        file.add(new File(140, "tora", "DOC", 234, new Storage(1, format, "GERMANY", 1024)));
        file.add(new File(141, "toraNew2", "XML", 234, null));

//StorageDAO
//        StorageDAO.save(new Storage(4,format,"korea",1800));
//        StorageDAO.update(new Storage(2,format,"ENGLAND",1850));d
//        System.out.println(StorageDAO.findById(4));
//        StorageDAO.delete(5);

//FileDAO
        System.out.println(FileDAO.findById(111));
//        System.out.println(FileDAO.update (file));
//        System.out.println(FileDAO.save(file));
//        FileDAO.delete(120);


//---------------------------------------------Controller -------------------------------------------------------------
//        List<File> file=new ArrayList<>();
//            file.add(new File(110,"COMPUTER","XML",27, null));
//            file.add(new File(111,"TELEVISION","JPEG",48, null));
//            file.add(new File(112,"GOOMlUC","DOC",67, null));
//            file.add(new File(113,"TABLE","JPEG",196, null));
//
//        Storage storage=StorageDAO.findById(1);
//
//        Storage storage2=StorageDAO.findById(2);

//-------------------------------------------------check---------------------------------------------------------------
//checkFormat
//        try{
//            Controller.checkFormat(storage, file);
//        }catch (RuntimeException e) {
//            System.err.println("Error:" + e.getMessage());
//            System.exit(0);
//        }

//checkSize
//        try{
//            Controller. checkSize(storage, file);
//        }catch (RuntimeException e) {
//            System.err.println("Error:" + e.getMessage());
//            System.exit(0);
//        }

//check    --- пример не проходит по размеру
//        try{
//            Controller. check(storage, file);
//        } catch (RuntimeException e) {
//             System.err.println("Error:" + e.getMessage());
//             System.exit(0);
//        }

//-------------------------------------------------delete---------------------------------------------------------------
//        Controller.delete(new File(112,"GOOMlUC","DOC",67, storage));

//-------------------------------------------------add------------------------------------------------------------------
//        Controller.put(storage,new File(112,"GOOMlUC","DOC",67, null));

//        Controller.putAll(storage, file);

//-------------------------------------------------transfer-------------------------------------------------------------
//        Controller.transferFile(storage,storage2,112);
//        Controller.transferFile(storage,storage2,110);

//           Controller.transferAll(StorageDAO.findById(2), StorageDAO.findById(1));
    }
}
