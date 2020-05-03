package com.pradipta.polly.util;

import com.pradipta.polly.model.choice.Choice;
import com.pradipta.polly.model.poll.Poll;
import com.pradipta.polly.model.user.User;
import com.pradipta.polly.model.user.UserSummary;
import com.pradipta.polly.pollingapi.dto.ChoiceResponse;
import com.pradipta.polly.pollingapi.dto.PollResponse;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {
    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        Instant now = Instant.now();
        pollResponse.setIsExpired(poll.getExpirationDateTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> createChoiceResponse(choice, choiceVotesMap)).collect(Collectors.toList());

        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        pollResponse.setCreatedBy(creatorSummary);

        if(userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoices()
                .stream()
                .mapToLong(ChoiceResponse::getVoteCount)
                .sum();
        pollResponse.setTotalVotes(totalVotes);

        return pollResponse;
    }

    private static ChoiceResponse createChoiceResponse(Choice choice, Map<Long, Long> choiceVotesMap) {
        ChoiceResponse choiceResponse = new ChoiceResponse();
        choiceResponse.setId(choice.getId());
        choiceResponse.setText(choice.getText());

        if(choiceVotesMap.containsKey(choice.getId())) {
            choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
        } else {
            choiceResponse.setVoteCount(0);
        }
        return choiceResponse;
    }
}
