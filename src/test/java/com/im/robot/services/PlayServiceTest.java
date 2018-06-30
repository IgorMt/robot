package com.im.robot.services;

import org.junit.Assert;
import org.junit.Test;

public class PlayServiceTest {

    @Test
    public void testValidationOfCommands() {
        PlayService ps = new PlayService(4, 4);
        Assert.assertEquals("Check empty command.", ps.runCommand(""),
                "Your command is empty. Enter a valid command.>");
        Assert.assertEquals("Check blank command.", ps.runCommand(" "),
                "Your command is blank. Enter a valid command.");
        Assert.assertEquals("Check wrong command.", ps.runCommand("wrong"),
                "Your command 'wrong' is not valid. Enter a valid command.");
        Assert.assertEquals("Check first non-PLACE command.", ps.runCommand("MOVE"),
                "Command is ignored. Enter the valid PLACE command, e.g.: >PLACE 1,2,EAST ");
        Assert.assertEquals("Check first PLACE command with invalid params.",
                ps.runCommand("PLACE 123"),
                "Not enough parameters in the PLACE command. Enter a valid command, e.g. " +
                        ">PLACE 1,2,EAST");
        Assert.assertEquals("Check first PLACE command with out of board position.",
                ps.runCommand("PLACE 7,7,WEST"),
                "The X and/or Y parameters are out of the range, X and Y should not be less " +
                        "than zero, X not more than 4 and Y not more than 4");
        Assert.assertEquals("Check first PLACE command with wrong facing.",
                ps.runCommand("PLACE 1,2,NORTH-WEST"),
                "Incorrect Face direction parameter in the PLACE command. " +
                        "Enter a valid command, e.g. >PLACE 1,2,EAST");
    }

    @Test
    public void testRunPlaceCommand() {
        PlayService ps = new PlayService(4, 4);
        Assert.assertEquals("Check first PLACE command.", ps.runCommand("PLACE 1,2,EAST"),
                "");
        Assert.assertEquals("Check REPORT after first PLACE command.",
                ps.runCommand("REPORT"),
                "Output: 1,2,EAST");
    }

    @Test
    public void testMoveTurnCommands() {
        PlayService ps = new PlayService(4, 4);
        ps.runCommand("PLACE 0,0,NORTH");
        ps.runCommand("MOVE");
        ps.runCommand("MOVE");
        Assert.assertEquals("Check REPORT double MOVE command.",
                ps.runCommand("REPORT"),
                "Output: 0,2,NORTH");

        ps.runCommand("RIGHT");
        ps.runCommand("MOVE");
        ps.runCommand("LEFT");
        ps.runCommand("MOVE");
        Assert.assertEquals("Check REPORT double MOVE command.",
                ps.runCommand("REPORT"),
                "Output: 1,3,NORTH");
    }

    @Test
    public void testOutOfBoardMoveCommands() {
        PlayService ps = new PlayService(4, 4);
        ps.runCommand("PLACE 0,0,SOUTH");
        ps.runCommand("MOVE");
        Assert.assertEquals("Check REPORT with out  of board MOVE command.",
                ps.runCommand("REPORT"),
                "Output: 0,0,SOUTH");
        ps.runCommand("RIGHT");
        ps.runCommand("MOVE");
        Assert.assertEquals("Check REPORT with out of board MOVE after turn.",
                ps.runCommand("REPORT"),
                "Output: 0,0,WEST");
    }
}
