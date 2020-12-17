package kurlyk.communication;

import com.google.gson.Gson;
import kurlyk.MainProperties;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class AbstractCommunicator {

    //GET - запрос
    protected <OUT> OUT getData(Type outType, String addr) {
        return getData(outType, null, addr);
    }

    protected <OUT> OUT getData(Type outType, Map<String, String> parameters, String addr){
        try {
            URL url = new URL(
                    MainProperties.getInstance().getProperty("addr") +
                            deleteSlashIfExist(addr) +
                            getParamsString(parameters)
            );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Token " + getToken());
            conn.setDoOutput(true);

            return request(conn, outType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //POST - запрос
    protected <IN, OUT> OUT postData(Type outType, IN data, String addr){
        try {
            byte[] dataBytes = new Gson().toJson(data).getBytes();
            URL url = new URL(MainProperties.getInstance().getProperty("addr") + addr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", String.valueOf(dataBytes.length));
            conn.setRequestProperty("Authorization", "Token " + getToken());
//            conn.setRequestProperty("content-type", "application/json;  charset=utf-8");
            conn.setDoOutput(true);

            conn.getOutputStream().write(dataBytes);

            return request(conn, outType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //DELETE - запрос
    protected <OUT> OUT deleteData(Type outType, Long id, String addr){
        try {
            URL url = new URL(MainProperties.getInstance().getProperty("addr") + addr + id.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Authorization", "Token " + getToken());
            conn.setDoOutput(true);

            return request(conn, outType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <OUT> OUT request(HttpURLConnection conn, Type outType) throws IOException{
        System.out.println("Код ответа сервера" + conn.getResponseCode());

        StringBuilder content = new StringBuilder();
        try(Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            for (int c; (c = in.read()) >= 0;) {
                content.append((char) c);
            }
        }

        conn.disconnect();
        return new Gson().fromJson(content.toString(), outType);
    }


    private String getParamsString(Map<String, String> params){
        if(params == null || params.size() == 0){
            return "";
        }

        StringBuilder result = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    result.append("&");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String resultString = result.toString();
        if (resultString.isEmpty()){
            return "";
        } else {
            return "?" + resultString.substring(0, resultString.length() - 1);
        }
    }

    //удалить последний слешь для GET запросов
    private String deleteSlashIfExist(String addr){
        return addr.charAt(addr.length() - 1) == '/' ? addr.substring(0, addr.length() - 1) : addr;
    }

    public abstract String getToken();
}
