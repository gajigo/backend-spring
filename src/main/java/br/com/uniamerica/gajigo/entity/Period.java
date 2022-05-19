package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class Period {
    /*
        Implements Allen's Interval Algebra
    */
    private LocalDateTime start;
    private LocalDateTime end;

    public Period(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public PeriodRelation getRelation(Period b) {
        if (start.isAfter(b.end))  return PeriodRelation.After;
        if (start.isEqual(b.end))  return PeriodRelation.StartTouching;
        if (start.isBefore(b.end)) return PeriodRelation.StartInside;
        if (start.isEqual(b.start)  && end.isBefore(b.end)) return PeriodRelation.InsideStartTouching;
        if (start.isEqual(b.start)  && end.isAfter(b.end))  return PeriodRelation.EnclosingStartTouching;
        if (start.isBefore(b.start) && end.isAfter(b.end))  return PeriodRelation.Enclosing;
        if (start.isBefore(b.start) && end.isEqual(b.end))  return PeriodRelation.EnclosingEndTouching;
        if (start.isEqual(b.start)  && end.isEqual(b.end))  return PeriodRelation.ExactMatch;
        if (start.isAfter(b.start)  && end.isBefore(b.end)) return PeriodRelation.Inside;
        if (start.isAfter(b.start)  && end.isEqual(b.end))  return PeriodRelation.InsideEndTouching;
        if (start.isBefore(b.start) && end.isBefore(b.end)) return PeriodRelation.EndInside;
        if (end.isEqual(b.start))  return PeriodRelation.EndTouching;
        if (end.isBefore(b.start)) return PeriodRelation.Before;
        return null; // Unreachable
    }

    public boolean isAfter(Period b) {
        return getRelation(b) == PeriodRelation.After;
    }

    public boolean isAfterOrEqual(Period b) {
        return isAfter(b) ||
               getRelation(b) == PeriodRelation.StartTouching;
    }

    public boolean isBefore(Period b) {
        return getRelation(b) == PeriodRelation.Before;
    }

    public boolean isBeforeOrEqual(Period b) {
        return isBefore(b) ||
               getRelation(b) == PeriodRelation.EndTouching;
    }

    public boolean isEqual(Period b) {
        return getRelation(b) == PeriodRelation.ExactMatch;
    }

    public boolean isEnclosing(Period b) {
        // B completely inside A, no touching at the start or end
        return getRelation(b) == PeriodRelation.Enclosing;
    }

    public boolean hasInside(Period b) {
        // B inside A, allowing touching at the start or end (or completely equal)
        PeriodRelation relation = getRelation(b);

        return isEnclosing(b) ||
               relation == PeriodRelation.EnclosingStartTouching ||
               relation == PeriodRelation.EnclosingEndTouching ||
               relation == PeriodRelation.ExactMatch;
    }

    public boolean isOverlapping(Period b) {
        // Periods conflict, ignoring start and end
        // e.g. 10:00-11:00 does not overlap with 11:00-12:00
        PeriodRelation relation = getRelation(b);

        return hasInside(b) ||
               relation == PeriodRelation.StartInside ||
               relation == PeriodRelation.InsideStartTouching ||
               relation == PeriodRelation.Inside ||
               relation == PeriodRelation.InsideEndTouching ||
               relation == PeriodRelation.EndInside;
    }

    public long difference(LocalDateTime time) {
        if (time.isBefore(start))   return ChronoUnit.MILLIS.between(time, start);
        else if (time.isAfter(end)) return ChronoUnit.MILLIS.between(time, end);
        else return 0;
    }

    public long duration() {
        return ChronoUnit.MILLIS.between(start, end);
    }

    public boolean isIntersecting(Period b) {
        // Periods conflict, not ignoring start and end
        // e.g. 10:00-11:00 overlaps with 11:00-12:00
        PeriodRelation relation = getRelation(b);

        return isOverlapping(b) ||
               relation == PeriodRelation.EndTouching ||
               relation == PeriodRelation.StartTouching;
    }

    public boolean valid() {
        return start.isBefore(end);
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
