package com.pradipta.polly.pollingapi;

import com.pradipta.polly.exception.ResourceNotFoundException;
import com.pradipta.polly.model.poll.PollRepository;
import com.pradipta.polly.model.poll.PollService;
import com.pradipta.polly.model.user.User;
import com.pradipta.polly.model.user.UserIdentityAvailability;
import com.pradipta.polly.model.user.UserProfile;
import com.pradipta.polly.model.user.UserRepository;
import com.pradipta.polly.model.user.UserSummary;
import com.pradipta.polly.model.vote.VoteRepository;
import com.pradipta.polly.pollingapi.dto.PagedResponse;
import com.pradipta.polly.pollingapi.dto.PollResponse;
import com.pradipta.polly.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollService pollService;

    @Autowired
    private VoteRepository voteRepository;

    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    public UserIdentityAvailability checkUsernameAvailability(String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }


    public UserIdentityAvailability checkEmailAvailability(String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    public UserProfile getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);

        return userProfile;
    }

    public PagedResponse<PollResponse> getPollsCreatedBy(String username, UserPrincipal currentUser, int page, int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }

    public PagedResponse<PollResponse> getPollsVotedBy(String username, UserPrincipal currentUser, int page, int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }
}
