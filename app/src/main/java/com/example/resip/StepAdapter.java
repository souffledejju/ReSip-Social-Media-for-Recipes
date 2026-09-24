package com.example.resip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private List<Step> stepList;

    public StepAdapter(List<Step> stepList) {
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Step currentStep = stepList.get(position);

        holder.txtStepTitle.setText(currentStep.getTitle());
        holder.txtStepTime.setText(currentStep.getTime());
        holder.txtStepDetail.setText(currentStep.getDetail());

        holder.stepHeader.setOnClickListener(v -> {
            if (holder.expandedContent.getVisibility() == View.GONE) {
                holder.expandedContent.setVisibility(View.VISIBLE);
                holder.imgArrow.setRotation(180f);
            } else {
                holder.expandedContent.setVisibility(View.GONE);
                holder.imgArrow.setRotation(0f);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stepList != null ? stepList.size() : 0;
    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout stepHeader;
        LinearLayout expandedContent;
        TextView txtStepTitle, txtStepTime, txtStepDetail;
        ImageView imgArrow;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepHeader = itemView.findViewById(R.id.stepHeader);
            expandedContent = itemView.findViewById(R.id.expandedContent);
            txtStepTitle = itemView.findViewById(R.id.txtStepTitle);
            txtStepTime = itemView.findViewById(R.id.txtStepTime);
            txtStepDetail = itemView.findViewById(R.id.txtStepDetail);
            imgArrow = itemView.findViewById(R.id.imgArrow);
        }
    }
}