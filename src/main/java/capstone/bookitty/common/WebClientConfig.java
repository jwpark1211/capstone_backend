package capstone.bookitty.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    @Value("${api.aladin.uri}")
    private String aladinUri;

    @Value("${api.naru.uri}")
    private String naruUri;

    /*알라딘 WebClient*/
    @Bean @Qualifier("AladinWebClient")
    public WebClient aladinWebClientApi(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(aladinUri)
                .build();
    }

    /*도서관 정보 나루 WebClient*/
    @Bean @Qualifier("NaruWebClient")
    public WebClient naruWebClientApi(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(naruUri)
                .build();
    }
}
