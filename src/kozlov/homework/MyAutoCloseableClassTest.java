package kozlov.homework;

import static org.junit.Assert.assertEquals;

public class MyAutoCloseableClassTest {

    @org.junit.Test
    public void Testing_close_method_of_Autocloseable_interface_implementation() {

        TriggerForTest closeTrigger = new TriggerForTest();

        assertEquals("Checking close flag before try-catch", false, closeTrigger.isClosed);

        try(MyAutoCloseableClass autoCloseableResource = new MyAutoCloseableClass(closeTrigger)) {

            System.out.println("\nNow all of a sudden an exception is being thrown " +
                    "\nand we'll find it out if the close() method works in such a case:");

            throw new RuntimeException("SURPRISE!!! Runtime exception!");

        } catch (Exception e) {

            e.printStackTrace();

        }

        assertEquals("Checking close flag after try-catch", true, closeTrigger.isClosed);

    }

}