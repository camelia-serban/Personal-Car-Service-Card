package com.example.personalcarservicecard.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class HttpManager implements Callable<String> {
    //pentru conexiunea la retea
    private URL url;
    private HttpURLConnection connection;

    //pentru preluarea informatiilor din retea
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    private final String urlAddress;

    public HttpManager(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public String call() {
        try {
            return getResultFromHttp();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return null;
    }

    private String getResultFromHttp() throws IOException {
        //obiectul url asigura verificarea unui string ca fiind un url valid
        url = new URL(urlAddress);

        //conexiunea realizata de url
        connection = (HttpURLConnection) url.openConnection();

        //se preia toata informatia prin intermediul conexiunii, apoi este impartita in unitati mai mici
        inputStream = connection.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder result = new StringBuilder();
        String line;
        //se citeste linie cu linie toate informatiile
        while ((line = bufferedReader.readLine()) != null) {
            //informatiile sunt concatenate intr-un StringBuilder
            result.append(line);
        }
        return result.toString();
    }

    private void closeConnections() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();
    }
}
