package filmrental.sokra.com.filmrental.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import filmrental.sokra.com.filmrental.EditActivity;
import filmrental.sokra.com.filmrental.MainActivity;
import filmrental.sokra.com.filmrental.R;
import filmrental.sokra.com.filmrental.modal.Film;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.Viewholder> {
    private int itemPosition;
    private List<Film> filmList;
    private AppCompatActivity context;
    public static final int EDIT_REQUEST_CODE=222;
    public FilmAdapter(List<Film> filmList, AppCompatActivity context) {
        this.filmList = filmList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.film_lists_items,viewGroup,false);
        return new FilmAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int i) {

        final Film film = filmList.get(i);
        viewholder.filmImage.setImageResource(film.getFilmImage());
        viewholder.title.setText(film.getTitle());
        viewholder.description.setText(film.getDescription());
        viewholder.viewCount.setText(film.getViewCount());
        viewholder.btnOption.setImageResource(film.getBtnOption());

        viewholder.btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context,viewholder.btnOption);
                popupMenu.getMenuInflater().inflate(R.menu.menu_option,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.edit:
                                //editRecyclerViewItem();
                                Intent intent = new Intent(context,EditActivity.class);
                                Film film1=filmList.get(viewholder.getAdapterPosition());
                                Bundle bundle=new Bundle();
                                bundle.putParcelable("data",film1);
                                intent.putExtras(bundle);
                                context.startActivityForResult(intent,EDIT_REQUEST_CODE);

                                return true;
                            case  R.id.remove:
                                //removeRecyclerViewItem();
                                int currentPosition = viewholder.getAdapterPosition();
                                filmList.remove(currentPosition);
                                notifyItemRemoved(currentPosition);
                                notifyItemRangeChanged(currentPosition, filmList.size());
                                Toast.makeText(context, "Removed "+currentPosition, Toast.LENGTH_SHORT).show();
                                return true;
                            default:return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.filmList.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView viewCount;
        private ImageView btnOption;
        private ImageView filmImage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            description = itemView.findViewById(R.id.txtDescription);
            viewCount = itemView.findViewById(R.id.txtCount);
            btnOption = itemView.findViewById(R.id.btnOption);
            filmImage = itemView.findViewById(R.id.filmImage);
        }
    }
    /*
     private void removeRecyclerViewItem(){
        int newPosition = viewholder.getAdapterPosition();
        filmList.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, filmList.size());
    }*/

    /*
    private void editRecyclerViewItem(){


    }*/
}