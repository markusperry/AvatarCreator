package cs301.up.edu.avatarcreator;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.channels.SelectionKey;

/**
 * Created by markusperry on 2/5/16.
 */
public class myListeners implements SeekBar.OnSeekBarChangeListener {


    protected TextView redView;
    protected TextView greenView;
    protected TextView blueView;
    protected SeekBar redSeeker;
    protected SeekBar greenSeeker;
    protected SeekBar blueSeeker;


    public myListeners(TextView red, TextView green, TextView blue, SeekBar redSeek, SeekBar greenSeek, SeekBar blueSeek)
    {
        redView=red;
        greenView=green;
        blueView=blue;
        redSeeker=redSeek;
        greenSeeker=greenSeek;
        blueSeeker=blueSeek;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
