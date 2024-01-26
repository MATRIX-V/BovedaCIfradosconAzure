
package com.boveda.views.crearclave;

import com.boveda.Boveda;
import com.boveda.models.Credenciales;
import com.boveda.Generar;
import com.boveda.Utils;
import com.boveda.services.CredencialesService;
import com.boveda.views.MainLayout;
import com.boveda.views.inicio.InicioView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Crear Clave")
@Route(value = "Crear-Clave", layout = MainLayout.class)
@Uses(Icon.class)
public class CrearClaveView extends Composite<VerticalLayout>{

    private CredencialesService credencialesService;
    public CrearClaveView(CredencialesService credencialesService) {
        this.credencialesService=credencialesService;
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Boveda boveda = Boveda.obtenerInstancia();
        H4 h4 = new H4();
        H4 h42 = new H4();
        FormLayout formLayout2Col = new FormLayout();
        TextField ContenedorClaveNueva = new TextField();
        TextField ContenedorUsuario = new TextField();
        Paragraph ClaveCreada = new Paragraph();
        TextField ContenedorPlat = new TextField();
        Button BotonCrear = new Button();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button BotonGuardar = new Button();
        Button BotonRegresar = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h4.setText("Crea una nueva contraseña y guárdala");
        h4.setWidth("max-content");
        h42.setText("Si deseas guardar esta contraseña en tu bóveda ingresa los siguientes datos");
        h42.setWidth("max-content");
        formLayout2Col.setWidth("100%");
        ContenedorClaveNueva.setLabel("Ingrese la longitud de su contraseña");
        ContenedorUsuario.setLabel("Usuario");
        final String[] clave = {new String()};
        BotonCrear.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            String longitud = ContenedorClaveNueva.getValue();
            Generar clave1 = new Generar();
            clave[0] = clave1.ingresar(Integer.parseInt(longitud));
            ClaveCreada.setText(clave[0]);

        });
        ClaveCreada.setWidth("100%");
        ClaveCreada.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        ContenedorPlat.setLabel("Plataforma");
        BotonCrear.setText("Crear");
        BotonCrear.setWidth("min-content");
        BotonCrear.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Paragraph textSmall = new Paragraph();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("768px");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(Alignment.END);
        layoutRow.setJustifyContentMode(JustifyContentMode.END);
        BotonGuardar.setText("Guardar");
        BotonGuardar.setWidth("min-content");
        BotonGuardar.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            String usuario = ContenedorUsuario.getValue();
            String plat= ContenedorPlat.getValue();
            if(boveda.existePlataforma(plat)){
                textSmall.setText("Ya existe una entrada para esa plataforma");
            }else {
                String claveE = boveda.guardarClave(plat, clave[0]);
                boveda.guardarUsuario(plat, usuario);
                Credenciales credenciales1 = new Credenciales(plat, usuario, claveE, credencialesService.id);
                //Utils.Cred.add( credenciales1);
                credencialesService.listaCredenciales.add(credenciales1);
                credencialesService.agregarCredenciales(credenciales1);
                textSmall.setText("Guardado con éxito");
            }
        });
        BotonRegresar.setText("Regresar a inicio");
        BotonRegresar.setWidth("min-content");
        BotonRegresar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonRegresar.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            UI.getCurrent().navigate(InicioView.class);
        });

        textSmall.setWidth("100%");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h4);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(ContenedorClaveNueva);
        formLayout2Col.add(ClaveCreada);
        formLayout2Col.add(BotonCrear);
        layoutColumn2.add(layoutRow);
        layoutColumn2.add(h42);
        layoutColumn2.add(ContenedorUsuario);
        layoutColumn2.add(ContenedorPlat);
        layoutColumn2.add(BotonGuardar);
        layoutColumn2.add(BotonRegresar);
        layoutColumn2.add(textSmall);



    }
}


