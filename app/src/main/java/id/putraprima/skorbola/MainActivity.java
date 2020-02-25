package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import id.putraprima.skorbola.model.Score;

public class MainActivity extends AppCompatActivity {

    private EditText nameHome;
    private  EditText nameAway;
    private ImageView imgHome;
    private  ImageView imgAway;
    private boolean homeStatus, awayStatus, homeBitmapStatus, awayBitmapStatus;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity

        nameHome = findViewById(R.id.home_team);
        nameAway = findViewById(R.id.away_team);
        imgHome = findViewById(R.id.home_logo);
        imgAway = findViewById(R.id.away_logo);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    homeUri = data.getData();
                    homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
                    imgHome.setImageBitmap(homeBitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == 2){
            if (data != null) {
                try {
                    awayUri = data.getData();
                    awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
                    imgAway.setImageBitmap(awayBitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    public void gotoMatchAct(View view) {
//        String homeName = nameHome.getText().toString();
//        String awayName = nameAway.getText().toString();
//        String homeImg = imgHome.getText().toString();
//        String awayImg = imgAway.getText().toString();
//
//        Score score = new Score(homeName, awayName);
//        Intent intent =  new Intent(this, MatchActivity.class);
//
//        intent.putExtra("score", score);
//        startActivity(intent);
        String homeName = nameHome.getText().toString();
        String awayName = nameAway.getText().toString();

        if((homeName).equals("Home Team") || (homeName).equals("") ){
            Toast.makeText(this,"Please Fill home team name!!", Toast.LENGTH_SHORT).show();
            homeStatus = false;
        }else{
            homeStatus = true;
        }
        if(homeBitmap == null){
            Toast.makeText(this,"Please Attach home Image!!", Toast.LENGTH_SHORT).show();
            homeBitmapStatus = false;
        }else{
            homeBitmapStatus = true;
        }
        if(awayBitmap == null){
            Toast.makeText(this,"Please Attach away Image!!", Toast.LENGTH_SHORT).show();
            awayBitmapStatus = false;
        }else{
            awayBitmapStatus = true;
        }
        if ((awayName).equals("Away Team") || (awayName).equals("")){
            Toast.makeText(this,"Please Fill away team name!!", Toast.LENGTH_SHORT).show();
            awayStatus = false;
        }else{
            awayStatus = true;
        }

        if (homeStatus == true && awayStatus == true && homeBitmapStatus == true && awayBitmapStatus == true){
            Score score = new Score(homeName, awayName,  homeUri, awayUri);
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    }

    public void handleLogoHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleLogoAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}
