package br.com.leogmsantos.languagesstats.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.leogmsantos.languagesstats.model.dto.GITRepositoryItemDTO;

public class GITRepositoryResponse {

    @SerializedName("items")
    private List<GITRepositoryItemDTO> repositoryItemList;

    public GITRepositoryResponse() {
    }

    public List<GITRepositoryItemDTO> getRepositoryItemList() {
        return repositoryItemList;
    }

    public void setRepositoryItemList(List<GITRepositoryItemDTO> repositoryItemList) {
        this.repositoryItemList = repositoryItemList;
    }
}
