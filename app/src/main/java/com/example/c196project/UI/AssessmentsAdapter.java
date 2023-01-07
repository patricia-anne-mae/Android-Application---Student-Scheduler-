package com.example.c196project.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196project.Entity.Assessments;
import com.example.c196project.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentViewHolder> {
   class AssessmentViewHolder extends RecyclerView.ViewHolder {
       private final TextView AssessmentItemView;

       private AssessmentViewHolder(View itemView) {
           super(itemView);
           AssessmentItemView = itemView.findViewById(R.id.AssessmentItemView);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int position = getAdapterPosition();
                   final Assessments current = mAssessmentList.get(position);
                   Intent intent = new Intent(context, AssessmentList.class);
                   intent.putExtra("assessmentID", current.getAssessmentID());
                   intent.putExtra("assessmentCourseID", current.getCourseID());
                   intent.putExtra("courseID", current.getCourseID());
                   context.startActivity(intent);
               }
           });
       }
   }

   private List<Assessments> mAssessmentList;
   private final Context context;
   private final LayoutInflater mInflater;

   public AssessmentsAdapter(Context context) {
       mInflater = LayoutInflater.from(context);
       this.context = context;
   }

    @NonNull
    @Override
    public AssessmentsAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_assessment_list, parent, false);
       return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentsAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessmentList != null){
            Assessments current = mAssessmentList.get(position);
            String name = current.getAssessmentName();
            holder.AssessmentItemView.setText(name);
        } else {
            holder.AssessmentItemView.setText("No Term Name");
        }
    }

    @Override
    public int getItemCount() {
        if (mAssessmentList != null) {
            return mAssessmentList.size();
        }
        else return 0;
    }

    public void setAssessments(List<Assessments> allAssessments) {
        mAssessmentList = allAssessments;
        notifyDataSetChanged();
    }
}
