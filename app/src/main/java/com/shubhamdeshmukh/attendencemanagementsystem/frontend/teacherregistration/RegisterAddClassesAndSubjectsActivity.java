package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseDBConnection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class RegisterAddClassesAndSubjectsActivity extends AppCompatActivity {


    public static ArrayList<Class> classList;
    public static ArrayList<Subject> subjectList;
    private boolean toggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_classes_and_subjects);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton  fab1 = findViewById(R.id.add);
        CardView fab2 = findViewById(R.id.addSubject);
        CardView fab3 = findViewById(R.id.add_class);

        RecyclerView classRecyclerView = findViewById(R.id.register_class_recycler);
        RecyclerView subjectRecyclerView = findViewById(R.id.register_subject_recycler);

        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDBConnection dbConnection = new FirebaseDBConnection(MainActivity.database, MainActivity.mAuth);
        classList = dbConnection.getFetchedData().classes;
        subjectList = dbConnection.getFetchedData().subjects;

        ClassRegisterRecyclerAdapter classRegisterRecyclerAdapter = new ClassRegisterRecyclerAdapter(this,classList);
        classRecyclerView.setAdapter(classRegisterRecyclerAdapter);
        SubjectRegisterRecyclerAdapter subjectRecyclerAdapter = new SubjectRegisterRecyclerAdapter(this, subjectList);
        subjectRecyclerView.setAdapter(subjectRecyclerAdapter);


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle)
                {
                    fab2.setVisibility(View.GONE);
                    fab3.setVisibility(View.GONE);
                }
                else {
                    fab2.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.VISIBLE);
                }
                toggle = !toggle;
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterSubjectInfoAndCategoryActivity.class);
                startActivity(intent);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {

            Intent intent = new Intent(getApplicationContext(), RegisterClassInfoAndBatchesActivity.class);
            @Override
            public void onClick(View view) {
                    startActivity(intent);
            }
        });
    }
}