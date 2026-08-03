package com.safetyandsecurityinterplay.functional;

import java.util.List;

import com.safetyandsecurityinterplay.component.FusionProcessor;
import com.safetyandsecurityinterplay.component.VotingSystem;
import com.safetyandsecurityinterplay.component.interfaces.IAuthenticatedSensorData;
import com.safetyandsecurityinterplay.component.interfaces.IFuseData;
import com.safetyandsecurityinterplay.component.interfaces.IVotedData;

public class SensorFusionFunction {

    //Attributes/Variables
    private VotingSystem votingSystem;
    private FusionProcessor fusionProcessor;
    private int fusionLatencyInMilliseconds;
    private boolean inputValidationRequired;

    //Constructor
    public SensorFusionFunction(VotingSystem vs, FusionProcessor fp, int f, boolean i)
    {
        votingSystem = vs;
        fusionProcessor = fp;
        fusionLatencyInMilliseconds = f;
        inputValidationRequired = i;
    }

    //Behaviour
    //OCL: SFF Constraint 1 (Security to Safety Property): self.inputValidationRequired = true implies self.fusionLatency <= 20
    public boolean satisfiesSFF1() 
    {
        return !inputValidationRequired || fusionLatencyInMilliseconds <= 20;
    }

    public IFuseData fuse(List<IAuthenticatedSensorData> authenticatedInputs) 
    {
        IVotedData voted = votingSystem.voteData(authenticatedInputs);
        return fusionProcessor.fuse(voted);
    }

    //Setters
    public void setVotingSystem(VotingSystem vs)
    {
        votingSystem = vs;
    }

    public void setFusionProcessor(FusionProcessor fp)
    {
        fusionProcessor = fp;
    }

    public void setFusionLatencyInMilliseconds(int f)
    {
        fusionLatencyInMilliseconds = f;
    }

    public void setInputValidationRequired(boolean i)
    {
        inputValidationRequired = i;
    }

    //Getters
    public VotingSystem getVotingSystem() 
    {
        return votingSystem;
    }

    public FusionProcessor getFusionProcessor() 
    {
        return fusionProcessor;
    }

    public int getFusionLatencyInMilliseconds() 
    {
        return fusionLatencyInMilliseconds;
    }

    public boolean getInputValidationRequired() 
    {
        return inputValidationRequired;
    }
}
