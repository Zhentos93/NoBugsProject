package debug_example;

import java.util.ArrayList; // ❌ BP не нужен: import не выполняется во время работы программы
import java.util.List;      // ❌ BP не нужен: import не выполняется

public class DebugPractice {

    public static void main(String[] args) {
        // ❌ BP обычно не нужен: просто создаём тестовые данные, без сложной логики
        List<String> names = List.of("Anna", "Bob", "", "Alex", "Tom");

        // ❌ BP обычно не нужен: простое создание объекта
        UserReportService service = new UserReportService();

        // 🔴 BP-1: перед вызовом важного метода
        // Зачем: проверить, что именно передаём в createReport()
        // Здесь удобно нажать Step Into и зайти внутрь метода
        String report = service.createReport(names);

        // ❌ BP обычно не нужен: просто вывод результата
        // Можно поставить только если проверяешь, дошла ли программа до конца
        System.out.println(report);
    }
}

class UserReportService {

    // 🔴 BP-2: начало подозреваемого метода
    // Зачем: проверить, вызвался ли метод и какие входные данные пришли в names
    public String createReport(List<String> names) {

        // 🔴 BP-3: перед if
        // Зачем: понять, почему код пойдёт или не пойдёт в ветку ошибки
        if (names == null) {
            // ❌ отдельный BP тут обычно не нужен:
            // если BP стоит на if, ты уже увидишь, что условие true
            throw new IllegalArgumentException("Список имён не должен быть null");
        }

        // ❌ BP обычно не нужен: просто создаём пустой список
        List<String> validNames = new ArrayList<>();

        // 🔴 BP-4: начало цикла
        // Зачем: смотреть значение name на каждой итерации
        // Здесь видно, какой элемент сейчас обрабатывается
        for (String name : names) {

            // 🔴 BP-5: перед фильтрацией
            // Зачем: понять, почему элемент пропускается
            // Особенно полезно для "", null, "   "
            if (name == null || name.isBlank()) {
                // ❌ BP на continue обычно не нужен:
                // если условие true, ты и так поймёшь, что сейчас будет пропуск
                continue;
            }

            // 🔴 BP-6: перед вызовом другого метода
            // Зачем: проверить, какое значение уйдёт в normalizeName()
            // Здесь можно выбрать Step Into, если подозреваешь проблему внутри normalizeName()
            String normalizedName = normalizeName(name);

            // ❌ BP тут можно поставить, но не обязательно:
            // значение normalizedName видно сразу после выполнения предыдущей строки
            validNames.add(normalizedName);
        }

        // 🔴 BP-7: перед if после цикла
        // Зачем: проверить, что получилось после фильтрации
        // Здесь важно посмотреть validNames
        if (validNames.isEmpty()) {
            // ❌ BP на return внутри ветки обычно не нужен:
            // если BP стоит на if и условие true, причина уже понятна
            return "Нет подходящих имён";
        }

        // 🔴 BP-8: перед вызовом метода сборки отчёта
        // Зачем: проверить, какие данные передаются в buildReport()
        String result = buildReport(validNames);

        // 🔴 BP-9: перед return
        // Зачем: увидеть финальный результат метода createReport()
        return result;
    }

    // 🔴 BP-10: начало вспомогательного метода
    // Зачем: проверить, что реально пришло в name
    private String normalizeName(String name) {

        // 🔴 BP-11: строка изменения данных
        // Зачем: проверить результат trim()
        // Особенно полезно, если строка была с пробелами
        String trimmed = name.trim();

        // ❌ BP можно не ставить:
        // return простой, а результат trimmed.toUpperCase() можно посмотреть через Evaluate Expression
        return trimmed.toUpperCase();
    }

    // 🔴 BP-12: начало метода, который формирует итоговый текст
    // Зачем: проверить, какой список дошёл до отчёта
    private String buildReport(List<String> names) {

        // ❌ BP обычно не нужен: просто создаётся StringBuilder
        StringBuilder builder = new StringBuilder();

        // ❌ BP на try обычно не нужен:
        // try сам по себе ничего не делает, он только оборачивает потенциально опасный код
        try {

            // ❌ BP обычно не нужен:
            // append просто добавляет текст; важнее проверить builder позже
            builder.append("Всего пользователей: ")
                    .append(names.size())
                    .append("\n");

            // 🔴 BP-13: цикл с индексом
            // Зачем: проверить i и names.get(i)
            // Полезно при IndexOutOfBoundsException или неверном порядке элементов
            for (int i = 0; i < names.size(); i++) {

                // ❌ BP обычно не нужен:
                // это просто цепочка append, а i и names.get(i) уже видны на BP-13
                builder.append(i + 1)
                        .append(". ")
                        .append(names.get(i))
                        .append("\n");
            }

            // 🔴 BP-14: catch
            // Зачем: если сюда попали, нужно смотреть e: тип исключения, message, stack trace
        } catch (Exception e) {
            // ❌ BP на return внутри catch обычно не нужен:
            // главное уже поймано на строке catch
            return "Ошибка при создании отчёта";
        }

        // 🔴 BP-15: финальный return метода buildReport()
        // Зачем: увидеть готовый текст отчёта перед возвратом
        return builder.toString();
    }
}

/*
Короткое правило по этому примеру:

Ставим BP там, где:
        - метод начинается
        - данные уходят в другой метод
        - есть if
        - начинается цикл
        - меняется значение
        - формируется return
        - есть catch

Не ставим BP там, где:
        - import/package
        - простое создание объекта
        - простой println
        - continue/return внутри очевидной ветки
        - try без логики
        - цепочки append без подозрения на ошибку

Главное: breakpoint не должен быть “меткой на каждой строке”. Он должен отвечать на конкретный вопрос: что сейчас
        пришло, куда пошло, почему выбрана эта ветка, каким стало значение.
*/
