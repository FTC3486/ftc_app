package net.jacobmason.GroupMe;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;


/**
 * Created by JacobAMason on 2/29/16.
 */
public class Bot {
    private int bot_id;

    public Bot(final String name, final String group_id) {
        RequestParams request = new RequestParams();
        Map<String, String> bot = new HashMap<String, String>();
        bot.put("name", name);
        bot.put("group_id", group_id);
        request.put("bot", bot);

        RestClient.post("bots", request, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    bot_id = response.getJSONObject("meta").getInt("code");
                } catch (JSONException e) {
                    throw new GroupMeException(String.format("could not create bot with name '%s' and group_id '%s'", name, group_id));
                }
            }
        });
    }


    public void say(String text) {
        RequestParams request = new RequestParams();
        request.put("bot_id", bot_id);
        request.put("text", text);

        RestClient.post("bots/post", request, new JsonHttpResponseHandler());
    }

}
