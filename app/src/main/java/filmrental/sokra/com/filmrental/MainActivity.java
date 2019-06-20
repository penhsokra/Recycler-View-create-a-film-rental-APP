package filmrental.sokra.com.filmrental;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import filmrental.sokra.com.filmrental.adapter.FilmAdapter;
import filmrental.sokra.com.filmrental.modal.Film;

public class MainActivity extends AppCompatActivity implements FilmAdapter.getCurrentPosition{
    private RecyclerView rlvFilmBox;
    private int itemPosition;
    private List<Film> filmList = new ArrayList<>();
    private FilmAdapter filmAdapter;
    public static  final int ADD_REQUEST_CODE=111;
    public static final int EDIT_REQUEST_CODE=222;
    FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        setContentView(R.layout.activity_main);

        intiUI();
        getResult();
        addNew();

    }

    private void addNew(){
        btnAdd = findViewById(R.id.btnAdd);
       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainActivity.this,AddActivity.class);
               startActivityForResult(i,ADD_REQUEST_CODE);
           }
       });
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

        MenuItem searchItems = menu.findItem(R.id.searchFilm);
        SearchView searchView = (SearchView) searchItems.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                filmList.clear();
                filmList.addAll(oldFilm);
                filmAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return true;
    }
    List<Film> oldFilm = new ArrayList<>();
    private void doSearch(String s){
        if(s.isEmpty()){
            return;
        }
        filmList.clear();
        for(Film film:oldFilm){
            if (film.getTitle().contains(s)){
                filmList.add(film);
            }
        }
        if (filmList.size()==0){
            Toast.makeText(this, "No data match 1", Toast.LENGTH_SHORT).show();
            filmAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            //get new Inbox from AddItemActivity
            if (EDIT_REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
                Film film = data.getParcelableExtra("data");
                this.filmList.set(this.itemPosition, film);
                filmAdapter.notifyItemChanged(itemPosition);
            }else if(ADD_REQUEST_CODE == requestCode && resultCode == RESULT_OK){
                Film film = data.getParcelableExtra("data");
                this.filmList.add(0,film);
                filmAdapter.notifyDataSetChanged();
                rlvFilmBox.smoothScrollToPosition(0);
        }

    }

    @Override
    public void getCurrentPosition(int position) {
        this.itemPosition=position;
    }
}
