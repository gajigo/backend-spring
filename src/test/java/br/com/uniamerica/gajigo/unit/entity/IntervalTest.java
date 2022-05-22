package br.com.uniamerica.gajigo.unit.entity;

import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.IntervalRelation;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class IntervalTest extends AbstractUnitTest {
    @Test
    public void testIntervalRelations() {
        Interval a = validInterval();

        Interval after                  = new Interval(a.getStartDate().minusHours(2), a.getStartDate().minusHours(1));
        Interval startTouching          = new Interval(a.getStartDate().minusHours(1), a.getStartDate());
        Interval startInside            = new Interval(a.getStartDate().minusHours(1), a.getStartDate().plusHours(1));
        Interval insideStartTouching    = new Interval(a.getStartDate(), a.getEndDate().plusMinutes(15));
        Interval enclosingStartTouching = new Interval(a.getStartDate(), a.getEndDate().minusMinutes(15));
        Interval enclosing              = new Interval(a.getStartDate().plusHours(1), a.getEndDate().minusHours(1));
        Interval enclosingEndTouching   = new Interval(a.getStartDate().plusMinutes(15), a.getEndDate());
        Interval exactMatch             = new Interval(a.getStartDate(), a.getEndDate());
        Interval inside                 = new Interval(a.getStartDate().minusHours(1), a.getEndDate().plusHours(1));
        Interval insideEndTouching      = new Interval(a.getStartDate().minusHours(1), a.getEndDate());
        Interval endInside              = new Interval(a.getStartDate().plusHours(1), a.getEndDate().plusHours(1));
        Interval endTouching            = new Interval(a.getEndDate(), a.getEndDate().plusHours(1));
        Interval before                 = new Interval(a.getEndDate().plusHours(1), a.getEndDate().plusHours(2));

        assertEquals(IntervalRelation.After, a.getRelation(after));
        assertEquals(IntervalRelation.StartTouching, a.getRelation(startTouching));
        assertEquals(IntervalRelation.StartInside, a.getRelation(startInside));
        assertEquals(IntervalRelation.InsideStartTouching, a.getRelation(insideStartTouching));
        assertEquals(IntervalRelation.EnclosingStartTouching, a.getRelation(enclosingStartTouching));
        assertEquals(IntervalRelation.Enclosing, a.getRelation(enclosing));
        assertEquals(IntervalRelation.EnclosingEndTouching, a.getRelation(enclosingEndTouching));
        assertEquals(IntervalRelation.ExactMatch, a.getRelation(exactMatch));
        assertEquals(IntervalRelation.Inside, a.getRelation(inside));
        assertEquals(IntervalRelation.InsideEndTouching, a.getRelation(insideEndTouching));
        assertEquals(IntervalRelation.EndInside, a.getRelation(endInside));
        assertEquals(IntervalRelation.EndTouching, a.getRelation(endTouching));
        assertEquals(IntervalRelation.Before, a.getRelation(before));
    }

    @Test
    public void testIntervalValidation() {
        Interval interval = validInterval();

        assertTrue(interval.valid());

        interval.setEndDate(interval.getStartDate());
        assertFalse(interval.valid());

        interval.setEndDate(interval.getStartDate().minusHours(1));
        assertFalse(interval.valid());
    }

    @Test
    public void testDuration() {
        LocalDateTime now = LocalDateTime.now();
        Interval interval = new Interval(now, now.plusHours(1));
        long minute = 1000 * 60;
        long hour = minute * 60;

        assertNotEquals(minute, interval.duration());
        assertEquals(hour, interval.duration());
    }

    @Test
    public void testDifference() {
        LocalDateTime now = LocalDateTime.now();

        Interval before = new Interval(now.minusHours(2), now.minusHours(1));
        Interval inside = new Interval(now.minusHours(1), now.plusHours(1));
        Interval after  = new Interval(now.plusHours(1), now.plusHours(2));

        assertTrue(before.difference(now) < 0);
        assertEquals(0, inside.difference(now));
        assertTrue(after.difference(now) > 0);
    }

    private Interval validInterval() {
        return new Interval(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
    }
}
