package com.im.robot.services;

import java.util.Optional;

/**
 * @author imaltsev
 * @since 30/06/18
 * <p>
 * This service class holds the logic of robot's actions based on valid commands
 */
public class PlayService {

    private final int xMax;
    private final int yMax;
    private Optional<PositionHolder> ps = Optional.empty();

    public PlayService(int x, int y) {
        this.xMax = x;
        this.yMax = y;
    }

    /**
     * This method checks and executes validated commands for the robot activity
     *
     * @param command     PLACE/MOVE/RIGHT/LEFT/REPORT commands
     * @return String - the result of validating or executing of commands
     */
    public String runCommand(String command) {
        if (command == null || "".equals(command)) {
            return "Your command is empty. Enter a valid command.>";
        }

        String[] cmdAndParams = command.split(" ");
        if (cmdAndParams.length == 0) {
            return "Your command is blank. Enter a valid command.";
        }

        Commands cmdValued;
        try {
            cmdValued = Commands.valueOf(cmdAndParams[0]);
        } catch (IllegalArgumentException e) {
            return "Your command '" + cmdAndParams[0] + "' is not valid. Enter a valid command.";
        }

        switch (cmdValued) {
            case PLACE:
                return place(cmdAndParams);
            case MOVE:
                if (!ps.isPresent()) {
                    break;
                }
                move(ps.get());
                break;
            case LEFT:
                if (!ps.isPresent()) {
                    break;
                }
                ps.get().setFace(ps.get().getFace().getByCommand(Commands.LEFT));
                break;
            case RIGHT:
                if (!ps.isPresent()) {
                    break;
                }
                ps.get().setFace(ps.get().getFace().getByCommand(Commands.RIGHT));
                break;
            case REPORT:
                if (!ps.isPresent()) {
                    break;
                }
                return "Output: " + ps.get().getXpos() + "," + ps.get().getYpos() + "," +
                        ps.get().getFace().name();
        }

        if (ps.isPresent()) {
            return "";
        } else {
            return "Command is ignored. Enter the valid PLACE command, e.g.: >PLACE 1,2,EAST ";
        }
    }

    private void move(PositionHolder psm) {
        if (Facing.WEST == psm.getFace() || Facing.EAST == psm.getFace()) {
            int xNext = psm.getXpos() + psm.getFace().getTend();
            if (xNext < 0 || xNext > xMax) {
                return;
            }
            psm.setXpos(xNext);
        } else if (Facing.NORTH == psm.getFace() || Facing.SOUTH == psm.getFace()) {
            int yNext = psm.getYpos() + psm.getFace().getTend();
            if (yNext < 0 || yNext > yMax) {
                return;
            }
            psm.setYpos(yNext);
        }
    }

    private String place(String[] cmdAndParams) {
        if (cmdAndParams.length < 2) {
            return "Parameters missing in the PLACE command. Enter a valid command, e.g. " +
                    ">PLACE 1,2,EAST";
        }
        String[] prm = cmdAndParams[1].split(",");
        if (prm.length < 3) {
            return "Not enough parameters in the PLACE command. Enter a valid command, e.g. " +
                    ">PLACE 1,2,EAST";
        }
        int xInit;
        int yInit;
        Facing faceInit;
        try {
            xInit = Integer.valueOf(prm[0]);
            yInit = Integer.parseInt(prm[1]);
        } catch (NumberFormatException e) {
            return "Incorrect X or Y parameters in the PLACE command. Enter a valid command, e.g." +
                    " >PLACE 1,2,EAST";
        }
        if (xInit < 0 || xInit > xMax || yInit < 0 || yInit > yMax) {
            return "The X and/or Y parameters are out of the range, X and Y should not be" +
                     " less than zero, X not more than " + xMax + " and Y not more than " + yMax;
        }

        try {
            faceInit = Facing.valueOf(prm[2]);
        } catch (IllegalArgumentException e) {
            return "Incorrect Face direction parameter in the PLACE command. " +
                    "Enter a valid command, e.g. >PLACE 1,2,EAST";
        }
        if (!ps.isPresent()) {
            ps = Optional.of(new PositionHolder(xInit, yInit, faceInit));
        } else {
            ps.get().setXpos(xInit);
            ps.get().setYpos(yInit);
            ps.get().setFace(faceInit);
        }
        return "";
    }
}
