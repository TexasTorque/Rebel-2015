package org.texastorque.texastorque20155.input;

import org.texastorque.torquelib.util.GenericController;

public class DriverInput {

    private GenericController driver;
    private GenericController operator;

    private DriverInput() {
        driver = new GenericController(1, GenericController.TYPE_XBOX, 0.1);
        operator = new GenericController(2, GenericController.TYPE_XBOX, 0.1);
    }

    public void update() {
    }

    //singleton
    private static DriverInput instance;

    public static DriverInput getInstance() {
        if (instance == null) {
            instance = new DriverInput();
        }
        return instance;
    }
}
