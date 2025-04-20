package com.company.demo.view.project;

import com.company.demo.entity.Project;
import com.company.demo.entity.Task;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.core.FileStorageLocator;
import io.jmix.flowui.kit.component.button.JmixButton;
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
    @Autowired
    private FileStorageLocator locator;

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

    @Subscribe(id = "clearFile", subject = "clickListener")
    public void onClearFileClick(final ClickEvent<JmixButton> event) {
        FileRef attached = getEditedEntity().getAttachment();
        if (attached == null) {
            return;
        }
        FileStorage currentStorage = locator.getDefault();
        if (currentStorage.fileExists(attached)) {
            currentStorage.removeFile(attached);
            getEditedEntity().setAttachment(null);
        }
    }
}