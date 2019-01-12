/*
 * SmartPKW
 * Die SmartPKW API ermöglicht die Organisation von Fahrgemeinschaften.  - Benutzerverwaltung - Planung von Rides   - Ein Ride enthält eine von Google Routes generiete Route, welche alle Haltepunkte des Rides enthält.   - Ein Ride enthält mindestens den Haltepunkt \"Start\" und \"Ziel\".   - Jeder Haltepunkt ist an einen Zeitraum gebunden. Der Zeitraum berechnet sich von Google Maps Distance Matrix in Abhängigkeit des geplanten Fahrbeginns.   - Benutzer können Rides teilnehmen, indem sie ...        - ... zwei Haltepunkte vorschlagen. Dieser Vorschlag kann vom Ride-Ersteller angenommen oder angelehnt werden. Beim Ablehnen kann eine Nachricht hinzugefügt werden und es kann ein anderer Vorschlag für den Ein- und/oder Ausstiegspunkt gesendet werden.       - ... einen Haltepunkt vorschlagen. Neben diesem Vorschlag wird einer vorhandenen Haltepunkte zum Ein- bzw. Aussteigen des Mitfahrers verwendet.       - ... an zwei bestehenden Haltepunkten (zum Ein- und Aussteigen) teilnehmen.   - Automatisches Ablehnen der vorgeschlagengen Haltepunkten, wenn der Fahrer den ersten dieser Haltepunkte passiert hat.   - Nach der Teilnahme an einem kann jeder jeden bewerten.   - Bewertungsuntergrenze    
 *
 * OpenAPI spec version: 1.0.0
 * Contact: henning.westervelt@hof-university.de
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * CarWithId
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public class CarWithId   {
  @JsonProperty("carId")
  private Integer carId = null;

  @JsonProperty("createdByUserById")
  private Long createdByUserById = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("seats")
  private Integer seats = null;

  /**
   * the space in the car&#39;s trunk is either small, medium or large
   */
  public enum TrunkspaceEnum {
    SMALL("small"),
    
    MEDIUM("medium"),
    
    LARGE("large");

    private String value;

    TrunkspaceEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TrunkspaceEnum fromValue(String text) {
      for (TrunkspaceEnum b : TrunkspaceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("trunkspace")
  private TrunkspaceEnum trunkspace = null;

  public CarWithId carId(Integer carId) {
    this.carId = carId;
    return this;
  }

  /**
   * Get carId
   * @return carId
   **/
  @JsonProperty("carId")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getCarId() {
    return carId;
  }

  public void setCarId(Integer carId) {
    this.carId = carId;
  }

  public CarWithId createdByUserById(Long createdByUserById) {
    this.createdByUserById = createdByUserById;
    return this;
  }

  /**
   * Get createdByUserById
   * @return createdByUserById
   **/
  @JsonProperty("createdByUserById")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Long getCreatedByUserById() {
    return createdByUserById;
  }

  public void setCreatedByUserById(Long createdByUserById) {
    this.createdByUserById = createdByUserById;
  }

  public CarWithId description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @JsonProperty("description")
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CarWithId seats(Integer seats) {
    this.seats = seats;
    return this;
  }

  /**
   * Get seats
   * @return seats
   **/
  @JsonProperty("seats")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getSeats() {
    return seats;
  }

  public void setSeats(Integer seats) {
    this.seats = seats;
  }

  public CarWithId trunkspace(TrunkspaceEnum trunkspace) {
    this.trunkspace = trunkspace;
    return this;
  }

  /**
   * the space in the car&#39;s trunk is either small, medium or large
   * @return trunkspace
   **/
  @JsonProperty("trunkspace")
  @ApiModelProperty(value = "the space in the car's trunk is either small, medium or large")
  public TrunkspaceEnum getTrunkspace() {
    return trunkspace;
  }

  public void setTrunkspace(TrunkspaceEnum trunkspace) {
    this.trunkspace = trunkspace;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CarWithId carWithId = (CarWithId) o;
    return Objects.equals(this.carId, carWithId.carId) &&
        Objects.equals(this.createdByUserById, carWithId.createdByUserById) &&
        Objects.equals(this.description, carWithId.description) &&
        Objects.equals(this.seats, carWithId.seats) &&
        Objects.equals(this.trunkspace, carWithId.trunkspace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(carId, createdByUserById, description, seats, trunkspace);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CarWithId {\n");
    
    sb.append("    carId: ").append(toIndentedString(carId)).append("\n");
    sb.append("    createdByUserById: ").append(toIndentedString(createdByUserById)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    seats: ").append(toIndentedString(seats)).append("\n");
    sb.append("    trunkspace: ").append(toIndentedString(trunkspace)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

