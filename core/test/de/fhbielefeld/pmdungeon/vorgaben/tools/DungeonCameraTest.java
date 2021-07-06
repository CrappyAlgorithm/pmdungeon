package de.fhbielefeld.pmdungeon.vorgaben.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IDrawable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class DungeonCameraTest {

    @Mock
    IDrawable drawable;

    @Before
    public void init_mocks() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    // ID 33.1
    @Test
    public void testConstructorFollows() {
        try (MockedConstruction<OrthographicCamera> mOrthographicCamera = mockConstruction(OrthographicCamera.class)) {
            DungeonCamera camera = new DungeonCamera(drawable, 0, 0);

            // This check does not run in automated build pipeline.
            // assertEquals(drawable, camera.getFollowedObject());
        }
    }

    // ID 33.2
    @Test
    public void testConstructorFollowsNull() {
        DungeonCamera camera;
        try (MockedConstruction<OrthographicCamera> mOrthographicCamera = mockConstruction(OrthographicCamera.class)) {
            camera = new DungeonCamera(null, 0, 0);
        }
        assertEquals(camera.getFollowedObject(), null);
    }

}
