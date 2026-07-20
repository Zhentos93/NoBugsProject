public class TypeErasureExample {
    /**
     * Алгоритм стирания (Type Erasure) для Дженериков
     *
     * 1. Нет ограничений:
     *      class Box<T>
     *      T -> Object
     *
     * 2. Верхнее ограничение:
     *      class Box<T extends Number>
     *      T -> Number
     *
     * 3. Параметризованные типы становятся "сырыми":
     *      List<String>        -> List
     *      Map<String,Integer> -> Map
     *      EntityManager<User> -> EntityManager
     *
     * 4. Нижнее ограничение:
     *      List<? super Student> -> List
     *
     *      Здесь T не заменяется на Student.
     *      "? super Student" существует только
     *      на этапе компиляции.
     *
     *      После стирания остаётся обычный List.
     */
}
