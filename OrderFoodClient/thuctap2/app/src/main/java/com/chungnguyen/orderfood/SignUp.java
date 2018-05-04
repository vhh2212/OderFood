package com.chungnguyen.orderfood;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chungnguyen.orderfood.Common.Common;
import com.chungnguyen.orderfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText edtPhone,edtName,edtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtPhone =  findViewById(R.id.edtPhone);
        edtName =  findViewById(R.id.edtName);
        edtPassword =  findViewById(R.id.edtPassword);

        btnSignUp =  findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference talbe_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this,"AA",Toast.LENGTH_SHORT).show();
                if (Common.isConnectedToInterner(getBaseContext())) {
                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("Vui lòng chờ");
                    mDialog.show();

                    talbe_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this, "Số điện thoại đã tồn tại", Toast.LENGTH_LONG).show();
                            } else {
                                mDialog.dismiss();
                                User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                                talbe_user.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Bạn đã đăng ký thành công", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    Toast.makeText(SignUp.this,"Hãy kiểm tra đường truyền Internet của bạn",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }
}
