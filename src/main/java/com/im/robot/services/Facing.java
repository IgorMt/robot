package com.im.robot.services;

/**
 * @author imaltsev
 * @since 30/06/18
 * <p>
 * This enum holds the set of directions with the rotate by command method
 */
public enum Facing {
    NORTH(0, 1),
    EAST(1, 1),
    SOUTH(2, -1),
    WEST(3, -1);

    private int id;
    private int tend;

    private Facing(int id, int tend) {
        this.id = id;
        this.tend = tend;
    }

    /**
     * This method returns Facing after rotate by command
     *
     * @param cmd     RIGHT/LEFT command to rotate
     * @return Facing - new Facing after rotate
     */
    public Facing getByCommand(Commands cmd) {

        int nextId = this.id;
        if (Commands.RIGHT == cmd) {
            nextId = ++nextId > Facing.values().length - 1 ? 0 : nextId;
        } else if (Commands.LEFT == cmd) {
            nextId = --nextId < 0 ? Facing.values().length - 1 : nextId;
        }
        for (Facing face : Facing.values()) {
            if (face.id == nextId) {
                return face;
            }
        }
        return this;
    }

    public int getTend() {
        return tend;
    }
}
