package com.safetyandsecurityinterplay.component.interfaces;

public interface IAuthenticatedSensorData {
    double[] getValues();
    boolean isAuthentic();
    long getTimestampInMilliseconds();

}
