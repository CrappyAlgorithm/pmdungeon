package de.fhbielefeld.pmdungeon.vorgaben.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
@RunWith(Parameterized.class)
public class PointOfPointTest {

    private static final Float DELTA = 0.0000001F;

    private final Point otherPoint;

    public PointOfPointTest(Point otherPoint) {
        this.otherPoint = otherPoint;
    }

    // ID 24.1
    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(new Object[][]{
                {new Point(2.353F, 325.1F)},  // ID 24.1
                {new Point(-1, 539.806F)},      // ---
                {new Point(972.59F, -1)}        // ---
        });
    }

    @Test
    public void testConstructorForPoint() {
        Point point = new Point(otherPoint);
        assertEquals(otherPoint.x, point.x, DELTA);
        assertEquals(otherPoint.y, point.y, DELTA);
    }
}
