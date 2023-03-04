package com.haileysun.bookmyhotel.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class StayAvailabilityKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long stay_id;
    private LocalDate localDate;

    // default constructor
    public StayAvailabilityKey() {};

    // another constructor
    public StayAvailabilityKey(Long stayId, LocalDate localDate) {
        this.stay_id = stayId;
        this.localDate = localDate;
    }

    // overrided equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        StayAvailabilityKey that = (StayAvailabilityKey) o;

        return stay_id.equals(that.stay_id) && localDate.equals(that.localDate);
    }

    // overrided hashCode()
    public int hashCode() {
        return Objects.hash(stay_id, localDate);
    }

    // getters and setters
    public Long getStayId() {
        return stay_id;
    }

    public StayAvailabilityKey setStayId(Long stayId) {
        this.stay_id = stayId;
        // TODO - purpose?
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public StayAvailabilityKey setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        // TODO - purpose?
        return this;
    }
}
