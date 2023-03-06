package com.haileysun.bookmyhotel.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class StayAvailabilityID implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long stay_id;
    private LocalDate localDate;

    // default constructor
    public StayAvailabilityID() {};

    // another constructor
    public StayAvailabilityID(Long stayId, LocalDate localDate) {
        this.stay_id = stayId;
        this.localDate = localDate;
    }

    // overrided equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        StayAvailabilityID that = (StayAvailabilityID) o;

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

    public StayAvailabilityID setStayId(Long stayId) {
        this.stay_id = stayId;
        // TODO - purpose?
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public StayAvailabilityID setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        // TODO - purpose?
        return this;
    }
}
