package br.com.leogmsantos.languagesstats.network;


import br.com.leogmsantos.languagesstats.service.response.GITRepositoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("search/repositories")
    Call<GITRepositoryResponse> getGitRepositoryList(@Query("q") String searchedLanguage,
                                                     @Query("sort") String selectedSort,
                                                     @Query("page") String pageNumber);
}
