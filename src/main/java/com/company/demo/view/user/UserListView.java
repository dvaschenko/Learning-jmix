package com.company.demo.view.user;

import com.company.demo.entity.User;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "users", layout = MainView.class)
@ViewController(id = "User.list")
@ViewDescriptor(path = "user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
    @ViewComponent
    private DataGrid<User> usersDataGrid;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private CollectionLoader<User> usersDl;

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

    @Subscribe(id = "genUsers", subject = "clickListener")
    public void onGenUsersClick(final ClickEvent<JmixButton> event) {

        for (int i = 0; i < 100; i++) {
            User newUser = dataManager.create(User.class);
            newUser.setUsername(RandomStringUtils.randomAlphabetic(10));
            newUser.setPassword("admin");
            dataManager.save(newUser);
        }

        usersDl.load();
    }
    
    
}