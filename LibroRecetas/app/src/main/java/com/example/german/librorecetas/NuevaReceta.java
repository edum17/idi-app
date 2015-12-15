package com.example.german.librorecetas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class NuevaReceta extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_receta);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_receta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Toast.makeText(getBaseContext(), "Receta guardada", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_cancel) {
            Toast.makeText(getBaseContext(),"No se ha guardado la receta",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_help) {
            Toast.makeText(getBaseContext(),"Has pulsado en Ayuda",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
