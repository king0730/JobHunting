package com.yee.jobhunting.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.yee.jobhunting.R;
import com.yee.jobhunting.activity.JobDetailsActivity;
import com.yee.jobhunting.bean.AddJob;

import java.util.List;

public class JobDetailsAdapter extends RecyclerView.Adapter<JobDetailsAdapter.ViewHolder> {

    private List<AddJob> mAddJobList;



    static public class ViewHolder extends RecyclerView.ViewHolder{
        View jobView;
        TextView jobTitle,jobSalary,jobEducation,jobExperience,companyTitle,companyLocation,jobDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobView = itemView;
            jobTitle = itemView.findViewById(R.id.job_details_job_title);
            jobSalary = itemView.findViewById(R.id.job_details_job_salary);
            jobEducation = itemView.findViewById(R.id.job_details_job_education);
            jobExperience = itemView.findViewById(R.id.job_details_job_experience);
            companyTitle = itemView.findViewById(R.id.job_details_company_title);
            companyLocation = itemView.findViewById(R.id.job_details_company_location);
            jobDescription = itemView.findViewById(R.id.job_details_job_description);


        }
    }

    public JobDetailsAdapter(FragmentActivity activity, List<AddJob> addJobList) {
        mAddJobList = addJobList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_details, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.jobView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int position = holder.getAdapterPosition();
                AddJob addJob = mAddJobList.get(position);
                Toast.makeText(v.getContext(),"点击"+addJob.getJobTitle(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), JobDetailsActivity.class);
                v.getContext().startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AddJob addJob = mAddJobList.get(position);
        holder.jobTitle.setText(addJob.getJobTitle());
        holder.jobSalary.setText(addJob.getJobSalary());
        holder.jobEducation.setText(addJob.getJobEducation());
        holder.jobExperience.setText(addJob.getJobExperience());
        holder.companyTitle.setText(addJob.getCompanyTitle());
        holder.companyLocation.setText(addJob.getCompanyLocation());
        holder.jobDescription.setText(addJob.getJobDescription());


    }

    @Override
    public int getItemCount() {
        return mAddJobList.size();
    }

}
