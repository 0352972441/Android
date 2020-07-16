package com.example.designgraphics.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designgraphics.Data.Book;
import com.example.designgraphics.PagerDisigner.Pager;
import com.example.designgraphics.R;
import com.example.designgraphics.RecyclerView.CardViewCustomes;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {

    ArrayList<Book> bookArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        createRecycler(view);

        return view;
    }

    public void createRecycler(View view){
        addList("one",R.drawable.titan);
        addList("one",R.drawable.titan);
        addList("one",R.drawable.titan);
        addList("one",R.drawable.titan);
        addList("one",R.drawable.titan);
        addList("one",R.drawable.titan);
        CardViewCustomes cardViewCustomes = new CardViewCustomes(bookArrayList);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(cardViewCustomes);
        RecyclerView recyclerView2 = (RecyclerView)view.findViewById(R.id.recyclerview2);
        recyclerView2.setAdapter(cardViewCustomes);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
    }

    private void addList(String title, int image){
        Book book = new Book(title,image);
        bookArrayList.add(book);
    }
}
