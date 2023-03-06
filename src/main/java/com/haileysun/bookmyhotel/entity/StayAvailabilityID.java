package com.haileysun.bookmyhotel.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The @Embeddable class should meet the following requirements
 *
 * 1. Implements Serializable
 *
 * 2. Implements no-arguments constructor
 *
 * 3. Implements `equals` and `hashCode`
 */
@Embeddable
public class StayAvailabilityID implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long stay_id;
    private LocalDate local_date;

    // default constructor
    public StayAvailabilityID() {};

    // another constructor
    public StayAvailabilityID(Long stayId, LocalDate localDate) {
        this.stay_id = stayId;
        this.local_date = localDate;
    }

    // override equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        StayAvailabilityID that = (StayAvailabilityID) o;

        return stay_id.equals(that.stay_id) && local_date.equals(that.local_date);
    }

    // override hashCode()
    public int hashCode() {
        return Objects.hash(stay_id, local_date);
    }

    // getters and setters
    public Long getStayId() {
        return stay_id;
    }

    public StayAvailabilityID setStayId(Long stayId) {
        this.stay_id = stayId;
        return this;
    }

    public LocalDate getLocal_date() {
        return local_date;
    }

    public StayAvailabilityID setLocal_date(LocalDate local_date) {
        this.local_date = local_date;
        return this;
    }
}
