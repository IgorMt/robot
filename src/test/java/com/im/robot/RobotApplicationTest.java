package com.im.robot;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class RobotApplicationTest {

    @Test
    public void testCallMainMethod() {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
            System.setIn(in);
            RobotApplication.main(null);
        } catch (NoSuchElementException e) {
            assertTrue("The correct stopping should be done with EXIT command. ",
                    false);
            return;
        }
        assertTrue("Application stopped properly.", true);
    }
}
