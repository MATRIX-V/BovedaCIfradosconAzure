package com.boveda.views.inicio;

import com.boveda.Boveda;
import com.boveda.models.Credenciales;
import com.boveda.Utils;
import com.boveda.services.CredencialesService;
import com.boveda.views.MainLayout;
import com.boveda.views.buscarcredenciales.BuscarCredencialesView;
import com.boveda.views.crearclave.CrearClaveView;
import com.boveda.views.editarcredenciales.EditarCredencialesView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.List;

@PageTitle("Inicio")
@Route(value = "inicio", layout = MainLayout.class)
@RouteAlias(value = "inicio", layout = MainLayout.class)
@Uses(Icon.class)
public class InicioView extends Composite<VerticalLayout> {
    private CredencialesService credencialesService;
    public InicioView(CredencialesService credencialesService) {
        H1 h1 = new H1();
        H2 h2 = new H2();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        ListDataProvider<Credenciales> dataProvider = new ListDataProvider<>(CredencialesService.listaCredenciales/*Utils.Cred*/);
        Grid<Credenciales> grid = new Grid<>(Credenciales.class, false);
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button BotonCrear = new Button();
        Button BotonBuscar = new Button();
        Button BotonEditar = new Button();

        getContent().setWidth("50%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("¡Bienvenido a tu bóveda de contraseñas!");
        h1.setWidth("max-content");
        h2.setText("Creado por Naomi Lizano y Matías Criollo");
        h2.setWidth("max-content");
        Anchor enlace = new Anchor("https://www.security.org/how-secure-is-my-password/", "¡Comprueba la seguridad de nuestras contraseñas aquí!");


        getContent().add(h1);
        getContent().add(h2);
        getContent().add(enlace);
        // Configurar las columnas del Grid
        grid.addColumn(Credenciales::getPlataforma).setHeader("Plataformas con credenciales guardadas");
        grid.setDataProvider(dataProvider);

        // Configurar datos de muestra en el Grid
        List<Credenciales> credenciales = CredencialesService.listaCredenciales;//Utils.Cred;
        grid.setItems(credenciales);
        layoutColumn2.setWidthFull();
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        BotonCrear.setText("Crear clave");
        BotonCrear.setWidth("min-content");
        BotonCrear.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            UI.getCurrent().navigate(CrearClaveView.class);

        });
        BotonCrear.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonEditar.setText("Editar credenciales");
        BotonEditar.setWidth("min-content");
        BotonEditar.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            UI.getCurrent().navigate(EditarCredencialesView.class);

        });
        BotonEditar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        BotonBuscar.setText("Ver credenciales");
        BotonBuscar.setWidth("min-content");
        BotonBuscar.addClickListener(event -> {
            // La lógica que se desea ejecutar cuando se presiona el botón
            UI.getCurrent().navigate(BuscarCredencialesView.class);

        });
        BotonBuscar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        grid.addComponentColumn(credencial -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addClickListener(event -> {
                // Lógica para eliminar la credencial seleccionada
                Boveda.eliminarDato(credencial.getPlataforma());

                // Remover la credencial del Grid
                CredencialesService.listaCredenciales.remove(credencial);
                //Utils.Cred.remove(credencial);
                grid.setItems(CredencialesService.listaCredenciales);
                //grid.setItems(Utils.Cred);
                credencialesService.borrarCredenciales(credencial);
            });
            return deleteButton;
        });

        layoutColumn2.add(grid);
        getContent().add(layoutColumn2);  //
        getContent().add(layoutRow);
        layoutRow.add(BotonCrear);
        layoutRow.add(BotonBuscar);
        layoutRow.add(BotonEditar);

    }
}
