package com.safetyandsecurityinterplay.component;

public class Sensor {

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

    public boolean  getEncryption()
    {
        return encryptionEnabled;
    }

}
