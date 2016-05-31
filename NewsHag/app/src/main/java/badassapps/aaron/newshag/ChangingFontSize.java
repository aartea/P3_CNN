package badassapps.aaron.newshag;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChangingFontSize extends AppCompatActivity {
    private Spinner spinner;
    private Button button;
    private TextView changingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_font_size);

        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<>();
        list.add("Large");
        list.add("Medium");
        list.add("Small");
        list.add("X-Small");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        addListenerOnSpinnerItemSelection();

        changingText = (TextView) findViewById(R.id.changingText);
        changingText.setMovementMethod(new ScrollingMovementMethod());

        addListenerOnButton();


    }

    public void addListenerOnSpinnerItemSelection() {

        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.spinner);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                changingText = (TextView) findViewById(R.id.changingText);


                Toast.makeText(ChangingFontSize.this,"On Button Click : " + String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_SHORT).show();




            }

        });
    }


}
