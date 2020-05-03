package com.pradipta.polly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChoiceVoteCount {
    private Long choiceId;
    private Long voteCount;
}
