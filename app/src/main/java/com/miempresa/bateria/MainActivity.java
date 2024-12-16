package com.miempresa.bateria;

import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AdapterCancion adapterCancion;
    List<Cancion> canciones;
    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    TextView txtTiempoInicio, txtTiempoFinal;
    ImageButton btnBombo, btnTomb, btnPlatilloIzq, btnPlatilloDerecha, btnPlay, btnPause, btnStop;
    SeekBar seekBar;

    private Cancion cancionSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        inicializarComponentes();
        cargarCanciones();

        adapterCancion = new AdapterCancion(this, canciones);
        listView.setAdapter(adapterCancion);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                cancionSeleccionada = canciones.get(position);
            }
        });

        AudioAttributes atributosAudio = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(atributosAudio)
                .setMaxStreams(4)
                .build();

        int idSonidoBombo = soundPool.load(this, R.raw.sonido_bombo, 1);
        btnBombo.setOnClickListener(v -> soundPool.play(idSonidoBombo, 1f, 1f, 0, 0, 1f));

        int idSonidoTomb = soundPool.load(this, R.raw.sonido_tomb, 1);
        btnTomb.setOnClickListener(v -> soundPool.play(idSonidoTomb, 1f, 1f, 0, 0, 1f));

        int idSonidoPlatilloIzq = soundPool.load(this, R.raw.sonido_platillo_izq, 1);
        btnPlatilloIzq.setOnClickListener(v -> soundPool.play(idSonidoPlatilloIzq, 1f, 1f, 0, 0, 1f));

        int idSonidoPlatilloDerecha = soundPool.load(this, R.raw.sonido_platillo_derecha, 1);
        btnPlatilloDerecha.setOnClickListener(v -> soundPool.play(idSonidoPlatilloDerecha, 1f, 1f, 0, 0, 1f));

        btnPlay.setOnClickListener(v -> {
            if (cancionSeleccionada != null) {
                iniciarCancion(cancionSeleccionada.getIdSonido());
            }
        });

        btnPause.setOnClickListener(v -> pausarCancion());

        btnStop.setOnClickListener(v -> detenerCancion());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void iniciarCancion(int idSonido) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, idSonido);
        mediaPlayer.start();
    }

    private void pausarCancion() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void detenerCancion() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void cargarCanciones() {
        canciones = new ArrayList<>();
        canciones.add(new Cancion("A mi", R.raw.a_mi));
        canciones.add(new Cancion("Canguinaje", R.raw.cangrinaje));
        canciones.add(new Cancion("Caonsentia", R.raw.consentia));
    }

    private void inicializarComponentes() {
        listView = findViewById(R.id.listView);
        txtTiempoInicio = findViewById(R.id.txtTiempoInicio);
        txtTiempoFinal = findViewById(R.id.txtTiempoFinal);
        btnBombo = findViewById(R.id.btnBombo);
        btnTomb = findViewById(R.id.btnTomb);
        btnPlatilloIzq = findViewById(R.id.btnPlatilloIzq);
        btnPlatilloDerecha = findViewById(R.id.btnPlatilloDerecha);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        seekBar = findViewById(R.id.seekBar);
    }
}