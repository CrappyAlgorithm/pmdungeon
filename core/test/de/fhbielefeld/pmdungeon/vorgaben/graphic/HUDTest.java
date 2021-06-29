package de.fhbielefeld.pmdungeon.vorgaben.graphic;



import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IHUDElement;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;


public class HUDTest{
    HUD hud;
    @Before
    public void setup(){
        this.hud = new HUD(mock(SpriteBatch.class),mock(OrthographicCamera.class),new ArrayList());
    }
    //ID=25
    @Test
    public void testCreateHUD() {

        assertNotEquals(hud.getHudBatch(),null);
        assertNotEquals(hud.hudCamera,null);
        assertNotEquals(hud.hudElements,null);
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
        assertEquals(1,hud.hudElements.size());
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
    /*
    @Test
    public void testDraw() {
        OrthographicCamera mCamera=mock(OrthographicCamera.class);
        HUD mhud= (HUD) mockConstruction(HUD.class,(hud, context) -> {context.constructor()});
        verify(mHUD)
    }*/
}
