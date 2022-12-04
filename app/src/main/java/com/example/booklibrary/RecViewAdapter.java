package com.example.booklibrary;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {
    private static final String TAG = "RecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();

    private Context context;

    public RecViewAdapter(Context context) {
        this.context = context;
    }

    public RecViewAdapter() {
    }

    public interface IDeleteBook{
        void onDeleteBook(String bookName);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_book_rec_view, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookName.setText(books.get(position).getName());
        holder.shortDesc.setText(books.get(position).getShortDescription());
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.image);

        holder.parentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MoreDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(context.getString(R.string.book), books.get(position));
                intent.putExtra(context.getString(R.string.bundle),bundle);
                context.startActivity(intent);

            }
        });

        holder.parentCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Do you really want to delete this book ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try{
                                    IDeleteBook iDeleteBook = (IDeleteBook) context;
                                    iDeleteBook.onDeleteBook(books.get(position).getName());

                                }catch (ClassCastException e){
                                    e.printStackTrace();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                deleteAlert.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bookName;
        private ImageView image;
        private TextView shortDesc;
        private CardView parentCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.bookName);
            image = itemView.findViewById(R.id.bookImage);
            shortDesc = itemView.findViewById(R.id.shortDesc);
            parentCardView = itemView.findViewById(R.id.bookCardView);
        }
    }
}
