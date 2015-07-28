package org.texastorque.texastorque20155.auto.commands;

public abstract class AutoCommand {

    private final String name;

    public AutoCommand(String name_) {
        name = name_;
    }

    public abstract void run();
}
