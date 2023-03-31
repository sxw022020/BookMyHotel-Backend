package com.haileysun.bookmyhotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "stay")
@JsonDeserialize(builder = Stay.Builder.class)
// specify the deserializer to be used when converting JSON data into a Java object
public class Stay implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String stayName;

    private String description;

    private String address;

    private int guestNumber;

    @ManyToOne
    @JoinColumn(name = "host_id") // specify the foreign key column that links one entity to another
    private User host;

    @JsonIgnore
    // TODO - mappedBy + FetchType.LAZY?
    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StayAvailability> availabilities;

    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StayImage> images;

    // default constructor
    /**
     * when Spring creates an instance of a class,
     * it first creates an empty object using the default constructor and
     * then populates its properties using setters, constructors or fields injection.
     *
     * If you don't provide an empty constructor,
     * Spring won't be able to create an instance of your class using the default constructor,
     * and you'll get a runtime error.
     */
    public Stay(){}

    // Builder Pattern for constructor
    // `private` --> only Builder can access it, outside caller cannot access it
    private Stay(Builder builder) {
        this.id = builder.id;
        this.stayName = builder.stayName;
        this.description = builder.description;
        this.address = builder.address;
        this.guestNumber = builder.guestNumber;
        this.host = builder.host;
        this.availabilities = builder.availabilities;
        this.images = builder.images;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return stayName;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public User getHost() {
        return host;
    }

    public List<StayAvailability> getAvailabilities() {
        return availabilities;
    }

    // extra setAvailabilities - because StayService will use it
    public Stay setAvailabilities(List<StayAvailability> availabilities) {
        this.availabilities = availabilities;
        return this;
    }

    // getter and setter of images
    public List<StayImage> getImages() {
        return images;
    }

    public void setImages(List<StayImage> images) {
        this.images = images;
    }

    public static class Builder {
        @JsonProperty("stay_id")
        private Long id;

        @JsonProperty("stay_name")
        private String stayName;

        @JsonProperty("description")
        private String description;

        @JsonProperty("address")
        private String address;

        @JsonProperty("guest_number")
        private int guestNumber;

        @JsonProperty("host")
        private User host;

        // ignore in deserialization, so no need to use @JsonProperty
        private List<StayAvailability> availabilities;

        @JsonProperty("images")
        private List<StayImage> images;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.stayName = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setGuestNumber(int guestNumber) {
            this.guestNumber = guestNumber;
            return this;
        }

        public Builder setHost(User host) {
            this.host = host;
            return this;
        }

        public Builder setAvailabilities(List<StayAvailability> availabilities) {
            this.availabilities = availabilities;
            return this;
        }

        public Builder setImages(List<StayImage> images) {
            this.images = images;
            return this;
        }

        // build
        public Stay build() {
            return new Stay(this);
        }
    }
}
