package br.com.leogmsantos.languagesstats.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.leogmsantos.languagesstats.R;
import br.com.leogmsantos.languagesstats.service.response.GITRepositoryResponse;

public class GITRepositoryAdapter extends RecyclerView.Adapter<GITRepositoryAdapter.MyViewHolder>{

    private GITRepositoryResponse response;
    private Context context;

    public GITRepositoryAdapter(GITRepositoryResponse response, Context context){
        this.response = response;
        this.context = context;
    }

    public void setResponse(GITRepositoryResponse response) {
        this.response = response;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_git_repository, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemTitle.setText(response.getRepositoryItemList().get(position).getName());
        holder.itemDescription.setText(response.getRepositoryItemList().get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if (response != null){
            return 10;
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle, itemDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.tv_item_title);
            itemDescription = itemView.findViewById(R.id.tv_item_description);
        }
    }
}
