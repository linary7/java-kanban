package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;
import taskStorage.TaskStatus;

public class Main {
    /* public static void main(String[] args) {

        TaskManager inMemoryTaskManager = Managers.getDefault();

        // Создан новый эпик и его 2 сабтаска
        EpicTask epicTask = new EpicTask("Приготовить праздничный стол", "Не забудь: у Леши аллергия на " +
                "орехи");
        inMemoryTaskManager.createEpicTask(epicTask);

        Subtask subtask = new Subtask(epicTask.getId(), "Приготовить грузинский салат", "Орехи убрать, " +
                "добавить больше граната");
        subtask.setEpicId(epicTask.getId());
        inMemoryTaskManager.addSubtask(subtask);

        Subtask subtask1 = new Subtask(epicTask.getId(), "Испечь торт", "Три слоя, без крема");
        subtask1.setEpicId(epicTask.getId());
        inMemoryTaskManager.addSubtask(subtask1);



        // Создан ещё один эпик и его 1 сабтаск
        EpicTask epicTask2 = new EpicTask("Украсить комнату", "Нужно будет убрать лишние кресла из гостиной");
        inMemoryTaskManager.createEpicTask(epicTask2);
        Subtask subtask3 = new Subtask(epicTask2.getId(), "Купить свечи", "Нужны будут два вида: для торта " +
                "и для освещения");
        subtask3.setEpicId(epicTask2.getId());
        inMemoryTaskManager.addSubtask(subtask3);

        System.out.println(inMemoryTaskManager.getEpicTaskById(epicTask2.getId()));
        System.out.println("Просмотренные задачи:" + inMemoryTaskManager.getHistory());



        // Создан новый таск и передан на хранение
        Task task = new Task("Позвать Лешу на праздник", "Не забудь сказать, что приготовишь для него " +
                "грузинский салат без орехов");
        inMemoryTaskManager.createTask(task);

        // Создан ещё один таск и передан на хранение
        Task task1 = new Task("Позвать на праздник Алину", "Если она занята в этот день, можно перенести " +
                "на выходные");
        inMemoryTaskManager.createTask(task1);
        // Распечатан список имеющихся задач
        System.out.println(inMemoryTaskManager.getAllTasks());

        System.out.println(inMemoryTaskManager.getTaskById(task1.getId()));
        System.out.println("Просмотренные задачи:" + inMemoryTaskManager.getHistory());

        // Создан эпик с измененным содержанием
        EpicTask updatedEpicTask = new EpicTask("Приготовить праздничный стол", "Не забудь: у Леши аллергия " +
                "на орехи, на гранат тоже");
        updatedEpicTask.setId(epicTask.getId());

        Subtask updatedSubtask = new Subtask(epicTask.getId(), "Приготовить оливье", "Добавить побольше " +
                "соленых огурцов");
        updatedSubtask.setStatus(TaskStatus.IN_PROGRESS);
        updatedSubtask.setId(subtask.getId());
        updatedSubtask.setEpicId(epicTask.getId());

        Subtask updatedSubtask1 = new Subtask(epicTask.getId(), "Испечь торт", "Четыре слоя, без крема");
        updatedSubtask1.setStatus(TaskStatus.DONE);
        updatedSubtask1.setId(subtask1.getId());
        updatedSubtask1.setEpicId(epicTask.getId());


        // Измененный эпик передан на место старого
        inMemoryTaskManager.updateEpicTask(updatedEpicTask);
        inMemoryTaskManager.updateSubtask(updatedSubtask);
        inMemoryTaskManager.updateSubtask(updatedSubtask1);


        // Изменен таск
        Task updatedTask = new Task("Позвать Лешу на праздник", "Не забудь сказать, что приготовишь для " +
                "него оливье и торт");
        updatedTask.setId(task.getId());
        updatedTask.setStatus(TaskStatus.DONE);
        inMemoryTaskManager.updateTask(updatedTask);
        System.out.println(inMemoryTaskManager.getAllTasks());

        System.out.println("Просмотренные задачи:" + inMemoryTaskManager.getHistory());

        // Удалены один таск и один эпик
        inMemoryTaskManager.deleteTaskById(task1.getId());
        inMemoryTaskManager.deleteEpicTaskById(epicTask2.getId());
        // Удалены все таски и эпики
        inMemoryTaskManager.removeAllTasks();
    } */
}
