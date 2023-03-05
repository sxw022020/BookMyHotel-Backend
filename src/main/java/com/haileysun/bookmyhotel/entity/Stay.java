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
    private Long stay_id;
    private String name;
    private String description;
    private String address;

    @JsonProperty("guest_number")
    private int guestNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User host;
    @JsonIgnore
    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StayAvailability> availabilities;

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
        this.stay_id = builder.stay_id;
        this.name = builder.name;
        this.description = builder.description;
        this.address = builder.address;
        this.guestNumber = builder.guestNumber;
        this.host = builder.host;
        this.availabilities = builder.availabilities;
    }

    // getters
    public Long getId() {
        return stay_id;
    }

    public String getName() {
        return name;
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

    // TODO - 课件里这有个setter, 忘记删了?

    public static class Builder {
        @JsonProperty("id")
        private Long stay_id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("address")
        private String address;

        @JsonProperty("guest_number")
        private int guestNumber;

        @JsonProperty("host")
        private User host;

        @JsonProperty("availabilities")
        private List<StayAvailability> availabilities;

        public Builder setId(Long id) {
            this.stay_id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
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

        // build
        public Stay build() {
            return new Stay(this);
        }
    }
}
