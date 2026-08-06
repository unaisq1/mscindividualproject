package com.safetyandsecurityinterplay.component;

import com.safetyandsecurityinterplay.component.interfaces.IAuthenticateData;
import com.safetyandsecurityinterplay.component.interfaces.IAuthenticatedSensorData;
import com.safetyandsecurityinterplay.component.interfaces.IRawSensorData;

public class AuthenticationSystem implements IAuthenticateData{

    //Attributes/Variables
    private String algorithm;
    private int processingTimeInMilliseconds;
    private int keyLength;
    
    //Constructor
    public AuthenticationSystem(String a, int p, int k)
    {
        algorithm = a;
        processingTimeInMilliseconds = p;
        keyLength = k;
    }

    //Behaviour
    @Override
    public IAuthenticatedSensorData authenticateData(IRawSensorData raw) 
    {
        return new AuthenticatedData(raw.getRawValues(), raw.getTimestampInMilliseconds());
    }

    private static final class AuthenticatedData implements IAuthenticatedSensorData //Provides Authenticated Data
    {
        private final double[] values;
        private final long timestamp;

        AuthenticatedData(double[] values, long timestamp) 
        {
            this.values = values;
            this.timestamp = timestamp;
        }

        @Override
        public double[] getValues() 
        {
            return values;
        }

        @Override
        public long getTimestampInMilliseconds() 
        {
            return timestamp;
        }
    }

    //Setters
    public void setAlgorithm(String a)
    {
        algorithm = a;
    }

    public void setProcessingTimeInMilliseconds(int p)
    {
        processingTimeInMilliseconds = p;
    }

    public void setKeyLength(int k)
    {
        keyLength = k;
    }

    //Getters
    public String getAlgorithm()
    {
        return algorithm;
    }

    public int getProcessingTimeInMilliseconds()
    {
        return processingTimeInMilliseconds;
    }

    public int getKeyLength()
    {
        return keyLength;
    }

}
