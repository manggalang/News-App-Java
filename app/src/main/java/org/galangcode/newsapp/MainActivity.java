package org.galangcode.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;

import org.galangcode.newsapp.models.NewsApiResponse;
import org.galangcode.newsapp.models.NewsHeadline;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    Button buttonPasscode;
    SearchView searchView;

//    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, deleteButton;
//    private Button openButton;
//    private TextView pinCodeView;
//    private String enteredPin = "";
//    private FlexboxLayout pinContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        pinContainer = findViewById(R.id.pinContainer);
//        button1 = findViewById(R.id.button1);
//        button2 = findViewById(R.id.button2);
//        button3 = findViewById(R.id.button3);
//        button4 = findViewById(R.id.button4);
//        button5 = findViewById(R.id.button5);
//        button6 = findViewById(R.id.button6);
//        button7 = findViewById(R.id.button7);
//        button8 = findViewById(R.id.button8);
//        button9 = findViewById(R.id.button9);
//        button0 = findViewById(R.id.button0);
//        deleteButton = findViewById(R.id.deleteButton);
//        pinCodeView = findViewById(R.id.pinTextView);
//        openButton = findViewById(R.id.openButton);
//        pincodeButton();
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager manager = new RequestManager(MainActivity.this);
                if(query.isEmpty()) {
                    manager.getNewsHeadlines(listener, "general", null);
                } else {
                    manager.getNewsHeadlines(listener, "general", query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);
        buttonPasscode = findViewById(R.id.button_passcode);
        buttonPasscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.passcode);
            }
        });
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {

        @Override
        public void onFetchData(List<NewsHeadline> list, String message) {
            if (list.isEmpty()) {
                Toast.makeText(MainActivity.this, "Oops no data found", Toast.LENGTH_SHORT).show();
            } else {
                showNews(list);
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An error Occured!!", Toast.LENGTH_SHORT).show();
        }
    };


    private void showNews(List<NewsHeadline> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadline headline) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                .putExtra("data", headline));
    }

//    private void pincodeButton() {
//        button1.setOnClickListener(v -> numberPressed("1"));
//
//        button2.setOnClickListener(v -> numberPressed("2"));
//
//        button3.setOnClickListener(v -> numberPressed("3"));
//
//        button4.setOnClickListener(v -> numberPressed("4"));
//
//        button5.setOnClickListener(v -> numberPressed("5"));
//
//        button6.setOnClickListener(v -> numberPressed("6"));
//
//        button7.setOnClickListener(v -> numberPressed("7"));
//
//        button8.setOnClickListener(v -> numberPressed("8"));
//
//        button9.setOnClickListener(v -> numberPressed("9"));
//
//        button0.setOnClickListener(v -> numberPressed("0"));
//
//        deleteButton.setOnClickListener(v -> resetEnteredPin());
//
//        openButton.setOnClickListener(v -> clickedOpen());
//    }
//
//    private void numberPressed(String number) {
//        if (this.enteredPin.length() >= 4) {
//            // ignore
//            return;
//        }
//
//        this.enteredPin += number;
//        pinCodeView.setText(this.enteredPin);
//    }
//
//    private void resetEnteredPin() {
//        enteredPin = "";
//        pinCodeView.setText(enteredPin);
//    }
//
//    private void clickedOpen() {
//        Log.d("ini pincode",  pinCodeView.getText().toString());
//        Log.d("ini entered pin",  enteredPin);
//        if (pinCodeView.getText().toString().equals(enteredPin)) {
//            pinContainer.setVisibility(View.GONE);
//        } else {
//            Toast.makeText(MainActivity.this, "Wrong ping", Toast.LENGTH_SHORT).show();
//        }
//        resetEnteredPin();
//    }
}