package com.company.demo.view.project;

import com.company.demo.entity.Project;
import com.company.demo.view.main.MainView;
import com.company.demo.view.projecttasks.ProjectTasksView;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "projects", layout = MainView.class)
@ViewController(id = "Project.list")
@ViewDescriptor(path = "project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectListView extends StandardListView<Project> {
    @ViewComponent
    private DataGrid<Project> projectsDataGrid;
    @Autowired
    private DialogWindows dialogWindows;
    @Autowired
    private DataManager dataManager;

    @Subscribe("projectsDataGrid.showTasks")
    public void onProjectsDataGridShowTasks(final ActionPerformedEvent event) {
        Project selected = projectsDataGrid.getSingleSelectedItem();
        if (selected == null) {
            return;
        }

        DialogWindow<ProjectTasksView> window = dialogWindows.view(this, ProjectTasksView.class)
                .build();
        window.setResizable(true);
        window.setDraggable(false);

        window.getView().setProject(selected);
        window.open();

    }

    @Supply(to = "projectsDataGrid.isRunning", subject = "renderer")
    private Renderer<Project> projectsDataGridIsRunningRenderer() {
        return new ComponentRenderer<>(Checkbox::new, (checkBox, project) ->{
            checkBox.addClickListener(click -> {
                if (checkBox.getValue()) {
                    project.setIsRunning(true);
                } else {
                    project.setIsRunning(false);
                }
                dataManager.save(project);
            });
            checkBox.setValue(project.getIsRunning());
        });
    }
}