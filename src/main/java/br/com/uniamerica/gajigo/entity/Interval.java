package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
@NoArgsConstructor
@Embeddable
public class Interval {
    /*
        Implements Allen's Interval Algebra
    */
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;

    public Interval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public IntervalRelation getRelation(Interval b) {
        if (start.isAfter(b.start)  && end.isBefore(b.end)) return IntervalRelation.Inside;
        if (start.isAfter(b.start)  && end.isEqual(b.end))  return IntervalRelation.InsideEndTouching;
        if (start.isBefore(b.start) && end.isAfter(b.end))  return IntervalRelation.Enclosing;
        if (start.isBefore(b.start) && end.isBefore(b.end)) return IntervalRelation.EndInside;
        if (start.isBefore(b.start) && end.isEqual(b.end))  return IntervalRelation.EnclosingEndTouching;
        if (start.isEqual(b.start)  && end.isAfter(b.end))  return IntervalRelation.EnclosingStartTouching;
        if (start.isEqual(b.start)  && end.isBefore(b.end)) return IntervalRelation.InsideStartTouching;
        if (start.isEqual(b.start)  && end.isEqual(b.end))  return IntervalRelation.ExactMatch;
        if (start.isAfter(b.end))  return IntervalRelation.After;
        if (start.isBefore(b.end)) return IntervalRelation.StartInside;
        if (start.isEqual(b.end))  return IntervalRelation.StartTouching;
        if (end.isBefore(b.start)) return IntervalRelation.Before;
        if (end.isEqual(b.start))  return IntervalRelation.EndTouching;
        return null; // Unreachable
    }

    public boolean isAfter(Interval b) {
        return getRelation(b) == IntervalRelation.After;
    }

    public boolean isAfterOrEqual(Interval b) {
        return isAfter(b) ||
               getRelation(b) == IntervalRelation.StartTouching;
    }

    public boolean isBefore(Interval b) {
        return getRelation(b) == IntervalRelation.Before;
    }

    public boolean isBeforeOrEqual(Interval b) {
        return isBefore(b) ||
               getRelation(b) == IntervalRelation.EndTouching;
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

        return isEnclosing(b) ||
               relation == IntervalRelation.EnclosingStartTouching ||
               relation == IntervalRelation.EnclosingEndTouching ||
               relation == IntervalRelation.ExactMatch;
    }

    public boolean isOverlapping(Interval b) {
        // Periods conflict, ignoring start and end
        // e.g. 10:00-11:00 does not overlap with 11:00-12:00
        IntervalRelation relation = getRelation(b);

        return hasInside(b) ||
               relation == IntervalRelation.StartInside ||
               relation == IntervalRelation.InsideStartTouching ||
               relation == IntervalRelation.Inside ||
               relation == IntervalRelation.InsideEndTouching ||
               relation == IntervalRelation.EndInside;
    }

    public boolean isIntersecting(Interval b) {
        // Periods conflict, not ignoring start and end
        // e.g. 10:00-11:00 overlaps with 11:00-12:00
        IntervalRelation relation = getRelation(b);

        return isOverlapping(b) ||
                relation == IntervalRelation.EndTouching ||
                relation == IntervalRelation.StartTouching;
    }

    public long difference(LocalDateTime time) {
        if (time.isBefore(start))   return ChronoUnit.MILLIS.between(time, start);
        else if (time.isAfter(end)) return ChronoUnit.MILLIS.between(time, end);
        else return 0;
    }

    public long duration() {
        return ChronoUnit.MILLIS.between(start, end);
    }

    public boolean isValid() {
        return start.isBefore(end);
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
