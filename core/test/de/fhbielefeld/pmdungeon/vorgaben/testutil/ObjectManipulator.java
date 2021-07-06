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
        Field field;
        try {
            field = classInstance.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = searchFieldInSuperClassRek(classInstance.getClass(), fieldName);
        }
        field.setAccessible(true);
        field.set(classInstance, newValue);
    }

    /**
     * Search an attribute in all super classes.
     *
     * @param clazz     actual class to search the field in.
     * @param fieldName The name of the field which should be change.
     * @return The searched field.
     * @throws NoSuchFieldException Will be thrown if the field can not be found in class or super classes.
     */
    private static Field searchFieldInSuperClassRek(Class clazz, String fieldName) throws NoSuchFieldException {
        Field field;
        try {
            field = clazz.getSuperclass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = searchFieldInSuperClassRek(clazz.getSuperclass(), fieldName);
        }
        return field;
    }
}
