package com.dome.yyh.dome.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dome.yyh.dome.R;
import com.dome.yyh.dome.VR.DragMethod;
import com.dome.yyh.dome.VR.GyroscopeImageView;
import com.dome.yyh.dome.VR.ImageVo;
import com.squareup.picasso.Picasso;


import java.util.Collections;
import java.util.List;

/**
 * 创建时间: 2018/03/12 21:43 <br>
 * 作者: jiangye <br>
 * 描述:
 */
public class GyroscopeImageAdapter extends RecyclerView.Adapter<GyroscopeImageAdapter.ViewHolder> implements DragMethod {

  private Context mContext;
  private List<ImageVo> mItems;
  private OnGvItemClickListener mOnGvItemClickListener;

  /**
   * 记录 GyroscopeImageView 的宽高，用于进行 TransFormation
   */
  private int mHeight;
  private int mWidth;

  public GyroscopeImageAdapter(Context context, List<ImageVo> items) {
    mContext = context;
    mItems = items;
  }

  public void setDatas(List<ImageVo> data) {
    mItems = data;
  }

  @NonNull
  @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.gyroscope_image_item, parent, false);
    ViewHolder holder = new ViewHolder(view);

    ViewGroup.LayoutParams params = holder.gImg.getLayoutParams();
    mHeight = params.height;
    mWidth = params.width;

    return holder;
  }

  @Override public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
    holder.imgName.setText(mItems.get(position).mImageName);

    Picasso.get()
        .load(mItems.get(position).mImageUrl)
       // .transform(new GyroscopeTransFormation(mWidth, mHeight))
        .into(holder.gImg);

    if (mOnGvItemClickListener != null) {
      holder.gImg.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          mOnGvItemClickListener.onGvItemClick(holder, holder.getAdapterPosition());
        }
      });
    }
  }

  @Override public int getItemCount() {
    return mItems != null ? mItems.size() : 0;
  }

  public interface OnGvItemClickListener {
    void onGvItemClick(ViewHolder holder, int position);
  }

  public void setOnGvItemClickListener(OnGvItemClickListener onGvItemClickListener) {
    mOnGvItemClickListener = onGvItemClickListener;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public GyroscopeImageView gImg;
    private TextView imgName;

    private ViewHolder(View itemView) {
      super(itemView);
      this.gImg = itemView.findViewById(R.id.gv_image_view);
      this.imgName = itemView.findViewById(R.id.tv_img_name);
    }
  }

  @Override
  public void onMove(int fromPosition, int toPosition) {
    if (fromPosition == mItems.size() - 1 || toPosition == mItems.size() - 1) {
      return;
    }
    if (fromPosition < toPosition) {
      for (int i = fromPosition; i < toPosition; i++) {
        Collections.swap(mItems, i, i + 1);
      }
    } else {
      for (int i = fromPosition; i > toPosition; i--) {
        Collections.swap(mItems, i, i - 1);
      }
    }
    notifyItemMoved(fromPosition, toPosition);
  }

  @Override
  public void onSwiped(int position) {
    mItems.remove(position);
    notifyItemRemoved(position);
  }

}