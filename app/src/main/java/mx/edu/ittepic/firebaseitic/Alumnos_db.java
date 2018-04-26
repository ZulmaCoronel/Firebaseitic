package mx.edu.ittepic.firebaseitic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Alumnos_db extends AppCompatActivity {

    //Firebase
    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();;
    TextView currentUser;
    DatabaseReference databaseReference;
    Button insertar;
    EditText nombre,ncontrol;

    ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    Alumno alumno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_db);
        //Shows Actual User
        listView = findViewById(R.id.lista);
        final String a=firebaseUser.getEmail();
        currentUser = findViewById(R.id.currentUser);
        currentUser.setText("Usuario actual: "+a);

        // For the DB
        databaseReference = FirebaseDatabase.getInstance().getReference().child("alumnos");

        //Insert seccion
        nombre = findViewById(R.id.nombre);
        ncontrol = findViewById(R.id.ncontrol);
        insertar= findViewById(R.id.btnInsert);
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString();
                String num = ncontrol.getText().toString();
                String id = databaseReference.push().getKey();
                Alumno alumno = new Alumno(nom,num);
                databaseReference.child(id).setValue(alumno);
                Toast.makeText(AlumnosDB.this, "Alumno registrado", Toast.LENGTH_SHORT).show();
            }
        });

        //Read seccion
        try {
            alumno = new Alumno();
            list = new ArrayList<>();
            adapter = new ArrayAdapter<String>(this,R.layout.item,R.id.info, list);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        alumno = ds.getValue(Alumno.class);
                        list.add(alumno.getNcontrol().toString()+" \n"+alumno.getNombre().toString());
                    }
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Log.i("ERROR",e.getMessage(),e.getCause());
        }

    }
}
