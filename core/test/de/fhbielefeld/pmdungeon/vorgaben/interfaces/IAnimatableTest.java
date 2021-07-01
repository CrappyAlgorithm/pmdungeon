package de.fhbielefeld.pmdungeon.vorgaben.interfaces;

import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.Animation;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IAnimatableTest {

    @Mock
    Animation animation;

    @Mock
    Texture texture;

    @Before
    public void init_mocks() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void testGetTexture() {
        when(animation.getNextAnimationTexture()).thenReturn(texture);
        IAnimatable iAnimatable = new IAnimatable() {
            @Override
            public Animation getActiveAnimation() {
                return animation;
            }

            @Override
            public Point getPosition() {
                return null;
            }
        };
        assertEquals(texture, iAnimatable.getTexture());
        verify(animation).getNextAnimationTexture();
    }
}
