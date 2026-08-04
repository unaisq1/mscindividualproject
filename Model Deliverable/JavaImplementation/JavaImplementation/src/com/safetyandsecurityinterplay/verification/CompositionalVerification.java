package com.safetyandsecurityinterplay.verification;

import com.safetyandsecurityinterplay.component.FusionProcessor;
import com.safetyandsecurityinterplay.constructs.AVComponentFactory;
import com.safetyandsecurityinterplay.functional.ObstacleDetectionFunction;
import com.safetyandsecurityinterplay.functional.SensorFusionFunction;
import com.safetyandsecurityinterplay.system.EmergencyBrakingSystem;

public class CompositionalVerification {
    public static void main(String[] args) {
        report("Variant A - Single sensor, Shared processor",
                AVComponentFactory.buildVariantA_SharedProcessor());

        report("Variant B - Triple redundancy, Shared processor",
                AVComponentFactory.buildVariantB_RedundantShared());

        report("Variant C - Triple redundancy, Dedicated crypto hardware",
                AVComponentFactory.buildVariantC_RedundantDedicated());
    }

    private static void report(String variantName, EmergencyBrakingSystem system) {
        ObstacleDetectionFunction odf = system.getDetectionFunction();
        SensorFusionFunction sff = odf.getSensorFusionFunction();
        FusionProcessor fusion = sff.getFusionProcessor();

        System.out.println("=== " + variantName + " ===");
        System.out.println("System Layer: EBS1 response time <=100ms:            " + system.satisfiesEBS1());
        System.out.println("System Layer: EBS2 detection range >=50m:            " + system.satisfiesEBS2());
        System.out.println("System Layer: EBS3 availability >=0.99:              " + system.satisfiesEBS3());
        System.out.println("Functional Layer: ODF1 deadline<=50 & accuracy>=.99: " + odf.satisfiesODF1());
        System.out.println("Functional Layer: ODF2 dataSources>=2:               " + odf.satisfiesODF2());
        System.out.println("Functional Layer: SFF1 validation implies <=20ms:    " + sff.satisfiesSFF1());
        System.out.println("Component Layer: FusionConstraint1 crypto+proc<=50:  " + fusion.satisfiesFusionConstraint1());
        System.out.println("Component Layer: SensorConstraint1 (all sensors):    " + odf.allSensorsSatisfyConstraint1());
        System.out.println("Overall Composition Holds:                           " + system.overallSystemCompositionHolds());
        System.out.println();
    }
}
