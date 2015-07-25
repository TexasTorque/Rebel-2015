package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.auto.AutoMode;

public class DoNothingAuto extends AutoMode {

    @Override
    public void run() {
    }

    //singleton
    private static DoNothingAuto instance;

    public static DoNothingAuto getInstance() {
        if (instance == null) {
            instance = new DoNothingAuto();
        }
        return instance;
    }
}
