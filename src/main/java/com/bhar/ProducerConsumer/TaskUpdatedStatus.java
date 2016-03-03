package com.bhar.ProducerConsumer;

/**
 * Created by bharghav on 3/3/2016.
 */
public class TaskUpdatedStatus extends Status{
    int taskCompleted = 0;

    @Override
    public void update() {
        taskCompleted++;
    }

    @Override
    public void printStatus() {
        System.out.println("Completed "+ taskCompleted +" tasks");
    }
}
