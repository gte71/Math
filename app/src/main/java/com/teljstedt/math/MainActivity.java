package com.teljstedt.math;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.teljstedt.math.persistance.AppViewModel;
import com.teljstedt.math.persistance.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final int QUIZ_ACTIVITY_REQUEST_CODE = 1;
    public static final int SETTIGS_ACTIVITY_REQUEST_CODE = 2;
    public static final String QUIZTYPE = "com.teljstedt.math.quiztype";
    public static final String USERTYPE = "com.teljstedt.math.usertype";
    public static final String WEEKTYPE = "com.teljstedt.math.weektype";
    public static String quiz = "";
    private String week;
    private static User currentUser;
    private AppViewModel appViewModel;
    private List<User> userList;
    private ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList = new ArrayList<>();
        userList.add(new User(0, "Clara", 0));

        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                // update the cached copy of user in the adapter
                userList = users;
            }
        });

        week = new SimpleDateFormat("w").format(new Date());

        // userListSpinner
        if (userList != null) {
            userList.forEach(user -> nameList.add(user.name));
        }
        Spinner userSpinner = (Spinner) findViewById(R.id.userSpinner);
        ArrayAdapter<String> userSpinnerAdapter = new ArrayAdapter<String>(this
                , R.layout.support_simple_spinner_dropdown_item
                , nameList);
        userSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        userSpinner.setAdapter(userSpinnerAdapter);
        userSpinner.setOnItemSelectedListener(this);
        if (!currentUser.name.isEmpty()) {
            userSpinner.setSelection(nameList.indexOf(currentUser.name));
        } else {

        }

        // quizListSpinner
        ArrayList<String> quizList = getQuizList();
        Spinner quizSpinner = (Spinner) findViewById(R.id.quizSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this
                , R.layout.support_simple_spinner_dropdown_item
                , quizList);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        quizSpinner.setAdapter(spinnerAdapter);
        quizSpinner.setOnItemSelectedListener(this);
        if (!quiz.isEmpty()) {
            quizSpinner.setSelection(quizList.indexOf(quiz));
        } else {
            quiz = (String) quizSpinner.getItemAtPosition(0);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        quiz = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    ArrayList<String> getQuizList() {
        ArrayList<String> quizList = new ArrayList<>();
        quizList.add("Addition");
        quizList.add("Subtraktion");
        quizList.add("Multiplikation");
        quizList.add("Blandat");
        return quizList;
    }

    public void goQuiz(View view) {
        Intent intent = new Intent(this, quizActivity.class);
        intent.putExtra(QUIZTYPE, quiz);
        intent.putExtra(USERTYPE, currentUser.uid);
        intent.putExtra(WEEKTYPE, week);
        startActivity(intent);
    }

    public void goSettings(View view) {
        Intent intent = new Intent(this, settingsActivity.class);
        startActivity(intent);
    }
}
