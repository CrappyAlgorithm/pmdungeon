package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;

import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class EntityControllerTest {

    EntityController entityController;
    IEntity entity1;
    IEntity entity2;

    @Before
    public void setup() {
        entityController = new EntityController();
        entity1 = new IEntity() {
            @Override
            public void update() {
            }

            @Override
            public boolean deleteable() {
                return false;
            }
        };
        entity2 = new IEntity() {
            @Override
            public void update() {
            }

            @Override
            public boolean deleteable() {
                return true;
            }
        };
    }

    // ID 1.1
    @Test
    public void TestConstruktor() {
        List<IEntity> entities = entityController.getList();
        assertEquals(true, entities.isEmpty());
    }

    // ID 2.1
    @Test
    public void TestAddEntity() {
        entityController.addEntity(entity1);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // ID 2.2
    @Test
    public void TestAddEntityWithNull() {
        entityController.addEntity(entity1);
        entityController.addEntity(null);
        List<IEntity> entities = entityController.getList();
        assertEquals(2, entities.size()); // set expected to 1 after fix
    }

    // ID 2.3
    @Test
    public void TestAddEntityTwice() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity1);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
    }

    // ID 3.1
    @Test
    public void TestRemoveEntity() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeEntity(entity2);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // ID 3.2
    @Test
    public void TestRemoveEntityNotInside() {
        entityController.addEntity(entity1);
        entityController.removeEntity(entity2);
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // ID 3.3
    @Test
    public void TestRemoveEntityWithNull() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeEntity(null);
        List<IEntity> entities = entityController.getList();
        assertEquals(2, entities.size());
        assertEquals(entity1, entities.get(0));
        assertEquals(entity2, entities.get(1));
    }

    // ID 4.1
    @Test
    public void TestRemoveAll() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeAll();
        List<IEntity> entities = entityController.getList();
        assertEquals(true, entities.isEmpty());
    }

    // ID 5.1
    @Test
    public void TestRemoveAllFrom() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeAllFrom(entity1.getClass());
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity2, entities.get(0));
    }

    // ID 5.2
    @Test
    public void TestRemoveAllFromNotInside() {
        entityController.addEntity(entity1);
        entityController.removeAllFrom(entity2.getClass());
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }

    // ID 5.3
    @Test(expected = NullPointerException.class)
    public void TestRemoveAllFromWithNull() {
        entityController.addEntity(entity1);
        entityController.addEntity(entity2);
        entityController.removeAllFrom(null);
    }

    // ID 6.1
    @Test
    public void testUpdate() {
        entityController.addEntity(entity2);
        entityController.addEntity(entity1);
        entityController.update();
        List<IEntity> entities = entityController.getList();
        assertEquals(1, entities.size());
        assertEquals(entity1, entities.get(0));
    }
}
