package filmrental.sokra.com.filmrental;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import filmrental.sokra.com.filmrental.adapter.FilmAdapter;
import filmrental.sokra.com.filmrental.modal.Film;
public class MainActivity extends AppCompatActivity implements FilmAdapter.getCurrentPosition{
    private TextView checkrst;
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
        setContentView(R.layout.activity_main);

        intiUI();
        getResult();
        addNew();
        newFilm.addAll(filmList);
        filmAdapter.notifyDataSetChanged();
    }

    private void addNew(){
        btnAdd = findViewById(R.id.btnSave);
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
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                doSearch(s);
                return true;
            }
        });
        return true;
    }
    List<Film> newFilm = new ArrayList<>();
    private void doSearch(String newText){
        newText = newText.toLowerCase();
        checkrst = findViewById(R.id.checkrst);
        if(newText.isEmpty()){
            checkrst.setVisibility(View.GONE);
            filmList.addAll(newFilm);
        }
        filmList.clear();
        for(Film film : newFilm){
            if (film.getTitle().toLowerCase().contains(newText)){
                filmList.add(film);
            }
            checkrst.setVisibility(View.GONE);
        }
        if (filmList.size()==0){
            checkrst.setVisibility(View.VISIBLE);
            filmAdapter.notifyDataSetChanged();
        }
        filmAdapter.notifyDataSetChanged();

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
