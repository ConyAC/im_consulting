package cl.koritsu.im.view;

import cl.koritsu.im.view.admin.AdministrationView;
import cl.koritsu.im.view.empresas.EmpresasView;
import cl.koritsu.im.view.empresas.EncuestasEmpresaView;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;


public enum ImViewType {
    /*DASHBOARD(DashboardView.NAME, DashboardView.class, FontAwesome.PIE_CHART, true), */
    EMPRESAS(EmpresasView.NAME/*"Empresas"*/, EmpresasView.class, FontAwesome.BUILDING, false),
    ESTUDIOS(EncuestasEmpresaView.NAME/*"Encuestas"*/, EncuestasEmpresaView.class, FontAwesome.FILE, false),
    ADMINISTRATION(AdministrationView.NAME/*"Administración"*/, AdministrationView.class, FontAwesome.GEAR, false);
    
    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private ImViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static ImViewType getByViewName(final String viewName) {
        ImViewType result = null;
        for (ImViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
