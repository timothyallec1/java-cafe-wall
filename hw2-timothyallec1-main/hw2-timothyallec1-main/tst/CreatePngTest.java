import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;


/**
 * With the way that DrawingPanel.java is setup, this class is necessary to generate the various different
 * MORTAR size png files if a student removes "panel.save()" from the main method in CafeWall.java
 **/
public class CreatePngTest {
    @Test
    public void generateMortarSize1() throws Exception {
        generateOutput(1);
    }

    //tests mortar2
    @Test
    public void generateMortarSize2() throws Exception {
        generateOutput(2);
    }

    private void generateOutput(int mortarSize) throws Exception {
        setStaticField(CafeWall.class, "MORTAR", mortarSize);
        System.setProperty(DrawingPanel.SAVE_PROPERTY, String.format("output%s.png", mortarSize));
        CafeWall.main(null); //should create an output.png with the correct size
    }

    private static void setStaticField(Class<?> clazz, String fieldName, Object value) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Assertions.fail(String.format("There is not a field named: %s", fieldName));
        }
        field.setAccessible(true);

        try {
            field.set(null, value);
        } catch (IllegalAccessException e) {
            Assertions.fail(String.format("Could not set value of field: %s %s Make sure the field does not have " +
                    "the FINAL modifier", fieldName, System.lineSeparator()));
        }
    }

}
