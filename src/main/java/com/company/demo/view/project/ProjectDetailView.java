package com.company.demo.view.project;

import com.company.demo.entity.Project;
import com.company.demo.entity.Task;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController(id = "Project.detail")
@ViewDescriptor(path = "project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {

    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private DataContext dataContext;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Project> event) {
        Task task = dataContext.create(Task.class);
        task.setName("My task");

        Project created = event.getEntity();
        task.setProject(created);

        List<Task> tasks = new ArrayList<>();

        tasks.add(task);

        created.setTasks(tasks);
        dataContext.merge(created);
        dataContext.merge(tasks);
    }
}