package de.fhbielefeld.pmdungeon.vorgaben.tools;

import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.dungeonconverter.Coordinate;
import org.junit.Test;

public class PointExceptionTests {

    // 23.2
    @Test(expected=NullPointerException.class)
    public void testCreateWithCoordinateNull() {
        Coordinate coordinate = null;
        Point point = new Point(coordinate);
    }

    // 24.2
    @Test(expected=NullPointerException.class)
    public void testCreateWithPointNull() {
        Point otherPoint = null;
        Point point = new Point(otherPoint);
    }
}
