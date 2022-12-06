package com.course.msp.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.msp.R;
import com.course.msp.controller.DBActivity;
import com.course.msp.controller.Foods;
import com.course.msp.controller.MyAdapter;
import com.course.msp.databinding.FragmentGalleryBinding;

<<<<<<< HEAD:app/src/main/java/com/course/msp/ui/gallery/GalleryFragment.java
import java.util.ArrayList;

public class GalleryFragment extends Fragment {
=======
public class ListFragment extends Fragment {
>>>>>>> 29f17ed5b438647a9c9c08c9eae1225de320dbc1:app/src/main/java/com/course/msp/ui/list/ListFragment.java

    private FragmentGalleryBinding binding;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

<<<<<<< HEAD:app/src/main/java/com/course/msp/ui/gallery/GalleryFragment.java
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
=======
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListViewModel listViewModel =
                new ViewModelProvider(this).get(ListViewModel.class);
>>>>>>> 29f17ed5b438647a9c9c08c9eae1225de320dbc1:app/src/main/java/com/course/msp/ui/list/ListFragment.java

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

<<<<<<< HEAD:app/src/main/java/com/course/msp/ui/gallery/GalleryFragment.java
        Intent intent = new Intent(root.getContext(), DBActivity.class);
        startActivity(intent);

//        recyclerView = root.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//
//        Log.d("GalleryFragment ", getActivity().toString());
//        layoutManager = new LinearLayoutManager(root.getContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        ArrayList<Foods> foods = galleryViewModel.getFoods();
//
//        myAdapter = new MyAdapter(foods);
//        recyclerView.setAdapter(myAdapter);
=======
>>>>>>> 29f17ed5b438647a9c9c08c9eae1225de320dbc1:app/src/main/java/com/course/msp/ui/list/ListFragment.java

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}