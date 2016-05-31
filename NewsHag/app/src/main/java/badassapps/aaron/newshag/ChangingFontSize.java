package badassapps.aaron.newshag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChangingFontSize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_font_size);
    }

    public void ClickingLarge(View view) {
        Toast.makeText(ChangingFontSize.this,
                "Changing font size to large, see text above for ref", Toast.LENGTH_SHORT).show();
    }

    public void ClickingMedium(View view) {
        Toast.makeText(ChangingFontSize.this,
                "Changing font size to medium, see text above for ref", Toast.LENGTH_SHORT).show();
    }

    public void ClickingSmall(View view) {
        Toast.makeText(ChangingFontSize.this,
                "Changing font size to small, see text above for ref", Toast.LENGTH_SHORT).show();
    }

    public void ClickingXSmall(View view) {
        Toast.makeText(ChangingFontSize.this,
                "Changing font size to x-small, see text above for ref", Toast.LENGTH_SHORT).show();
    }
}
