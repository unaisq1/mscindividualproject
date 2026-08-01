package com.safetyandsecurityinterplay.component;

public class VotingSystem {

    //Attributes/Variables
    private int minimumVotes;
    private int totalSources;

    //Constructor
    public VotingSystem(int m, int t)
    {
        minimumVotes = m;
        totalSources = t;
    }

    //Setters
    public void setMinimumVotes(int m)
    {
        minimumVotes = m;
    }

    public void setTotalSources(int m)
    {
        minimumVotes = m;
    }

    //Getters
    public int getMinimumVotes()
    {
        return minimumVotes;
    }

    public int getTotalSources()
    {
        return totalSources;
    }

}
