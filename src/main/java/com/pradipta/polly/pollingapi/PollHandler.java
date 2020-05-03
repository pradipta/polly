package com.pradipta.polly.pollingapi;

import com.pradipta.polly.authentication.dto.ApiResponse;
import com.pradipta.polly.model.poll.Poll;
import com.pradipta.polly.model.poll.PollService;
import com.pradipta.polly.pollingapi.dto.PagedResponse;
import com.pradipta.polly.pollingapi.dto.PollRequest;
import com.pradipta.polly.pollingapi.dto.PollResponse;
import com.pradipta.polly.pollingapi.dto.VoteRequest;
import com.pradipta.polly.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class PollHandler {

    @Autowired
    private PollService pollService;

    public PagedResponse<PollResponse> getAllPolls(UserPrincipal currentUser, int page, int size) {
        return pollService.getAllPolls(currentUser, page, size);
    }

    public ResponseEntity<?> createPoll(PollRequest pollRequest) {
        Poll poll = pollService.createPoll(pollRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(poll.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Poll Created Successfully"));
    }

    public PollResponse getPollById(Long pollId, UserPrincipal currentUser) {
        return pollService.getPollById(pollId, currentUser);
    }

    public PollResponse castVoteAndGetUpdatedPoll(Long pollId, VoteRequest voteRequest, UserPrincipal currentUser) {
        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
    }
}
