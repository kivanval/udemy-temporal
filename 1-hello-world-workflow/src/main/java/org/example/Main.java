package org.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class Main {

    public static void main(String[] args) {
        // Workflow client set up
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Worker definition
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("hello-world-queue");
        worker.registerWorkflowImplementationTypes(HelloWorldWorkflowImpl.class);

        // Start worker
        factory.start();

        // Workflow definition
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("hello-world-queue")
                .build();
        HelloWorldWorkflow workflow = client.newWorkflowStub(HelloWorldWorkflow.class, options);

        // Executing workflow
        String name = "John";
        System.out.printf("Calling workflow for %s%n", name);
        String helloWorld = workflow.sayHelloWorld(name);
        System.out.printf("Workflow returned: %s%n", helloWorld);

        System.exit(0);
    }
}
