package sg.edu.rp.c346.id18014854.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.EditTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Step 1a: Get the user input from the EditText and store it in a variable
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int intGenderId = rgGender.getCheckedRadioButtonId();

                // Step 1b: Obtain an instance of the SharedPerferences
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                // Step 1c: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                // Step 1d: Add the key-value pair
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", intGenderId);

                // Step 1e: Call commit() to save the changes into SharedPreferences
                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        // Step 2b: Retrieve the saved data from the SharedPreferences object
        String strName = prefs.getString("name", "Teren");
        float gpa = prefs.getFloat("gpa", 0);
        int intGenderId = prefs.getInt("genderId", R.id.radioGroupGender);
        // Step 2c: Update the UI element with the value
        etName.setText(strName);
        etGPA.setText(gpa + "");
        rgGender.check(intGenderId);
    }
}
