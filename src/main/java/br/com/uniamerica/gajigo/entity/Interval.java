package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Interval {
    /*
        Implements Allen's Interval Algebra
    */
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

    public Interval(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public IntervalRelation getRelation(Interval b) {
        if (startDate.isAfter(b.endDate)) {
            return IntervalRelation.After;
        }
        if (endDate.isBefore(b.startDate)) {
            return IntervalRelation.Before;
        }
        if (endDate.isEqual(b.startDate)) {
            return IntervalRelation.EndTouching;
        }
        if (startDate.isEqual(b.endDate)) {
            return IntervalRelation.StartTouching;
        }
        if (startDate.isEqual(b.startDate) && endDate.isEqual(b.endDate)) {
            return IntervalRelation.ExactMatch;
        }
        if (startDate.isAfter(b.startDate) && endDate.isBefore(b.endDate)) {
            return IntervalRelation.Inside;
        }
        if (startDate.isAfter(b.startDate) && endDate.isEqual(b.endDate)) {
            return IntervalRelation.InsideEndTouching;
        }
        if (startDate.isBefore(b.startDate) && endDate.isAfter(b.endDate)) {
            return IntervalRelation.Enclosing;
        }
        if (startDate.isBefore(b.startDate) && endDate.isEqual(b.endDate)) {
            return IntervalRelation.EnclosingEndTouching;
        }
        if (startDate.isEqual(b.startDate) && endDate.isAfter(b.endDate)) {
            return IntervalRelation.EnclosingStartTouching;
        }
        if (startDate.isEqual(b.startDate) && endDate.isBefore(b.endDate)) {
            return IntervalRelation.InsideStartTouching;
        }
        if (startDate.isBefore(b.startDate) && endDate.isBefore(b.endDate)) {
            return IntervalRelation.EndInside;
        }
        if (startDate.isBefore(b.endDate)) {
            return IntervalRelation.StartInside;
        }
        return null; // Unreachable
    }

    public boolean isAfter(Interval b) {
        return getRelation(b) == IntervalRelation.After;
    }

    public boolean isAfterOrEqual(Interval b) {
        return isAfter(b)
                || getRelation(b) == IntervalRelation.StartTouching;
    }

    public boolean isBefore(Interval b) {
        return getRelation(b) == IntervalRelation.Before;
    }

    public boolean isBeforeOrEqual(Interval b) {
        return isBefore(b)
                || getRelation(b) == IntervalRelation.EndTouching;
    }

    public boolean isEqual(Interval b) {
        return getRelation(b) == IntervalRelation.ExactMatch;
    }

    public boolean isEnclosing(Interval b) {
        // B completely inside A, no touching at the start or end
        return getRelation(b) == IntervalRelation.Enclosing;
    }

    public boolean hasInside(Interval b) {
        // B inside A, allowing touching at the start or end (or completely equal)
        IntervalRelation relation = getRelation(b);

        return isEnclosing(b)
                || relation == IntervalRelation.EnclosingStartTouching
                || relation == IntervalRelation.EnclosingEndTouching
                || relation == IntervalRelation.ExactMatch;
    }

    public boolean isOverlapping(Interval b) {
        // Periods conflict, ignoring start and end
        // e.g. 10:00-11:00 does not overlap with 11:00-12:00
        IntervalRelation relation = getRelation(b);

        return hasInside(b)
                || relation == IntervalRelation.StartInside
                || relation == IntervalRelation.InsideStartTouching
                || relation == IntervalRelation.Inside
                || relation == IntervalRelation.InsideEndTouching
                || relation == IntervalRelation.EndInside;
    }

    public boolean isIntersecting(Interval b) {
        // Periods conflict, not ignoring start and end
        // e.g. 10:00-11:00 overlaps with 11:00-12:00
        IntervalRelation relation = getRelation(b);

        return isOverlapping(b)
                || relation == IntervalRelation.EndTouching
                || relation == IntervalRelation.StartTouching;
    }

    public long difference(LocalDateTime time) {
        if (time.isBefore(startDate)) {
            return ChronoUnit.MILLIS.between(time, startDate);
        } else if (time.isAfter(endDate)) {
            return ChronoUnit.MILLIS.between(time, endDate);
        } else {
            return 0;
        }
    }

    public long duration() {
        return ChronoUnit.MILLIS.between(startDate, endDate);
    }

    public boolean valid() {
        return startDate.isBefore(endDate);
    }

    @Override
    public String toString() {
        return startDate + " - " + endDate;
    }
}
