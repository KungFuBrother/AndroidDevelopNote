package com.smartown.note.bmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.smartown.note.bmob.user.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void addUser(String phone, String pwd, String nick, String name) {
        User user = new User(phone, pwd, nick, name);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Log.i("addUser", s);
            }
        });
    }

    private void getUser(String phone) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("phone", phone);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                Log.i("getUser", list.toString());
            }
        });
    }

    private void deleteUser(final String phone) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("phone", phone);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                Log.i("deleteUser", list.toString());
                for (User user : list) {
                    if (user.getPhone().equals(phone)) {
                        user.delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Log.i("deleteUser", "成功");
                                } else {
                                    Log.i("deleteUser", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    public void add(View view) {
        addUser("13032889558", "123456", "Smartown", "雷小武");
        addUser("18048831447", "123456", "Smartown", "雷小武");
    }

    public void get(View view) {
        getUser("13032889558");
    }

    public void delete(View view) {
        deleteUser("13032889558");
    }
}
