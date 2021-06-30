package de.fhbielefeld.pmdungeon.vorgaben.tools;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PointOfFloatTest {

    private static final Float DELTA = 0.0000001F;

    private final Float x;
    private final Float y;

    public PointOfFloatTest(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    @Parameterized.Parameters
    public static Collection cords() {
        return Arrays.asList(new Object[][] {
                {-1F, 5.3251F},         // 22.1
                { 24.235F, 346.255F},   // 22.2
                {3547.32F, -1F},        // 22.3
                { 0F, 0F },             // 22.4
        });
    }

    @Test
    public void testConstructorForXY() {
        Point point = new Point(x, y);
        assertEquals(x, point.x, DELTA);
        assertEquals(y, point.y, DELTA);
    }
}
