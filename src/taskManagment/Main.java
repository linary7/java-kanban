package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();

        // Создан новый эпик и его 2 сабтаска
        EpicTask epicTask = new EpicTask("Приготовить праздничный стол", "Не забудь: у Леши аллергия на " +
                "орехи");

        Subtask subtask = new Subtask(epicTask.getId(), "Приготовить грузинский салат", "Орехи убрать, " +
                "добавить больше граната");
        epicTask.setSubtaskList(subtask);

        Subtask subtask1 = new Subtask(epicTask.getId(), "Испечь торт", "Три слоя, без крема");
        epicTask.setSubtaskList(subtask1);
        // Эпик передан на хранение в менеджер задач
        manager.createEpicTask(epicTask);

        // Создан ещё один эпик и его 1 сабтаск
        EpicTask epicTask2 = new EpicTask("Украсить комнату", "Нужно будет убрать лишние кресла из гостиной");

        Subtask subtask3 = new Subtask(epicTask2.getId(), "Купить свечи", "Нужны будут два вида: для торта " +
                "и для освещения");
        epicTask2.setSubtaskList(subtask3);
        // Второй эпик передан на хранение
        manager.createEpicTask(epicTask2);


        // Создан новый таск и передан на хранение
        Task task = new Task("Позвать Лешу на праздник", "Не забудь сказать, что приготовишь для него " +
                "грузинский салат без орехов");
        manager.createTask(task);

        // Создан ещё один таск и передан на хранение
        Task task1 = new Task("Позвать на праздник Алину", "Если она занята в этот день, можно перенести " +
                "на выходные");
        manager.createTask(task1);
        // Распечатан список имеющихся задач
        System.out.println(manager.getAllTasks());

        // Создан эпик с измененным содержанием
        EpicTask updatedEpicTask = new EpicTask("Приготовить праздничный стол", "Не забудь: у Леши аллергия " +
                "на орехи, на гранат тоже");

        Subtask updatedSubtask = new Subtask(epicTask.getId(), "Приготовить оливье", "Добавить побольше " +
                "соленых огурцов");
        updatedSubtask.setCompleteness("IN_PROGRESS");
        updatedEpicTask.setSubtaskList(updatedSubtask);

        Subtask updatedSubtask1 = new Subtask(epicTask.getId(), "Испечь торт", "Четыре слоя, без крема");
        updatedSubtask1.setCompleteness("DONE");
        updatedEpicTask.setSubtaskList(updatedSubtask1);
        // Измененный эпик передан на место старого
        manager.updateEpicTask(updatedEpicTask, epicTask.getId());

        // Изменен таск
        Task updatedTask = new Task("Позвать Лешу на праздник", "Не забудь сказать, что приготовишь для " +
                "него оливье и торт");
        updatedTask.setCompleteness("DONE");
        manager.updateTask(updatedTask, task.getId());
        // Удалены один таск и один эпик
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicTaskById(epicTask2.getId());
        // Удалены все таски и эпики
        manager.removeAllTasks();
    }
}
