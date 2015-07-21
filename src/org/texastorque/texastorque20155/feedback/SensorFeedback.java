package org.texastorque.texastorque20155.feedback;

public class SensorFeedback {

    //singleton
    private static SensorFeedback instance;

    public static SensorFeedback getInstance() {
        if (instance == null) {
            instance = new SensorFeedback();
        }
        return instance;
    }
}
