package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import id.putraprima.skorbola.model.Result;

public class ResultActivity extends AppCompatActivity {

    private TextView winnerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winnerTxt = findViewById(R.id.txtWinner);

        Bundle extras =  getIntent().getExtras();
        Result result = extras.getParcelable("result");
        int scoreAway = Integer.parseInt(String.valueOf(result.getScoreAw()));
        int scoreHome = Integer.parseInt(String.valueOf(result.getScoreHm()));

        if (scoreAway > scoreHome){
            winnerTxt.setText("Winner: "+result.getNameAway());
        }else if(scoreAway < scoreHome){
            winnerTxt.setText("Winner: "+result.getNameHome());
        }
        else if(scoreAway == scoreHome){
            winnerTxt.setText("DRAW");
        }
    }
}
