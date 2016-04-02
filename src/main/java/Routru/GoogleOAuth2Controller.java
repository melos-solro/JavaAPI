package Routru;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nisargap on 4/1/16.
 */
@CrossOrigin
@RestController
public class GoogleOAuth2Controller {


    private String getAccessToken(String code) throws IOException{


        // created http client
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://www.googleapis.com//oauth2/v4/token");

        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        List<NameValuePair> urlParameters = new ArrayList<>();

        // adds code as param
        urlParameters.add(new BasicNameValuePair("code", code));

        urlParameters.add(new BasicNameValuePair("client_id", Utility.getGoogleClientId()));

        urlParameters.add(new BasicNameValuePair("client_secret", Utility.getGoogleClientSecret()));

        urlParameters.add(new BasicNameValuePair("redirect_uri", Utility.getHost() + "googleLoginCallback"));

        urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }

    @RequestMapping(value = "/revokeToken", method = RequestMethod.GET)
    public ModelAndView revokeToken(@RequestParam(value = "access_token", defaultValue = "") String accessToken) {

        String url  = "https://accounts.google.com/o/oauth2/revoke?token=" + accessToken;

        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value = "/googleLogin", method = RequestMethod.GET)
    public ModelAndView googleLogin() {

        String params = "?response_type=code&scope=email&state=nisarga&client_id="+ Utility.getGoogleClientId()+"&redirect_uri=" + Utility.getHost() +"googleLoginCallback";


        String projectUrl = "https://accounts.google.com/o/oauth2/v2/auth" + params;
        return new ModelAndView("redirect:" + projectUrl);

    }

    @RequestMapping(value = "/getUserData", method = RequestMethod.GET)
    public ModelAndView getUserData(@RequestParam(value = "access_token", defaultValue = "") String accessToken){

        String params = "?access_token=" + accessToken;
        String url = "https://www.googleapis.com/plus/v1/people/me" + params;

        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value = "/googleLoginCallback", method = RequestMethod.GET)
    public ModelAndView getAuthToken(@RequestParam(value = "state", defaultValue = "") String state,
                             @RequestParam(value = "code", defaultValue = "") String code,
                             @RequestParam(value = "access_token", defaultValue = "") String accessToken) throws IOException {


        String result = getAccessToken(code);

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

        String url = "https://routru.me/#/signin/token/" + jsonObject.get("access_token").getAsString();

        return new ModelAndView("redirect:" + url);

    }
}
