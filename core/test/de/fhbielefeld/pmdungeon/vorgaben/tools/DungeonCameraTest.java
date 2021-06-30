package de.fhbielefeld.pmdungeon.vorgaben.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IDrawable;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class DungeonCameraTest {

    //35.2
    @Test
    public void testConstructorFollowsNull() {
        IDrawable follows=null;
        DungeonCamera camera = Mockito.spy(new DungeonCamera(null));
        assertEquals(camera.getFollowedObject(), null);
    }

    //35.1
    @Test
    public void testConstructorFollows() {
        IDrawable follows=new IDrawable() {
            @Override
            public Point getPosition() {
                return null;
            }

            @Override
            public Texture getTexture() {
                return null;
            }
        };
        DungeonCamera camera = new DungeonCamera(follows);
        assertEquals(camera.getFollowedObject(), follows);
    }
}
