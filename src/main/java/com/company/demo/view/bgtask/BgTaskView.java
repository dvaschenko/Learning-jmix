package com.company.demo.view.bgtask;


import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.DefaultMainViewParent;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "bg-task-view", layout = DefaultMainViewParent.class)
@ViewController(id = "BgTaskView")
@ViewDescriptor(path = "bg-task-view.xml")
public class BgTaskView extends StandardView {
}