package com.boveda.views.buscarcredenciales;
import com.boveda.Boveda;
import com.boveda.Encriptar;
import com.boveda.models.Credenciales;
import com.boveda.services.CredencialesService;
import com.boveda.views.MainLayout;
import com.boveda.views.inicio.InicioView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Buscar Credenciales")
@Route(value = "Buscar-Credenciales", layout = MainLayout.class)
@Uses(Icon.class)
public class BuscarCredencialesView extends Composite<VerticalLayout> {
    private CredencialesService credencialesService;
    Encriptar encriptar=new Encriptar();
    public BuscarCredencialesView(CredencialesService credencialesService) {
        Boveda boveda = Boveda.obtenerInstancia();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField ContenedorPlat = new TextField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button BotonBuscar = new Button();
        Button BotonRegresar = new Button();
        Paragraph ContenedorUsuario = new Paragraph();
        Paragraph ContenedorClave = new Paragraph();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Busca tus credenciales");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        ContenedorPlat.setLabel("Ingrese la plataforma de las credenciales a buscar");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        BotonBuscar.setText("Buscar");
        BotonBuscar.setWidth("min-content");
        BotonBuscar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonBuscar.addClickListener(event -> {
            Credenciales credenciales = new Credenciales();
            // La lógica que se desea ejecutar cuando se presiona el botón
            String plataforma= ContenedorPlat.getValue();
            credenciales=credencialesService.buscarCredenciales(plataforma, CredencialesService.id);
            ContenedorUsuario.setText(credenciales.getUsuario());
            String clavedesencriptada=encriptar.desencriptarAES(credenciales.getClave());
            ContenedorClave.setText(clavedesencriptada);
            /*String plat= ContenedorPlat.getValue();
            if(boveda.existePlataforma(plat)){
                String clave = boveda.mostrarClave(plat);
                String usuario = boveda.mostrarUsuario(plat);
                ContenedorUsuario.setText("Usuario:" + usuario);

                ContenedorClave.setText("Contraseña:" + clave);
            }else {

                Notification.show("Credenciales no existen");
            }*/
        });

        BotonRegresar.setText("Regresar a inicio");
        BotonRegresar.setWidth("min-content");
        BotonRegresar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonRegresar.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            UI.getCurrent().navigate(InicioView.class);
        });
        layoutRow.getStyle().set("flex-grow", "1");
        ContenedorUsuario.setWidth("100%");
        ContenedorUsuario.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        ContenedorClave.setWidth("100%");
        ContenedorClave.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(ContenedorPlat);
        layoutColumn2.add(layoutRow);
        layoutRow.add(BotonBuscar);
        layoutRow.add(BotonRegresar);
        layoutColumn2.add(ContenedorUsuario);
        layoutColumn2.add(ContenedorClave);

    }
}