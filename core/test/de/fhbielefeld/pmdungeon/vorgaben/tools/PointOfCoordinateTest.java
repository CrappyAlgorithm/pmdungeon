package de.fhbielefeld.pmdungeon.vorgaben.tools;

import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.dungeonconverter.Coordinate;
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
public class PointOfCoordinateTest {

    private Coordinate coordinate;

    public PointOfCoordinateTest(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    // ID 23.x
    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(new Object[][]{
                {new Coordinate(2353, 3251)},     // ID 23.1
                {new Coordinate(-1, 53980)},        // ---
                {new Coordinate(97259, -1)}         // ---
        });
    }

    @Test
    public void testConstructorForCoordinate() {
        Point point = new Point(coordinate);
        assertEquals(coordinate.getX(), ((Float) point.x).intValue());
        assertEquals(coordinate.getY(), ((Float) point.y).intValue());
    }
}
