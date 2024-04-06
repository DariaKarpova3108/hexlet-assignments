package exercise;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.*;

// BEGIN

/*
 * Метод принимает на вход экземпляр класса и проверяет, что свойства,
 * помеченные в классе аннотацией @NotNull, не имеют значение null.
 * Метод должен вернуть список List с названием полей, которые не прошли валидацию
 * (т.е. помечены аннотацией @NotNull, но при этом имеют значение null).
 * Обратите внимание, что свойства в объекте приватные и для обращения к ним нет селекторов,
 * поэтому вам нужно будет воспользоваться рефлексией.
 * */
public class Validator {
    public static List<String> validate(Address address) {
        Class<Address> addressClass = Address.class;
        Field[] fields = addressClass.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (var field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true); // Разрешаем доступ к приватным полям
                Object value = null;
                try {
                    value = field.get(address); // Получаем значение поля из объекта address
                } catch (IllegalAccessException e) {
                    System.err.println("Ошибка доступа к полю: " + field.getName() + ": " + e.getMessage());
                }
                if (value == null) {
                    String fieldName = field.getName();
                    list.add(fieldName); // Добавляем название поля в список невалидных
                }
                field.setAccessible(false);
            }
        }
        return list;
    }

    /*
     * В классе Validator создайте публичный статический метод advancedValidate(),
     * который может работать с двумя аннотациями: @MinLength и @NotNull.
     * Метод должен возвращать словарь Map, в котором ключ — это имя поля,
     * не прошедшего валидацию, а значение — список List строк, содержащих сообщение об ошибке.
     * */

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        Class<Address> classAddress = Address.class;
        Field[] fields = classAddress.getDeclaredFields();
        for (var field : fields) {
            if (field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(MinLength.class)) {
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(address);
                } catch (IllegalAccessException e) {
                    System.err.println("Ошибка доступа к полю: " + field.getName() + ": " + e.getMessage());
                    continue;
                }
                String nameField = field.getName();
                List<String> errors = new ArrayList<>();
                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    errors.add("can not be null");
                    result.put(nameField, errors);
                }

                if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                    int length = minLengthAnnotation.minLength();

                    if (value instanceof String) {
                        String strValue = (String) value;
                        if (strValue!=null && strValue.length() < length) {
                            errors.add("length less than 4");
                            result.put(nameField, errors);
                        }
                    }

                    if (!errors.isEmpty()) {
                        result.put(nameField, errors);
                    }
                    field.setAccessible(false);
                }
            }
        }
        return result;
    }
}
// END
