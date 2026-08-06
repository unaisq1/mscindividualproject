package com.safetyandsecurityinterplay.component;

import com.safetyandsecurityinterplay.component.interfaces.IFuseData;
import com.safetyandsecurityinterplay.component.interfaces.IVotedData;

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

    //Behaviour
    //OCL: Fusion Constraint 1: self.cryptoOverhead + self.processingTime <= 50
    public boolean satisfiesFusionConstraint1() 
    {
        return getTotalLatencyMs() <= 50;
    }

    public int getTotalLatencyMs() 
    {
        return cryptoOverhead + processingTimeInMilliseconds;
    }

    public IFuseData fuse(IVotedData voted) //Used to fuse Voted Data
    {
        return new FusedData(voted.getRepresentativeValues(), voted.hasSufficientValidSources());
    }

    private static final class FusedData implements IFuseData //Provides Fused Data to be used
    {
        private final double[] fusedPosition;
        private final boolean trustworthy;

        FusedData(double[] fusedPosition, boolean trustworthy) 
        {
            this.fusedPosition = fusedPosition;
            this.trustworthy = trustworthy;
        }

        @Override
        public double[] getFusedPosition() 
        {
            return fusedPosition;
        }

        @Override
        public boolean isTrustworthy() 
        {
            return trustworthy;
        }
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
