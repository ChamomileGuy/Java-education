import buildings.*;
import java.util.Arrays; // only to use toString in Flat class and visualise instances

public class Main {
    public static void main(String[] s) {

        System.out.println("Создаём квартиры flat1, flat2, flat3");
        Flat flat0 = new Flat();
        Flat flat1 = new Flat(100);
        Flat flat2 = new Flat(150, 4);

        System.out.println("Создаём массив квартир flatArr1");
        Flat[] flatArr1 = new Flat[]{flat0, flat1, flat2};

        System.out.println("Создаём этаж floor1");
        DwellingFloor floor1 = new DwellingFloor(flatArr1);
        System.out.println("Массив квартир на этаже floor1: " + Arrays.toString(floor1.getFlatArr()));

        System.out.println("\nИзменим на этаже floor1 количество комнат в 0 квартире на 3 и площадь в 1 квартире на 80");
        floor1.getFlatByNumber(0).setRoomCount(3);
        floor1.getFlatByNumber(1).setFlatSquare(80);
        System.out.println("Массив квартир на этаже floor1: " + Arrays.toString(floor1.getFlatArr()));

        System.out.println("\nКоличество комнат на этаже: " + floor1.getFlatCount());

        System.out.println("Общая площадь квартир на этаже: " + floor1.floorSquareSum());

        System.out.println("Общее количество комнат в квартирах на этаже: " + floor1.floorFlatSum());

        System.out.println("Квартира номер 0 на этаже floor1: " + floor1.getFlatByNumber(0));

        System.out.println("Создаём квартиру flat3");
        Flat flat3 = new Flat(200, 5);

        System.out.println("Заменяем квартиру под номером 0 на квартиру flat3");
        floor1.setFlatByNumber(0, flat3);

        System.out.println("Квартира номер 0 на этаже floor1: " + floor1.getFlatByNumber(0) + "\n");

        System.out.println("Зарезервируем номер квартиры 4 на будущее. Даём на вход номер квартиры и квартиру");
        floor1.addFlatOnFloor(4, flat3);
        System.out.println("Массив квартир на этаже floor1: " + Arrays.toString(floor1.getFlatArr()));
        System.out.println("Квартира не добавилась на этаж, так как номер квартиры слишком большой");
        System.out.println("Зарезервируем номер квартиры 3, который можно вставить на этаже");
        floor1.addFlatOnFloor(3, new Flat(13, 2));
        System.out.println("Массив квартир на этаже floor1: " + Arrays.toString(floor1.getFlatArr()));
        System.out.println("Мы добавили сразу 2 квартиры из резерва, так как их номера подходят для этажа\n");

        System.out.println("Максимальная площадь квартиры на этаже: " + floor1.getBestSpace() + "\n");

        System.out.println("Удалим 3 и 0 квартиры с этажа");
        floor1.delFlatOnFloor(3);
        floor1.delFlatOnFloor(0);
        System.out.println("Массив квартир на этаже floor1: " + Arrays.toString(floor1.getFlatArr()));
        System.out.println("Количество комнат на этаже: " + floor1.getFlatCount() + "\n\n");


        System.out.println("Создаём массив с количеством квартир по этажам");
        int[] countFlatsArr = new int[] {2, 3, 4};
        System.out.println(Arrays.toString(countFlatsArr));
        System.out.println("Создаём по этому массиву дом 1");
        Dwelling dwell1 = new Dwelling(4, countFlatsArr);
        for (DwellingFloor floor : dwell1.getFloorArr()){
            System.out.println(Arrays.toString(floor.getFlatArr()));
        }
        System.out.println("Как мы видим, объект Dwelling создался успешно, несмотря на то, что количество введённых этажей больше, ");
        System.out.println("чем количество этажей в введённом массиве. Лишние этажи успешно созданы выше без квартир внутри\n");


        System.out.println("Создаём дом 2 c помощью другого конструктора, принимающий массив этажей, содержащих массивы квартир");

        floor1 = new DwellingFloor(3);
        DwellingFloor floor2 = new DwellingFloor(2);
        DwellingFloor floor3 = new DwellingFloor(0);

        DwellingFloor[] floorArr1 = new DwellingFloor[]{floor1, floor2, floor3};
        Dwelling dwell2 = new Dwelling(floorArr1);

        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }
        System.out.println("Дом успешно создался!\n");

        System.out.println("Количество этажей в первом доме: " + dwell1.getFloorCount());
        System.out.println("Количество этажей во втором доме: " + dwell2.getFloorCount() + "\n");

        System.out.println("Количество квартир в первом доме: " + dwell1.flatCountDwell());
        System.out.println("Количество квартир во втором доме: " + dwell2.flatCountDwell() + "\n");

        System.out.println("Общая площадь квартир в первом доме: " + dwell1.dwellSquareSum());
        System.out.println("Общая площадь квартир во втором доме: " + dwell2.dwellSquareSum() + "\n");

        System.out.println("Общее количество комнат в первом доме: " + dwell1.dwellRoomSum());
        System.out.println("Общее количество комнат во втором доме: " + dwell2.dwellRoomSum() + "\n");

        System.out.print ("Этаж номер 1 в первом доме: ");                              // метод floorByNumber возвращает массив объектов Flat,
        StringBuilder strArr = new StringBuilder("[");                                  // поэтому для визуализации приходится выводить элементы
        DwellingFloor floorDwell1 = dwell1.floorByNumber(1);                   // этого массива "изощрённо"
        for (int i = 0; i < floorDwell1.getFlatCount(); i++){
            strArr.append(floorDwell1.getFlatByNumber(i)).append(", ");
        }
        strArr.append("]");
        System.out.println(strArr);

        System.out.print("Этаж номер 1 во втором доме: ");
        strArr = new StringBuilder("[");
        DwellingFloor floorDwell2 = dwell2.floorByNumber(1);
        for (int i = 0; i < floorDwell2.getFlatCount(); i++){
            strArr.append(floorDwell2.getFlatByNumber(i)).append(", ");
        }
        strArr.append("]");
        System.out.println(strArr + "\n");


        System.out.println("Заменим во втором доме этаж номер 2 на другой");
        DwellingFloor floor4 = new DwellingFloor(1);
        dwell2.setFloorByNum(2, floor4);

        System.out.println("Теперь дом 2 выглядит так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }
        System.out.println("Мы успешно изменили 2 этаж второго дома!\n");


        System.out.println("Для проверки метода получения квартиры по её номеру в ");
        System.out.println("доме изменим 1 этаж во 2 доме, чтобы были разные значения");
        flatArr1 = new Flat[]{new Flat(200, 5), new Flat(250, 6)};
        floor1 = new DwellingFloor(flatArr1);
        dwell2.setFloorByNum(1, floor1);
        System.out.println("Теперь дом 2 выглядит вот так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }

        System.out.println("\nПолучим квартиру по 3 номеру в доме: " + dwell2.getFlatInDwell(3));
        System.out.println("Вы великолепны!\n\n");


        System.out.println("Заменим 5 квартиру во втором доме на квартиру ");
        System.out.println("с 10 комнатами и 40 квадратными метрами");
        dwell2.setFlatInDwell(5, new Flat(40, 10));
        System.out.println("Теперь дом 2 выглядит вот так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }
        System.out.println("Поздравляю! Вы сделали из квартиры \"комуналку\"!\n\n");


        System.out.println("Добавим в дом 2 квартиру с номером 7");
        dwell2.addFlatInDwell(7, new Flat(10, 1));
        System.out.println("Теперь дом 2 выглядит вот так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }
        System.out.println("Квартира не добавилась в дом, так как номер квартиры слишком большой");

        System.out.println("\nДобавим в дом 2 квартиру с номером 6");
        dwell2.addFlatInDwell(6, new Flat(23, 3));
        System.out.println("Теперь дом 2 выглядит вот так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }
        System.out.println("Мы добавили сразу 2 квартиры из резерва, так как их номера подходят для добавления на последний этаж\n\n");


        System.out.println("Попробуем сломать программу и вставить на одно и то же место квартиру из резерва для дома ");
        System.out.println("и квартиру из резерва для последнего этажа.");
        System.out.println("Используем методы addFlatInDwell для дома и addFlatOnFloor для последнего этажа этого дома.\n");

        System.out.println("Добавим на 2 этаж квартиру под номером 4");
        System.out.println("Добавим во 2 дом квартиру под номером 9");
        dwell2.floorByNumber(2).addFlatOnFloor(4, new Flat(1, 1));
        dwell2.addFlatInDwell(9, new Flat(2, 2));

        System.out.println("Дом 2 всё ещё выглядит вот так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }
        System.out.println();

        System.out.println("""
                Добавим в дом 2 квартиру с номером 8.

                Эта квартира добавится успешно, но после добавления\s
                произойдёт коллизия, так как квартира из резерва на этаже и квартира из резерва в доме\s
                захотят вставиться в одно и то же место на последнем этаже.\s
                Для разрешения ситуации программа настроена так, что в первую очередь вставляются квартиры из\s
                резерва на этаже, а когда закончатся подходящие квартиры на этаже, программа пытается вставить\s
                квартиры из резерва для дома.\s
                Если номер квартиры из резерва дома уже создан, то квартира с этим номером удаляется из резерва дома, и программа\s
                пытается найти следующие подходящие квартиры в резерве дома.
                """);
        dwell2.addFlatInDwell(8, new Flat(3, 3));
        System.out.println("\nТеперь дом 2 выглядит вот так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }


        System.out.println("\n\nТеперь удалим из дома 2 квартиру под номером 8");
        dwell2.delFlatInDwel(8);
        System.out.println("\nТеперь дом 2 выглядит вот так:");
        for (DwellingFloor tempFloor : dwell2.getFloorArr()){
            System.out.println(Arrays.toString(tempFloor.getFlatArr()));
        }


        System.out.println("\n\nМаксимальная площадь квартиры в доме: " + dwell2.getBestSpace() + "\n");

        System.out.println("Массив квартир дома 2, отсортированных по убыванию площадей:");
        System.out.println(Arrays.toString(dwell2.sortedBySquareFlats()));
    }
}