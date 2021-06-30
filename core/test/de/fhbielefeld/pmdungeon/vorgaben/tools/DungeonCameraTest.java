package de.fhbielefeld.pmdungeon.vorgaben.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IDrawable;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;


public class DungeonCameraTest {

    //33.1
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

    //33.2
    @Test
    public void testConstructorFollowsNull() {
        IDrawable follows=null;
        DungeonCamera camera = Mockito.spy(new DungeonCamera(null));
        assertEquals(camera.getFollowedObject(), null);
    }

    //35.3
    @Test(expected = UnsatisfiedLinkError.class)
    public void testUpdateFollowsNotNull() {
        IDrawable follows=null;
        DungeonCamera camera = Mockito.spy(new DungeonCamera(null));
        camera.follow(new IDrawable() {
            @Override
            public Point getPosition() {
                return new Point(1,1);
            }

            @Override
            public Texture getTexture() {
                return null;
            }
        });

        camera.update();
        verify(camera).position.set(anyFloat(),anyFloat(),0);
    }

    //35.4
    @Test(expected = UnsatisfiedLinkError.class)
    public void testUpdateFollowsNull() {
        IDrawable follows=null;
        DungeonCamera camera = new DungeonCamera(null);
        camera.setFocusPoint(new Point(1,1));

        camera.update();
        verify(camera).position.set(1,1,0);
    }


}
