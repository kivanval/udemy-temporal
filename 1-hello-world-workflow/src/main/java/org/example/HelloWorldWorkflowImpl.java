package org.example;

import io.temporal.workflow.Workflow;

public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    @Override
    public String sayHelloWorld(String name) {
        Workflow.sleep(5000);
        // Some workflow logic ....
        return "Hello world from %s!".formatted(name);
    }

}
