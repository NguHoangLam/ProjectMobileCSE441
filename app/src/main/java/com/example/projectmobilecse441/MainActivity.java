package com.example.projectmobilecse441;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.SearchManager;
import android.content.Context;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.provider.Settings;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbarHomePage;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listViewCategories;
    DrawerLayout drawerLayout;
    EditText edt_UserName, edt_Password, edt_FullName, edt_Address, edt_Phone;
    Button btn_Login, btn_Logout, btn_Register, btn_Confirm, btn_historyOrders;
    DrawerLayout drawerLogin;
    TextView tv_UserNameProfile, tv_UserType, tv_TotalSpentDetails, tv_TotalRewardPoint, tv_loginTitle;
    SearchView searchView;

    ImageButton btn_moneysort;
    boolean moneyUp = true;

    ImageButton btn_namesort;
    boolean nameUp = true;
    List<BookCategories> bookCategoriesList = new ArrayList<>();
    ArrayAdapter<BookCategories> categoriesArrayAdapter = null;
    int UserId = -1;
    int timesShopping = -1;
    boolean checkLogin = false;
    boolean register = false;

    String address = null;
    int phone = 0;
    LinearLayout fullNameInput, addressInput, phoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        ShowMainMenu();
        ShowViewFlipper();
        List<Book> books = getListData();
        this.recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new BookRecyclerViewAdapter(this, books));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        registerForContextMenu(recyclerView);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String userName = edt_UserName.getText().toString().toLowerCase();
                String password = edt_Password.getText().toString().toLowerCase();
                if(!userName.equalsIgnoreCase("") || !password.equalsIgnoreCase("")) {
                    Map<String, UsersAccount> usersAccountList = UsersAccountHelper.getAll(getApplicationContext());
                    checkLogin = false;
                    if (usersAccountList.containsKey(userName)) {
                        UsersAccount auth = usersAccountList.get(userName);
                        assert auth != null;
                        String passwordChecking = auth.getPassword();
                        if (password.equalsIgnoreCase(passwordChecking)) {
                            drawerLogin.setVisibility(View.VISIBLE);
                            checkLogin = true;
                            CartHelper.resetCart(getApplicationContext());
                            tv_UserNameProfile.setText(auth.getFullName());
                            int rank = auth.getAccountType();
                            if (rank == 0) {
                                tv_UserType.setText(getString(R.string.normalMember));
                            }
                            if (rank == 1) {
                                tv_UserType.setText(getString(R.string.VIPMember));
                            }
                            tv_TotalSpentDetails.setText(auth.getTotalSpent() + "");
                            tv_TotalRewardPoint.setText(auth.getRewardPoint() + "");
                            UserId = auth.getId();
                            timesShopping = auth.getTimesShopping();
                            address = auth.getAddress();
                            phone = auth.getPhone();
                        } else {
                            Toast.makeText(MainActivity.this, getString(R.string.incorrectPassword), Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(MainActivity.this, getString(R.string.unResigteredAccount), Toast.LENGTH_SHORT).show();

                    }
//                for(int i =0;i<usersAccountList.size();i++){
//                    if(usersAccountList.get(i).getUsername().equalsIgnoreCase(userName)){
//                        if(usersAccountList.get(i).getPassword().equalsIgnoreCase(password)){
//
//                        }
//                    }
//                }
                    if (checkLogin) {
                        Toast.makeText(MainActivity.this, getString(R.string.loginSuccess), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, getString(R.string.loginFail), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, getString(R.string.loginFail), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLogin.setVisibility(View.GONE);
                checkLogin = false;
                CartHelper.resetCart(getApplicationContext());
                edt_UserName.setText("");
                edt_Password.setText("");
            }
        });
        btn_historyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),HistoryOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("UserId", UserId);
                myIntent.putExtra("UserId",bundle);
                startActivity(myIntent);
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!register){
                    register=true;
                    fullNameInput.setVisibility(View.VISIBLE);
                    addressInput.setVisibility(View.VISIBLE);
                    phoneInput.setVisibility(View.VISIBLE);
                    btn_Confirm.setVisibility(View.VISIBLE);
                    btn_Login.setVisibility(View.GONE);
                    tv_loginTitle.setText("Register Form");
                    btn_Register.setText("Back To Login");
                }else{
                    register=false;
                    fullNameInput.setVisibility(View.GONE);
                    addressInput.setVisibility(View.GONE);
                    phoneInput.setVisibility(View.GONE);
                    btn_Confirm.setVisibility(View.GONE);
                    edt_FullName.setText("");
                    edt_Address.setText("");
                    edt_Phone.setText("");
                    edt_UserName.setText("");
                    edt_Password.setText("");
                    tv_loginTitle.setText("Login Form");
                    btn_Register.setText("Don't Have Account");
                    btn_Login.setVisibility(View.VISIBLE);
                }

            }
        });
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edt_UserName.getText().toString().toLowerCase();
                String password = edt_Password.getText().toString().toLowerCase();
                String fullName = edt_FullName.getText().toString().toLowerCase();
                String address = edt_Address.getText().toString().toLowerCase();
                String phoneStr = edt_Phone.getText().toString().toLowerCase();
                Integer phone = Integer.parseInt(phoneStr);
                if(!userName.equalsIgnoreCase("")|| !password.equalsIgnoreCase("")|| !fullName.equalsIgnoreCase("")|| !address.equalsIgnoreCase("")|| !phoneStr.equalsIgnoreCase("")){
                    Map<String, UsersAccount> usersAccountList = UsersAccountHelper.getAll(getApplicationContext());
                    if(!usersAccountList.containsKey(userName)){
                        if(UsersAccountHelper.insert(getApplicationContext(),userName,password,fullName,address,phone)){
                            Toast.makeText(getApplicationContext(), "Register Success !!!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "User Name is Available !!!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Register Fail !!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_moneysort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = getListData();
                if (moneyUp){
                    view.setBackgroundResource(R.drawable.moneydown);
                    moneyUp = false;
                    Collections.sort(books, (s1, s2) -> {
                        return Integer.parseInt(s1.getPrice()) - Integer.parseInt(s2.getPrice());

                    });
                }else{
                    moneyUp = true;
                    view.setBackgroundResource(R.drawable.moneyup);
                    Collections.sort(books, (s1, s2) -> {
                        return Integer.parseInt(s2.getPrice()) - Integer.parseInt(s1.getPrice());
                    });
                }
                recyclerView.setAdapter(new BookRecyclerViewAdapter(MainActivity.this, books));
            }
        });

        btn_namesort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = getListData();
                if (nameUp){
                    view.setBackgroundResource(R.drawable.namedown);
                    nameUp = false;
                    Collections.sort(books, (s1, s2) -> {
                        return s1.getBookTitle().compareTo(s2.getBookTitle());
                    });
                }else{
                    nameUp = true;
                    view.setBackgroundResource(R.drawable.nameup);
                    Collections.sort(books, (s1, s2) -> {
                        return s2.getBookTitle().compareTo(s1.getBookTitle());
                    });
                }
                recyclerView.setAdapter(new BookRecyclerViewAdapter(MainActivity.this, books));
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                BookRecyclerViewAdapter bookRecycle = new BookRecyclerViewAdapter(MainActivity.this, books);
                bookRecycle.getFilter().filter(s);
                recyclerView.setAdapter(bookRecycle);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                BookRecyclerViewAdapter bookRecycle = new BookRecyclerViewAdapter(MainActivity.this, books);
                bookRecycle.getFilter().filter(s);
                recyclerView.setAdapter(bookRecycle);
                return false;
            }
        });






    }


    private void ShowViewFlipper() {
        List<Integer> advertisements = new ArrayList<>();
        advertisements.add(R.drawable.banner1);
        advertisements.add(R.drawable.banner2);
        advertisements.add(R.drawable.banner3);
        for (int i = 0; i < advertisements.size(); i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(advertisements.get(i)); // set image in ImageView
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        viewFlipper.setFlipInterval(3000);
        // set auto start for flipping between views
        viewFlipper.setAutoStart(true);

    }

    private void ShowMainMenu() {
        setSupportActionBar(toolbarHomePage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarHomePage.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarHomePage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



    }

    public void mapping(){
        toolbarHomePage = findViewById(R.id.toolbarhomepage);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        edt_UserName = (EditText) findViewById(R.id.edt_username);
        edt_Password = (EditText) findViewById(R.id.edt_password);
        btn_Login = (Button) findViewById(R.id.btn_login);
        drawerLogin = (DrawerLayout) findViewById(R.id.drawerLogin);
        btn_Logout = (Button) findViewById(R.id.btn_logout);
        tv_UserNameProfile = (TextView) findViewById(R.id.tv_userNameProfile);
        tv_UserType = (TextView) findViewById(R.id.tv_userRankProfile);
        tv_TotalSpentDetails =(TextView) findViewById(R.id.tv_totalspentdetails);
        tv_TotalRewardPoint = (TextView) findViewById(R.id.tv_rewardpointdetails);
        fullNameInput = (LinearLayout) findViewById(R.id.fullNameInput);
        addressInput = (LinearLayout) findViewById(R.id.addressInput);
        phoneInput = (LinearLayout) findViewById(R.id.phoneInput);
        btn_Register = (Button) findViewById(R.id.btn_register);
        btn_Confirm = (Button) findViewById(R.id.btn_confirm);
        edt_FullName = (EditText) findViewById(R.id.edt_FullName);
        edt_Address = (EditText) findViewById(R.id.edt_Address);
        edt_Phone = (EditText) findViewById(R.id.edt_Phone);
        tv_loginTitle = (TextView) findViewById(R.id.tv_loginFromTitle);
        btn_historyOrders = (Button) findViewById(R.id.btn_historyOder);
        btn_moneysort  = (ImageButton) findViewById(R.id.btn_moneysort);
        btn_namesort  = (ImageButton) findViewById(R.id.btn_namesort);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.exit){
            finish();
        }
        if(id==R.id.Cart){
            if(checkLogin){
                Intent myIntent = new Intent(getApplicationContext(),CartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("UserId", UserId);
                bundle.putInt("TimesShopping",timesShopping);
                bundle.putString("Address",address);
                bundle.putInt("Phone",phone);
                myIntent.putExtra("UserPackage",bundle);
                startActivity(myIntent);
            }else {
                Toast.makeText(MainActivity.this,getString(R.string.loginFirst), Toast.LENGTH_SHORT).show();
            }


        }
        if(id==R.id.action_language){
            Intent languageIntent = new
                    Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(languageIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addtocart:
                Toast.makeText(getApplicationContext(),"Add Successful!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.details:
                Toast.makeText(getApplicationContext(),"Details...",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private List<Book> getListData(){
        List<Book> list = new ArrayList<Book>();
        Book book1 = new Book(1,"The Words of Jesus", "290000", "Various", "10", "Science fiction","book1","11/12/2022",getString(R.string.book1));
        Book book2 = new Book(2,"Steve Jobs", "349000", "Walter Isaacson", "10", "Information Technology","book2","11/12/2022",getString(R.string.book2));
        Book book3 = new Book(3,"A Promised Land", "299000", "Barack Obama", "10", "U.S. History ","book3","11/12/2022",getString(R.string.book3));
        Book book4 = new Book(4,"A Guide to Visual Presentation", "399000", "Ruzaimi Mat Rani", "10", "Drawing","book4","11/12/2022",getString(R.string.book4));
        Book book5 = new Book(5,"Noise : A Flaw in Human Judgment", "199000", "Daniel Kahneman", "10", "Psychology","book5","11/12/2022",getString(R.string.book5));
        Book book6 = new Book(6,"How Google Works", "179000", "Eric Schmidt, Jonathan Rosenberg", "10", "Information Technology","book6","11/12/2022",getString(R.string.book6));
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);
        list.add(book6);
        return list;
    }
}