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

import com.yee.jobhunting.activity.JobDetailsActivity;
import com.yee.jobhunting.bean.AddJob;
import com.yee.jobhunting.R;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private List<AddJob> mAddJobList;



    static public class ViewHolder extends RecyclerView.ViewHolder{
        View jobView;
        TextView jobTitle,jobSalary,jobEducation,jobExperience,companyTitle,companyLocation,jobDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobView = itemView;
            jobTitle = itemView.findViewById(R.id.job_title);
            jobSalary = itemView.findViewById(R.id.job_salary);
            jobEducation = itemView.findViewById(R.id.job_education);
            jobExperience = itemView.findViewById(R.id.job_experience);
            companyTitle = itemView.findViewById(R.id.company_title);
            companyLocation = itemView.findViewById(R.id.company_location);
            jobDescription = itemView.findViewById(R.id.job_description);


        }
    }

    public JobAdapter(FragmentActivity activity, List<AddJob> addJobList) {
        mAddJobList = addJobList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.jobView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int position = holder.getAdapterPosition();
                AddJob addJob = mAddJobList.get(position);

                Intent intent = new Intent(v.getContext(), JobDetailsActivity.class);

                //intent传值
                intent.putExtra("jobTitle",mAddJobList.get(position).getJobTitle());
                intent.putExtra("jobSalary",mAddJobList.get(position).getJobSalary());
                intent.putExtra("jobEducation",mAddJobList.get(position).getJobEducation());
                intent.putExtra("jobExperience",mAddJobList.get(position).getJobExperience());
                intent.putExtra("companyTitle",mAddJobList.get(position).getCompanyTitle());
                intent.putExtra("companyLocation",mAddJobList.get(position).getCompanyLocation());
                intent.putExtra("jobDescription",mAddJobList.get(position).getJobDescription());


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
