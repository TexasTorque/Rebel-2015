package org.texastorque.texastorque20155.constants;

import org.texastorque.torquelib.util.Parameters;

public class Constants extends Parameters {

    public static final Constant TUNED_VOLTAGE = new Constant("TUNED_VOLTAGE", 12.2);

    public static final Constant XBOX_X_DRIVE_MULTIPLIER = new Constant("XBOX_X_DRIVE_MULTIPLIER", 0.6);
    public static final Constant XBOX_Y_DRIVE_MULTIPLIER = new Constant("XBOX_Y_DRIVE_MULTIPLIER", 0.6);

    //drivebase
    public static final Constant AUTO_CAN_GRAB_INCHES = new Constant("D_AUTO_CAN_GRAB_INCHES", 0.0);
    
    public static final Constant D_MAX_SPEED = new Constant("D_MAX_SPEED", 1.0);

    public static final Constant D_MAX_VELOCITY = new Constant("D_MAX_VELOCITY", 10.0);
    public static final Constant D_MAX_ACCELERATION = new Constant("D_MAX_ACCELERATION", 5.0);

    public static final Constant D_LEFT_PV_P = new Constant("D_LEFT_PV_P", 0.0);
    public static final Constant D_LEFT_PV_V = new Constant("D_LEFT_PV_V", 0.0);
    public static final Constant D_LEFT_PV_ffP = new Constant("D_LEFT_PV_ffP", 0.0);
    public static final Constant D_LEFT_PV_ffV = new Constant("D_LEFT_PV_ffV", 0.0);

    public static final Constant D_RIGHT_PV_P = new Constant("D_RIGHT_PV_P", 0.0);
    public static final Constant D_RIGHT_PV_V = new Constant("D_RIGHT_PV_V", 0.0);
    public static final Constant D_RIGHT_PV_ffP = new Constant("D_RIGHT_PV_ffP", 0.0);
    public static final Constant D_RIGHT_PV_ffV = new Constant("D_RIGHT_PV_ffV", 0.0);

    //intake
    public static final Constant I_MAX_SPEED = new Constant("I_MAX_SPEED", 1.0);

    //elevator
    public static final Constant E_MAX_SPEED = new Constant("E_MAX_SPEED", 1.0);

    public static final Constant E_MAX_VELOCITY = new Constant("E_MAX_VELOCITY", 10.0);
    public static final Constant E_MAX_ACCELERATION = new Constant("E_MAX_ACCELERATION", 5.0);

    public static final Constant E_PV_P = new Constant("E_PV_P", 0.0);
    public static final Constant E_PV_V = new Constant("E_PV_V", 0.0);
    public static final Constant E_PV_ffV = new Constant("E_PV_ffV", 0.0);
    public static final Constant E_PV_ffA = new Constant("E_PV_ffA", 0.0);

    public static final Constant E_UP_POSITION = new Constant("E_UP_POSITION", 0.0);
    public static final Constant E_DOWN_POSITION = new Constant("E_DOWN_POSITION", 0.0);
    public static final Constant E_SIX_POSITION = new Constant("E_SIX_POSITION", 0.0);

    static {
        load();
    }
}
