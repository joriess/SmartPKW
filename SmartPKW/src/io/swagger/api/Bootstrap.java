package io.swagger.api;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import io.swagger.models.auth.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("SmartPKW")
      .description("Die SmartPKW API ermöglicht die Organisation von Fahrgemeinschaften.  - Benutzerverwaltung - Planung von Rides   - Ein Ride enthält eine von Google Routes generiete Route, welche alle Haltepunkte des Rides enthält.   - Ein Ride enthält mindestens den Haltepunkt \"Start\" und \"Ziel\".   - Jeder Haltepunkt ist an einen Zeitraum gebunden. Der Zeitraum berechnet sich von Google Maps Distance Matrix in Abhängigkeit des geplanten Fahrbeginns.   - Benutzer können Rides teilnehmen, indem sie ...        - ... zwei Haltepunkte vorschlagen. Dieser Vorschlag kann vom Ride-Ersteller angenommen oder angelehnt werden. Beim Ablehnen kann eine Nachricht hinzugefügt werden und es kann ein anderer Vorschlag für den Ein- und/oder Ausstiegspunkt gesendet werden.       - ... einen Haltepunkt vorschlagen. Neben diesem Vorschlag wird einer vorhandenen Haltepunkte zum Ein- bzw. Aussteigen des Mitfahrers verwendet.       - ... an zwei bestehenden Haltepunkten (zum Ein- und Aussteigen) teilnehmen.   - Automatisches Ablehnen der vorgeschlagengen Haltepunkten, wenn der Fahrer den ersten dieser Haltepunkte passiert hat.   - Nach der Teilnahme an einem kann jeder jeden bewerten.   - Bewertungsuntergrenze    ")
      .termsOfService("http://swagger.io/terms/")
      .contact(new Contact()
        .email("henning.westervelt@hof-university.de"))
      .license(new License()
        .name("Apache 2.0")
        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

    ServletContext context = config.getServletContext();
    Swagger swagger = new Swagger().info(info);

    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}
