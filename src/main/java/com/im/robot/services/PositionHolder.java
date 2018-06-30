package com.im.robot.services;

/**
 * @author imaltsev
 * @since 30/06/18
 * <p>
 * This class holds the current robot position and Facing
 */
public class PositionHolder {

    private int xpos;
    private int ypos;
    private Facing face;

    public PositionHolder(int xpos, int ypos, Facing face){
        this.xpos = xpos;
        this.ypos = ypos;
        this.face = face;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public Facing getFace() {
        return face;
    }

    public void setFace(Facing face) {
        this.face = face;
    }
}
