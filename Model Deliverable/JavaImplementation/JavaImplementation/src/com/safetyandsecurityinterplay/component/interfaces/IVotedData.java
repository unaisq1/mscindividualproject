package com.safetyandsecurityinterplay.component.interfaces;

public interface IVotedData {
    double[] getRepresentativeValues();
    boolean hasSufficientValidSources();
    int getValidSourceCount();
    
}
