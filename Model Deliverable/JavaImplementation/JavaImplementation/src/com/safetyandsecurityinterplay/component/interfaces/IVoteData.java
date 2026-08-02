package com.safetyandsecurityinterplay.component.interfaces;

import java.util.List;

public interface IVoteData {
    IVotedData voteData(List<IAuthenticatedSensorData> inputs);
}
