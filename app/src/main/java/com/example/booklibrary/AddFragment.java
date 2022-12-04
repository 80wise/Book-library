package com.example.booklibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddFragment extends DialogFragment {
    private EditText name;
    private EditText author;
    private EditText language;
    private EditText pages;
    private EditText shortDesc;
    private EditText longDesc;
    private EditText url;
    private Button addBtn;
    private Button cancelBtn;

    // in order to pass back the new book to the parent activity after clicking on Add button (call back)
    public interface IAddNewBook{
        void onAddNewBook(Book newBook);
    }
    public IAddNewBook iAddNewBook;

    private  SQLiteDatabase database;
    public AddFragment(SQLiteDatabase db){
        this.database = db;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.add_dialog_layout,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);
        builder.setTitle("Enter the data of the book");
        builder.setView(view);

        init(view);
        setOnClickListeners();

        return builder.create();
    }
    private void init(View view){
        name = view.findViewById(R.id.inputName);
        author = view.findViewById(R.id.inputAuthor);
        language = view.findViewById(R.id.inputLanguage);
        pages = view.findViewById(R.id.inputPages);
        shortDesc = view.findViewById(R.id.inputShortDesc);
        longDesc = view.findViewById(R.id.inputLongDesc);
        url = view.findViewById(R.id.inputUrl);
        addBtn = view.findViewById(R.id.add_btn);
        cancelBtn = view.findViewById(R.id.cancel_btn);

    }
    private void setOnClickListeners(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*SQLDatabaseHelper.insert(database,"library",String.valueOf(name.getText()),
                        String.valueOf(author.getText()), String.valueOf(language.getText()),
                        Integer.valueOf(String.valueOf(pages.getText())),
                        String.valueOf(shortDesc.getText()),String.valueOf(longDesc.getText()),
                        String.valueOf(url.getText()));*/

                //to get our new book
                try {
                    iAddNewBook = (IAddNewBook) getActivity();
                    iAddNewBook.onAddNewBook(new Book(String.valueOf(name.getText()),
                            String.valueOf(author.getText()), String.valueOf(language.getText()),
                            Integer.valueOf(String.valueOf(pages.getText())),
                            String.valueOf(shortDesc.getText()),String.valueOf(longDesc.getText()),
                            String.valueOf(url.getText())));

                    dismiss();
                }catch (ClassCastException ex){
                    ex.printStackTrace();
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
