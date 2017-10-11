package com.anthrino.raspicontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private String[] functionList;
    private int selectedFn;
    private Spinner functionSelector;
    private Button submitButton;
    private TextView fragmentTitle;
    private ImageView fragmentImage;
    private VideoView fragmentVideo;
    private LinearLayout mediaLayout;
    private LinearLayout LEDLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        functionSelector = (Spinner) findViewById(R.id.functionSpinner);
        submitButton = (Button) findViewById(R.id.submit);
        fragmentTitle = (TextView) findViewById(R.id.fragmentTitle);
        fragmentImage = (ImageView) findViewById(R.id.fragmentImage);
        fragmentVideo = (VideoView) findViewById(R.id.fragmentVideo);
        mediaLayout = (LinearLayout) findViewById(R.id.mediaLayout);
        LEDLayout = (LinearLayout) findViewById(R.id.lightLayout);

        functionList = new String[]{"Select Function", "Media Server", "Light Control"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, functionList);
        functionSelector.setAdapter(adapter1);

        functionSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                if (index != 0) {
                    selectedFn = index;
                    if (index == 1) {
                        Toast.makeText(getApplicationContext(), "Media Streaming ", Toast.LENGTH_SHORT).show();
                        LEDLayout.setVisibility(View.GONE);
                        mediaLayout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Light Controls", Toast.LENGTH_SHORT).show();
                        mediaLayout.setVisibility(View.GONE);
                        LEDLayout.setVisibility(View.VISIBLE);
//                        studentAttendancePresenter.fetchData(prefs.getRollNo(), month, "");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (monthSelector.getSelectedItemPosition() != 0) {
//                    month = monthSelector.getSelectedItem().toString();
//                    if (courseSelector.getSelectedItemPosition() != 0 && monthSelector.getSelectedItemPosition() != 0) {
//                        month = monthSelector.getSelectedItem().toString();
//                        course = courseSelector.getSelectedItem().toString();
//                        studentAttendancePresenter.fetchData(prefs.getRollNo(), month, course);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Select Course", Toast.LENGTH_SHORT).show();
//                        studentAttendancePresenter.fetchData(prefs.getRollNo(), month, "");
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Select Month", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }


//    @Override
//    public void onResponse(AttendanceResponse attendanceResponse) {
//        Toast.makeText(getApplicationContext(), String.valueOf(attendanceResponse.isSuccess()), Toast.LENGTH_LONG).show();
//        TextView overall_tv = (TextView) findViewById(R.id.tvOverallAttendance);
//        TextView course_tv = (TextView) findViewById(R.id.tvCourseAttendance);
//        overall_tv.append(String.valueOf(attendanceResponse.getOverall_apc()) + "%");
//        course_tv.append(String.valueOf(attendanceResponse.getCourse_apc()) + "%");
//    }
}
