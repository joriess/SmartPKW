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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

/**
 * StopWithId
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-01-09T18:17:56.749Z")
public class StopWithId   {
  @JsonProperty("stopId")
  private Integer stopId = null;

  @JsonProperty("rank")
  private Integer rank = null;

  @JsonProperty("createdByUserById")
  private String createdByUserById = null;

  @JsonProperty("createdForRideById")
  private Integer createdForRideById = null;

  @JsonProperty("startPointForUserById")
  private List<String> startPointForUserById = null;

  @JsonProperty("endPointForUserById")
  private List<String> endPointForUserById = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("latitude")
  private Integer latitude = null;

  @JsonProperty("longitude")
  private Integer longitude = null;

  @JsonProperty("timeFrameStart")
  private Date timeFrameStart = null;

  @JsonProperty("timeFrameEnd")
  private Date timeFrameEnd = null;

  /**
   * The status of a stop is either accepted, declined or pending.
   */
  public enum StatusEnum {
    ACCEPTED("accepted"),
    
    DECLINED("declined"),
    
    PENDING("pending");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public StopWithId stopId(Integer stopId) {
    this.stopId = stopId;
    return this;
  }

  /**
   * Get stopId
   * @return stopId
   **/
  @JsonProperty("stopId")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getStopId() {
    return stopId;
  }

  public void setStopId(Integer stopId) {
    this.stopId = stopId;
  }

  public StopWithId rank(Integer rank) {
    this.rank = rank;
    return this;
  }

  /**
   * The sequence of the stops is called rank and changes dynamically. The starting-point of the driver is always rank 1 and the destination of the driver is the highest rank.
   * @return rank
   **/
  @JsonProperty("rank")
  @ApiModelProperty(value = "The sequence of the stops is called rank and changes dynamically. The starting-point of the driver is always rank 1 and the destination of the driver is the highest rank.")
  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }

  public StopWithId createdByUserById(String createdByUserById) {
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
  public String getCreatedByUserById() {
    return createdByUserById;
  }

  public void setCreatedByUserById(String createdByUserById) {
    this.createdByUserById = createdByUserById;
  }

  public StopWithId createdForRideById(Integer createdForRideById) {
    this.createdForRideById = createdForRideById;
    return this;
  }

  /**
   * Get createdForRideById
   * @return createdForRideById
   **/
  @JsonProperty("createdForRideById")
  @ApiModelProperty(value = "")
  public Integer getCreatedForRideById() {
    return createdForRideById;
  }

  public void setCreatedForRideById(Integer createdForRideById) {
    this.createdForRideById = createdForRideById;
  }

  public StopWithId startPointForUserById(List<String> startPointForUserById) {
    this.startPointForUserById = startPointForUserById;
    return this;
  }

  public StopWithId addStartPointForUserByIdItem(String startPointForUserByIdItem) {
    if (this.startPointForUserById == null) {
      this.startPointForUserById = new ArrayList<String>();
    }
    this.startPointForUserById.add(startPointForUserByIdItem);
    return this;
  }

  /**
   * Get startPointForUserById
   * @return startPointForUserById
   **/
  @JsonProperty("startPointForUserById")
  @ApiModelProperty(value = "")
  public List<String> getStartPointForUserById() {
    return startPointForUserById;
  }

  public void setStartPointForUserById(List<String> startPointForUserById) {
    this.startPointForUserById = startPointForUserById;
  }

  public StopWithId endPointForUserById(List<String> endPointForUserById) {
    this.endPointForUserById = endPointForUserById;
    return this;
  }

  public StopWithId addEndPointForUserByIdItem(String endPointForUserByIdItem) {
    if (this.endPointForUserById == null) {
      this.endPointForUserById = new ArrayList<String>();
    }
    this.endPointForUserById.add(endPointForUserByIdItem);
    return this;
  }

  /**
   * Get endPointForUserById
   * @return endPointForUserById
   **/
  @JsonProperty("endPointForUserById")
  @ApiModelProperty(value = "")
  public List<String> getEndPointForUserById() {
    return endPointForUserById;
  }

  public void setEndPointForUserById(List<String> endPointForUserById) {
    this.endPointForUserById = endPointForUserById;
  }

  public StopWithId address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   **/
  @JsonProperty("address")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public StopWithId latitude(Integer latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
   **/
  @JsonProperty("latitude")
  @ApiModelProperty(value = "")
  public Integer getLatitude() {
    return latitude;
  }

  public void setLatitude(Integer latitude) {
    this.latitude = latitude;
  }

  public StopWithId longitude(Integer longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
   **/
  @JsonProperty("longitude")
  @ApiModelProperty(value = "")
  public Integer getLongitude() {
    return longitude;
  }

  public void setLongitude(Integer longitude) {
    this.longitude = longitude;
  }

  public StopWithId timeFrameStart(Date timeFrameStart) {
    this.timeFrameStart = timeFrameStart;
    return this;
  }

  /**
   * Get timeFrameStart
   * @return timeFrameStart
   **/
  @JsonProperty("timeFrameStart")
  @ApiModelProperty(value = "")
  public Date getTimeFrameStart() {
    return timeFrameStart;
  }

  public void setTimeFrameStart(Date timeFrameStart) {
    this.timeFrameStart = timeFrameStart;
  }

  public StopWithId timeFrameEnd(Date timeFrameEnd) {
    this.timeFrameEnd = timeFrameEnd;
    return this;
  }

  /**
   * Get timeFrameEnd
   * @return timeFrameEnd
   **/
  @JsonProperty("timeFrameEnd")
  @ApiModelProperty(value = "")
  public Date getTimeFrameEnd() {
    return timeFrameEnd;
  }

  public void setTimeFrameEnd(Date timeFrameEnd) {
    this.timeFrameEnd = timeFrameEnd;
  }

  public StopWithId status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * The status of a stop is either accepted, declined or pending.
   * @return status
   **/
  @JsonProperty("status")
  @ApiModelProperty(required = true, value = "The status of a stop is either accepted, declined or pending.")
  @NotNull
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StopWithId stopWithId = (StopWithId) o;
    return Objects.equals(this.stopId, stopWithId.stopId) &&
        Objects.equals(this.rank, stopWithId.rank) &&
        Objects.equals(this.createdByUserById, stopWithId.createdByUserById) &&
        Objects.equals(this.createdForRideById, stopWithId.createdForRideById) &&
        Objects.equals(this.startPointForUserById, stopWithId.startPointForUserById) &&
        Objects.equals(this.endPointForUserById, stopWithId.endPointForUserById) &&
        Objects.equals(this.address, stopWithId.address) &&
        Objects.equals(this.latitude, stopWithId.latitude) &&
        Objects.equals(this.longitude, stopWithId.longitude) &&
        Objects.equals(this.timeFrameStart, stopWithId.timeFrameStart) &&
        Objects.equals(this.timeFrameEnd, stopWithId.timeFrameEnd) &&
        Objects.equals(this.status, stopWithId.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stopId, rank, createdByUserById, createdForRideById, startPointForUserById, endPointForUserById, address, latitude, longitude, timeFrameStart, timeFrameEnd, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StopWithId {\n");
    
    sb.append("    stopId: ").append(toIndentedString(stopId)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    createdByUserById: ").append(toIndentedString(createdByUserById)).append("\n");
    sb.append("    createdForRideById: ").append(toIndentedString(createdForRideById)).append("\n");
    sb.append("    startPointForUserById: ").append(toIndentedString(startPointForUserById)).append("\n");
    sb.append("    endPointForUserById: ").append(toIndentedString(endPointForUserById)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    timeFrameStart: ").append(toIndentedString(timeFrameStart)).append("\n");
    sb.append("    timeFrameEnd: ").append(toIndentedString(timeFrameEnd)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

