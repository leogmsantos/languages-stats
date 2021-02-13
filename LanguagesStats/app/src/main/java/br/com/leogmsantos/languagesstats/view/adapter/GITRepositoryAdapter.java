package br.com.leogmsantos.languagesstats.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.leogmsantos.languagesstats.R;
import br.com.leogmsantos.languagesstats.model.dto.GITRepositoryItemDTO;
import br.com.leogmsantos.languagesstats.service.response.GITRepositoryResponse;

public class GITRepositoryAdapter extends RecyclerView.Adapter<GITRepositoryAdapter.MyViewHolder> implements Filterable {

    private GITRepositoryResponse response;
    private List<GITRepositoryItemDTO> itemsListAll;
    private Context context;

    public GITRepositoryAdapter(GITRepositoryResponse response, Context context){
        this.response = response;
        this.context = context;
    }

    public void setResponse(GITRepositoryResponse response) {
        this.response = response;
        this.itemsListAll = new ArrayList<>(response.getRepositoryItemList());
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
        if (response.getRepositoryItemList().size() > 0){
            holder.itemTitle.setText(response.getRepositoryItemList().get(position).getName().toUpperCase());
            holder.itemDescription.setText(response.getRepositoryItemList().get(position).getDescription());
            holder.itemForkCount.setText(response.getRepositoryItemList().get(position).getForks().toString());
            holder.itemFavouriteCount.setText(response.getRepositoryItemList().get(position).getWatchers().toString());

            Glide.with(context)
                    .load(response.getRepositoryItemList().get(position).getOwner().getAvatarUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into(holder.ivAvatar);
        }
    }

    @Override
    public int getItemCount() {
        if (response != null){
            return response.getRepositoryItemList().size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return filterImplementation();
    }

    private Filter filterImplementation(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<GITRepositoryItemDTO> filteredList = new ArrayList<>();
                if (constraint.toString().isEmpty()){
                    filteredList.addAll(itemsListAll);
                }else{
                    for (GITRepositoryItemDTO item : itemsListAll){
                        if (item.getName().toLowerCase().contains(constraint.toString().toLowerCase())
                                || item.getDescription().toLowerCase().contains(constraint.toString().toLowerCase())){
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (response.getRepositoryItemList() != null){
                    response.getRepositoryItemList().clear();
                    response.getRepositoryItemList().addAll((Collection<? extends GITRepositoryItemDTO>) results.values);
                    notifyDataSetChanged();
                }
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle, itemDescription, itemForkCount, itemFavouriteCount;
        private ImageView ivAvatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.tv_item_title);
            itemDescription = itemView.findViewById(R.id.tv_item_description);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            itemForkCount = itemView.findViewById(R.id.tv_fork_count);
            itemFavouriteCount = itemView.findViewById(R.id.tv_favourite_count);
        }
    }
}
