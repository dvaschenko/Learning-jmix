package com.company.demo.view.clientinfofragment;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import org.springframework.lang.Nullable;

import java.io.ByteArrayInputStream;

@FragmentDescriptor("client-info-fragment.xml")
public class ClientInfoFragment extends Fragment<VerticalLayout> {

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostReady(final View.ReadyEvent event) {
    }


    private Avatar createAvatar(String name, @Nullable byte[] data, String size) {
        Avatar avatar = uiComponents.create(Avatar.class);
        avatar.setName(name);

        if (data != null) {
            StreamResource imageResource = new StreamResource("avatar.png",
                    () -> new ByteArrayInputStream(data));
            avatar.setImageResource(imageResource);
        }

        avatar.setWidth(size);
        avatar.setHeight(size);

        return avatar;
    }


}