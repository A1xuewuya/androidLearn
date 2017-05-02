package cn.edu.neusoft.phonebookdemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liningning on 2016/5/20.
 */
public class BaseDBUtil {
    static final String DB_DIR = "database";
    static final String DB_NAME="test.db3";
    private static String databasePath;
    public SQLiteDatabase database;

    public void closeDB(){

        if(database != null && database.isOpen()){

            database.close();
            database = null;
        }
    }
    public int openDB(Context context){

        databasePath=getDatabasePath(context);
        try {
            if(database == null || !database.isOpen()){
                database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
            }
        } catch (SQLiteException e) {
            return -1;
        }
        return 0;
    }

    private static String getDatabasePath(Context context) {
        ApplicationInfo applicationInfo;
        String packageName = context.getPackageName();
        String path = "";
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(
                    packageName, PackageManager.GET_META_DATA);
            String dbDir = applicationInfo.dataDir + File.separator + DB_DIR;
            File file = new File(dbDir);
            if (!file.exists()) {
                file.mkdir();
            }
            path = applicationInfo.dataDir + File.separator + DB_DIR
                    + File.separator + DB_NAME;
            System.out.println("========="+path);
        } catch (PackageManager.NameNotFoundException e) {
        }
        return path;
    }

    //-1代表失败  0代表无需拷贝，1代表拷贝成功
    public static  Integer copyDatabaseFile(Context context) {

        databasePath=getDatabasePath(context);
        Integer res = -1;
        try {
            InputStream inputStream = context.getAssets().open(DB_NAME);
            if (databasePath != null) {
                File file = new File(databasePath);
                if (!file.exists())
                    file.createNewFile();
                else{//如果已有数据库文件
                    res=0;
                    return res;
                }
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024 * 4];
                int count = 0;
                while ((count = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, count);
                }
                outputStream.close();
            }
            inputStream.close();
            res = 1;// 代表成功

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
