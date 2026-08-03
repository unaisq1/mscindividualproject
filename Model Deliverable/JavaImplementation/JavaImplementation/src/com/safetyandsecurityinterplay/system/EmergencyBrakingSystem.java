package com.safetyandsecurityinterplay.system;

import com.safetyandsecurityinterplay.functional.ObstacleDetectionFunction;

public class EmergencyBrakingSystem {
    //Attributes/Variables
    private int brakeResponseTimeInMilliseconds;
    private int obstacleDetectionRangeInMetres;
    private double systemAvailability;
    private ObstacleDetectionFunction detectionFunction;

    //Constructor
    public EmergencyBrakingSystem(int b, int o, double sa, ObstacleDetectionFunction df)
    {
        brakeResponseTimeInMilliseconds = b;
        obstacleDetectionRangeInMetres = o;
        systemAvailability = sa;
        detectionFunction = df;
    }

    //Behaviour
    //OCL: EBS Constraint 1 (Safety Property): self.brakeResponseTime <= 100
    public boolean satisfiesEBS1() 
    {
        return brakeResponseTimeInMilliseconds <= 100;
    }

    //OCL: EBS Constraint 2 (Safety Property): self.obstacleDetectionRange >= 50
    public boolean satisfiesEBS2() {
        return obstacleDetectionRangeInMetres >= 50;
    }

    // OCL: EBS Constraint 3 (Safety Property): self.systemAvailability >= 0.99
    public boolean satisfiesEBS3() {
        return systemAvailability >= 0.99;
    }

    public boolean overallSystemCompositionHolds() {
        return satisfiesEBS1()
                && satisfiesEBS2()
                && satisfiesEBS3()
                && detectionFunction.crossLayerCompositionHolds();
    }

    //Setters

    //Getters
    
}
