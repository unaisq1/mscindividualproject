package com.safetyandsecurityinterplay.component.interfaces;

public interface IAuthenticatedSensorData {
    double[] getValues();
    long getTimestampInMilliseconds();
}
