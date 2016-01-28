package org.navigators.financial.model;

import android.util.Log;

/**
 * Created by rodolfo on 1/20/16.
 */
public class Position {

    private final String LOG_TAG = Position.class.getSimpleName();

    //region Fields
    private Double latitude;
    private Double longitude;
    //endregion;

    //region Properties

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    //endregion;


    //region Constructors


    public Position() {
        this.latitude = 0d;
        this.longitude = 0d;
    }

    public Position(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        Log.v(LOG_TAG, "Create Position: "
                + Double.toString(this.latitude) + ","
                + Double.toString(this.longitude) + "");
    }

    //endregion;

    //region Methods

    //endregion;

    //region Overrides

    @Override
    public String toString() {
        return "Position{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (!getLatitude().equals(position.getLatitude())) return false;
        return getLongitude().equals(position.getLongitude());

    }

    @Override
    public int hashCode() {
        int result = getLatitude().hashCode();
        result = 31 * result + getLongitude().hashCode();
        return result;
    }

    //endregion;
}
