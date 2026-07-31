package com.safetyandsecurityinterplay.component;

public class AuthenticationSystem {

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
