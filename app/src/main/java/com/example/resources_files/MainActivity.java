package com.example.resources_files;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    /**
     * @author Roey Schwartz rs7532@bs.amalnet.k12.il
     * @version 1
     * @since 6.11.2023
     * this code will show you a text as the user choice text from the raw text or text that the user wrote
     */

    TextView tv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);

    }

    /**
     * <p
     *      the function get a variable of View type,
     *      the function will show a text from a raw file
     * </>
     */
    public void rawFile_pressed(View view) {
        final String FILE = "rawtext.txt";

        String file = FILE.substring(0, FILE.length() - 4);
        int resourceId = this.getResources().getIdentifier(file, "raw", this.getPackageName());

        InputStream iS = this.getResources().openRawResource(resourceId);
        InputStreamReader iSR = new InputStreamReader(iS);
        BufferedReader bR = new BufferedReader(iSR);
        StringBuilder sB = new StringBuilder();
        String line;
        try {
            line = bR.readLine();
            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        tv.setText(sB.toString());
    }

    /**
     * <p
     *      the function get a variable of View type,
     *      the function will show a text that the user wrote in the edit text
     * </>
     */
    public void Text_pressed(View view) {
        tv.setText(et.getText());
    }

    public void credits_pressed(View view) {
        Intent si = new Intent(this, credits.class);
        startActivity(si);
    }
}