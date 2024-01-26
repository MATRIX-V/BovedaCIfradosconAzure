package com.boveda.views.iniciosesion;



import com.boveda.services.CredencialesService;
import com.boveda.views.MainLayout;
import com.boveda.views.crearclave.CrearClaveView;
import com.boveda.views.inicio.InicioView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Inicio Sesion")
@Route(value = "inicio-sesion")
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class InicioSesionView extends Composite<VerticalLayout> {
    private CredencialesService credencialesService;

    public InicioSesionView(CredencialesService credencialesService) {

        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        TextField textField = new TextField();
        TextField textFieldClave = new TextField();
        Button BotonIniciar = new Button();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        textField.setLabel("Usuario");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        textField.setWidth("min-content");
        textFieldClave.setLabel("Contraseña");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, textFieldClave);
        textFieldClave.setWidth("min-content");
        BotonIniciar.setText("Entrar");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, BotonIniciar);
        BotonIniciar.setWidth("min-content");
        BotonIniciar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonIniciar.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            UI.getCurrent().navigate(InicioView.class);
            CredencialesService.id=textFieldClave.getValue();
            credencialesService.cargarDatosIniciales(CredencialesService.id);
        });
        layoutRow3.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        layoutRow4.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        getContent().add(layoutRow);
        layoutRow.add(layoutRow2);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(textField);
        layoutColumn2.add(textFieldClave);
        layoutColumn2.add(BotonIniciar);
        layoutRow.add(layoutRow3);
        getContent().add(layoutRow4);
    }
}
