package com.example.tarea_3;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.tarea_3.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Window window;
    private int unidad;
    private int cien, cincuenta, veinte, cinco, diez;

    //Inicializando variables
    RelativeLayout relativeLayout;

    TextView salir;
    TextView borrar;
    TextView valor;

    EditText cantidad;

    SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variables
        relativeLayout = findViewById(R.id.relative_layout);

        salir = findViewById(R.id.salir);
        borrar = findViewById(R.id.borrar);
        valor = findViewById(R.id.valor);

        cantidad = findViewById(R.id.cantidad);

        swipeListener = new SwipeListener(relativeLayout);

        this.window = getWindow();

        findViewById(R.id.blanco).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
        });

        findViewById(R.id.gray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundColor(Color.parseColor("#CED4DA"));
            }
        });

        findViewById(R.id.dark_gray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundColor(Color.parseColor("#6C757D"));
            }
        });

        findViewById(R.id.light_black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundColor(Color.parseColor("#343A40"));
            }
        });

        findViewById(R.id.black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundColor(Color.parseColor("##FF000000"));
            }
        });
    }

    private class SwipeListener implements View.OnTouchListener {
        //Inicializando varible
        GestureDetector gestureDetector;

        //Creando constructor
        SwipeListener(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener =
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                        float xDiff = e2.getX() - e1.getX();
                        float yDiff = e2.getY() - e1.getY();

                        try {
                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                    if (xDiff > 0) {
//                                        textView.setText("Swipe right");
                                        finish();
                                    } else {
//                                        textView.setText("Swipe Left");
                                        valor.setText("");
                                        cantidad.setText("");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return false;
                    }
                };
            gestureDetector = new GestureDetector(listener);

            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action, keycode;

        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_UP:

                if(KeyEvent.ACTION_UP == action){
//                    unidad = Double.parseDouble(cantidad.getText().toString());
                    unidad = Integer.parseInt(cantidad.getText().toString());

                    while (unidad > 0){
                        if(unidad >= 100){
                            cien++;
                            unidad = unidad - 100;
                        }else if(unidad >= 50){
                            cincuenta++;
                            unidad = unidad - 50;
                        }else if(unidad >= 20){
                            veinte++;
                            unidad = unidad - 20;
                        }else if(unidad >= 10){
                            diez++;
                            unidad = unidad - 10;
                        }else if(unidad >= 5){
                            cinco++;
                            unidad = unidad - 5;
                        }else{
                            unidad = 0;
                        }
                    }

                    valor.setText("100 = " + cien + ", 50 = " + cincuenta + ", 20 = " + veinte + ", 10 = " + diez + ", 5 = " + cinco );
                }

                break;
        }

        return false;
    }
}