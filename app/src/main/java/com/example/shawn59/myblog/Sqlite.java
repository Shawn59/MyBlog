package com.example.shawn59.myblog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Rec.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "record";

    // поля таблицы для хранения ФИО, Должности и Телефона (id формируется автоматически)
    //_id с подчеркиванием для работы с курсором
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_Title = "title";
    public static final String COLUMN_Text = "text";
    public static final String COLUMN_Blog_Id = "blog_id";


    // формируем запрос для создания базы данных
    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_Title
            + " text not null, " + COLUMN_Text + " text not null,"
            + COLUMN_Blog_Id + " integer not null" + ");";


    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //создаем таблицу
        db.execSQL(DATABASE_CREATE);

        //добавляем строку
        ContentValues initialValues = createContentValues("dsfsd","sdfds",1);
        db.insert(DATABASE_TABLE, null, initialValues);


    }

    //сработает если версия поменяется
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS record");
        onCreate(db);
    }

    /**
     * Создаёт новый контакт. Если создан успешно - возвращается
     * номер строки rowId, иначе -1
     */
    public void insert (String title, String text, int blog_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = createContentValues(title, text, blog_id);
        long d = db.insert(DATABASE_TABLE, null, initialValues);
        db.close();
    }


    /**
     * Изменение строчки
     */
    public void update (long rowId, String title, String text, int blog_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateValues = createContentValues(title, text, blog_id);
        db.update(DATABASE_TABLE, updateValues, COLUMN_ID + "=" + rowId,null);
        db.close();
    }

    /**
     * Удаление контакта
     */
    public void delete (long rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, COLUMN_ID + "=" + rowId, null);
        db.close();
    }

    /**
     * Получение всех контактов
     */
    public Cursor getFull(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(DATABASE_TABLE, new String[] { COLUMN_ID,
                        COLUMN_Title, COLUMN_Text,COLUMN_Blog_Id }, COLUMN_Blog_Id + "=" + id,
                null, null, null, null);
    }

    /**
     * Получаем конкретный контакт
     */
    public Cursor getOneRecord(int id) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, DATABASE_TABLE,
                new String[] { COLUMN_ID, COLUMN_Title, COLUMN_Text,
                        COLUMN_Blog_Id }, COLUMN_ID + "=" + id, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    /*
     * Описываем структуру данных
     */
    private ContentValues createContentValues(String title, String text,
                                              int blog_id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_Title, title);
        values.put(COLUMN_Text, text);
        values.put(COLUMN_Blog_Id, blog_id);
        return values;
    }
}
