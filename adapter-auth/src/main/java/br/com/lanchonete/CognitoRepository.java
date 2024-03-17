package br.com.lanchonete;

import br.com.lanchonete.port.repository.AuthClientProviderRepository;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CognitoRepository implements AuthClientProviderRepository {

    @Value("${cognito.userPoolId}")
    private String userPoolId;

    @Value("${cognito.clientId}")
    private String clientId;

    @Autowired
    private AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    @Override
    public void signIn(String username, String password, String email) {
        try {
            AttributeType emailAttr = new AttributeType().withName("email").withValue(email);
            AttributeType emailVerifiedAttr = new AttributeType().withName("email_verified").withValue("true");

            AdminCreateUserRequest userRequest = new AdminCreateUserRequest().withUserPoolId(userPoolId)
                    .withUsername(username).withTemporaryPassword(password)
                    .withUserAttributes(emailAttr, emailVerifiedAttr)
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            awsCognitoIdentityProvider.adminCreateUser(userRequest);

            AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest()
                    .withUsername(username).withUserPoolId(userPoolId)
                    .withPassword(password).withPermanent(true);

            awsCognitoIdentityProvider.adminSetUserPassword(adminSetUserPasswordRequest);
        } catch (Exception e) {
            throw new RuntimeException("User registration failed: " + e.getMessage(), e);
        }
    }

    @Override
    public String signUp(String username, String password) {
        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow("USER_PASSWORD_AUTH")
                .withClientId(clientId)
                .withAuthParameters(Map.of("USERNAME", username, "PASSWORD", password));

        try {
            InitiateAuthResult authResult = awsCognitoIdentityProvider.initiateAuth(authRequest);
            AuthenticationResultType authResponse = authResult.getAuthenticationResult();

            return authResponse.getIdToken();
        } catch (Exception e) {
            throw new RuntimeException("User login failed: " + e.getMessage(), e);
        }
    }
}