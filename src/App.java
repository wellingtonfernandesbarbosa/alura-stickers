import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar a lista de Top 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI source = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(source).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrar apenas os dados necessários(título, poster, classificação)
        JasonParser parser = new JasonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // manipular e exibit os dados da lista
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: " + filme.get("title"));
            System.out.println("Classificação: " + filme.get("imDbRating"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println();
        }

    }
}
