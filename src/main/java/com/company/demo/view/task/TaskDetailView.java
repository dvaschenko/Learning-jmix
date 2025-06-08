package com.company.demo.view.task;

import com.company.demo.entity.Task;
import com.company.demo.entity.TaskStatus;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.view.*;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController(id = "Task_.detail")
@ViewDescriptor(path = "task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {

    @ViewComponent
    private JmixSelect<TaskStatus> statusField;

    @Subscribe
    public void onReady(final ReadyEvent event) {
        updateStatusFieldIcon();
    }

    @Subscribe("statusField")
    public void onStatusFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixSelect<TaskStatus>, TaskStatus> event) {
        updateStatusFieldIcon();
    }

    private void updateStatusFieldIcon() {
        TaskStatus status = statusField.getValue();
        Icon icon = status == null ? null : switch (status) {
            case REQUEST -> {
                Icon newIcon = VaadinIcon.PLUS_CIRCLE_O.create();
                newIcon.setColor("var(--lumo-success-color)");
                yield newIcon;
            }
            case CANCELED -> {
                Icon approvedIcon = VaadinIcon.EXCLAMATION_CIRCLE_O.create();
                approvedIcon.setColor("var(--lumo-error-color)");
                yield approvedIcon;
            }
            case DONE -> {
                Icon rejectedIcon = VaadinIcon.CHECK_CIRCLE_O.create();
                rejectedIcon.setColor("var(--lumo-error-color)");
                yield rejectedIcon;
            }
            case TO_DO, IN_PROGRESS, ON_CHECK -> VaadinIcon.CLOSE_CIRCLE_O.create();
        };

        statusField.setPrefixComponent(icon);
    }
}