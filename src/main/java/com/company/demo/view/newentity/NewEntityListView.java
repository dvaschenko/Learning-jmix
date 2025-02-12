package com.company.demo.view.newentity;

import com.company.demo.entity.NewEntity;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "newEntities", layout = MainView.class)
@ViewController(id = "NewEntity.list")
@ViewDescriptor(path = "new-entity-list-view.xml")
@LookupComponent("newEntitiesDataGrid")
@DialogMode(width = "64em")
public class NewEntityListView extends StandardListView<NewEntity> {

    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private CollectionLoader<NewEntity> newEntitiesDl;
    @ViewComponent
    private DataGrid<NewEntity> newEntitiesDataGrid;
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private CollectionContainer<NewEntity> newEntitiesDc;
    @Autowired
    private MetadataTools metadataTools;
    @Autowired
    private DataManager dataManager;

    @Subscribe(id = "navigateBtn", subject = "clickListener")
    public void onNavigateBtnClick(final ClickEvent<JmixButton> event) {
        viewNavigators.detailView(this, NewEntity.class)
                .newEntity()
                .navigate();
    }

    @Subscribe(id = "dialogBtn", subject = "clickListener")
    public void onDialogBtnClick(final ClickEvent<JmixButton> event) {
        NewEntity selected = newEntitiesDataGrid.getSingleSelectedItem();

        dialogWindows.detail(this, NewEntity.class)
                .newEntity()
                .open();

//        newEntitiesDl.load();
    }

//    @Subscribe("timer")
//    public void onTimerTimerAction(final Timer.TimerActionEvent event) {
//        newEntitiesDl.load();
//        notifications.show("Updated");
//    }

    @Subscribe("newEntitiesDataGrid.copyAction")
    public void onNewEntitiesDataGridCopyAction(final ActionPerformedEvent event) {
        NewEntity toCopy = newEntitiesDataGrid.getSingleSelectedItem();
        if (toCopy == null) {
            return;
        }

        NewEntity copy = metadataTools.copy(toCopy);

        dialogWindows.detail(newEntitiesDataGrid)
                .newEntity(copy)
                .open();
    }


}