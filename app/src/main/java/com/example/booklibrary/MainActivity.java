package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddFragment.IAddNewBook,RecViewAdapter.IDeleteBook {
    private RecyclerView allBooksRecView;
    private RecViewAdapter adapter = new RecViewAdapter(this);
    private ArrayList<Book> allBooks;
    private Book addedNewBook;
    SQLDatabaseHelper databaseHelper = new SQLDatabaseHelper(MainActivity.this);
    private SQLiteDatabase database;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        DatabaseAsyncTask dbAsyncTask = new DatabaseAsyncTask();
        dbAsyncTask.execute();

        allBooksRecView.setAdapter(adapter);
        allBooksRecView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }

    @Override
    public void onAddNewBook(Book newBook) {
        databaseHelper.insert(database,"library", newBook);

        DatabaseAsyncTask dbAsyncTask = new DatabaseAsyncTask();
        dbAsyncTask.execute();
    }

    @Override
    public void onDeleteBook(String bookName) {
        databaseHelper.delete(database,"library",bookName);

        DatabaseAsyncTask dbAsyncTask = new DatabaseAsyncTask();
        dbAsyncTask.execute();
    }

    private void init() {
        allBooksRecView = findViewById(R.id.bookRecView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.uprightmenu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addButton:
                AddFragment addDialog = new AddFragment(database);
                addDialog.show(getSupportFragmentManager(), "Add a new book to your library");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DatabaseAsyncTask extends AsyncTask<Void, Void, ArrayList<Book>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            databaseHelper = new SQLDatabaseHelper(MainActivity.this);
            allBooks = new ArrayList<>();
        }

        @Override
        protected ArrayList<Book> doInBackground(Void... voids) {
            try {
                database = databaseHelper.getReadableDatabase();
                cursor = database.query("library", new String[]{"name",
                                "author", "language", "pages", "shortDescription", "longDescription", "imageUrl"},
                        null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        allBooks.add(new Book(cursor.getString(0), cursor.getString(1),
                                cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                                cursor.getString(5), cursor.getString(6)));
                        cursor.moveToNext();
                    }
                }

                return allBooks;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);

            adapter.setBooks(books);
        }
    }
}
