package org.texastorque.texastorque20155.input;

import org.texastorque.torquelib.util.GenericController;

public class HumanInput {

    private GenericController driver;
    private GenericController operator;

    private double D_rightXAxis;
    private double D_leftYAxis;

    private double O_rightXAxis;
    private double O_rightYAxis;

    private HumanInput() {
        driver = new GenericController(1, 0.1);
        operator = new GenericController(2, 0.1);
        D_rightXAxis = 0.0;
        D_leftYAxis = 0.0;

        O_rightXAxis = 0.0;
        O_rightYAxis = 0.0;
    }

    public void update() {
        D_rightXAxis = driver.getRightXAxis();
        D_leftYAxis = driver.getLeftYAxis();

        O_rightXAxis = operator.getRightXAxis();
        O_rightYAxis = operator.getRightYAxis();
    }

    public double getDriveLeftSpeed() {
        return -D_leftYAxis + D_rightXAxis;
    }

    public double getDriveRightSpeed() {
        return -D_leftYAxis - D_rightXAxis;
    }

    public double getIntakeLeftSpeed() {
        return O_rightYAxis - O_rightXAxis;
    }

    public double getIntakeRightSpeed() {
        return O_rightYAxis + O_rightXAxis;
    }

    //singleton
    private static HumanInput instance;

    public static HumanInput getInstance() {
        if (instance == null) {
            instance = new HumanInput();
        }
        return instance;
    }
}
