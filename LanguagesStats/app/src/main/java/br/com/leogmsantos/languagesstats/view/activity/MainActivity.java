package br.com.leogmsantos.languagesstats.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.leogmsantos.languagesstats.R;
import br.com.leogmsantos.languagesstats.model.dto.GITRepositoryItemDTO;
import br.com.leogmsantos.languagesstats.service.response.GITRepositoryResponse;
import br.com.leogmsantos.languagesstats.view.viewmodel.GITRepositoryViewModel;

public class MainActivity extends AppCompatActivity {

    private List<GITRepositoryItemDTO> repositoryItemList;
    private GITRepositoryViewModel gitRepositoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewModelCall();
    }

    private void initializeViewModelCall(){
        gitRepositoryViewModel = ViewModelProviders.of(this).get(GITRepositoryViewModel.class);
        gitRepositoryViewModel.getResponseObserver().observe(this, new Observer<GITRepositoryResponse>() {
            @Override
            public void onChanged(GITRepositoryResponse gitRepositoryResponse) {
                if (gitRepositoryResponse != null){
                    repositoryItemList = gitRepositoryResponse.getRepositoryItemList();
                }
            }
        });
        gitRepositoryViewModel.makeAPICall();
    }
}