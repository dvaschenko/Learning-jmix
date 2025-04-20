package com.company.demo.view.keyvaluestats;


import com.company.demo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "key-value-stats-view", layout = MainView.class)
@ViewController(id = "KeyValueStatsView")
@ViewDescriptor(path = "key-value-stats-view.xml")
public class KeyValueStatsView extends StandardView {
}