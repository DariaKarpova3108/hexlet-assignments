package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN

/*
 * Создайте класс App с публичным статическим методом buildApartmentsList().
 * Метод принимает в качестве первого аргумента список List объектов недвижимости, реализующих интерфейс Home.
 * Метод сортирует объекты по площади по возрастанию, берет первые n элементов
 * и возвращает строковые представления этих объектов в виде списка List.
 * Количество n элементов передаётся в метод buildApartmentsList() вторым параметром.
 * */

public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        if (count > homes.size()) {
            count = homes.size();
        }

        List<Home> homes2 = homes.subList(0, count);
        List<String> result = homes2.stream().sorted(Comparator.comparing(Home::getArea)).map(Home::toString)
                .collect(Collectors.toList());
        return result;
    }
}

// END
