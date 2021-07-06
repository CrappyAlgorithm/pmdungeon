package de.fhbielefeld.pmdungeon.vorgaben.tools;

import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.dungeonconverter.Coordinate;
import org.junit.Test;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class PointExceptionTests {

    // ID 23.2
    @Test(expected = NullPointerException.class)
    public void testCreateWithCoordinateNull() {
        Coordinate coordinate = null;
        Point point = new Point(coordinate);
    }

    // ID 24.2
    @Test(expected = NullPointerException.class)
    public void testCreateWithPointNull() {
        Point otherPoint = null;
        Point point = new Point(otherPoint);
    }
}
