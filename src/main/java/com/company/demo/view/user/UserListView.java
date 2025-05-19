package com.company.demo.view.user;

import com.company.demo.entity.User;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.core.Messages;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "users", layout = MainView.class)
@ViewController(id = "User.list")
@ViewDescriptor(path = "user-list-view.xml")
//@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
    @ViewComponent
    private DataGrid<User> usersDataGrid;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private CollectionLoader<User> usersDl;
    private int page = 0;
    @ViewComponent
    private CollectionContainer<User> usersDc;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onReady(final ReadyEvent event) {
//        HeaderRow.HeaderCell headerCell = usersDataGrid.getDefaultHeaderRow().getCell();

    }

    @ViewComponent
    private MessageBundle messageBundle;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {

    }

    @Install(to = "usersDl", target = Target.DATA_LOADER)
    private List<User> usersDlLoadDelegate(final LoadContext<User> loadContext) {
        return dataManager.load(User.class).all().maxResults(10).list();
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        getElement().executeJs("""
                let container = this;
                let el = this.querySelector("#usersDataGrid");
                let table = el.$.table;
                table.addEventListener("scroll", function(e) {
                    console.log(e);
                    if(table.scrollTop + table.clientHeight == table.scrollHeight) {
                        container.$server.loadMoreRows();
                        }
                    });
                """);
    }

    @ClientCallable
    public void loadMoreRows() {
        page++;
        List<User> moreUsers = dataManager.load(User.class).all().firstResult(page * 10).maxResults(10).list();
        List<User> newUsers = new ArrayList<>();
        newUsers.addAll(usersDc.getItems());
        newUsers.addAll(moreUsers);
        usersDc.setItems(newUsers);
        notifications.show("Load called!");
    }


    @Subscribe(id = "genUsers", subject = "clickListener")
    public void onGenUsersClick(final ClickEvent<JmixButton> event) {

        for (int i = 0; i < 100; i++) {
            User newUser = dataManager.create(User.class);
            newUser.setUsername(RandomStringUtils.randomAlphabetic(10));
            newUser.setPassword("{noop}admin");
            dataManager.save(newUser);
        }

        usersDl.load();
    }

    @Subscribe(id = "usersDc", target = Target.DATA_CONTAINER)
    public void onUsersDcCollectionChange(final CollectionContainer.CollectionChangeEvent<User> event) {
        notifications.show(event.getChangeType().name());
        notifications.show("Collection size: " + usersDc.getItems().size());
    }


}