package com.safetyandsecurityinterplay.component;

public class FusionProcessor {

    //Attributes/Variables
    private String processorType;
    private int cpuAllocation;
    private int cryptoOverhead;
    private int processingTimeInMilliseconds;

    //Constructor
    public FusionProcessor(String pt, int ca, int co, int p)
    {
        processorType = pt;
        cpuAllocation = ca;
        cryptoOverhead = co;
        processingTimeInMilliseconds = p;
    }

    //Setters
    public void setProcessorType(String pt)
    {
        processorType = pt;
    }

    public void setCPUAllocation(int ca)
    {
        cpuAllocation = ca;
    }

    public void setCryptoOverhead(int co)
    {
        cryptoOverhead = co;
    }

    public void setProcessingTimeInMilliseconds(int p)
    {
        processingTimeInMilliseconds = p;
    }

    //Getters
    public String getProcessorType()
    {
        return processorType;
    }

    public int getCPUAllocation()
    {
        return cpuAllocation;
    }

    public int getCryptoOverhead()
    {
        return cryptoOverhead;
    }

    public int getProcessingTimeInMilliseconds()
    {
        return processingTimeInMilliseconds;
    }
}
