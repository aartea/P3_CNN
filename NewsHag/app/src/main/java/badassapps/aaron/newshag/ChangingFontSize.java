package badassapps.aaron.newshag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChangingFontSize extends AppCompatActivity {
    private Spinner spinner;
    private Button button;

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

                Toast.makeText(ChangingFontSize.this,"On Button Click : " + String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_SHORT).show();
            }

        });
    }

//    public void ClickingLarge(View view) {
//        Toast.makeText(ChangingFontSize.this,
//                "Changing font size to large, see text above for reference point", Toast.LENGTH_SHORT).show();
//    }
//
//    public void ClickingMedium(View view) {
//        Toast.makeText(ChangingFontSize.this,
//                "Changing font size to medium, see text above for reference point", Toast.LENGTH_SHORT).show();
//    }
//
//    public void ClickingSmall(View view) {
//        Toast.makeText(ChangingFontSize.this,
//                "Changing font size to small, see text above for reference point", Toast.LENGTH_SHORT).show();
//    }
//
//    public void ClickingXSmall(View view) {
//        Toast.makeText(ChangingFontSize.this,
//                "Changing font size to x-small, see text above for reference point", Toast.LENGTH_SHORT).show();
//    }


}
