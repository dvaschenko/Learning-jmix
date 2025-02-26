package com.company.demo.view.projecttasks;


import com.company.demo.entity.Project;
import com.company.demo.entity.Task;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.lang.Nullable;

@Route(value = "project-tasks-view", layout = MainView.class)
@ViewController(id = "ProjectTasksView")
@ViewDescriptor(path = "project-tasks-view.xml")
public class ProjectTasksView extends StandardView {

    @ViewComponent
    private CollectionLoader<Task> tasksDl;

    public void setProject(@Nullable Project project) {
        if (project != null) {
            tasksDl.setParameter("projectId", project.getId());
        } else {
            tasksDl.removeParameter("projectId");
        }

        tasksDl.load();
    }

}