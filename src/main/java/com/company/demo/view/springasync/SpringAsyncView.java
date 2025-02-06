package com.company.demo.view.springasync;


import com.company.demo.app.AsyncService;
import com.company.demo.entity.NewEntity;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Route(value = "spring-async-view", layout = DefaultMainViewParent.class)
@ViewController(id = "SpringAsyncView")
@ViewDescriptor(path = "spring-async-view.xml")
public class SpringAsyncView extends StandardView {
    private static final Logger log = LoggerFactory.getLogger(SpringAsyncView.class);

    @ViewComponent
    private TypedTextField<Object> firstField;
    @ViewComponent
    private TypedTextField<Object> secondField;
    @ViewComponent
    private TypedTextField<Object> thirdField;
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private DialogWindows dialogWindows;
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "callAsyncBean", subject = "clickListener")
    public void onCallAsyncBeanClick(final ClickEvent<JmixButton> event) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        CompletableFuture<String> resultOne = asyncService.getResult(firstField.getValue());
        CompletableFuture<String> resultTwo = asyncService.getResult(secondField.getValue());
        CompletableFuture<String> resultThree = asyncService.getResult(thirdField.getValue());

        CompletableFuture.allOf(resultOne, resultTwo, resultThree).join();

        log.info("Total time spent: {}", System.currentTimeMillis() - start);
        log.info("Results are: {} + {} + {}", resultOne.get(), resultTwo.get(), resultThree.get());
    }

}