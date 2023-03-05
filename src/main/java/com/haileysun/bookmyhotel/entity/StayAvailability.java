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
    // TODO
    private StayAvailabilityKey id;

    @MapsId("stay_id")
    @ManyToOne
    private Stay stay;
    private StayAvailabilityState state;

    // default constructor
    // TODO - why?
    public StayAvailability() {}

    // another constructor
    private StayAvailability(Builder builder) {
        this.id = builder.id;
        this.stay = builder.stay;
        this.state = builder.state;
    }

    // getters
    public StayAvailabilityKey getId() {
        return id;
    }

    public Stay getStay() {
        return stay;
    }

    public StayAvailabilityState getState() {
        return state;
    }

    // Builder pattern
    public static class Builder {
        // TODO
        /**
         * - When an instance of this class is serialized to JSON, the resulting object
         * will have a property named "first_name" with the value of the firstName field.
         * - Similarly, when JSON data is deserialized into an instance of this class,
         * the "first_name" property in the JSON data will be mapped to the firstName field.
         */
        @JsonProperty("id")
        private StayAvailabilityKey id;

        @JsonProperty("stay")
        private Stay stay;

        @JsonProperty("state")
        private StayAvailabilityState state;

        // setters
        public Builder setId(StayAvailabilityKey id) {
            this.id = id;
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
