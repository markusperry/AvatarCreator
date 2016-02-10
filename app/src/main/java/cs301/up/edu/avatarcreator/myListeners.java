package cs301.up.edu.avatarcreator;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Description: Listener class that will handle all event from MainActivity.
 *
 * @author markusperry
 * @date Feb 10th, 2016
 */
public class myListeners implements SeekBar.OnSeekBarChangeListener,
                                    View.OnClickListener,
                                    RadioGroup.OnCheckedChangeListener,
                                    Spinner.OnItemSelectedListener,
                                    Switch.OnCheckedChangeListener,
                                    View.OnLongClickListener
{
    //Instance Variables
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
    protected Switch smule;

    /**
     * Constructor to pass objects back and forth from Main Activity.
     *
     * @param red TextView for Red SeekBar
     * @param green TextView for green SeekBar
     * @param blue TextView for blue SeekBar
     * @param redSeek Red SeekBar
     * @param greenSeek Green SeekBar
     * @param blueSeek blue SeekBar
     * @param rand Random Button
     * @param hairs Hair Style Selection Spinner
     * @param eyes Eye Style selection Spinner
     * @param noses Nose style selection Spinner
     * @param buttonGroup RadioGroup that controls current body color.
     * @param userFace Face surfaceView object that is being drawn
     * @param smiler Toggle to control smile on Face
     */
    public myListeners(TextView red, TextView green, TextView blue, SeekBar redSeek, SeekBar greenSeek, SeekBar blueSeek,
                       Button rand, Spinner hairs, Spinner eyes, Spinner noses, RadioGroup buttonGroup, Face userFace, Switch smiler)
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
        smule=smiler;
    }//ctor

    /**
     * SeekBar change listener called when any SeekBar Progress is changed
     *
     * @param seekBar The seekBar that was changed
     * @param progress new progress of seekbar
     * @param fromUser true if the user changed the value of the seekbar
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //Change red TextView text if red bar changed
        if (seekBar.getId()==R.id.redSeekBar)
        {
            this.redView.setText("Red: "+progress);
        }

        //change green TextView if green bar changed
        else if (seekBar.getId()==R.id.greenSeekBar)
        {
            this.greenView.setText("Green: "+progress);
        }

        //change blue TextView if blue bar changed.
        else
        {
            this.blueView.setText("Blue: "+progress);
        }

        //If under hair color, assign current RGB value to Face class
        if (faceSelection.getCheckedRadioButtonId()==R.id.hairRadioButton)
        {
            int r = redSeeker.getProgress();
            int g = greenSeeker.getProgress();
            int b = blueSeeker.getProgress();

            drawingFace.hairColor = Color.rgb(r,g,b);
        }

        //If under eye color, assign current RGB value to Face class
        else if (faceSelection.getCheckedRadioButtonId()==R.id.eyesRadioButton)
        {
            int r = redSeeker.getProgress();
            int g = greenSeeker.getProgress();
            int b = blueSeeker.getProgress();

            drawingFace.eyeColor = Color.rgb(r,g,b);
        }

        //If under skin color, assign current RGB value to the Face class
        else if (faceSelection.getCheckedRadioButtonId()==R.id.skinRadioButton)
        {
            int r = redSeeker.getProgress();
            int g = greenSeeker.getProgress();
            int b = blueSeeker.getProgress();

            drawingFace.skinColor = Color.rgb(r,g,b);
        }

        //re-draw Face
        drawingFace.invalidate();
    }//onProgressChanged()

    /**
     * ========UNUSED===========
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }//onStartTrackingTouch()

    /**
     * ========UNUSED===========
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }//onStopTrackingTouch

    /**
     * OnClick listener that is called when Buttons are pressed on the screen.
     *
     * @param v the view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        //Random button was pressed
        if (v.getId()==R.id.randomButton)
        {
            //Method from Face class to randomize all instance variable
            drawingFace.randomize();

            //Depending on what radio selection user is, set seekbars to appropriate new random color.
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

            //Set current face style to be displayed in Spinners
            hairSpin.setSelection(this.drawingFace.hairStyleIndex);
            eyeSpin.setSelection(this.drawingFace.eyeStyle);
            noseSpin.setSelection(this.drawingFace.noseStyle);

            //Re-draw if necessary
            drawingFace.invalidate();
        }
    }//onClick

    /**
     * Called when the user changes the selection in the radio group
     *
     * @param group The radio group where the change was detected
     * @param checkedId the ID of the RadioButton that was checked
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //Set sliders when user changes RadioButton
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

        //re-draw face if necessary
        drawingFace.invalidate();
    }//onCheckedChange()

    /**
     * Method to set RGB sliders to a given color
     *
     * @param r Red SeekBar object
     * @param g Green SeekBar object
     * @param b Blue SeekBar object
     * @param color The RGB color to set the sliders to
     */
    public void setSliders(SeekBar r, SeekBar g, SeekBar b, int color)
    {
        //Set progress based on RGB
        r.setProgress(Color.red(color));
        g.setProgress(Color.green(color));
        b.setProgress(Color.blue(color));
    }//setSliders

    /**
     * Spinner change listener called when user changes selection in any Spinner
     *
     * @param parent Adapter for the altered Spinner
     * @param view the Spinner that was changed
     * @param position new position of the spinner
     * @param id ID of the changed Spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Hair spinner was changed, change hairStyleIndex in face class to match new Style
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

        //Eye spinner was changed, change eyeStyle in Face class to matach new Style
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

        //nose spinner was changed, change nose style in Face class to match new style
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

        //Re-draw face with changes
        drawingFace.invalidate();
    }//OnItemSelected()

    /**
     * ==============UNUSED================
     *
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }//onNothingSelected()

    /**
     * Change listener for Toggle Switches
     *
     * @param buttonView the Button or switch that was changed
     * @param isChecked true if the switch was switched on.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //On draws the smile, off takes the smile away by changing a
        //boolean in the Face class.
        if (isChecked)
        {
            drawingFace.smile=true;
            smule.setText("YAAY!");
        }
        else
        {
            drawingFace.smile=false;
            smule.setText("Smile?");
        }

        //Re-draw face
        drawingFace.invalidate();
    }//onCheckChange()

    /**
     * Long-click listener called when a button is long-clicked
     *
     * @param v button that was long-clicked.
     * @return I don't really know what it returns but it's true.
     */
    @Override
    public boolean onLongClick(View v) {
        //Draw an easter egg for the user. Long click to view.
        drawingFace.easter = true;
        drawingFace.invalidate();
        return true;
    }//onLongClick()
}//myListeners
