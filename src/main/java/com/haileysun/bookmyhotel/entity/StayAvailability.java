package com.haileysun.bookmyhotel.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stay_availability")
@JsonDeserialize(builder = StayAvailability.Builder.class)
public class StayAvailability implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private StayAvailabilityID stayAvailabilityID;

    /**
     * Use @MapsId to specify the correspondent primary key
     * defined in the @Embeddable composite_primary_key class
     */
    @MapsId("stay_id")
    @ManyToOne
    private Stay stay;
    private StayAvailabilityState state;

    // default constructor
    public StayAvailability() {}

    // another constructor
    private StayAvailability(Builder builder) {
        this.stayAvailabilityID = builder.stayAvailabilityID;
        this.stay = builder.stay;
        this.state = builder.state;
    }

    // getters
    public StayAvailabilityID getId() {
        return stayAvailabilityID;
    }

    public Stay getStay() {
        return stay;
    }

    public StayAvailabilityState getState() {
        return state;
    }

    // Builder pattern
    public static class Builder {
        /**
         * - When an instance of this class is serialized to JSON, the resulting object
         * will have a property named "first_name" with the value of the firstName field.
         * - Similarly, when JSON data is deserialized into an instance of this class,
         * the "first_name" property in the JSON data will be mapped to the firstName field.
         */
        @JsonProperty("stayAvailability_id")
        private StayAvailabilityID stayAvailabilityID;

        @JsonProperty("stay")
        private Stay stay;

        @JsonProperty("state")
        private StayAvailabilityState state;

        // setters
        public Builder setId(StayAvailabilityID id) {
            this.stayAvailabilityID = id;
            return this;
        }

        public Builder setStay(Stay stay) {
            this.stay = stay;
            return this;
        }

        public Builder setState(StayAvailabilityState state) {
            this.state = state;
            return this;
        }

        // build
        public StayAvailability build() {
            return new StayAvailability((this));
        }
    }
}
