package org.example.genData;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class getDataAndInsert {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer your_token_here")
                .GET()
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://picsum.photos/v2/list"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
            String responseBody2 = response2.body();

//            responseBody = "[\n" +
//                    "{\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 1,\n" +
//                    "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
//                    "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 2,\n" +
//                    "    \"title\": \"qui est esse\",\n" +
//                    "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 3,\n" +
//                    "    \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
//                    "    \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 4,\n" +
//                    "    \"title\": \"eum et est occaecati\",\n" +
//                    "    \"body\": \"ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"userId\": 1,\n" +
//                    "    \"id\": 5,\n" +
//                    "    \"title\": \"nesciunt quas odio\",\n" +
//                    "    \"body\": \"repudiandae veniam quaerat sunt sed\\nalias aut fugiat sit autem sed est\\nvoluptatem omnis possimus esse voluptatibus quis\\nest aut tenetur dolor neque\"\n" +
//                    "  }\n" +
//                    "]\n";
//
//            responseBody2 = "[\n" +
//                    "  {\n" +
//                    "    \"id\": \"0\",\n" +
//                    "    \"author\": \"Alejandro Escamilla\",\n" +
//                    "    \"width\": 5000,\n" +
//                    "    \"height\": 3333,\n" +
//                    "    \"url\": \"https://unsplash.com/photos/yC-Yzbqy7PY\",\n" +
//                    "    \"download_url\": \"https://picsum.photos/id/0/5000/3333\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"id\": \"1\",\n" +
//                    "    \"author\": \"Alejandro Escamilla\",\n" +
//                    "    \"width\": 5000,\n" +
//                    "    \"height\": 3333,\n" +
//                    "    \"url\": \"https://unsplash.com/photos/LNRyGwIJr5c\",\n" +
//                    "    \"download_url\": \"https://picsum.photos/id/1/5000/3333\"\n" +
//                    "  },\n" +
//                    "  {\n" +
//                    "    \"id\": \"2\",\n" +
//                    "    \"author\": \"Alejandro Escamilla\",\n" +
//                    "    \"width\": 5000,\n" +
//                    "    \"height\": 3333,\n" +
//                    "    \"url\": \"https://unsplash.com/photos/N7XodRrbzS0\",\n" +
//                    "    \"download_url\": \"https://picsum.photos/id/2/5000/3333\"\n" +
//                    "  }\n" +
//                    "]\n";

            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
//            ApiDataModel responseObject = mapper.readValue(responseBody, ApiDataModel.class);

//            int count = 0;
            String insertSql = "INSERT INTO stories(title, body, author, img) VALUES ('val1', 'val2', 'val3', 'val4');";
            List<ApiDataModel> apiDataList = mapper.readValue(responseBody, new TypeReference<List<ApiDataModel>>(){});
            List<ApiDataImgModel> apiDataImgList = mapper.readValue(responseBody2, new TypeReference<List<ApiDataImgModel>>(){});

            System.out.println("apiDataImgList.size()="+apiDataImgList.size());
//            for (ApiDataModel apiData : apiDataList) {
////                if (count <= 10) {
////                    boolean created = callInserApi(apiData.getTitle(), apiData.getBody());
////                    if(created) {
////                        System.out.println(">>> Created new story; apiData="+ apiData.getId());
////                    }
////                }
////                count++;
//                System.out.println("apiData.getId()="+apiData.getId());
//                apiDataImgList.forEach(img -> {
//                    String newInsertSql = insertSql.replace("val1", apiData.getTitle().replace("\n", " "));
//                    newInsertSql = newInsertSql.replace("val2", apiData.getBody().replace("\n", " "));
//                    newInsertSql = newInsertSql.replace("val3", img.getAuthor());
//                    newInsertSql = newInsertSql.replace("val4", img.getDownload_url());
//                    System.out.println(newInsertSql);
//                });
//                if (apiData.getId() == apiDataImgList.size()) {
//                    System.out.println(">>>>>>");
//                    break;
//                }
//            }

            for(int i=0; i<apiDataImgList.size(); i++) {
                String newInsertSql = insertSql.replace("val1", apiDataList.get(i).getTitle().replace("\n", " "));
                newInsertSql = newInsertSql.replace("val2", apiDataList.get(i).getBody().replace("\n", " "));
                newInsertSql = newInsertSql.replace("val3", apiDataImgList.get(i).getAuthor());
                newInsertSql = newInsertSql.replace("val4", apiDataImgList.get(i).getDownload_url());
                System.out.println(newInsertSql);
            }

//            System.out.println("Finish insert data");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static boolean callInserApi(String title, String body) {
//        String jsonBody = "{\n" +
//                "    \"title\": \""+title+"\",\n" +
//                "    \"body\": \""+body+"\"\n" +
//                "}";
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8080/jdbc/story/create"))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return true;
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}