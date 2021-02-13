package br.com.leogmsantos.languagesstats.view.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.leogmsantos.languagesstats.network.APIService;
import br.com.leogmsantos.languagesstats.network.RetrofitInstance;
import br.com.leogmsantos.languagesstats.service.response.GITRepositoryResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GITRepositoryViewModel extends ViewModel {

    private MutableLiveData<GITRepositoryResponse> responseMutableLiveData;

    public GITRepositoryViewModel() {
        responseMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<GITRepositoryResponse> getResponseObserver() {
        return responseMutableLiveData;
    }

    public void makeAPICall(){
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<GITRepositoryResponse> call = apiService.getGitRepositoryList("language:Java","name", "1");
        call.enqueue(new Callback<GITRepositoryResponse>() {
            @Override
            public void onResponse(Call<GITRepositoryResponse> call, Response<GITRepositoryResponse> response) {
                responseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GITRepositoryResponse> call, Throwable t) {
                responseMutableLiveData.postValue(null);
            }
        });
    }
}
