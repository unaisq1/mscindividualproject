package com.safetyandsecurityinterplay.constructs;

import com.safetyandsecurityinterplay.component.AuthenticationSystem;
import com.safetyandsecurityinterplay.component.FusionProcessor;
import com.safetyandsecurityinterplay.component.Sensor;
import com.safetyandsecurityinterplay.component.VotingSystem;
import com.safetyandsecurityinterplay.functional.ObstacleDetectionFunction;
import com.safetyandsecurityinterplay.functional.SensorFusionFunction;
import com.safetyandsecurityinterplay.system.EmergencyBrakingSystem;
import java.util.List;

public class AVComponentFactory {
    //Objects
    private static final Sensor Camera1 = new Sensor("Camera", 100, 30, true);

    //Variant A: single sensor, authentication shares the fusion processor.
    public static EmergencyBrakingSystem buildVariantA_SharedProcessor() {
        List<Sensor> sensors = List.of(Camera1);
        AuthenticationSystem auth = new AuthenticationSystem("HMAC-SHA256", 15, 256);
        VotingSystem voting = new VotingSystem(1, Sensor.getNumberOfInstances()); //1 total source
        FusionProcessor fusion = new FusionProcessor("Shared", 100, 15, 40);
        SensorFusionFunction sff = new SensorFusionFunction(voting, fusion, 40, true);
        ObstacleDetectionFunction detection = new ObstacleDetectionFunction(sensors, auth, sff, 0.995, 55);
        return new EmergencyBrakingSystem(100, 100, 0.995, detection);
    }

    private static final Sensor LiDAR1 = new Sensor("LiDAR", 100, 25, true);
    private static final Sensor Radar1 = new Sensor("Radar", 150, 20, true);

    //Variant B: redundancy applied with three sensors, AuthenticationSystem shares the fusion processor.
    public static EmergencyBrakingSystem buildVariantB_RedundantShared() {
        List<Sensor> sensors = List.of(Camera1, LiDAR1, Radar1);
        AuthenticationSystem auth = new AuthenticationSystem("HMAC-SHA256", 15, 256);
        VotingSystem voting = new VotingSystem(2, Sensor.getNumberOfInstances());
        FusionProcessor fusion = new FusionProcessor("Shared", 100, 45, 40);
        SensorFusionFunction sff = new SensorFusionFunction(voting, fusion, 40, true);
        ObstacleDetectionFunction detection = new ObstacleDetectionFunction(sensors, auth, sff, 0.995, 85);
        return new EmergencyBrakingSystem(100, 100, 0.999, detection);
    }

    //Variant C: triple redundant, three sensors, with DEDICATED crypto hardware.
    public static EmergencyBrakingSystem buildVariantC_RedundantDedicated() {
        List<Sensor> sensors = List.of(Camera1, LiDAR1, Radar1);
        AuthenticationSystem auth = new AuthenticationSystem("HMAC-SHA256", 15, 256);
        VotingSystem voting = new VotingSystem(2, Sensor.getNumberOfInstances());
        FusionProcessor fusion = new FusionProcessor("Dedicated", 60, 0, 40);
        SensorFusionFunction sff = new SensorFusionFunction(voting, fusion, 40, true);
        ObstacleDetectionFunction detection = new ObstacleDetectionFunction(sensors, auth, sff, 0.995, 40);
        return new EmergencyBrakingSystem(100, 100, 0.999, detection);
    }
}
