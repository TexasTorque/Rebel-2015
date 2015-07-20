package org.texastorque.torquelib.controlLoop;

/**
 * A PID implementation.
 *
 * @author TexasTorque
 */
public class TorquePID extends ControlLoop {

    private double kP;
    private double kI;
    private double kD;
    private double epsilon;
    private double previousValue;
    private double errorSum;
    private boolean firstCycle;
    private double maxOutput;

    /**
     * Create a new PID with all constants 0.0.
     */
    public TorquePID() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Create a new PID.
     *
     * @param p The proportionality constant.
     * @param i The integral constant.
     * @param d The derivative constant.
     */
    public TorquePID(double p, double i, double d) {
        super();
        kP = p;
        kI = i;
        kD = d;
        epsilon = 0.0;
        doneRange = 0.0;
        previousValue = 0.0;
        errorSum = 0.0;
        firstCycle = true;
        maxOutput = 1.0;
        minDoneCycles = 10;
    }

    /**
     * Change the PID constants.
     *
     * @param p The proportionality constant.
     * @param i The integral constant.
     * @param d The derivative constant.
     */
    public void setPIDGains(double p, double i, double d) {
        kP = p;
        kI = i;
        kD = d;
    }

    /**
     * Set the epsilon value.
     *
     * @param e The new epsilon value.
     */
    public void setEpsilon(double e) {
        epsilon = e;
    }

    /**
     * Set the limit of the output.
     *
     * @param max The maximum value that the value can be.
     */
    public void setMaxOutput(double max) {
        if (max < 0.0) {
            maxOutput = 0.0;
        } else if (max > 1.0) {
            maxOutput = 1.0;
        } else {
            maxOutput = max;
        }
    }

    /**
     * Reset the PID controller.
     */
    public void reset() {
        errorSum = 0.0;
        firstCycle = true;
    }

    public double getPreviousValue() {
        return previousValue;
    }

    /**
     * Calculate output based off of the current sensor value.
     *
     * @param currentValue the current sensor feedback.
     * @return Motor ouput to the system.
     */
    public double calculate(double currentValue) {
        double pVal = 0.0;
        double iVal = 0.0;
        double dVal = 0.0;

        if (firstCycle) {
            previousValue = currentValue;
            firstCycle = false;
        }

        //----- P Calculation -----
        double error = setPoint - currentValue;

        pVal = kP * error;

        //----- I Calculation -----
        if (error > epsilon) {
            if (errorSum < 0.0) {
                errorSum = 0.0;
            }
            errorSum += Math.min(error, 1.0);
        } else {
            errorSum = 0.0;
        }

        iVal = kI * errorSum;

        //----- D Calculation -----
        double deriv = currentValue - previousValue;

        dVal = kD * deriv;

        //---- Combine Calculations -----
        double output = pVal + iVal - dVal;

        //---- Limit Output -----
        if (output > maxOutput) {
            output = maxOutput;
        } else if (output < -maxOutput) {
            output = -maxOutput;
        }

        //----- Save Value -----
        previousValue = currentValue;

        return output;
    }

}
