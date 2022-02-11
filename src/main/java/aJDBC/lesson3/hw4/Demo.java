package aJDBC.lesson3.hw4;

public class Demo {
    public static void main(String[] args) {
        Solution solution=new Solution();

//добавление в базу данных  - 1000 значений  (That took 145732...149138 milliseconds)
        solution.testSavePerfomance();


//удаление 1000 записей отдельными запросами по полю ID  (That took 143136...143937 milliseconds)
        solution.testDeleteByIdPerfomance();

//удаление 1000 записей одним запросом SQL  (That took 155 milliseconds)
        solution.testDeletePerfomance();


//выбирает 1000 записей отдельным запросом по полю ID  (That took 139608 milliseconds)
        solution.testSelectByIdPerfomance();

//выбирает 1000 записей одним запросом SQL  (That took 359 milliseconds)
        solution.testSelectPerfomance();
    }
}
