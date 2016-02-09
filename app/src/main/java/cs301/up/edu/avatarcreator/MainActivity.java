package cs301.up.edu.avatarcreator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected Spinner hairStyle;
    protected Spinner eyes;
    protected Spinner nose;

    protected TextView red;
    protected TextView green;
    protected TextView blue;

    protected SeekBar redSeek;
    protected SeekBar greenSeek;
    protected SeekBar blueSeek;

    protected Button randomizer;

    protected RadioGroup buttons;

    protected Face surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] harryStyles = {"Hair Styles","Mohawk", "Bald", "Bowl Cut"};
        String[] eyeStyles = {"Eye Styles","Small", "Square", "Round"};
        String[] noseStyles = {"Nose Styles", "Small", "Tomato", "Voldemort"};

        hairStyle = (Spinner)findViewById(R.id.hairSpinner);
        ArrayAdapter hair = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, harryStyles);
        hairStyle.setAdapter(hair);
        hairStyle.setSelection(0);

        eyes = (Spinner)findViewById(R.id.eyeSpinner);
        ArrayAdapter eyeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, eyeStyles);
        eyes.setAdapter(eyeAdapter);
        eyes.setSelection(0);

        nose = (Spinner)findViewById(R.id.noseSpinner);
        ArrayAdapter noseAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,noseStyles);
        nose.setAdapter(noseAdapter);
        nose.setSelection(0);

        red = (TextView)findViewById(R.id.redTextView);
        blue = (TextView)findViewById(R.id.blueTextView);
        green = (TextView)findViewById(R.id.greenTextView);

        redSeek = (SeekBar)findViewById(R.id.redSeekBar);
        blueSeek = (SeekBar)findViewById(R.id.blueSeekBar);
        greenSeek = (SeekBar)findViewById(R.id.greenSeekBar);

        redSeek.setMax(255);
        blueSeek.setMax(255);
        greenSeek.setMax(255);

        randomizer = (Button)findViewById(R.id.randomButton);

        buttons = (RadioGroup)findViewById(R.id.RadioGroup1);
        /**
         * EXTERNAL CITATION
         * DATE: February 8th, 2016
         * PROBLEM: Select the default button in RadioGroup
         * RESOURCE: http://stackoverflow.com/questions/9175635/how-to-set-
         *              radio-button-checked-as-default-in-radiogroup-with-android
         * SOLUTION: call check on RadioGroup.
         */
        buttons.check(R.id.hairRadioButton);

        surface = (Face)findViewById(R.id.drawingTable);

        myListeners listeners = new myListeners(red, green, blue, redSeek, greenSeek, blueSeek, randomizer, hairStyle,
                eyes, nose, buttons, surface);

        redSeek.setOnSeekBarChangeListener(listeners);
        blueSeek.setOnSeekBarChangeListener(listeners);
        greenSeek.setOnSeekBarChangeListener(listeners);

        randomizer.setOnClickListener(listeners);

        buttons.setOnCheckedChangeListener(listeners);

        hairStyle.setOnItemSelectedListener(listeners);
        eyes.setOnItemSelectedListener(listeners);
        nose.setOnItemSelectedListener(listeners);


        /**
         * EXTERNAL CITATION
         *
         * DATE: February 8th, 2016
         * PROBLEM: Wanted the random to be pressed on app startup
         * RESOURCE: http://stackoverflow.com/questions/4553374/how-to-simulate-a-button-click-through-code-in-android
         * SOLUTION: Call performClick on random Button
         */
       randomizer.performClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
