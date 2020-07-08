package com.chugunova.dreamstracker.currentdream;

public class CurrentDreamPresenter {

    private CurrentDreamFragment mView;

    public void onViewResumed(CurrentDreamFragment view) {
        mView = view;
    }
}
