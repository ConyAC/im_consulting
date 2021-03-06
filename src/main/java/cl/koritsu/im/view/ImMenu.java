package cl.koritsu.im.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import cl.koritsu.im.ImUI;
import cl.koritsu.im.domain.Usuario;
import cl.koritsu.im.utils.SecurityHelper;


/**
 * A responsive menu component providing user information and the controls for
 * primary navigation between the views.
 */
@SuppressWarnings({ "serial", "unchecked" })
public final class ImMenu extends CustomComponent {

    public static final String ID = "dashboard-menu";
    public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
    private static final String STYLE_VISIBLE = "valo-menu-visible";
    private Label notificationsBadge;
    private Label reportsBadge;
    private Label administrationBadge;
    private Label verTasacionesBadge;
    private Label empresasBadge;
    private Label estudiosBadge;
    private MenuItem settingsItem;

    public ImMenu() {
        setPrimaryStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();

        // There's only one DashboardMenu per UI so this doesn't need to be
        // unregistered from the UI-scoped DashboardEventBus.
//        ValuedEventBus.register(this);

        setCompositionRoot(buildContent());
    }

    private Component buildContent() {
        final CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addStyleName("no-vertical-drag-hints");
        menuContent.addStyleName("no-horizontal-drag-hints");
        menuContent.setWidth(null);
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildUserMenu());
        menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(buildMenuItems());

        return menuContent;
    }

    private Component buildTitle() {
        Label logo = new Label("<strong>STAKEHOLDER</strong><br />MANAGMENT TI",
                ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        return logoWrapper;
    }

    private Usuario getCurrentUser() {
        return (Usuario) VaadinSession.getCurrent().getAttribute(Usuario.class.getName());
    }

    private Component buildUserMenu() {
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        
        settingsItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
//        updateUserName(null);
//        settingsItem.addItem("Editar Perfil", new Command() {
//            @Override
//            public void menuSelected(final MenuItem selectedItem) {
//                ProfilePreferencesWindow.open(user, false);
//            }
//        });
        
        settingsItem.addItem("Exit", new Command() {
            public void menuSelected(final MenuItem selectedItem) {
//                ValuedEventBus.post(new UserLoggedOutEvent());
            	((ImUI)UI.getCurrent()).userLoggedOut();
            }
        });
        return settings;
    }

    private Component buildToggleButton() {
        Button valoMenuToggleButton = new Button("Menu", new ClickListener() {
            public void buttonClick(final ClickEvent event) {
                if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
                    getCompositionRoot().removeStyleName(STYLE_VISIBLE);
                } else {
                    getCompositionRoot().addStyleName(STYLE_VISIBLE);
                }
            }
        });
        valoMenuToggleButton.setIcon(FontAwesome.LIST);
        valoMenuToggleButton.addStyleName("valo-menu-toggle");
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
        return valoMenuToggleButton;
    }

    private Component buildMenuItems() {
        CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.addStyleName("valo-menuitems");
        
        if(SecurityHelper.isLogged()){
	        for (final ImViewType view : ImViewType.values()) {
	            Component menuItemComponent = new ValoMenuItemButton(view);

//	            if (view == ImViewType.DASHBOARD) {
//	                notificationsBadge = new Label();
//	                notificationsBadge.setId(NOTIFICATIONS_BADGE_ID);
//	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
//	                        notificationsBadge);
//	                
//		            menuItemsLayout.addComponent(menuItemComponent);
//	            }
	            
	            if (view == ImViewType.EMPRESAS) {
	                empresasBadge = new Label();
	                empresasBadge.setId(STYLE_VISIBLE);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		empresasBadge);
	                
		            menuItemsLayout.addComponent(menuItemComponent);
	            }
	            
	            if (view == ImViewType.ESTUDIOS) {
	            	estudiosBadge = new Label();
	            	estudiosBadge.setId(STYLE_VISIBLE);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		estudiosBadge);
	                
		            menuItemsLayout.addComponent(menuItemComponent);
	            }
	            
//	            if (view == ImViewType.REPORTS && SecurityHelper.hasPermission(Permiso.VISUALIZAR_REPORTES)) {
//	                reportsBadge = new Label();
//	                reportsBadge.setId(REPORTS_BADGE_ID);
//	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
//	                        reportsBadge);
//	                
//		            menuItemsLayout.addComponent(menuItemComponent);
//	            }
	            
	            if (view == ImViewType.ADMINISTRATION) {
	                administrationBadge = new Label();
	                administrationBadge.setId(STYLE_VISIBLE);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		administrationBadge);
	                
		            menuItemsLayout.addComponent(menuItemComponent);
	            }
	            
//	            if (view == ImViewType.TRANSACTIONS2 && SecurityHelper.hasPermission(Permiso.VISUALIZAR_TASACIONES)) {
//	                verTasacionesBadge = new Label();
//	                verTasacionesBadge.setId(STYLE_VISIBLE);
//	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
//	                		verTasacionesBadge);
//	                
//		            menuItemsLayout.addComponent(menuItemComponent);
//	            }
//	            
//	            if (view == ImViewType.SALES && SecurityHelper.hasPermission(Permiso.INGRESAR_SOLICITUD)) {
//	                ingresarTasacionBadge = new Label();
//	                ingresarTasacionBadge.setId(STYLE_VISIBLE);
//	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
//	                		ingresarTasacionBadge);
//	                
//		            menuItemsLayout.addComponent(menuItemComponent);
//	            }
	            	
//	            if (view == ValuedViewType.REPORTS) {
//	                // Add drop target to reports button
//	                DragAndDropWrapper reports = new DragAndDropWrapper(
//	                        menuItemComponent);
//	                reports.setSizeUndefined();
//	                reports.setDragStartMode(DragStartMode.NONE);
//	                reports.setDropHandler(new DropHandler() {
//	
//	                    @Override
//	                    public void drop(final DragAndDropEvent event) {
//	                        UI.getCurrent()
//	                                .getNavigator()
//	                                .navigateTo(
//	                                        ValuedViewType.REPORTS.getViewName());
//	                        Table table = (Table) event.getTransferable()
//	                                .getSourceComponent();
//	                        ValuedEventBus.post(new TransactionReportEvent(
//	                                (Collection<Transaction>) table.getValue()));
//	                    }
//	
//	                    @Override
//	                    public AcceptCriterion getAcceptCriterion() {
//	                        return AcceptItem.ALL;
//	                    }
//	
//	                });
//	                menuItemComponent = reports;
//	            }
	
	        }
        }
        return menuItemsLayout;

    }

    private Component buildBadgeWrapper(final Component menuItemButton,
            final Component badgeLabel) {
        CssLayout dashboardWrapper = new CssLayout(menuItemButton);
        dashboardWrapper.addStyleName("badgewrapper");
        dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
        badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
        badgeLabel.setWidthUndefined();
        badgeLabel.setVisible(false);
        dashboardWrapper.addComponent(badgeLabel);
        return dashboardWrapper;
    }

    @Override
    public void attach() {
        super.attach();
//        updateNotificationsCount(null);
    }

//    @Subscribe
//    public void postViewChange(final PostViewChangeEvent event) {
//        // After a successful view change the menu can be hidden in mobile view.
//        getCompositionRoot().removeStyleName(STYLE_VISIBLE);
//    }
//
//    @Subscribe
//    public void updateNotificationsCount(
//            final NotificationsCountUpdatedEvent event) {
//        int unreadNotificationsCount = ValuedUI.getDataProvider()
//                .getUnreadNotificationsCount();
//        notificationsBadge.setValue(String.valueOf(unreadNotificationsCount));
//        notificationsBadge.setVisible(unreadNotificationsCount > 0);
//    }
//
//    @Subscribe
//    public void updateReportsCount(final ReportsCountUpdatedEvent event) {
//        reportsBadge.setValue(String.valueOf(event.getCount()));
//        reportsBadge.setVisible(event.getCount() > 0);
//    }
//
//    @Subscribe
//    public void updateUserName(final ProfileUpdatedEvent event) {
//        Usuario user = getCurrentUser();
//        if(user != null)
//        	settingsItem.setText(user.getNombres() + " " + user.getApellidoPaterno());
//    }

    public final class ValoMenuItemButton extends Button {

        private static final String STYLE_SELECTED = "selected";

        private final ImViewType view;

        public ValoMenuItemButton(final ImViewType view) {
            this.view = view;
            setPrimaryStyleName("valo-menu-item");
            setIcon(view.getIcon());
            setCaption(view.getViewName().substring(0, 1).toUpperCase()
                    + view.getViewName().substring(1));
//            ValuedEventBus.register(this);
            addClickListener(new ClickListener() {
                public void buttonClick(final ClickEvent event) {
                    UI.getCurrent().getNavigator()
                            .navigateTo(view.getViewName());
                }
            });

        }

//        @Subscribe
//        public void postViewChange(final PostViewChangeEvent event) {
//            removeStyleName(STYLE_SELECTED);
//            if (event.getView() == view) {
//                addStyleName(STYLE_SELECTED);
//            }
//        }
    }
}
