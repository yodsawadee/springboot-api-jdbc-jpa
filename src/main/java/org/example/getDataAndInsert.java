package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
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

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            responseBody = "[\n" +
                    "{\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 1,\n" +
                    "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                    "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 2,\n" +
                    "    \"title\": \"qui est esse\",\n" +
                    "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 3,\n" +
                    "    \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
                    "    \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 4,\n" +
                    "    \"title\": \"eum et est occaecati\",\n" +
                    "    \"body\": \"ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 5,\n" +
                    "    \"title\": \"nesciunt quas odio\",\n" +
                    "    \"body\": \"repudiandae veniam quaerat sunt sed\\nalias aut fugiat sit autem sed est\\nvoluptatem omnis possimus esse voluptatibus quis\\nest aut tenetur dolor neque\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 6,\n" +
                    "    \"title\": \"dolorem eum magni eos aperiam quia\",\n" +
                    "    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 7,\n" +
                    "    \"title\": \"magnam facilis autem\",\n" +
                    "    \"body\": \"dolore placeat quibusdam ea quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 8,\n" +
                    "    \"title\": \"dolorem dolore est ipsam\",\n" +
                    "    \"body\": \"dignissimos aperiam dolorem qui eum\\nfacilis quibusdam animi sint suscipit qui sint possimus cum\\nquaerat magni maiores excepturi\\nipsam ut commodi dolor voluptatum modi aut vitae\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 9,\n" +
                    "    \"title\": \"nesciunt iure omnis dolorem tempora et accusantium\",\n" +
                    "    \"body\": \"consectetur animi nesciunt iure dolore\\nenim quia ad\\nveniam autem ut quam aut nobis\\net est aut quod aut provident voluptas autem voluptas\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 10,\n" +
                    "    \"title\": \"optio molestias id quia eum\",\n" +
                    "    \"body\": \"quo et expedita modi cum officia vel magni\\ndoloribus qui repudiandae\\nvero nisi sit\\nquos veniam quod sed accusamus veritatis error\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 11,\n" +
                    "    \"title\": \"et ea vero quia laudantium autem\",\n" +
                    "    \"body\": \"delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\\naccusamus in eum beatae sit\\nvel qui neque voluptates ut commodi qui incidunt\\nut animi commodi\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 12,\n" +
                    "    \"title\": \"in quibusdam tempore odit est dolorem\",\n" +
                    "    \"body\": \"itaque id aut magnam\\npraesentium quia et ea odit et ea voluptas et\\nsapiente quia nihil amet occaecati quia id voluptatem\\nincidunt ea est distinctio odio\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 13,\n" +
                    "    \"title\": \"dolorum ut in voluptas mollitia et saepe quo animi\",\n" +
                    "    \"body\": \"aut dicta possimus sint mollitia voluptas commodi quo doloremque\\niste corrupti reiciendis voluptatem eius rerum\\nsit cumque quod eligendi laborum minima\\nperferendis recusandae assumenda consectetur porro architecto ipsum ipsam\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 14,\n" +
                    "    \"title\": \"voluptatem eligendi optio\",\n" +
                    "    \"body\": \"fuga et accusamus dolorum perferendis illo voluptas\\nnon doloremque neque facere\\nad qui dolorum molestiae beatae\\nsed aut voluptas totam sit illum\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 15,\n" +
                    "    \"title\": \"eveniet quod temporibus\",\n" +
                    "    \"body\": \"reprehenderit quos placeat\\nvelit minima officia dolores impedit repudiandae molestiae nam\\nvoluptas recusandae quis delectus\\nofficiis harum fugiat vitae\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 16,\n" +
                    "    \"title\": \"sint suscipit perspiciatis velit dolorum rerum ipsa laboriosam odio\",\n" +
                    "    \"body\": \"suscipit nam nisi quo aperiam aut\\nasperiores eos fugit maiores voluptatibus quia\\nvoluptatem quis ullam qui in alias quia est\\nconsequatur magni mollitia accusamus ea nisi voluptate dicta\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 17,\n" +
                    "    \"title\": \"fugit voluptas sed molestias voluptatem provident\",\n" +
                    "    \"body\": \"eos voluptas et aut odit natus earum\\naspernatur fuga molestiae ullam\\ndeserunt ratione qui eos\\nqui nihil ratione nemo velit ut aut id quo\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 18,\n" +
                    "    \"title\": \"voluptate et itaque vero tempora molestiae\",\n" +
                    "    \"body\": \"eveniet quo quis\\nlaborum totam consequatur non dolor\\nut et est repudiandae\\nest voluptatem vel debitis et magnam\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 19,\n" +
                    "    \"title\": \"adipisci placeat illum aut reiciendis qui\",\n" +
                    "    \"body\": \"illum quis cupiditate provident sit magnam\\nea sed aut omnis\\nveniam maiores ullam consequatur atque\\nadipisci quo iste expedita sit quos voluptas\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 2,\n" +
                    "    \"id\": 20,\n" +
                    "    \"title\": \"doloribus ad provident suscipit at\",\n" +
                    "    \"body\": \"qui consequuntur ducimus possimus quisquam amet similique\\nsuscipit porro ipsam amet\\neos veritatis officiis exercitationem vel fugit aut necessitatibus totam\\nomnis rerum consequatur expedita quidem cumque explicabo\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 21,\n" +
                    "    \"title\": \"asperiores ea ipsam voluptatibus modi minima quia sint\",\n" +
                    "    \"body\": \"repellat aliquid praesentium dolorem quo\\nsed totam minus non itaque\\nnihil labore molestiae sunt dolor eveniet hic recusandae veniam\\ntempora et tenetur expedita sunt\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 22,\n" +
                    "    \"title\": \"dolor sint quo a velit explicabo quia nam\",\n" +
                    "    \"body\": \"eos qui et ipsum ipsam suscipit aut\\nsed omnis non odio\\nexpedita earum mollitia molestiae aut atque rem suscipit\\nnam impedit esse\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 23,\n" +
                    "    \"title\": \"maxime id vitae nihil numquam\",\n" +
                    "    \"body\": \"veritatis unde neque eligendi\\nquae quod architecto quo neque vitae\\nest illo sit tempora doloremque fugit quod\\net et vel beatae sequi ullam sed tenetur perspiciatis\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 24,\n" +
                    "    \"title\": \"autem hic labore sunt dolores incidunt\",\n" +
                    "    \"body\": \"enim et ex nulla\\nomnis voluptas quia qui\\nvoluptatem consequatur numquam aliquam sunt\\ntotam recusandae id dignissimos aut sed asperiores deserunt\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 25,\n" +
                    "    \"title\": \"rem alias distinctio quo quis\",\n" +
                    "    \"body\": \"ullam consequatur ut\\nomnis quis sit vel consequuntur\\nipsa eligendi ipsum molestiae et omnis error nostrum\\nmolestiae illo tempore quia et distinctio\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 26,\n" +
                    "    \"title\": \"est et quae odit qui non\",\n" +
                    "    \"body\": \"similique esse doloribus nihil accusamus\\nomnis dolorem fuga consequuntur reprehenderit fugit recusandae temporibus\\nperspiciatis cum ut laudantium\\nomnis aut molestiae vel vero\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 27,\n" +
                    "    \"title\": \"quasi id et eos tenetur aut quo autem\",\n" +
                    "    \"body\": \"eum sed dolores ipsam sint possimus debitis occaecati\\ndebitis qui qui et\\nut placeat enim earum aut odit facilis\\nconsequatur suscipit necessitatibus rerum sed inventore temporibus consequatur\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 28,\n" +
                    "    \"title\": \"delectus ullam et corporis nulla voluptas sequi\",\n" +
                    "    \"body\": \"non et quaerat ex quae ad maiores\\nmaiores recusandae totam aut blanditiis mollitia quas illo\\nut voluptatibus voluptatem\\nsimilique nostrum eum\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 29,\n" +
                    "    \"title\": \"iusto eius quod necessitatibus culpa ea\",\n" +
                    "    \"body\": \"odit magnam ut saepe sed non qui\\ntempora atque nihil\\naccusamus illum doloribus illo dolor\\neligendi repudiandae odit magni similique sed cum maiores\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 3,\n" +
                    "    \"id\": 30,\n" +
                    "    \"title\": \"a quo magni similique perferendis\",\n" +
                    "    \"body\": \"alias dolor cumque\\nimpedit blanditiis non eveniet odio maxime\\nblanditiis amet eius quis tempora quia autem rem\\na provident perspiciatis quia\"\n" +
                    "  }" +
                    "]\n";


            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
//            ApiDataModel responseObject = mapper.readValue(responseBody, ApiDataModel.class);

//            int count = 1;
            List<ApiDataModel> apiDataList = mapper.readValue(responseBody, new TypeReference<List<ApiDataModel>>(){});
            for (ApiDataModel apiData : apiDataList) {
//                if (count <= 10) {
                    boolean created = callInserApi(apiData.getTitle(), apiData.getBody());
                    if(created) {
                        System.out.println(">>> Created new story; apiData="+ apiData.getId());
                    }
//                }
//                count++;
            }

            System.out.println("Finish insert data");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean callInserApi(String title, String body) {
        String jsonBody = "{\n" +
                "    \"title\": \""+title+"\",\n" +
                "    \"body\": \""+body+"\"\n" +
                "}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/jdbc/story/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}