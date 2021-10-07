package model;

public class Project {
    private int id;
    private String name;
    private String workflow;
    private String objective;
    private String time_scheduler;
    private String budget;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getTime_scheduler() {
        return time_scheduler;
    }

    public void setTime_scheduler(String time_scheduler) {
        this.time_scheduler = time_scheduler;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
