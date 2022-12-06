package com.course.msp.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

<<<<<<< HEAD:app/src/main/java/com/course/msp/ui/gallery/GalleryViewModel.java
import com.course.msp.R;
import com.course.msp.controller.Foods;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {
=======
public class ListViewModel extends ViewModel {
>>>>>>> 29f17ed5b438647a9c9c08c9eae1225de320dbc1:app/src/main/java/com/course/msp/ui/list/ListViewModel.java


<<<<<<< HEAD:app/src/main/java/com/course/msp/ui/gallery/GalleryViewModel.java
    public GalleryViewModel() {
=======
    public ListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
>>>>>>> 29f17ed5b438647a9c9c08c9eae1225de320dbc1:app/src/main/java/com/course/msp/ui/list/ListViewModel.java
    }

}