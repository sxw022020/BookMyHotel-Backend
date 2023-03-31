package com.haileysun.bookmyhotel.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@JsonDeserialize(builder = Reservation.Builder.class)
public class Reservation implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate checkinData;

    private LocalDate checkoutDate;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User guest;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    // default constructor
    public Reservation() {};

    // constructor with builder pattern
    public Reservation(Builder builder) {
        this.id = builder.id;
        this.checkinData = builder.checkinData;
        this.checkoutDate = builder.checkoutDate;
        this.guest = builder.guest;
        this.stay = builder.stay;
    }

    // getters
    public Long getId() {
        return id;
    }

    public LocalDate getCheckinData() {
        return checkinData;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public User getGuest() {
        return guest;
    }

    public Stay getStay() {
        return stay;
    }

    // necessary setter
    public Reservation setGuest(User guest) {
        this.guest = guest;
        return this;
    }

    // Builder patten
    public static class Builder {
        @JsonProperty("reservation_id")
        private Long id;

        @JsonProperty("checkin_date")
        private LocalDate checkinData;

        @JsonProperty("checkout_date")
        private LocalDate checkoutDate;

        @JsonProperty("guest")
        private User guest;

        @JsonProperty("stay_id")
        private Stay stay;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCheckinData(LocalDate checkinData) {
            this.checkinData = checkinData;
            return this;
        }

        public Builder setCheckoutDate(LocalDate checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        public Builder setGuest(User guest) {
            this.guest = guest;
            return this;
        }

        public Builder setStay(Stay stay) {
            this.stay = stay;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }
}
