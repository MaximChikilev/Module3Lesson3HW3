import java.io.*;
import java.lang.reflect.Field;
import java.rmi.MarshalledObject;
import java.util.*;

public class UnitManager {
    public static List<Unit> getUnitList() {
        List<Unit> unitList = new ArrayList<>();
        Unit unit1 = new Unit("11111", "Iron", "Philips", "China", 20.8);
        Unit unit2 = new Unit("22222", "TV", "Sony", "China", 1500.0);
        Unit unit3 = new Unit("33333", "Smartphone", "Apple", "China", 455.0);
        Unit unit4 = new Unit("44444", "Vacuum cleaner", "Philips", "China", 120.8);
        Unit unit5 = new Unit("55555", "Washing machine", "Philips", "China", 200.8);
        unitList.add(unit1);
        unitList.add(unit2);
        unitList.add(unit3);
        unitList.add(unit4);
        unitList.add(unit5);
        return unitList;
    }

    public static List<Unit> getListWithOnlyAnnotatedFields(List<Unit> fullList) {
        List<Unit> listWithAnnotatedFields = new ArrayList<>();
        for (Unit unit : fullList) {
            try {
                Map<String, Object> map = getAnnotatedFieldsWithValue(unit);
                Unit newUnit = getNewUnitWithAnnotatedFieldsWithValues(map);
                listWithAnnotatedFields.add(newUnit);
            } catch (IllegalAccessException e) {

            }
        }
        return listWithAnnotatedFields;
    }

    private static Map<String, Object> getAnnotatedFieldsWithValue(Unit unit) throws IllegalAccessException {
        Map<String, Object> annotatedFieldsWithValue = new HashMap<>();
        Class unitClass = unit.getClass();
        Field[] fields = unitClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                annotatedFieldsWithValue.put(field.getName(), field.get(unit));
            }
        }
        return annotatedFieldsWithValue;
    }

    private static Unit getNewUnitWithAnnotatedFieldsWithValues(Map<String, Object> map) throws IllegalAccessException {
        Unit newUnit = new Unit();
        Class newUnitClass = newUnit.getClass();
        Field[] fields = newUnitClass.getDeclaredFields();
        for (Field field : fields) {
            if (map.containsKey(field.getName())) {
                Boolean defaultAccessible = field.isAccessible();
                field.setAccessible(true);
                field.set(newUnit, map.get(field.getName()));
                field.setAccessible(defaultAccessible);
            }
        }
        return newUnit;
    }

    public static void saveListToFile(List<Unit> list, String pathToFile) {
        try (FileOutputStream fos = new FileOutputStream(pathToFile)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Unit unit : list) {
                oos.writeObject(unit);
            }
            oos.close();
        } catch (IOException e) {
        }
    }

    public static List<Unit> loadListFromFile(String pathToFile) {
        List<Unit> list = new ArrayList<>();
        try {
            FileInputStream fis= new FileInputStream(pathToFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (ois.read()==-1){
                list.add((Unit) ois.readObject());
            }
        }  catch (IOException e) {

        } catch (ClassNotFoundException e) {
        }
        return list;
    }
}
