package com.glizzy.milliondollarpicks.authservice.graphql;

import com.glizzy.milliondollarpicks.authservice.dto.CredentialsDto;
import com.glizzy.milliondollarpicks.authservice.service.AuthService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import com.netflix.graphql.dgs.DgsData;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@DgsComponent
@RequiredArgsConstructor
public class UserReferenceResolver {
    private final AuthService authService;

    // This resolves User references by ID in a federated GraphQL setup
    @DgsEntityFetcher(name = "User")
    public Map<String, Object> fetchUserById(Map<String, Object> values) {
        // Just return the reference with the ID
        // The actual User entity will be resolved by the User service
        return values;
    }

    // This resolves the credentials field on the User type
    @DgsData(parentType = "User", field = "credentials")
    public CredentialsDto getCredentialsForUser(DgsDataFetchingEnvironment dfe) {
        Map<String, Object> user = dfe.getSource();
        String userId = user.get("id").toString();

        // Get credentials for this user ID
        return authService.findCredentialsByUserId(Long.parseLong(userId));
    }
}