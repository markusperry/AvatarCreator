package cs301.up.edu.avatarcreator;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Random;

/**
 * Created by markusperry on 2/5/16.
 */
public class myListeners implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener, Spinner.OnItemSelectedListener{

    protected TextView redView;
    protected TextView greenView;
    protected TextView blueView;
    protected SeekBar redSeeker;
    protected SeekBar greenSeeker;
    protected SeekBar blueSeeker;
    protected Button randomButton;
    protected Spinner hairSpin;
    protected Spinner eyeSpin;
    protected Spinner noseSpin;
    protected RadioGroup faceSelection;
    protected Face drawingFace;


    public myListeners(TextView red, TextView green, TextView blue, SeekBar redSeek, SeekBar greenSeek, SeekBar blueSeek,
                       Button rand, Spinner hairs, Spinner eyes, Spinner noses, RadioGroup buttonGroup, Face userFace)
    {
        redView=red;
        greenView=green;
        blueView=blue;
        redSeeker=redSeek;
        greenSeeker=greenSeek;
        blueSeeker=blueSeek;
        randomButton=rand;
        hairSpin=hairs;
        eyeSpin=eyes;
        noseSpin=noses;
        faceSelection=buttonGroup;
        drawingFace=userFace;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId()==R.id.redSeekBar)
        {
            this.redView.setText("Red: "+progress);
        }

        else if (seekBar.getId()==R.id.greenSeekBar)
        {
            this.greenView.setText("Green: "+progress);
        }

        else
        {
            this.blueView.setText("Blue: "+progress);
        }

        if (faceSelection.getCheckedRadioButtonId()==R.id.hairRadioButton)
        {
            int r = redSeeker.getProgress();
            int g = greenSeeker.getProgress();
            int b = blueSeeker.getProgress();

            drawingFace.hairColor = Color.rgb(r,g,b);
        }
        else if (faceSelection.getCheckedRadioButtonId()==R.id.eyesRadioButton)
        {
            int r = redSeeker.getProgress();
            int g = greenSeeker.getProgress();
            int b = blueSeeker.getProgress();

            drawingFace.eyeColor = Color.rgb(r,g,b);
        }

        else if (faceSelection.getCheckedRadioButtonId()==R.id.skinRadioButton)
        {
            int r = redSeeker.getProgress();
            int g = greenSeeker.getProgress();
            int b = blueSeeker.getProgress();

            drawingFace.skinColor = Color.rgb(r,g,b);
        }

        drawingFace.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v)
    {
        if (v.getId()==R.id.randomButton)
        {
            drawingFace.randomize();
            if (this.faceSelection.getCheckedRadioButtonId()==R.id.skinRadioButton)
            {
                setSliders(this.redSeeker,this.greenSeeker,this.blueSeeker, drawingFace.skinColor);
            }
            else if (this.faceSelection.getCheckedRadioButtonId()==R.id.eyesRadioButton)
            {
                setSliders(this.redSeeker,this.greenSeeker,this.blueSeeker, drawingFace.eyeColor);
            }
            else if (this.faceSelection.getCheckedRadioButtonId()==R.id.hairRadioButton)
            {
                setSliders(this.redSeeker,this.greenSeeker,this.blueSeeker, drawingFace.hairColor);
            }

            hairSpin.setSelection(this.drawingFace.hairStyleIndex);
            eyeSpin.setSelection(this.drawingFace.eyeStyle);
            noseSpin.setSelection(this.drawingFace.noseStyle);

            drawingFace.invalidate();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId==R.id.hairRadioButton)
        {
            int hair = drawingFace.hairColor;
            setSliders(this.redSeeker,this.greenSeeker,this.blueSeeker, hair);
        }
        else if (checkedId==R.id.eyesRadioButton)
        {
            int eye = drawingFace.eyeColor;
            setSliders(this.redSeeker,this.greenSeeker,this.blueSeeker, eye);
        }
        else if (checkedId==R.id.skinRadioButton)
        {
            int skin = drawingFace.skinColor;
            setSliders(this.redSeeker,this.greenSeeker,this.blueSeeker, skin);
        }

        drawingFace.invalidate();
    }

    public void setSliders(SeekBar r, SeekBar g, SeekBar b, int color)
    {
        r.setProgress(Color.red(color));
        g.setProgress(Color.green(color));
        b.setProgress(Color.blue(color));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId()==R.id.hairSpinner) {
            if (position == 1)
            {
                drawingFace.hairStyleIndex = position;
            }
            else if (position == 2)
            {
                drawingFace.hairStyleIndex = position;
            }
            else if (position == 3)
            {
                drawingFace.hairStyleIndex = position;
            }
        }
        else if (parent.getId()==R.id.eyeSpinner)
        {
            if (position == 1)
            {
                drawingFace.eyeStyle = position;
            }
            else if (position == 2)
            {
                drawingFace.eyeStyle = position;
            }
            else if (position == 3)
            {
                drawingFace.eyeStyle = position;
            }
        }
        else if (parent.getId()==R.id.noseSpinner)
        {
            if (position == 1)
            {
                drawingFace.noseStyle = position;
            }
            else if (position == 2)
            {
                drawingFace.noseStyle = position;
            }
            else if (position == 3)
            {
                drawingFace.noseStyle = position;
            }
        }

        drawingFace.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
