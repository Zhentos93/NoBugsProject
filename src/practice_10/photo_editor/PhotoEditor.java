package practice_10.photo_editor;

/*
Пример 5. Система отмены последней операции при редактировании фото. Возможность добавления нового действия и
отмена последнего действия
*/

import java.util.Stack;

public class PhotoEditor {
    // хранить последние действия
    private Stack<String> actions;

    public PhotoEditor() {
        this.actions = new Stack<>();
    }

    // добавление нового действия
    public void addNewAction(String action) {
        actions.push(action);
    }

    // откатить последнее действие
    public void undoLastAction() {
        actions.pop();
    }

    public void printActions() {
        System.out.println("Все действия:");
        actions.forEach(System.out::println);
    }
}
