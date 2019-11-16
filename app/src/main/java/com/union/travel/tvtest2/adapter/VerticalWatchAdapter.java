package com.union.travel.tvtest2.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.union.travel.tvtest2.FrescoLoader;
import com.union.travel.tvtest2.R;

import java.util.ArrayList;
import java.util.List;


public class VerticalWatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private List<String> itemUrls = new ArrayList<>();
	private int selectedPosition = 0;
	private FrescoLoader frescoLoader;



	public VerticalWatchAdapter(List<String> itemUrls) {
		this.itemUrls = itemUrls;
		frescoLoader = new FrescoLoader();

	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view;
		view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.watch_category_vertical_item, parent, false);
		return new VerticalWatchAdapter.ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
		final ItemViewHolder holder = (ItemViewHolder) viewHolder;
		String itemUrl = itemUrls.get(position);

		frescoLoader.loadWithParams(Uri.parse(itemUrl), holder.icModel, false);

		if (selectedPosition == position) {
			holder.indicatorView.setVisibility(View.VISIBLE);
			Log.d("dwd","true");
		} else {
			Log.d("dwd","false");
			holder.indicatorView.setVisibility(View.INVISIBLE);
		}


//		holder.itemView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				holder.indicatorView.setVisibility(View.VISIBLE);
//			}
//		});


	}

	@Override
	public int getItemCount() {
		return itemUrls.size();
	}



	public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private SimpleDraweeView icModel;
		private SimpleDraweeView indicatorView;


		ItemViewHolder(View view) {
			super(view);
			itemView.setOnClickListener(this);
			icModel = view.findViewById(R.id.watchitem);
			indicatorView = view.findViewById(R.id.indicatorView);

		}


		@Override
		public void onClick(View v) {
// Below line is just like a safety check, because sometimes holder could be null,
			// in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
			if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

			// Updating old as well as new positions
			notifyItemChanged(selectedPosition);
			selectedPosition = getAdapterPosition();
			notifyItemChanged(selectedPosition);

			// Do your another stuff for your onClick
		}
	}
}
