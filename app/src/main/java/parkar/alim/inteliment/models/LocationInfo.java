package parkar.alim.inteliment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationInfo {

    @SerializedName("id") @Expose private Integer id;
    @SerializedName("name") @Expose private String name;
    @SerializedName("fromcentral") @Expose private FromCentral fromCentral;
    @SerializedName("location") @Expose private Location location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FromCentral getFromCentral() {
        return fromCentral;
    }

    public void setFromCentral(FromCentral fromCentral) {
        this.fromCentral = fromCentral;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}