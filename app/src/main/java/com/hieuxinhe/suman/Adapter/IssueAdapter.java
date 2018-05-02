package com.hieuxinhe.suman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.hieuxinhe.suman.Model.Issue;
import com.hieuxinhe.suman.R;

import java.util.ArrayList;

public class IssueAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<Issue> issueArrayList;
    public ArrayList<Issue> orig;

    public IssueAdapter(Context context, ArrayList<Issue> issueArrayList) {
        this.context = context;
        this.issueArrayList = issueArrayList;

    }
    public class EmployeeHolder
    {
        TextView title;
        TextView des;
    }


    @Override
    public int getCount() {
        return issueArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return issueArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        EmployeeHolder holder;
        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.issue_row, viewGroup, false);
            holder=new EmployeeHolder();
            holder.title=(TextView) view.findViewById(R.id.txtTitle);
            holder.des=(TextView) view.findViewById(R.id.txtDes);
            view.setTag(holder);
        }
        else
        {
            holder=(EmployeeHolder) view.getTag();
        }

        holder.title.setText(issueArrayList.get(i).getTitle());
        holder.des.setText(String.valueOf(issueArrayList.get(i).getDes()));

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Issue> results = new ArrayList<Issue>();
                if (orig == null)
                    orig = issueArrayList;
                if (constraint != null) {
                    // TODO : CHANGE THIS CODE
                    if (orig != null && orig.size() > 0) {
                        for (final Issue g : orig) {
                            if (g.getTitle().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                issueArrayList = (ArrayList<Issue>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
