package de.fhbielefeld.pmdungeon.vorgaben.testutil;

import java.lang.reflect.Field;

/**
 * Helper class to manipulate objects at runtime.
 *
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class ObjectManipulator {

    /**
     * Set a new value to a final attribute.
     *
     * @param classInstance The instance object of which a field should be changed.
     * @param fieldName     The name of the field which should be change.
     * @param newValue      The new value to set.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void overrrideFinalAttribute(Object classInstance, String fieldName, Object newValue)
            throws NoSuchFieldException, IllegalAccessException {
        final Field field = classInstance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(classInstance, newValue);
    }

}
