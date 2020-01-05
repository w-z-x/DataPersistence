package controllers;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entities.UserEntity;
import services.UserService;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserController implements HttpHandler {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        Object result;

        if ("POST".equals(method)) {
            String postBody = is2str(httpExchange.getRequestBody());
            UserEntity user = JSON.parseObject(postBody, UserEntity.class);
            result = userService.createUser(user);
        } else if ("GET".equals(method)) {
            Map<String, String> map = str2map(httpExchange.getRequestURI().getQuery());
            int id = Integer.parseInt(map.get("id"));
            result = JSON.toJSON(userService.retrieveUser(id));
            Headers responseHeaders = httpExchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "application/json;charset=utf-8");
        } else if ("PUT".equals(method)) {
            String postBody = is2str(httpExchange.getRequestBody());
            UserEntity user = JSON.parseObject(postBody, UserEntity.class);
            result = userService.updateUser(user);
        } else if ("DELETE".equals(method)) {
            Map<String, String> map = str2map(httpExchange.getRequestURI().getQuery());
            int id = Integer.parseInt(map.get("id"));
            result = userService.deleteUser(id);
        } else if ("VIEW".equals(method)) {
            result = JSON.toJSON(userService.findAllUsers());
            Headers responseHeaders = httpExchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/json;charset=utf-8");
        } else {
            result = "不支持此方法";
        }

        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        String response = result.toString();
        os.write(response.getBytes());
        os.close();
    }

    private static String is2str(InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    private static Map<String, String> str2map(String queryString) {
        Map<String, String> result = new HashMap<>();
        if (queryString == null || queryString.trim().length() == 0) {
            return result;
        }
        final String[] items = queryString.split("&");
        Arrays.stream(items).forEach(item -> {
            final String[] keyAndVal = item.split("=");
            if (keyAndVal.length == 2) {
                try {
                    final String key = URLDecoder.decode(keyAndVal[0], "UTF-8");
                    final String val = URLDecoder.decode(keyAndVal[1], "UTF-8");
                    result.put(key, val);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        return result;
    }
}
