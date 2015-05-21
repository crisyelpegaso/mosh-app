package org.mosh.util.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mosh.domain.Concert;
import org.mosh.exception.ServerResponseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crodriguez on 15/05/15.
 */
public abstract class ParseServerResponseUtil {


    public static List<Concert> getConcertsFromInputStream(InputStream inputStream) throws ServerResponseException {

        List<Concert> resultConcerts = new ArrayList<Concert>();
        String jsonResult = convertConcertsInputStreamToString(inputStream);

        if (!(jsonResult.length() > 0)) {
            return resultConcerts;
        }

        try {
            JSONArray concerts = new JSONArray(jsonResult);
            for (int i = 0; i < concerts.length(); i++) {
                JSONObject currentConcert = concerts.getJSONObject(i);
                Concert concert = new Concert();
                concert.setName(currentConcert.getString("name"));
                concert.setDate(currentConcert.getString("date"));
                concert.setLocation(currentConcert.getString("location"));
                resultConcerts.add(concert);
            }
        } catch (Exception e) {
            throw new ServerResponseException(e);
        }


        return resultConcerts;
    }

    private static String convertConcertsInputStreamToString(InputStream inputStream) throws ServerResponseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        try {
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            inputStream.close();
        } catch (IOException e) {
            throw new ServerResponseException(e);
        }

        return result;
    }

    //convert inputstream to String
    //TODO this should be private and shouldn't be use elsewhere but in this Util Class
    public static String convertResultsInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
