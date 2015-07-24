package org.texastorque.texastorque20155.constants;

import org.texastorque.torquelib.util.Parameters;

public class Constants extends Parameters {

    public static final Constant TUNED_VOLTAGE = new Constant("TUNED_VOLTAGE", 12.6);

    //drivebase
    public static final Constant D_MAX_SPEED = new Constant("D_MAX_SPEED", 1.0);

    public static final Constant D_MAX_VELOCITY = new Constant("D_MAX_VELOCITY", 10.0);//ft/s
    public static final Constant D_MAX_ACCELERATION = new Constant("D_MAX_ACCELERATION", 5.0);

    public static final Constant D_LEFT_PV_P = new Constant("E_LEFT_PV_P", 0.0);
    public static final Constant D_LEFT_PV_V = new Constant("E_LEFT_PV_V", 0.0);
    public static final Constant D_LEFT_PV_ffP = new Constant("E_LEFT_PV_ffP", 0.0);
    public static final Constant D_LEFT_PV_ffV = new Constant("E_LEFT_PV_ffV", 0.0);

    public static final Constant D_RIGHT_PV_P = new Constant("E_RIGHT_PV_P", 0.0);
    public static final Constant D_RIGHT_PV_V = new Constant("E_RIGHT_PV_V", 0.0);
    public static final Constant D_RIGHT_PV_ffP = new Constant("E_RIGHT_PV_ffP", 0.0);
    public static final Constant D_RIGHT_PV_ffV = new Constant("E_RIGHT_PV_ffV", 0.0);

    //intake
    public static final Constant I_MAX_SPEED = new Constant("I_MAX_SPEED", 1.0);

    //elevator
    public static final Constant E_MAX_SPEED = new Constant("E_MAX_SPEED", 1.0);

    public static final Constant E_MAX_VELOCITY = new Constant("E_MAX_VELOCITY", 10.0);//ft/s
    public static final Constant E_MAX_ACCELERATION = new Constant("E_MAX_ACCELERATION", 5.0);

    public static final Constant E_PV_P = new Constant("E_PV_P", 0.0);
    public static final Constant E_PV_V = new Constant("E_PV_V", 0.0);
    public static final Constant E_PV_ffP = new Constant("E_PV_ffP", 0.0);
    public static final Constant E_PV_ffV = new Constant("E_PV_ffV", 0.0);

    public static final Constant E_UP_POSITION = new Constant("E_UP_POSITION", 0.0);//ft
    public static final Constant E_DOWN_POSITION = new Constant("E_DOWN_POSITION", 0.0);

    static {
        load();
    }
}
