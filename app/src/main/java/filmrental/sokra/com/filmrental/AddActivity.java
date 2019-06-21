package filmrental.sokra.com.filmrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import filmrental.sokra.com.filmrental.modal.Film;

public class AddActivity extends AppCompatActivity {
    EditText title,description,viewCount;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_add);

        title = findViewById(R.id.editTitle);
        description = findViewById(R.id.editDescription);
        viewCount = findViewById(R.id.editViewcount);
        btnAdd = findViewById(R.id.btnSave);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Film film = new Film(title.getText().toString(),description.getText().toString(),viewCount.getText().toString(),R.drawable.newfilm,R.drawable.ic_more_vert_black_24dp);

                Intent addInten = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",film);
                addInten.putExtras(bundle);
                setResult(RESULT_OK,addInten);
                finish();

            }
        });




    }
}
