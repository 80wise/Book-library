package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "library";

    public SQLDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "CREATE TABLE library (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT, author TEXT, language TEXT, pages INTEGER, shortDescription TEXT," +
                " longDescription TEXT, imageUrl TEXT)";
        sqLiteDatabase.execSQL(sqlCommand);

        String sqlInsert = "INSERT INTO library (name, author, language, pages, shortDescription, " +
                "longDescription, imageUrl) VALUES (\"The Death of the Heart\", \"Elizabeth Bowen\"," +
                "\"English\",418, \"The Death of the Heart is perhaps Elizabeth Bowen's best-known book.\"," +
                "\"The Death of the Heart is perhaps Elizabeth Bowen's best-known book." +
                " As she deftly and delicately exposes the cruelty that lurks behind the polished" +
                " surfaces of conventional society, Bowen reveals herself as a masterful novelist who" +
                " combines a sense of humor with a devastating gift for divining human motivations.\n" +
                "\n" +
                "In this piercing story of innocence betrayed set in the thirties," +
                " the orphaned Portia is stranded in the sophisticated and politely treacherous world" +
                " of her wealthy half-brother's home in London. There she encounters the attractive," +
                " carefree cad Eddie. To him, Portia is at once child and woman, and he fears her gushing love." +
                " To her, Eddie is the only reason to be alive. But when Eddie follows Portia to a sea-side resort," +
                " the flash of a cigarette lighter in a darkened cinema illuminates " +
                "a stunning romantic betrayal—and sets in motion one of the most moving and desperate" +
                " flights of the heart in modern literature\"," +
                "\"https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1497190244i/91494.jpg\");";
        sqLiteDatabase.execSQL(sqlInsert);

        String sqlInsert1 = "INSERT INTO library (name, author, language, pages, shortDescription, " +
                "longDescription, imageUrl) VALUES (\"Falconer\", \"John Cheever\",\"English\",211,\"Stunning and brutally powerful, Falconer tells the story of a man named Farragut," +
                " his crime and punishment.\", \"Stunning and brutally powerful, Falconer tells the story of a man named Farragut," +
                " his crime and punishment, and his struggle to remain a man in a universe bent on beating him backwards into childhood." +
                " Only John Cheever could deliver these grand themes with the irony," +
                " unforced eloquence, and exhilarating humor that make Falconer such a triumphant work of the moral imagination.\"," +
                "\"https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1339091605i/722367.jpg\");";
        sqLiteDatabase.execSQL(sqlInsert1);

        String sqlInsert2 = "INSERT INTO library (name, author, language, pages, shortDescription, " +
                "longDescription, imageUrl) VALUES (\"The Assistant\", \"Bernard Malamud\",\"English\",246, \"Bernard Malamud’s second novel, originally published in 1957," +
                " is the story of Morris Bober, a grocer in postwar Brooklyn, who “wants better” for himself and his family\"," +
                "\"Bernard Malamud’s second novel, originally published in 1957, is the story of Morris Bober," +
                " a grocer in postwar Brooklyn, who “wants better” for himself and his family." +
                " First two robbers appear and hold him up;" +
                " then things take a turn for the better when broken-nosed Frank Alpine becomes his assistant." +
                " But there are complications: Frank, whose reaction to Jews is ambivalent," +
                " falls in love with Helen Bober; at the same time he begins to steal from the store.\"," +
                "\"https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1411778913i/3068.jpg\");";
        sqLiteDatabase.execSQL(sqlInsert2);

        String sqlInsert3 = "INSERT INTO library (name, author, language, pages, shortDescription, " +
                "longDescription, imageUrl) VALUES (\"Loving\", \"Henry Green\",\"English\",224,\"One of his most admired works," +
                " Loving describes life above and below stairs in an Irish country house during the Second World War\"," +
                "\"One of his most admired works, Loving describes life above and below stairs in an Irish country house during the Second World War." +
                " In the absence of their employers the Tennants, the servants enact their own battles and conflict amid rumours about the war in Europe," +
                " invading one another's provinces of authority to create an anarchic environment of self-seeking behaviour," +
                " pilfering, gossip, and love.\"," +
                "\"https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1358882664i/771507.jpg\");";
        sqLiteDatabase.execSQL(sqlInsert3);

        String sqlInsert4 = "INSERT INTO library (name, author, language, pages, shortDescription, " +
                "longDescription, imageUrl) VALUES (\"Future Stories: What's Next?\", \"David Christian\",\"English\",368, \"The New York Times bestselling author of Origin Story," +
                " who Bill Gates has “long been a fan of,” turns his attention to the future of humanity\"," +
                "\"The New York Times bestselling author of Origin Story, who Bill Gates has “long been a fan of,” " +
                "turns his attention to the future of humanity — and how we think about it — in this ambitious book.\n" +
                "\n" +
                "The future is uncertain, a bit spooky, possibly dangerous, maybe wonderful. " +
                "We cope with this never-ending uncertainty by telling stories about the future, future stories." +
                " How do we construct those stories? Where is the future, the place where we set those stories?" +
                " Can we trust our future stories? And what sort of futures do they show us?\"," +
                "\"https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1649028995i/59227974.jpg\");";
        sqLiteDatabase.execSQL(sqlInsert4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static void insert(SQLiteDatabase db, String tableName, String name, String author, String language,
                       int pages, String shortDescription, String longDescription, String imageUrl) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("author", author);
        cv.put("language", language);
        cv.put("pages", pages);
        cv.put("shortDescription", shortDescription);
        cv.put("longDescription", longDescription);
        cv.put("imageUrl", imageUrl);
        db.insert(tableName, null, cv);
    }
    public void insert(SQLiteDatabase db, String tableName, Book book) {
        ContentValues cv = new ContentValues();
        cv.put("name", book.getName());
        cv.put("author", book.getAuthor());
        cv.put("language", book.getLanguage());
        cv.put("pages", book.getPages());
        cv.put("shortDescription", book.getShortDescription());
        cv.put("longDescription", book.getLongDescription());
        cv.put("imageUrl", book.getImageUrl());
        db.insert(tableName, null, cv);
    }

    public void delete(SQLiteDatabase db, String tableName, String name) {
        db.delete(tableName, "name=?", new String[]{name});
    }
}
