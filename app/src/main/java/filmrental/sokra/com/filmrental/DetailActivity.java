package filmrental.sokra.com.filmrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import filmrental.sokra.com.filmrental.modal.Film;

public class DetailActivity extends AppCompatActivity {
    TextView title,count,des;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.txtTitle);
        count = findViewById(R.id.txtCount);
        des = findViewById(R.id.txtDescription);
        imageView = findViewById(R.id.imageView);
        if (getIntent() !=null){
            Film film = getIntent().getParcelableExtra("data");
            imageView.setImageResource(film.getFilmImage());
            title.setText(film.getTitle());
            des.setText(film.getDescription());
            count.setText(film.getViewCount());
        }
    }
}
