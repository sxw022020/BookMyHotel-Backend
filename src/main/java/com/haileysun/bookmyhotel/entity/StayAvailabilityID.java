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

    // override equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        StayAvailabilityID that = (StayAvailabilityID) o;

        return stay_id.equals(that.stay_id) && localDate.equals(that.localDate);
    }

    // override hashCode()
    public int hashCode() {
        return Objects.hash(stay_id, localDate);
    }

    // getters and setters
    public Long getStayId() {
        return stay_id;
    }

    public StayAvailabilityID setStayId(Long stayId) {
        this.stay_id = stayId;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public StayAvailabilityID setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }
}
