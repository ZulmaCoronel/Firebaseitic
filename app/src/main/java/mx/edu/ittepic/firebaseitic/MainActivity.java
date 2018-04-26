package mx.edu.ittepic.firebaseitic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class  MainActivity extends AppCompatActivity {

    Button btnlogin,btnsignup;
    EditText username, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = findViewById(R.id.login);
        btnsignup = findViewById(R.id.signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        firebaseAuth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                }catch (Exception e){
                    Log.i("Error: ", e.getMessage());
                }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signup();
                }catch (Exception e){
                    Log.i("Error: ", e.getMessage());
                }
            }
        });
    }

    public void signup(){

        String user = username.getText().toString();
        String pass = password.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Oops! Su registro falló ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void login(){
        String user = username.getText().toString();
        String pass = password.getText().toString();

        try {
            firebaseAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sesión Iniciada", Toast.LENGTH_SHORT).show();
                        Intent alumnosActivity = new Intent(MainActivity.this, AlumnosDB.class);
                        MainActivity.this.startActivity(alumnosActivity);
                    }else{
                        Toast.makeText(MainActivity.this, "Oops! no se pudo iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (Exception e){
            Log.i("ERROR",e.getMessage(),e.getCause());
        }

    }

}
