package com.safetyandsecurityinterplay.component;

public class Sensor {

    public enum SensorTypes
    {
        Camera,
        LiDAR,
        Radar
    }

    private String sensorName;
    private String sensorType;
    private int range;
    private int latencyInMilliseconds;
    private boolean encryptionEnabled;

    public Sensor (String sn, String st, int r, int l, boolean ee)
    {
        sensorName = sn;
        
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

    public void setSensorName(String sn)
    {
        sensorName = sn;
    }

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

    public String getSensorName()
    {
        return sensorName;
    }

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
