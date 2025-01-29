import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSizeTaskList() {
        return taskList.size();
    }

    public Task getTask(int ndx) {
        return taskList.get(ndx);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int ndx) {
        taskList.remove(ndx);
    }

}
