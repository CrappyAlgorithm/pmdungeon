package de.fhbielefeld.pmdungeon.vorgaben.graphic;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.game.GameSetup;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IHUDElement;
import de.fhbielefeld.pmdungeon.vorgaben.testutil.ObjectManipulator;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;


public class HUDTest{
    HUD hud;
    SpriteBatch mBatch = mock(SpriteBatch.class);
    OrthographicCamera mCamera = mock(OrthographicCamera.class);

    @Before
    public void setup(){
        this.hud = new HUD(mBatch, mCamera,new ArrayList());
    }

    //ID=25.1
    @Test
    public void testCreateHUD() {

        assertEquals(mBatch, hud.getHudBatch());
        assertEquals(mCamera, hud.hudCamera);
        assertNotEquals(null, hud.hudElements);
    }

    //ID=26.1
    @Test
    public void testUsePixelSystemTrue() {
        hud.usePixelSystem(true);
        assertEquals(hud.usePixelSystem,true);
    }

    //ID=26.2
    @Test
    public void testUsePixelSystemFalse() {
        hud.usePixelSystem(false);
        assertEquals(hud.usePixelSystem,false);
    }

    //ID=27.1
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

        assertEquals(1,hud.hudElements.size());
    }

    //ID=27.2
    @Test
    public void testAddHudElementNull() {

        hud.addHudElement(null);
        assertEquals(1,hud.hudElements.size()); // expected should be set to 0 when fixed
    }

    //ID=28.1
    @Test
    public void testRemoveHudElement() {

        IHUDElement element=new IHUDElement() {
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
        assertEquals(hud.hudElements.contains(element),false);
    }

    //ID=28.2
    @Test
    public void testRemoveHudElementNull() {

        IHUDElement element=new IHUDElement() {
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
        assertEquals(hud.hudElements.contains(element),true);
    }

    //ID=29.1
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
        verify(mCamera).update();
        verify(mBatch, times(2)).setProjectionMatrix(mCamera.combined);
        verify(mHud).drawElements();
    }

    //ID=29.2
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
        verify(mCamera).update();
        verify(mBatch, times(2)).setProjectionMatrix(mCamera.combined);
        verify(mHud).drawElements();
    }
}
