package com.example.v11t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    FragmentManager manager;
    TextViewFragment fragment;
    Spinner fontOptions, widthOptions, heightOptions, rowOptions, languageOptions;
    EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        //create new fragment and commit to manager
        fragment = new TextViewFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container,
                fragment).commit();

        populateSpinners(menu);
        title = (EditText) menu.findItem(R.id.give_title).getActionView().findViewById(R.id.change_title);

        //change default toolbar new toolbar with menu button to open the navigation drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //create listener for switch in menu
        Switch actionView = (Switch) menu.findItem(R.id.edit_switch).getActionView().findViewById(R.id.editSwitch);
        actionView.setChecked(true);
        actionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fragment.changeEnabled();
                } else {
                    fragment.changeDisabled();
                }
            }
        });
    }

    //Populate all spinners in drawer menu with string arrays
    public void populateSpinners(Menu menu) {
        fontOptions = (Spinner) menu.findItem(R.id.font_size).getActionView().findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fontSizes));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontOptions.setAdapter(myAdapter);

        widthOptions = (Spinner) menu.findItem(R.id.width).getActionView().findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.widths));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        widthOptions.setAdapter(myAdapter2);

        heightOptions = (Spinner) menu.findItem(R.id.height).getActionView().findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.heights));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightOptions.setAdapter(myAdapter3);

        rowOptions = (Spinner) menu.findItem(R.id.row_count).getActionView().findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rows));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rowOptions.setAdapter(myAdapter4);

        languageOptions = (Spinner) menu.findItem(R.id.change_language).getActionView().findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter5 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.languages));
        myAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageOptions.setAdapter(myAdapter5);
    }

    //change application language and restart activity to see changes
    //NOTE!: this could be done without restart by using the new language resource and manually changing all UI elements
    public void changeLanguage(String language) {
        LocaleHelper.setLocale(MainActivity.this, language);
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_language:
                if (languageOptions.getSelectedItemId() == 1) {
                    changeLanguage("en");
                } else if (languageOptions.getSelectedItemId() == 2) {
                    changeLanguage("fi");
                }
                break;
            case R.id.give_title:
                fragment.changeTitle(title.getText().toString());
                break;
            case R.id.font_size:
                fragment.changeFont(Integer.parseInt(fontOptions.getSelectedItem().toString()));
                break;
            case R.id.width:
                fragment.changeWidth(Integer.parseInt(widthOptions.getSelectedItem().toString()));
                break;
            case R.id.height:
                fragment.changeHeight(Integer.parseInt(heightOptions.getSelectedItem().toString()));
                break;
            case R.id.row_count:
                fragment.changeRowCount(Integer.parseInt(rowOptions.getSelectedItem().toString()));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}