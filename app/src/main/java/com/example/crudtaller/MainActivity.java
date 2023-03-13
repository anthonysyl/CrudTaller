package com.example.crudtaller;

import static com.example.crudtaller.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    EditText fname,lname;
    Button submit,display,edit;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBmain=new DBmain(this);
        findid();
        insertData();
        editData();
    }

    private void editData(){
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle=getIntent().getBundleExtra("userdata");
            id=bundle.getInt("id");
            fname.setText(bundle.getString("fname"));
            lname.setText(bundle.getString("lname"));
            edit.setVisibility((View.VISIBLE));
            submit.setVisibility(View.GONE);

        }
    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv= new ContentValues();
                cv.put("fname",fname.getText().toString());
                cv.put("lname",lname.getText().toString());
                sqLiteDatabase=dBmain.getWritableDatabase();
                Long recinsert=sqLiteDatabase.insert(TABLENAME,null,cv);
                if(recinsert!=null)
                {
                    Toast.makeText(MainActivity.this,"Te has registrado correctamente",Toast.LENGTH_SHORT).show();
                    fname.setText("");
                    lname.setText("");
                }else
                {
                    Toast.makeText(MainActivity.this,"Oh, lo lamento no pudistes registrarte",Toast.LENGTH_SHORT).show();
                }

            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DisplayData.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ContentValues cv=new ContentValues();
                cv.put("fname",fname.getText().toString());
                cv.put("lname",lname.getText().toString());
                sqLiteDatabase=dBmain.getReadableDatabase();
                long recedit=sqLiteDatabase.update(TABLENAME, cv,"id="+id, null );
                if(recedit!=1)
                {
                    Toast.makeText(MainActivity.this, "Los datos se han subido de manera correcta",Toast.LENGTH_SHORT).show();
                    submit.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                }else
                {
                    Toast.makeText(MainActivity.this, "Se modifico correctamente",Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    private void findid() {
        fname=(EditText)findViewById(R.id.edit_fname);
        lname=(EditText) findViewById(R.id.edit_lname);
        submit=(Button) findViewById(R.id.submit_btn);
        display=(Button) findViewById(R.id.display_btn);
        edit=(Button) findViewById(R.id.edit_btn);
    }
}