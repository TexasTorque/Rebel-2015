package org.texastorque.texastorque20155.input;

public class DriverInput {

    //singleton
    private static DriverInput instance;

    public static DriverInput getInstance() {
        if (instance == null) {
            instance = new DriverInput();
        }
        return instance;
    }
}
