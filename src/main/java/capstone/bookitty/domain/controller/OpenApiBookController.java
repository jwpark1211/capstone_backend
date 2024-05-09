package capstone.bookitty.domain.controller;

import capstone.bookitty.domain.service.OpenApiBookService;
import capstone.bookitty.global.api.dto.AladinBestSellerResponseDTO;
import capstone.bookitty.global.api.dto.AladinBookListResponseDTO;
import capstone.bookitty.global.api.dto.AladinBookSearchResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "openApi(book)", description = "알라딘 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/open")
public class OpenApiBookController {

    private final OpenApiBookService openApiBookService;

    @Operation(summary = "isbn(13)으로 책 세부 정보 확인")
    @GetMapping(path = "/search/book/{isbn}")
    public Mono<AladinBookListResponseDTO> getBookbyISBN(
            @PathVariable("isbn") String isbn
    ) {
        return openApiBookService.searchByBookISBN(isbn);
    }

    @Operation(summary = "keyword로 검색하기(제목, 저자, 출판사 모든 결과 조회 가능)")
    @GetMapping(path = "/search/keyword/{keyword}")
    public Mono<AladinBookSearchResponseDTO> getBooksByKeyword(
            @PathVariable("keyword") String keyword
    ){
        return openApiBookService.searchByKeyword(keyword);
    }

    @Operation(summary = "전체 베스트셀러 Top 10")
    @GetMapping(path = "/bestseller")
    public Mono<AladinBestSellerResponseDTO> getBestSeller(){
        return openApiBookService.getBestSeller();
    }

    /* [ 170 : 국내 경제경영 ] [ 987 : 과학 ] [ 2551 : 만화 ][ 798 : 사회 ]
    [ 1 : 소설/시/희곡 ] [ 656 : 인문 ] [ 336 : 자기계발 ] [ 351 : 컴퓨터/모바일 ] */
    @Operation(summary = "카테고리별 베스트셀러 Top 10\n CategoryId: " +
            "(170 : 국내 경제경영 ] [ 987 : 과학 ] [ 2551 : 만화 ][ 798 : 사회 ]" +
            "[ 1 : 소설/시/희곡 ] [ 656 : 인문 ] [ 336 : 자기계발 ] [ 351 : 컴퓨터/모바일)")
    @GetMapping(path = "/bestseller/category/{category-id}")
    public Mono<AladinBestSellerResponseDTO> getBestSellerByGenre(
            @PathVariable("category-id") int cid
    ){
        return openApiBookService.getBestSellerByGenre(cid);
    }

    @Operation(summary = "신간 베스트셀러 Top 10")
    @GetMapping(path = "/bestseller/newBook")
    public Mono<AladinBestSellerResponseDTO> getBestSellerNewBook(){
        return openApiBookService.getBestSellerNewBook();
    }

    @Operation(summary = "blogChoice 베스트셀러 Top 10")
    @GetMapping(path = "/bestseller/blogChoice")
    public Mono<AladinBestSellerResponseDTO> getBestSellerBlogChoice(){
        return openApiBookService.getBlogChoice();
    }
}
