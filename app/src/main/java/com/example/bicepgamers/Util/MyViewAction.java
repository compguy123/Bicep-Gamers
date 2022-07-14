package com.example.bicepgamers.Util;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;
//credit to : blade @ https://stackoverflow.com/questions/28476507/using-espresso-to-click-view-inside-recyclerview-item
//this is a helper class to click a button which inside a recycler viewer
// specifically it is to view a workout video
public class MyViewAction {

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

}