package filmrental.sokra.com.filmrental;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import filmrental.sokra.com.filmrental.modal.Film;

public class EditActivity extends AppCompatActivity {
    EditText title,description,viewCount;
    Button btnSave;
    private Film film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.editTitle);
        description = findViewById(R.id.editDescription);
        viewCount = findViewById(R.id.editViewcount);
        btnSave = findViewById(R.id.btnSave);

        //get data from intent
        if(getIntent()!=null){
            film = getIntent().getParcelableExtra("data");
            title.setText(film.getTitle());
            description.setText(film.getDescription());
            viewCount.setText(film.getViewCount());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                film.setTitle(title.getText().toString());
                film.setDescription(description.getText().toString());
                film.setViewCount(viewCount.getText().toString());
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putParcelable("data", (Parcelable) film);
                intent.putExtras(bundle);

                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }
}
