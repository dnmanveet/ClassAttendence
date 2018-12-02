package com.example.android.classattendence;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Marking extends AppCompatActivity {

   MyCustomAdapter dataAdapter = null;
   public int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marking);

        String message = getIntent().getStringExtra("key");
        setTitle(message);

        //Generate list View from ArrayList
        displayListView();

        checkButtonClick();
    }

    private void displayListView() {

        //Array list of countries
        ArrayList<Student> StudentList = new ArrayList<Student>();
        Student Student = new Student("Vivek", "1","17H61A1201", false);
        StudentList.add(Student);
        Student = new Student("A.SaiAkhil", "2", "17H61A1202", false);
        StudentList.add(Student);
        Student = new Student("Nikitha", "3", "17H61A1203",false);
        StudentList.add(Student);
        Student = new Student("Santhoshini", "4","17H61A1204", false);
        StudentList.add(Student);
        Student = new Student("Chandana", "5","17H61A1205", false);
        StudentList.add(Student);
        Student = new Student("Prajwal", "6", "17H61A1206",false);
        StudentList.add(Student);
        Student = new Student("D Nikhil", "7", "17H61A1207",false);
        StudentList.add(Student);
        Student = new Student("D Rohan", "8", "17H61A1208",false);
        StudentList.add(Student);
        Student = new Student("D Naga Manveet", "9", "17H61A1209",false);
        StudentList.add(Student);
        Student = new Student("D Meghana", "10", "17H61A1210",false);
        StudentList.add(Student);
        Student = new Student("Chandralekha", "11", "17H61A1211",false);
        StudentList.add(Student);
        Student = new Student("Yashwanth", "12", "17H61A1212",false);
        StudentList.add(Student);


        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.row_layout, StudentList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                Student Student = (Student) parent.getItemAtPosition(position);
                CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkBox1);
                checkBox.performClick();

            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Student> {

        private ArrayList<Student> StudentList;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Student> StudentList) {
            super(context, textViewResourceId, StudentList);
            this.StudentList = new ArrayList<Student>();
            this.StudentList.addAll(StudentList);
        }

        private class ViewHolder {
            TextView code;
            TextView id;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MyCustomAdapter.ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.row_layout, null);

                holder = new MyCustomAdapter.ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.id = (TextView)convertView.findViewById(R.id.id);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Student Student = (Student) cb.getTag();
                        if(cb.isChecked()){
                            count++;
                        }
                        else{
                            count--;
                        }
//                        Toast.makeText(getApplicationContext(), "Clicked on Checkbox: " + count, Toast.LENGTH_SHORT).show();
                        Student.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (MyCustomAdapter.ViewHolder) convertView.getTag();
            }

            Student Student = StudentList.get(position);
            holder.code.setText( Student.getCode());
            holder.id.setText(Student.getIdnum());
            holder.name.setChecked(Student.isSelected());
            holder.name.setTag(Student);

            return convertView;

        }

    }

    private void checkButtonClick() {


        FloatingActionButton myButton = (FloatingActionButton) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Student> StudentList = dataAdapter.StudentList;
                for (int i = 0; i < StudentList.size(); i++) {
                    Student Student = StudentList.get(i);
                    if (Student.isSelected()) {
                        responseText.append("\n" + Student.getCode());
                    }
                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Marking.this);
                alertDialogBuilder.setMessage("Presentence : " + count +"\n"+"Absentece : " + String.valueOf(14-count));
                        alertDialogBuilder.setPositiveButton("Submit",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Toast.makeText(Marking.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("Edit",null);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


//                Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();

            }
        });

    }
}
