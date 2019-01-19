package io.swagger.api;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;


@Path("/auth")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class AuthService  {
	private String REDIRECT_URI = "http://localhost:8080";
	@POST
	public void authUser(@HeaderParam("X-Requested-With") String header)
	{	
		//authcode as param
		// (Receive authCode via HTTPS POST)


		//if (request.getHeader('X-Requested-With') == null) {
		  // Without the `X-Requested-With` header, this request could be forged. Aborts.
		//}

		// Set path to the Web application client_secret_*.json file you downloaded from the
		// Google API Console: https://console.developers.google.com/apis/credentials
		// You can also find your Web application client ID and client secret from the
		// console and specify them directly when you create the GoogleAuthorizationCodeTokenRequest
		// object.
		String CLIENT_SECRET_FILE = "/path/to/client_secret.json";

		// Exchange auth code for access token
		GoogleClientSecrets clientSecrets = null;
		try {
			clientSecrets = GoogleClientSecrets.load(
		        JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GoogleTokenResponse tokenResponse = null;
		try {
			tokenResponse = new GoogleAuthorizationCodeTokenRequest(
			      new NetHttpTransport(),
			      JacksonFactory.getDefaultInstance(),
			      "https://www.googleapis.com/oauth2/v4/token",
			      clientSecrets.getDetails().getClientId(),
			      clientSecrets.getDetails().getClientSecret(),
			      "123",
			      REDIRECT_URI)  // Specify the same redirect URI that you use with your web
			                     // app. If you don't have a web version of your app, you can
			                     // specify an empty string.
			      .execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String accessToken = tokenResponse.getAccessToken();

		/* Use access token to call API
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
		Drive drive =
		    new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
		        .setApplicationName("Auth Code Exchange Demo")
		        .build();
		File file = drive.files().get("appfolder").execute();*/
		
		// Get profile info from ID token
		GoogleIdToken idToken = null;
		try {
			idToken = tokenResponse.parseIdToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");

	}
}
