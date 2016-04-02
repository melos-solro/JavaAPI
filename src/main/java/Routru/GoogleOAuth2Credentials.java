package Routru;

import com.google.gson.JsonObject;

/**
 * Created by nisargap on 4/1/16.
 */
public class GoogleOAuth2Credentials {

    private String accessToken;
    private String expiresIn;
    private String idToken;

    GoogleOAuth2Credentials(JsonObject creds){

        this.accessToken = creds.get("access_token").getAsString();
        this.expiresIn = creds.get("expires_in").getAsString();
        this.idToken = creds.get("id_token").getAsString();

    }

    public String getAccessToken(){

        return this.accessToken;
    }

    public String getExpiresIn(){

        return this.expiresIn;
    }

    public String getIdToken(){

        return this.idToken;
    }
}
