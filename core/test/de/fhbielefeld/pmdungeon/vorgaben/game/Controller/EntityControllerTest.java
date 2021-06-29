package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;

import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class EntityControllerTest {

    EntityController entityController;
    IEntity entity1;
    IEntity entity2;

    @Before
    public void setup() {
        entityController = new EntityController();
        entity1 = new IEntity() {
            @Override
            public void update() { }

            @Override
            public boolean deleteable() {
                return false;
            }
        };
        entity2 = new IEntity() {
            @Override
            public void update() { }

            @Override
            public boolean deleteable() {
                return false;
            }
        };
    }

    // 1.1
    @Test
    public void KonstruktorTest() {
        List<IEntity> entities = entityController.getList();
        assertEquals(true, entities.isEmpty());
    }

    // 2.1
    @Test
    public void addEntityTest() {
        entityController.addEntity(entity1);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // 2.2
    @Test
    public void addEntityWithNullTest() {
        entityController.addEntity(entity1);
        entityController.addEntity(null);
        List<IEntity> entities = entityController.getList();
        assertEquals(2, entities.size()); // set expected to 1 after fix
    }

    // 2.3
    @Test
    public void addEntityTwiceTest() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity1);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
    }

    // 3.1
    @Test
    public void removeEntityTest() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeEntity(entity2);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // 3.2
    @Test
    public void removeEntityNotInsideTest() {
        entityController.addEntity(entity1);
        entityController.removeEntity(entity2);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // 3.3
    @Test
    public void removeEntityWithNullTest() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeEntity(null);
        List<IEntity> entities = entityController.getList();
        assertEquals(2, entities.size());
        assertEquals(entity1, entities.get(0));
        assertEquals(entity2, entities.get(1));
    }

    // 4.1
    @Test
    public void removeAllTest() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeAll();
        List<IEntity> entities = entityController.getList();
        assertEquals(true, entities.isEmpty());
    }

    // 5.1
    @Test
    public void removeAllFromTest() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeAllFrom(entity1.getClass());
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity2, entities.get(0));
    }

    // 5.2
    @Test
    public void removeAllFromNotInsideTest() {
        entityController.addEntity(entity1);
        entityController.removeAllFrom(entity2.getClass());
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // 5.3
    @Test(expected=NullPointerException.class)
    public void removeAllFromWithNullTest() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeAllFrom(null);
    }

}
