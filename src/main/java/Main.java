import java.util.List;

public class Main {
    static String filePath = "src/main/resources/Data.txt";
    public static void main(String[] args) {
        List<Unit> someUnits = UnitManager.getUnitList();
        List<Unit> listWithOnlyAnnotatedFields = UnitManager.getListWithOnlyAnnotatedFields(someUnits);
        UnitManager.saveListToFile(listWithOnlyAnnotatedFields,filePath );
        List<Unit> loadedList = UnitManager.loadListFromFile(filePath);
        for (Unit unit:loadedList){
            System.out.println(unit);
        }
    }
}

