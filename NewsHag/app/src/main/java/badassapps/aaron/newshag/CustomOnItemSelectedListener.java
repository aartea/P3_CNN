package badassapps.aaron.newshag;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by austin on 5/31/16.
 */
public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {

        Toast.makeText(parent.getContext(),
                "On Item Select : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
