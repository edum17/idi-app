package com.example.german.librorecetas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class NuevaReceta extends ActionBarActivity {

    private String APP_DIRECTORY = "myPictureApp";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    private ImageView ImagenView;
    Button btFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_receta);
        //findViewById(R.id.bFoto);
        //ImagenView = (ImageView) findViewById(R.id.iVFoto);
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

    public void makePicture(View v) {
        btFoto = (Button) findViewById(R.id.bFoto);

        final CharSequence[] options = {"Hacer foto","Elegir de galeria","Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(NuevaReceta.this);
        builder.setTitle("Elige una opcion");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            //Escucha la opcion del dialogo que hemos elegido
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (options[seleccion] == "Hacer foto") {
                    openCamera();
                } else if (options[seleccion] == "Elegir de galeria") {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona imagen"), SELECT_PICTURE);
                } else if (options[seleccion] == "Cancelar") {
                    dialog.dismiss(); //El dialogo se deshace
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    String dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitMap(dir); //Decodifica la imagen para presentarsela al usuario
                }
            break;
            case SELECT_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri path = data.getData();
                    ImagenView.setImageURI(path);
                }
            break;
        }
    }

    private void decodeBitMap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        ImagenView = (ImageView) findViewById(R.id.iVFoto);
        ImagenView.setImageBitmap(bitmap);
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();
        String path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); //Mediante este llamada se abirar la camara y captura la imagen
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile)); //Para almacenar imagen o video
        startActivityForResult(intent,PHOTO_CODE);
    }
}