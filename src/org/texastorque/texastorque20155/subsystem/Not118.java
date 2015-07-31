/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.texastorque.texastorque20155.subsystem;

/**
 *
 * @author Texas Torque
 */
public class Not118 extends Subsystem {
//this is not 118 at all -Crispy
    private boolean down;
    
    @Override
    public void pushToDashboard() {
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        down = input.isTailDown();
        
        output();
    }

    @Override
    protected void output() {
        output.setCanRake(down);
    }
    
}
