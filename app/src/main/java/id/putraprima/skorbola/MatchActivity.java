package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import id.putraprima.skorbola.model.Result;
import id.putraprima.skorbola.model.Score;

public class MatchActivity extends AppCompatActivity {

    private TextView homeTxt, awayTxt, homeScore, awayScore;
    private ImageView homeImage, awayImage;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        homeTxt = findViewById(R.id.txt_home);
        awayTxt = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Score score = extras.getParcelable("score");
            homeTxt.setText(score.getNameHome());
            awayTxt.setText(score.getNameAway());
            homeScore.setText(String.valueOf(0));
            awayScore.setText(String.valueOf(0));
            homeUri = score.getHomeUri();
            awayUri = score.getAwayUri();

            try {
                homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeImage.setImageBitmap(homeBitmap);
            awayImage.setImageBitmap(awayBitmap);
        }


    }

    public void addAway(View view) {
        String data = awayScore.getText().toString();
        int number =  Integer.parseInt(data);
        number++;
        awayScore.setText(String.valueOf(number));
    }

    public void addHome(View view) {
        String data = homeScore.getText().toString();
        int number =  Integer.parseInt(data);
        number++;
        homeScore.setText(String.valueOf(number));
    }

    public void handleResult(View view) {

        String nameAway = homeTxt.getText().toString();
        String nameHome = awayTxt.getText().toString();
        int scoreAw = Integer.parseInt(awayScore.getText().toString());
        int scoreHm = Integer.parseInt(homeScore.getText().toString());

        Result result = new Result(scoreAw,scoreHm,nameHome ,nameAway);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);

    }
}
