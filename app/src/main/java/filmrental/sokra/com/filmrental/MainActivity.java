package filmrental.sokra.com.filmrental;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import filmrental.sokra.com.filmrental.adapter.FilmAdapter;
import filmrental.sokra.com.filmrental.modal.Film;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rlvFilmBox;
    private List<Film> filmList = new ArrayList<>();
    private FilmAdapter filmAdapter;
    public static  final int CODE_REQUEST=111;
    public static final int EDIT_REQUEST_CODE=222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        setContentView(R.layout.activity_main);

        intiUI();
        getResult();

    }

    private void intiUI(){
        rlvFilmBox = findViewById(R.id.rvlFilmList);
        rlvFilmBox.setLayoutManager(new LinearLayoutManager(this));
        filmAdapter = new FilmAdapter(filmList,this);
        rlvFilmBox.setAdapter(filmAdapter);
    }

    private void getResult(){
        for(int i =0;i<5;i++){
            filmList.add( new Film("Tom and jery "+i,"Story description","1000K",R.drawable.film,R.drawable.ic_more_vert_black_24dp));
        }
        filmAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (CODE_REQUEST == requestCode && resultCode == RESULT_OK) {
            //get new Inbox from AddItemActivity
            if (EDIT_REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
                Film film = data.getParcelableExtra("data");
                this.filmList.set(1, film);
                filmAdapter.notifyItemChanged(1);
            }
        }
    }



}
