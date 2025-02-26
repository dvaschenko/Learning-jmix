package com.company.demo.view.task;

import com.company.demo.entity.Task;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "tasks", layout = MainView.class)
@ViewController(id = "Task_.list")
@ViewDescriptor(path = "task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {
    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        QueryParameters queryParameters = event.getQueryParameters();
        queryParameters.getParameters().get("project_id");

    }
    
}