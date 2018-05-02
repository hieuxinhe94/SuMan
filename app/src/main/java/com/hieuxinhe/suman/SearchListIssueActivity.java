package com.hieuxinhe.suman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;

import com.hieuxinhe.suman.Adapter.IssueAdapter;
import com.hieuxinhe.suman.Model.Issue;

import java.util.ArrayList;

public class SearchListIssueActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private ListView lstIssue;
    private ArrayList<Issue> issueArrayList;
    private IssueAdapter issueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        issueAdapter=new IssueAdapter(SearchListIssueActivity.this, issueArrayList);
        lstIssue.setAdapter(issueAdapter);
        lstIssue.setTextFilterEnabled(true);
        setupSearchView();
    }


    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_search_list_issue;
    }

    @Override
    public void initView() {
        mSearchView=(SearchView) findViewById(R.id.txtsearchIssue);
        lstIssue=(ListView) findViewById(R.id.listIssue);
    }

    @Override
    public void initData() {
        issueArrayList=new ArrayList<Issue>();
        issueArrayList.add(new Issue("snake attack", "blue snake"));
        issueArrayList.add(new Issue("snake attack", "gold snake"));
        issueArrayList.add(new Issue("issue 01 ", "issue 01 describes..."));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (TextUtils.isEmpty(s)) {
            lstIssue.clearTextFilter();
        } else {
            lstIssue.setFilterText(s);
        }
        return true;
    }
}
