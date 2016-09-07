package mx.octaviocervantes.contactos;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.design.*;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DatosContacto extends AppCompatActivity {

    int REQUEST_CODE = 1;
    ArrayList<String> datos;
    Contacto datoContacto;

    TextInputEditText txtNombre;
    TextView txtFecha;
    TextInputEditText txtTelefono;
    TextInputEditText txtCorreo;
    TextInputEditText txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto_datos);

        Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        datos = new ArrayList<String>();

        txtNombre = (TextInputEditText) findViewById(R.id.txtNombre);
        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtTelefono = (TextInputEditText) findViewById(R.id.txtTelefono);
        txtCorreo = (TextInputEditText) findViewById(R.id.txtCorreo);
        txtDescripcion = (TextInputEditText) findViewById(R.id.txtDescripcion);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datos.size() > 0)
                    datos.clear();

                datoContacto = new Contacto(txtNombre.getText().toString(), txtFecha.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString(), txtDescripcion.getText().toString());

                datos.add(datoContacto.getNombreContacto());
                datos.add(datoContacto.getFechaContacto());
                datos.add(datoContacto.getTelefonoContacto());
                datos.add(datoContacto.getCorreoContacto());
                datos.add(datoContacto.getDescripcionContacto());

                Intent intent = new Intent(DatosContacto.this, ConfirmarDatos.class);
                intent.putExtra(getResources().getString(R.string.pDatos),datos);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle params;

        //Comprobar de qué petición se está obteniendo el resultado (requestCode)
        if(requestCode==1) {
            //Comprobar el tipo de resultado (resultCode)
            if(resultCode==ConfirmarDatos.RESULT_OK) {
                params = data.getExtras();

                //Operaciones a realizar si se ha pulsado un botón "Aceptar"
                txtNombre.setText(params.getStringArrayList(getResources().getString(R.string.pDatos)).get(0));
                txtFecha.setText(params.getStringArrayList(getResources().getString(R.string.pDatos)).get(1));
                txtTelefono.setText(params.getStringArrayList(getResources().getString(R.string.pDatos)).get(2));
                txtCorreo.setText(params.getStringArrayList(getResources().getString(R.string.pDatos)).get(3));
                txtDescripcion.setText(params.getStringArrayList(getResources().getString(R.string.pDatos)).get(4));

            } else {
                //Operaciones a realizar si se ha pulsado un botón "Cancelar" o "Retroceder"
                txtNombre.setText(datoContacto.getNombreContacto());
                txtFecha.setText(datoContacto.getFechaContacto());
                txtTelefono.setText(datoContacto.getTelefonoContacto());
                txtCorreo.setText(datoContacto.getCorreoContacto());
                txtDescripcion.setText(datoContacto.getDescripcionContacto());
            }
        }
    }

    //Cuadro para seleccionar fecha
    public void mostrarDialogoDeFecha(View v){
        DatePickerFragment newFragment = new DatePickerFragment(txtFecha);
        newFragment.show(getSupportFragmentManager(), "Seleccionar Fecha");

    }
}
