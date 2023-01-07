package com.example.c196project.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196project.Entity.Courses;
import com.example.c196project.Entity.Terms;
import com.example.c196project.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {


    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView CourseItemView;

        private CourseViewHolder(View itemView) {
            super(itemView);
            CourseItemView = itemView.findViewById(R.id.CourseItemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Courses current = mCourseList.get(position);
                    Intent intent = new Intent(context, CourseList.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("courseTermID", current.getCourseTermID());
                    intent.putExtra("termID", current.getCourseTermID());
                    context.startActivity(intent);

                }
            });
        }
    }

    private List<Courses> mCourseList;
    private final Context context;
    private final LayoutInflater mInflater;

    public CoursesAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_course_list, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if (mCourseList != null) {
            Courses current = mCourseList.get(position);
            String name = current.getCourseName();
            holder.CourseItemView.setText(name);
        } else {
            holder.CourseItemView.setText("No Course Name");
        }

    }

    public void setCourses(List<Courses> allCourses) {
        mCourseList = allCourses;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mCourseList != null) {
            return mCourseList.size();
        } else return 0;
    }
    }


