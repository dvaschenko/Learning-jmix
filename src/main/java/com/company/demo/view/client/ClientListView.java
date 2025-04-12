package com.company.demo.view.client;

import com.company.demo.entity.Client;
import com.company.demo.view.main.MainView;
import com.google.common.base.Strings;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.AnchorTarget;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.AccessManager;
import io.jmix.core.MessageTools;
import io.jmix.core.Messages;
import io.jmix.core.accesscontext.InMemoryCrudEntityContext;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.accesscontext.UiEntityContext;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.util.RemoveOperation;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Route(value = "clients", layout = MainView.class)
@ViewController(id = "Client.list")
@ViewDescriptor(path = "client-list-view.xml")
@DialogMode(width = "64em")
public class ClientListView extends StandardListView<Client> {

//    @ViewComponent
//    private JmixButton createBtn;
//
//    @ViewComponent
//    private CollectionContainer<Client> clientsDc;
//
//    @Autowired
//    private AccessManager accessManager;
//    @Autowired
//    private ViewNavigators viewNavigators;
//    @Autowired
//    private Messages messages;
//    @Autowired
//    private MessageTools messageTools;
//    @Autowired
//    private UiComponents uiComponents;
//    @Autowired
//    private RemoveOperation removeOperation;
//
//    private InMemoryCrudEntityContext crudEntityContext;
//    private UiEntityContext entityContext;
//
//    @Subscribe
//    public void onInit(final InitEvent event) {
//        // Apply security permissions
//        createBtn.setEnabled(getEntityContext().isCreatePermitted());
//    }
//
//    @Subscribe(id = "createBtn", subject = "clickListener")
//    public void onCreateBtnClick(final ClickEvent<JmixButton> event) {
//        viewNavigators.detailView(this, Client.class)
//                .newEntity()
//                .withBackwardNavigation(true)
//                .navigate();
//    }
//
//    private UiEntityContext getEntityContext() {
//        if (entityContext == null) {
//            MetaClass metaClass = clientsDc.getEntityMetaClass();
//            entityContext = new UiEntityContext(metaClass);
//            accessManager.applyRegisteredConstraints(entityContext);
//        }
//
//        return entityContext;
//    }
//
//    @Supply(to = "clientsList", subject = "renderer")
//    private Renderer<Client> clientsListRenderer() {
//        return new ComponentRenderer<>(client -> {
//            HorizontalLayout layout = uiComponents.create(HorizontalLayout.class);
//            layout.setPadding(true);
//            layout.addClassNames("rounded-m", "m-s", "shadow-s");
//
//            layout.add(
//                    createAvatar(client.getName(), client.getImage(), "var(--lumo-size-xl)"),
//                    createInfoLayout(client),
//                    createActionsLayout(client)
//            );
//
//            return layout;
//        });
//    }
//
//    private Avatar createAvatar(String name, @Nullable byte[] data, String size) {
//        Avatar avatar = uiComponents.create(Avatar.class);
//        avatar.setName(name);
//
//        if (data != null) {
//            StreamResource imageResource = new StreamResource("avatar.png",
//                    () -> new ByteArrayInputStream(data));
//            avatar.setImageResource(imageResource);
//        }
//
//        avatar.setWidth(size);
//        avatar.setHeight(size);
//
//        return avatar;
//    }
//
//    private Component createInfoLayout(Client client) {
//        VerticalLayout infoBox = uiComponents.create(VerticalLayout.class);
//        infoBox.setPadding(false);
//
//        infoBox.getElement().appendChild(
//                ElementFactory.createStrong(client.getName())
//        );
//
//        if (client.getContactInformation() != null) {
//            infoBox.add(createContactInformationLayout(client));
//        }
//
//        return infoBox;
//    }
//
//    private Component createContactInformationLayout(Client client) {
//        VerticalLayout contactInformationLayout = uiComponents.create(VerticalLayout.class);
////        contactInformationLayout.setClassName("contact-information-layout");
//
//        createContactInformationItem(
//                client.getContactInformation().getEmail(),
//                VaadinIcon.ENVELOPE.create(),
//                Text::new
//        ).ifPresent(contactInformationLayout::add);
//
//        createContactInformationItem(
//                client.getContactInformation().getPhone(),
//                VaadinIcon.PHONE.create(),
//                Text::new
//        ).ifPresent(contactInformationLayout::add);
//
//        createContactInformationItem(
//                client.getContactInformation().getAddress(),
//                VaadinIcon.MAP_MARKER.create(),
//                Text::new
//        ).ifPresent(contactInformationLayout::add);
//
//        createContactInformationItem(
//                client.getContactInformation().getUrl(),
//                VaadinIcon.LINK.create(),
//                text -> new Anchor(text, text, AnchorTarget.BLANK)
//        ).ifPresent(contactInformationLayout::add);
//
//        Details contactDetails = uiComponents.create(Details.class);
//        contactDetails.setOpened(true);
//        contactDetails.setSummaryText(
//                messageTools.getPropertyCaption(clientsDc.getEntityMetaClass(), "contactInformation"));
//        contactDetails.add(contactInformationLayout);
//
//        return contactDetails;
//    }
//
//    private Optional<Component> createContactInformationItem(@Nullable String value, Icon icon,
//                                                             Function<String, Component> valueRenderer) {
//        return Strings.isNullOrEmpty(value) ?
//                Optional.empty() :
//                Optional.of(new HorizontalLayout(icon, valueRenderer.apply(value)));
//    }
//
//    private Component createActionsLayout(Client client) {
//        VerticalLayout actionsBox = uiComponents.create(VerticalLayout.class);
//        actionsBox.setPadding(false);
//        actionsBox.setWidth("auto");
//
//        Button editBtn = createEditButton(client);
//        Button removeBtn = createRemoveButton(client);
//        actionsBox.add(editBtn, removeBtn);
//        return actionsBox;
//    }
//
//    private Button createEditButton(Client client) {
//        boolean editPermitted = editPermitted(client);
//
//        Button button = uiComponents.create(Button.class);
//        button.setTooltipText(messages.getMessage(editPermitted ? "actions.Edit" : "actions.Read"));
//        button.setIcon(editPermitted ? VaadinIcon.PENCIL.create() : VaadinIcon.EYE.create());
//        button.addClickListener(event -> editClient(client));
//        return button;
//    }
//
//    private Button createRemoveButton(Client client) {
//        Button button = uiComponents.create(Button.class);
//        button.setTooltipText(messages.getMessage("actions.Remove"));
//        button.setIcon(VaadinIcon.TRASH.create());
//        button.addThemeVariants(ButtonVariant.LUMO_ERROR);
//        button.addClickListener(event -> removeClient(client));
//
//        button.setVisible(deletePermitted(client));
//
//        return button;
//    }
//
//    private boolean editPermitted(Client client) {
//        return getEntityContext().isEditPermitted()
//                && (getCrudEntityContext().updatePredicate() == null
//                || getCrudEntityContext().isUpdatePermitted(client));
//    }
//
//    private boolean deletePermitted(Client client) {
//        return getEntityContext().isDeletePermitted()
//                && (getCrudEntityContext().updatePredicate() == null
//                || getCrudEntityContext().isDeletePermitted(client));
//    }
//
//    private void editClient(Client client) {
//        viewNavigators.detailView(this, Client.class)
//                .editEntity(client)
//                .withBackwardNavigation(true)
//                .navigate();
//    }
//
//    private void removeClient(Client client) {
//        removeOperation.builder(Client.class, this)
//                .withConfirmation(true)
//                .withItems(List.of(client))
//                .withContainer(clientsDc)
//                .remove();
//    }
//
//    private InMemoryCrudEntityContext getCrudEntityContext() {
//        if (crudEntityContext == null) {
//            crudEntityContext = new InMemoryCrudEntityContext(clientsDc.getEntityMetaClass(), getApplicationContext());
//            accessManager.applyRegisteredConstraints(crudEntityContext);
//        }
//
//        return crudEntityContext;
//    }

}