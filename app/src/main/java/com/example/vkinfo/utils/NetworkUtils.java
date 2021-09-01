package com.example.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com/";
    private static final String VK_USERS_GET = "method/users.get";
    public static URL generateURL (String userId){

        Uri buildUri = Uri.parse(VK_API_BASE_URL+VK_USERS_GET).buildUpon()
                .appendQueryParameter("user_ids",userId)
                .appendQueryParameter("v","5.62")
                .appendQueryParameter("access_token", "b055c2c0b055c2c0b055c2c0e9b02cd0f7bb055b055c2c0d1712024ab802138e74c9702").build();

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromURL(URL url){
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if(scanner.hasNext()){
                return scanner.next();
            }
            else{
                return null;
            }

        } catch (UnknownHostException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }
}
