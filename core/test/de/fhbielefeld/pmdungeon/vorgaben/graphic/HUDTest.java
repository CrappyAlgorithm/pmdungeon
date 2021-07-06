package de.fhbielefeld.pmdungeon.vorgaben.graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IHUDElement;
import de.fhbielefeld.pmdungeon.vorgaben.testutil.ObjectManipulator;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class HUDTest {

    @Mock
    SpriteBatch mBatch;
    @Mock
    OrthographicCamera mCamera;
    @InjectMocks
    HUD hud;

    Vector3 v3 = new Vector3(0, 0, 0);

    @Before
    public void setup() throws Exception {
        try (MockedConstruction<SpriteBatch> mSpriteBatch = mockConstruction(SpriteBatch.class);
             MockedConstruction<OrthographicCamera> mOrthographicCamera = mockConstruction(OrthographicCamera.class,
                (mock, context) -> {
                    ObjectManipulator.overrrideFinalAttribute(mock, "position", new Vector3(0, 0, 0));
                })) {
            MockitoAnnotations.openMocks(this).close();
            mBatch = mSpriteBatch.constructed().get(0);
            mCamera = mOrthographicCamera.constructed().get(0);
        }
    }

    //ID 25.1
    @Test
    public void testCreateHUD() {
        assertEquals(mBatch, hud.getHudBatch());
        assertEquals(mCamera, hud.hudCamera);
        assertNotEquals(null, hud.hudElements);
    }

    //ID 26.1
    @Test
    public void testUsePixelSystemTrue() {
        hud.usePixelSystem(true);
        assertEquals(hud.usePixelSystem, true);
    }

    //ID 26.2
    @Test
    public void testUsePixelSystemFalse() {
        hud.usePixelSystem(false);
        assertEquals(hud.usePixelSystem, false);
    }

    //ID 27.1
    @Test
    public void testAddHudElement() {

        hud.addHudElement(new IHUDElement() {
            @Override
            public Point getPosition() {
                return null;
            }

            @Override
            public Texture getTexture() {
                return null;
            }
        });

        assertEquals(1, hud.hudElements.size());
    }

    //ID 27.2
    @Test
    public void testAddHudElementNull() {

        hud.addHudElement(null);
        assertEquals(1, hud.hudElements.size()); // expected should be set to 0 when fixed
    }

    //ID 28.1
    @Test
    public void testRemoveHudElement() {

        IHUDElement element = new IHUDElement() {
            @Override
            public Point getPosition() {
                return null;
            }

            @Override
            public Texture getTexture() {
                return null;
            }
        };
        hud.addHudElement(element);
        hud.removeHudElement(element);
        assertEquals(hud.hudElements.contains(element), false);
    }

    //ID 28.2
    @Test
    public void testRemoveHudElementNull() {

        IHUDElement element = new IHUDElement() {
            @Override
            public Point getPosition() {
                return null;
            }

            @Override
            public Texture getTexture() {
                return null;
            }
        };
        hud.addHudElement(element);
        hud.removeHudElement(null);
        assertEquals(hud.hudElements.contains(element), true);
    }

    //ID 29.1
    @Test
    public void testDraw() throws NoSuchFieldException, IllegalAccessException {
        HUD mHud = mock(HUD.class);
        ObjectManipulator.overrrideFinalAttribute(mHud, "hudCamera", mCamera);
        ObjectManipulator.overrrideFinalAttribute(mHud, "hudBatch", mBatch);
        ObjectManipulator.overrrideFinalAttribute(mHud, "hudElements", new ArrayList<IHUDElement>());
        doCallRealMethod().when(mHud).draw();
        doCallRealMethod().when(mHud).addHudElement(any());
        doCallRealMethod().when(mHud).resize();
        doNothing().when(mHud).drawElements();
        mHud.addHudElement(new IHUDElement() {
            @Override
            public Point getPosition() {
                return null;
            }

            @Override
            public Texture getTexture() {
                return null;
            }
        });
        mHud.usePixelSystem(true);
        mHud.draw();
        verify(mCamera, times(2)).update();
        verify(mBatch, times(2)).setProjectionMatrix(mCamera.combined);
        verify(mHud).drawElements();
    }

    //ID 29.2
    @Test
    public void testDrawWithPixelFalse() throws NoSuchFieldException, IllegalAccessException {
        HUD mHud = mock(HUD.class);
        ObjectManipulator.overrrideFinalAttribute(mHud, "hudCamera", mCamera);
        ObjectManipulator.overrrideFinalAttribute(mHud, "hudBatch", mBatch);
        ObjectManipulator.overrrideFinalAttribute(mHud, "hudElements", new ArrayList<IHUDElement>());
        doCallRealMethod().when(mHud).draw();
        doCallRealMethod().when(mHud).addHudElement(any());
        doCallRealMethod().when(mHud).resize();
        doNothing().when(mHud).drawElements();
        mHud.addHudElement(new IHUDElement() {
            @Override
            public Point getPosition() {
                return null;
            }

            @Override
            public Texture getTexture() {
                return null;
            }
        });
        mHud.usePixelSystem(false);
        mHud.draw();
        verify(mCamera).setToOrtho(anyBoolean(), anyFloat(), anyFloat());
        verify(mCamera, times(2)).update();
        verify(mBatch, times(2)).setProjectionMatrix(mCamera.combined);
        verify(mHud).drawElements();
    }

    @Test
    public void testDrawElements() throws NoSuchFieldException, IllegalAccessException {
        hud.addHudElement(new IHUDElement() {
            @Override
            public Point getPosition() {
                return new Point(1, 1);
            }

            @Override
            public Texture getTexture() {
                Texture t = mock(Texture.class);
                when(t.getHeight()).thenReturn(1);
                return t;
            }
        });
        hud.addHudElement(new IHUDElement() {
            @Override
            public Point getPosition() {
                return new Point(1, 1);
            }

            @Override
            public Texture getTexture() {
                Texture t = mock(Texture.class);
                when(t.getHeight()).thenReturn(1);
                return t;
            }
        });
        hud.addHudElement(new IHUDElement() {
            @Override
            public Point getPosition() {
                return new Point(1, 1);
            }

            @Override
            public Texture getTexture() {
                Texture t = mock(Texture.class);
                when(t.getHeight()).thenReturn(1);
                return t;
            }
        });
        try (MockedConstruction<Sprite> mSprite = mockConstruction(Sprite.class)) {
            hud.drawElements();
            assertEquals(3, mSprite.constructed().size());
            verify(mSprite.constructed().get(0)).draw(mBatch);
            verify(mSprite.constructed().get(1)).draw(mBatch);
            verify(mSprite.constructed().get(2)).draw(mBatch);
        }
        verify(mBatch, times(3)).begin();
        verify(mBatch, times(3)).end();
    }
}
