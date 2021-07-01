package de.fhbielefeld.pmdungeon.vorgaben.graphic;


import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.Animation;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IDrawable;
import org.junit.Test;

import java.text.Format;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class AnimationTest {

    //31.1
    @Test(expected=IllegalArgumentException.class)
    public void testCreateWithAnimationFramesEmpty() {
        ArrayList <Texture> animationFrames = new ArrayList<Texture>();
        Animation animation = new Animation(animationFrames, 1);
    }

    //31.2
    @Test
    public void testCreateWithAnimationFramesNotEmpty() {
        ArrayList <Texture> animationFrames = new ArrayList<Texture>();
        animationFrames.add(mock(Texture.class));
        Animation animation = new Animation(animationFrames, 1);
    }

    //31.3
    @Test(expected=IllegalArgumentException.class)
    public void testCreateWithframeTimeLessThanZero() {
        ArrayList <Texture> animationFrames = new ArrayList<Texture>();
        animationFrames.add(mock(Texture.class));
        Animation animation = new Animation(animationFrames, -1);
    }

    //31.4
    @Test
    public void testCreateWithframeTimeGreaterEqualsZero() {
        ArrayList <Texture> animationFrames = new ArrayList<Texture>();
        animationFrames.add(mock(Texture.class));
        Animation animation = new Animation(animationFrames, 1);
    }

    //32.1 und 32.2
    @Test
    public void testCreateWithframegetNextAnimationTexture() {
        ArrayList <Texture> animationFrames = new ArrayList<Texture>();
        animationFrames.add(mock(Texture.class));
        animationFrames.add(mock(Texture.class));
        Animation animation = new Animation(animationFrames, 1);
        assertEquals(animationFrames.get(0),animation.getNextAnimationTexture());
        animation.getNextAnimationTexture();
        assertEquals(animationFrames.get(1),animation.getNextAnimationTexture());
    }
}
