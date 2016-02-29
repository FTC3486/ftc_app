package net.jacobmason.GroupMe;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by JacobAMason on 2/29/16.
 */
public class Bot {
    public Bot(String name, String group_id) {
        String url = "bots";

        RequestParams request = new RequestParams();
        Map<String, String> bot = new HashMap<String, String>();
        bot.put("name", name);
        bot.put("group_id", group_id);
        request.put("bot", bot);

        RestClient.post(url, request, new JsonHttpResponseHandler());
    }
}
