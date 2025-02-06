package com.company.demo.view.newentity;

import com.company.demo.entity.NewEntity;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.asynctask.UiAsyncTasks;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Route(value = "newEntities/:id", layout = MainView.class)
@ViewController(id = "NewEntity.detail")
@ViewDescriptor(path = "new-entity-detail-view.xml")
@EditedEntityContainer("newEntityDc")
@DialogMode(height = "20em", width = "30em")
public class NewEntityDetailView extends StandardDetailView<NewEntity> {

    @Autowired
    private UiAsyncTasks uiAsyncTasks;
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "doStuffBtn", subject = "clickListener")
    public void onDoStuffBtnClick(final ClickEvent<JmixButton> event) {
        uiAsyncTasks.runnableConfigurer(this::doSome)
                .withResultHandler(() -> {
                    notifications.show("OK");
                })
                .withTimeout(10, TimeUnit.MINUTES)
                .withExceptionHandler(ex -> {
                    if(ex instanceof TimeoutException){

                    }
                })
                .runAsync();
    }

    private void doSome() {

    }
}