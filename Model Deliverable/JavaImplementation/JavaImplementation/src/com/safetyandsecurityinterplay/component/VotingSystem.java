package com.safetyandsecurityinterplay.component;

import com.safetyandsecurityinterplay.component.interfaces.IAuthenticatedSensorData;
import com.safetyandsecurityinterplay.component.interfaces.IVoteData;
import com.safetyandsecurityinterplay.component.interfaces.IVotedData;
import java.util.List;

public class VotingSystem implements IVoteData{

    //Attributes/Variables
    private int minimumVotes;
    private int totalSources;

    //Constructor
    public VotingSystem(int m, int t)
    {
        minimumVotes = m;
        totalSources = t;
    }

    //Behaviour
    @Override
    public IVotedData voteData(List<IAuthenticatedSensorData> inputs) 
    {
        long validCount = inputs.stream().count();
        boolean sufficient = validCount >= minimumVotes;
        double[] representative = inputs.isEmpty() ? new double[]{ 0.0 } : inputs.get(0).getValues();
        return new VotedData(representative, sufficient, (int) validCount);
    }

    private static final class VotedData implements IVotedData 
    {
        private final double[] representativeValues;
        private final boolean sufficientSources;
        private final int validSourceCount;

        VotedData(double[] representativeValues, boolean sufficientSources, int validSourceCount) 
        {
            this.representativeValues = representativeValues;
            this.sufficientSources = sufficientSources;
            this.validSourceCount = validSourceCount;
        }

        @Override
        public double[] getRepresentativeValues() 
        {
            return representativeValues;
        }

        @Override
        public boolean hasSufficientValidSources() 
        {
            return sufficientSources;
        }

        @Override
        public int getValidSourceCount() 
        {
            return validSourceCount;
        }
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
