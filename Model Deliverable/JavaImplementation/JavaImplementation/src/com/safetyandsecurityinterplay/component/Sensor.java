package com.safetyandsecurityinterplay.component;

import com.safetyandsecurityinterplay.component.interfaces.IGatherData;
import com.safetyandsecurityinterplay.component.interfaces.IRawSensorData;

public class Sensor implements IGatherData{

    //Fixed Sensor Types
    public enum SensorTypes
    {
        Camera,
        LiDAR,
        Radar
    }

    //Attributes/Variables
    private String sensorType;
    private int range;
    private int latencyInMilliseconds;
    private boolean encryptionEnabled;
    private static int counter;

    //Constructor
    public Sensor(String st, int r, int l, boolean ee)
    {
        //Ensures only the recognised sensor types can be applied
        for (SensorTypes s : SensorTypes.values()) 
        {
            if (st.equals(s.toString()))
            {
                sensorType = st;
            }
            else
            {
                sensorType = "Camera";
            }
        }

        range = r;
        latencyInMilliseconds = l;
        encryptionEnabled = ee;

        counter++; //Increments by 1 each time an object is instantiated
    }

    //Behaviour
    
    //OCL: Sensor Constraint 1 (Security Property): self.encryptionEnabled = true and self.latency <= 30
    public boolean satisfiesSensorConstraint1() 
    {
        return encryptionEnabled && latencyInMilliseconds <= 30;
        //Checks if a sensor has encryption capabilities enabled and if latency is equal or lower than 30 milliseconds
    }

    @Override
    public IRawSensorData collectData() 
    {
        double[] simulatedReading = { 42.0 }; //A placeholder for real sensor-derived data
        return new RawSensorData(simulatedReading, System.currentTimeMillis());
    }

    private static final class RawSensorData implements IRawSensorData 
    {
        private final double[] values;
        private final long timestamp;

        RawSensorData(double[] values, long timestamp) 
        {
            this.values = values;
            this.timestamp = timestamp;
        }

        @Override
        public double[] getRawValues() 
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
    public void setSensorType(String st)
    {
        for (SensorTypes s : SensorTypes.values()) 
        {
            if (st.equalsIgnoreCase(s.toString()))
            {
                sensorType = st;
            }
            else
            {
                sensorType = "Camera";
            }
        }
    }

    public void setRange(int r)
    {
        range = r;
    }

    public void setLatency(int l)
    {
        latencyInMilliseconds = l;
    }

    public void setEncryption(boolean ee)
    {
        encryptionEnabled = ee;
    }

    //Getters
    public String getSensorType()
    {
        return sensorType;
    }

    public int getRange()
    {
        return range;
    }

    public int getLatency()
    {
        return latencyInMilliseconds;
    }

    public boolean getEncryption()
    {
        return encryptionEnabled;
    }

    public static int getNumberOfInstances()
    {
        return counter; //Used to return the amount of Sensor objects instantiated, which is then used by Voting System's "totalSources" attribute
        //Source Code: Solution of this Forum Post - https://stackoverflow.com/questions/28947571/how-to-count-number-of-instances-of-a-class
    }

}
