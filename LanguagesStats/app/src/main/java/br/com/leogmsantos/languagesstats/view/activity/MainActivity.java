package br.com.leogmsantos.languagesstats.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import java.util.List;

import br.com.leogmsantos.languagesstats.R;
import br.com.leogmsantos.languagesstats.databinding.ActivityMainBinding;
import br.com.leogmsantos.languagesstats.model.dto.GITRepositoryItemDTO;
import br.com.leogmsantos.languagesstats.service.response.GITRepositoryResponse;
import br.com.leogmsantos.languagesstats.view.adapter.GITRepositoryAdapter;
import br.com.leogmsantos.languagesstats.view.viewmodel.GITRepositoryViewModel;

public class MainActivity extends AppCompatActivity {

    private GITRepositoryResponse response;
    private GITRepositoryViewModel gitRepositoryViewModel;
    private GITRepositoryAdapter adapter;
    private ActivityMainBinding binding;

    private String sortBy = "stars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeComponents();
        initializeRecycerView();
        initializeViewModelCall();
    }

    private void initializeComponents(){
        binding.chipFilterForks.setOnClickListener(v ->{
            binding.chipGroup.clearCheck();
            binding.chipFilterForks.setChecked(true);
            sortBy = "forks";
            gitRepositoryViewModel.makeAPICall(sortBy);
        });

        binding.chipFilterName.setOnClickListener(v ->{
            binding.chipGroup.clearCheck();
            binding.chipFilterName.setChecked(true);
            sortBy = "name";
            gitRepositoryViewModel.makeAPICall(sortBy);
        });

        binding.chipFilterStars.setOnClickListener(v -> {
            binding.chipGroup.clearCheck();
            binding.chipFilterStars.setChecked(true);
            sortBy = "stars";
            gitRepositoryViewModel.makeAPICall(sortBy);
        });
    }

    private void initializeRecycerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvRepositoryList.setLayoutManager(layoutManager);
        adapter = new GITRepositoryAdapter(response, this);
        binding.rvRepositoryList.setAdapter(adapter);
    }

    private void initializeViewModelCall(){
        gitRepositoryViewModel = ViewModelProviders.of(this).get(GITRepositoryViewModel.class);
        gitRepositoryViewModel.getResponseObserver().observe(this, new Observer<GITRepositoryResponse>() {
            @Override
            public void onChanged(GITRepositoryResponse gitRepositoryResponse) {
                if (gitRepositoryResponse != null){
                    response = gitRepositoryResponse;
                    adapter.setResponse(response);
                }
            }
        });
        gitRepositoryViewModel.makeAPICall(sortBy);
    }
}