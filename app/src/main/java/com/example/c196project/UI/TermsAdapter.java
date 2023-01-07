package com.example.c196project.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196project.Entity.Terms;
import com.example.c196project.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.TermViewHolder> {
    class TermViewHolder extends RecyclerView.ViewHolder {

        private final TextView ItemViewTermName;


        private TermViewHolder(View itemView) {
            super(itemView);
            ItemViewTermName = itemView.findViewById(R.id.TermNameItemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Terms current = mTermsList.get(position);
                    Intent intent = new Intent(context, TermsList.class);
                    intent.putExtra("termID", current.getTermID());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Terms> mTermsList;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermsAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_term_list, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if (mTermsList != null) {
            Terms current = mTermsList.get(position);
            String name = current.getTermName();
            holder.ItemViewTermName.setText(name);

        } else {
            holder.ItemViewTermName.setText("No term name found");
        }

    }

    @Override
    public int getItemCount() {
        if (mTermsList != null) {
            return mTermsList.size();
        } else return 0;
    }

    public void setTerms(List<Terms> allTerms) {
        mTermsList = allTerms;
        notifyDataSetChanged();
    }

}


