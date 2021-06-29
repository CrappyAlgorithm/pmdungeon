package de.fhbielefeld.pmdungeon.vorgaben.graphic;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.Animation;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.HUD;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IHUDElement;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;


public class HUDTest{

    @Test
    public void testCreateHUD() {
        HUD hud = new HUD();
        assertNotEquals(hud.getHudBatch(),null);
        assertNotEquals(hud.hudCamera,null);
        assertNotEquals(hud.hudElements,null);
    }

    @Test
    public void testUsePixelSystemTrue() {
        HUD hud = new HUD();
        hud.usePixelSystem(true);
        assertEquals(hud.usePixelSystem,true);
    }

    @Test
    public void testUsePixelSystemFalse() {
        HUD hud = new HUD();
        hud.usePixelSystem(false);
        assertEquals(hud.usePixelSystem,false);
    }

    @Test
    public void testAddHudElement() {
        HUD hud = new HUD();
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
        assertEquals(hud.hudElements.size(),1);
    }

    @Test
    public void testAddHudElementNull() {
        HUD hud = new HUD();
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
        assertEquals(hud.hudElements.size(),0);
    }

    @Test
    public void testRemoveHudElementNull() {
        HUD hud = new HUD();
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

    @Test
    public void testRemoveHudElement() {
        HUD hud = new HUD();
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

    @Test
    public void testDraw() {
        MockedConstruction<HUD> mhud = (MockedConstruction<HUD>) Mockito.mockConstruction(HUD.class);

        mHud.hudCamera=mock(OrthographicCamera.class);
        doCallRealMethod().when( mHud).draw();
    }
}
