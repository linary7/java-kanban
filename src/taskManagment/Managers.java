package taskManagment;

import java.io.File;

public final class Managers {

    private Managers(){

    }
    public static TaskManager getDefault(){
        return new FileBackedTasksManager(new File("file.txt"));
    }

    public static HistoryManager getDefaultHistoryManager(){
        return new InMemoryHistoryManager();
    }
}
