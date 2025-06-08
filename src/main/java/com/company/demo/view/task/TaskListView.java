package com.company.demo.view.task;

import com.company.demo.entity.Task;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.DataGridColumn;
import io.jmix.flowui.view.*;


@Route(value = "tasks", layout = MainView.class)
@ViewController(id = "Task_.list")
@ViewDescriptor(path = "task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {
    @ViewComponent
    private DataGrid<Task> tasksDataGrid;

    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        QueryParameters queryParameters = event.getQueryParameters();
        queryParameters.getParameters().get("project_id");

    }

    @Install(to = "tasksDataGrid.project", subject = "partNameGenerator")
    private String tasksDataGridProjectPartNameGenerator(final Task task) {
        return "styled-cell";
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        DataGridColumn<Task> columnByKey = tasksDataGrid.getColumnByKey("project");
        Component editorComponent = columnByKey.getEditorComponent();
    }

    @Supply(to = "tasksDataGrid.description", subject = "renderer")
    private Renderer<Task> tasksDataGridDescriptionRenderer() {
        return new ComponentRenderer<>(TextArea::new, (area, task) -> {
            area.setValue(task.getDescription() == null ? "" : task.getDescription());
            area.getStyle().set("style", "text-wrap: wrap");
            area.setEnabled(false);
            area.setMaxHeight("12em");
        });
    }

    

    
}