package org.example.genData;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallAPI {
    private final RestTemplate restTemplate;

    public CallAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    ApiDataModel getData() {
        var url = UriComponentsBuilder.fromHttpUrl("https://jsonplaceholder.typicode.com/posts")
                .path("/1")
                .build().toString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                ApiDataModel.class
        ).getBody();
    }

    List<ApiDataModel> getDataList() {
        var url = UriComponentsBuilder.fromHttpUrl("https://jsonplaceholder.typicode.com/posts")
                .build().toString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ApiDataModel>>() {}
        ).getBody();
    }

    List<ApiDataImgModel> getImgDataList(String page, String limit) {
        // dynamic queryParam
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", page);
        queryParams.put("limit", limit);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://picsum.photos/v2/list");

        queryParams.forEach((key, value) -> {
            if (value != "") {
                builder.queryParam(key, value);
            }
        });

        String url = builder.build().toUriString();
//        var url = UriComponentsBuilder.fromHttpUrl("https://picsum.photos/v2/list")
//                .queryParam("page", page)
//                .queryParam("limit", limit)
//                .build()
//                .toString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ApiDataImgModel>>() {}
        ).getBody();
    }
}
