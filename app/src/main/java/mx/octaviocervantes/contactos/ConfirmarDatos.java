package mx.octaviocervantes.contactos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ConfirmarDatos extends AppCompatActivity {

    ArrayList<String> datos = new ArrayList<String>();
    private TextView txtNombreConfirmar;
    private TextView txtFechaConfirmar;
    private TextView txtTelefonoConfirmar;
    private TextView txtCorreoConfirmar;
    private TextView txtdescirpcionConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_confirmar);

        Button btnEditar = (Button) findViewById(R.id.btnEditarDatos);
        final Bundle parametros = getIntent().getExtras();
        datos = parametros.getStringArrayList(getResources().getString(R.string.pDatos));

        try {
            txtNombreConfirmar = (TextView) findViewById(R.id.txtNombreConfirmar);
            txtFechaConfirmar = (TextView) findViewById(R.id.txtFechaConfirmar);
            txtTelefonoConfirmar = (TextView) findViewById(R.id.txtTelefonoConfirmar);
            txtCorreoConfirmar = (TextView) findViewById(R.id.txtCorreoConfirmar);
            txtdescirpcionConfirmar = (TextView) findViewById(R.id.txtDescripcionConfirmar);

            txtNombreConfirmar.setText(datos.get(0));
            txtFechaConfirmar.setText(datos.get(1));
            txtTelefonoConfirmar.setText(datos.get(2));
            txtCorreoConfirmar.setText(datos.get(3));
            txtdescirpcionConfirmar.setText(datos.get(4));
        } catch (NullPointerException ex) {
            System.out.println("Error datos: " + ex.getMessage());
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmarDatos.this, DatosContacto.class);
                intent.putExtra(getResources().getString(R.string.pDatos), datos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    public void llamar(View view) {
        String telefono = txtTelefonoConfirmar.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }

    public void enviarCorreo(View view){
        String[] correo = {txtCorreoConfirmar.getText().toString()};
        Intent correoIntent = new Intent((Intent.ACTION_SEND));
        correoIntent.setData(Uri.parse("mailto:"));
        correoIntent.putExtra(Intent.EXTRA_EMAIL, correo);
        correoIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(correoIntent, "Enviar correo"));
    }
}
