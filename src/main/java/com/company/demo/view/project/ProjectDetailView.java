package com.company.demo.view.project;

import com.company.demo.entity.Project;
import com.company.demo.entity.Task;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.core.FileStorageLocator;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController(id = "Project.detail")
@ViewDescriptor(path = "project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {

    @ViewComponent
    private DataContext dataContext;
    @Autowired
    private FileStorageLocator locator;
    @Autowired
    private FileStorageLocator fileStorageLocator;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private UiComponents uiComponents;

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

    @Subscribe
    public void onBeforeClose(final BeforeCloseEvent event) {
        if (event.closedWith(StandardOutcome.DISCARD)) {
            FileStorage myFS = fileStorageLocator.getByName("myfs");
            if (myFS.fileExists(getEditedEntity().getAttachment())) {
                myFS.removeFile(getEditedEntity().getAttachment());
            }
        }
    }

    @Subscribe(id = "fileImage", subject = "doubleClickListener")
    public void onFileImageClick(final ClickEvent<JmixImage<?>> event) {
        FileRef attachment = getEditedEntity().getAttachment();
        if (attachment != null) {
            Image image = uiComponents.create(Image.class);
            image.setSrc(new StreamResource("image", () -> {
                try {
                    return new FileInputStream("/var/tmp/myfs/" +attachment.getPath());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }) {
            });



            Dialog built = dialogs.createMessageDialog().withContent(image).build();
            built.setHeightFull();
            built.setWidthFull();
            built.open();

        }
    }
    
        
}