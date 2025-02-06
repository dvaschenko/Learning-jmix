package com.company.demo.view.simple;


import com.company.demo.entity.NewEntity;
import com.company.demo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

@Route(value = "simple-view", layout = MainView.class)
@ViewController(id = "SimpleView")
@ViewDescriptor(path = "simple-view.xml")
public class SimpleView extends StandardView {

    @ViewComponent
    private CollectionLoader<NewEntity> newEntitiesDl;

//    @Subscribe
//    public void onBeforeShow(final BeforeShowEvent event) {
//        newEntitiesDl.load();
//    }
    
    
}