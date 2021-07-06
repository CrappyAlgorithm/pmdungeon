package de.fhbielefeld.pmdungeon.vorgaben.interfaces;

import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class IHUDElementTest {

    private static final double DELTA = 0.0000001;

    @Mock
    Texture texture;

    IHUDElement ihudElement;

    @Before
    public void init_mocks() throws Exception {
        MockitoAnnotations.openMocks(this).close();

        when(texture.getHeight()).thenReturn(4);
        ihudElement = new IHUDElement() {
            @Override
            public Point getPosition() {
                return null;
            }

            @Override
            public Texture getTexture() {
                return texture;
            }
        };
    }

    // ID 39.1
    @Test
    public void testGetWidth() {
        assertEquals(0.5, ihudElement.getWidth(), DELTA);
    }

    // ID 40.1
    @Test
    public void testGetHeight() {
        assertEquals(2.0, ihudElement.getHeight(), DELTA);
    }
}
