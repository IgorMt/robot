package com.im.robot.services;

import org.junit.Test;
import org.junit.Assert;

public class FacingTest {

    @Test
    public void testRobotTurn() {

        Assert.assertEquals("Must be WEST.", Facing.NORTH.getByCommand(Commands.LEFT),
                Facing.WEST);

        Assert.assertEquals("Must be SOUTH.", Facing.WEST.getByCommand(Commands.LEFT),
                Facing.SOUTH);

        Assert.assertEquals("Must be EAST.", Facing.NORTH.getByCommand(Commands.RIGHT),
                Facing.EAST);

        Assert.assertEquals("Must be SOUTH.", Facing.EAST.getByCommand(Commands.RIGHT),
                Facing.SOUTH);
    }

}
