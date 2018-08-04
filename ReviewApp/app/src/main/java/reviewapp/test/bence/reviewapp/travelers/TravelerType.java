package reviewapp.test.bence.reviewapp.travelers;

import com.google.gson.annotations.SerializedName;

public enum TravelerType {
    @SerializedName("solo")
    SOLO,
    @SerializedName("couple")
    COUPLE,
    @SerializedName("family_old")
    FAMILY_OLD,
    UNKNOWN
}
