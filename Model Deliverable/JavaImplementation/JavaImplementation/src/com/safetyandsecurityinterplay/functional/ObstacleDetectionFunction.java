package com.safetyandsecurityinterplay.functional;

import com.safetyandsecurityinterplay.component.AuthenticationSystem;
import com.safetyandsecurityinterplay.component.Sensor;
import com.safetyandsecurityinterplay.component.interfaces.IAuthenticatedSensorData;
import com.safetyandsecurityinterplay.component.interfaces.IFuseData;
import java.util.List;
import java.util.stream.Collectors;

public class ObstacleDetectionFunction {
    //Attributes/Variables
    private List<Sensor> sensors;
    private AuthenticationSystem authenticationSystem;
    private SensorFusionFunction sensorFusionFunction;
    private double detectionAccuracy;
    private int processingDeadlineInMilliseconds;

    //Constructor
    public ObstacleDetectionFunction(List<Sensor> s, AuthenticationSystem as, SensorFusionFunction sff, double da, int p)
    {
        sensors = s;
        authenticationSystem = as;
        sensorFusionFunction = sff;
        detectionAccuracy= da;
        processingDeadlineInMilliseconds = p;   
    }

    //Behaviour
    //OCL: ODF Constraint 1 (Safety Property): self.processingDeadline <= 50 and self.detectionAccuracy >= 0.99
    public boolean satisfiesODF1() 
    {
        return processingDeadlineInMilliseconds <= 50 && detectionAccuracy >= 0.99;
    }

    //OCL: ODF Constraint 2 (Security Property): self.dataSources >= 2
    public boolean satisfiesODF2() 
    {
        return sensors.size() >= 2;
    }

    //OCL: Sensor Constraint 1, checked across every sensor feeding this function.
    public boolean allSensorsSatisfyConstraint1() 
    {
        return sensors.stream().allMatch(Sensor::satisfiesSensorConstraint1);
    }

    public IFuseData detectObstacles() 
    {
        List<IAuthenticatedSensorData> authenticatedReadings = sensors.stream()
                .map(Sensor::collectData)
                .map(authenticationSystem::authenticateData)
                .collect(Collectors.toList());
        return sensorFusionFunction.fuse(authenticatedReadings);
    }

    //THE CENTRAL RESEARCH QUESTION, IN CODE:
    //Does component-layer security (authentication + voting) compose to
    //guarantee functional-layer safety (timing deadlines + sufficient sources)?

    public boolean crossLayerCompositionHolds() {
        boolean odfOk = satisfiesODF1() && satisfiesODF2();
        boolean sffOk = sensorFusionFunction.satisfiesSFF1();
        boolean fusionOk = sensorFusionFunction.getFusionProcessor().satisfiesFusionConstraint1();
        boolean sensorsOk = allSensorsSatisfyConstraint1();
        boolean dataTrustworthy = detectObstacles().isTrustworthy();
        return odfOk && sffOk && fusionOk && sensorsOk && dataTrustworthy;
    }

    //Setters
    public void setSensors(List<Sensor> s)
    {
        sensors = s;
    }

    public void setAuthenticationSystem(AuthenticationSystem as)
    {
        authenticationSystem = as;
    }

    public void setSensorFusionFunction(SensorFusionFunction sff) 
    {
        sensorFusionFunction = sff;
    }

    public void setDetectionAccuracy(double da)
    {
        detectionAccuracy= da;
    }

    public void setProcessingTimeInMilliseconds(int p)
    {
        processingDeadlineInMilliseconds = p; 
    }        
        
    //Getters
    public List<Sensor> getSensors()
    {
        return sensors;
    }

    public AuthenticationSystem getAuthenticationSystem()
    {
        return authenticationSystem;
    }

    public SensorFusionFunction getSensorFusionFunction() 
    {
        return sensorFusionFunction;
    }

    public double getDetectionAccuracy()
    {
        return detectionAccuracy;
    }

    public int getProcessingTimeInMilliseconds()
    {
        return processingDeadlineInMilliseconds; 
    }        

    
}
