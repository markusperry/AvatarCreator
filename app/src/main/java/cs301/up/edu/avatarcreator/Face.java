package cs301.up.edu.avatarcreator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by markusperry on 2/8/16.
 */
public class Face extends SurfaceView {

    protected int skinColor;
    protected int eyeColor;
    protected int hairColor;

    protected Path[] hairStyles;
    protected int hairStyleIndex;

    protected int eyeStyle;
    protected int noseStyle;


    public Face(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas g)
    {
        drawHair(g, this.hairStyleIndex);
        drawEyes(g, this.eyeStyle);
        drawNose(g,noseStyle);
    }

    public void randomize()
    {
        Random gen = new Random();

        int r = gen.nextInt(256);
        int g = gen.nextInt(256);
        int b = gen.nextInt(256);
        this.skinColor = Color.rgb(r,g,b);

        r=gen.nextInt(256);
        g=gen.nextInt(256);
        b=gen.nextInt(256);
        this.eyeColor = Color.rgb(r,g,b);

        r=gen.nextInt(256);
        g=gen.nextInt(256);
        b=gen.nextInt(256);
        this.hairColor = Color.rgb(r,g,b);

        hairStyleIndex = gen.nextInt(3)+1;
        eyeStyle = gen.nextInt(3)+1;
        noseStyle = gen.nextInt(3)+1;
    }

    public void drawHair(Canvas g, int index)
    {
        Paint hair = new Paint();
        hair.setColor(this.hairColor);

        hairStyles = new Path[4];

        /**
         * EXTERNAL CITATION
         * DATE: February 8th, 2016
         * PROBLEM: Wanted to add rounded rectangle to Path Array
         * RESOURCE: http://developer.android.com/reference/android/graphics/RectF.html#RectF(float, float, float, float)
         * SOLUTION: Made a RectF and added it to the array with addRoundRect
         */
        RectF mohawk = new RectF(370f,20f,670,300f);
        Path hawk = new Path();
        hawk.addRoundRect(mohawk, 20f, 20f, Path.Direction.CW);

        Path bald = new Path();

        Path bowlCut = new Path();
        bowlCut.addArc(100f,20f,910f,800f,180f,180f);

        hairStyles[1] = hawk;
        hairStyles[2] = bald;
        hairStyles[3] = bowlCut;

        if (index == 1)
        {
            g.drawPath(hairStyles[1], hair);
        }
        else if (index == 2)
        {
            g.drawPath(hairStyles[2], hair);
        }
        else
        {
            g.drawPath(hairStyles[3],hair);
        }
    }

    public void drawEyes(Canvas g, int index)
    {
        Paint black = new Paint(Color.BLACK);

        Paint eyes = new Paint();
        eyes.setColor(eyeColor);
        Path[] eyeBall = new Path[4];

        Path smallEyes = new Path();
        smallEyes.addOval(300f,500f,375f,530f,Path.Direction.CW);
        smallEyes.addOval(630f,500f,705,530f,Path.Direction.CW);

        Path squareOutside = new Path();
        RectF outsideEyeLeft = new RectF(275f,500f,400f,600f);
        RectF outsideEyeRight = new RectF(600f, 500f,725f, 600f);
        squareOutside.addRoundRect(outsideEyeLeft, 15f, 15f, Path.Direction.CW);
        squareOutside.addRoundRect(outsideEyeRight, 15f, 15f, Path.Direction.CW);

        Path squareInside = new Path();
        squareInside.addCircle(338f, 545f, 30f, Path.Direction.CW);
        squareInside.addCircle(660f, 545f, 30f, Path.Direction.CW);

        Path roundEyes = new Path();
        roundEyes.addCircle(350f,575f,60f, Path.Direction.CW);
        roundEyes.addCircle(655f, 575f, 60f, Path.Direction.CW);

        eyeBall[1]=smallEyes;
        eyeBall[2]=squareInside;
        eyeBall[3]=roundEyes;

        if (index==1)
        {
            g.drawPath(eyeBall[1],eyes);
        }
        else if (index==2)
        {
            black.setStyle(Paint.Style.STROKE);
            /**
             * EXTERNAL CITATION
             * DATE: February 8th, 2015
             * PROBLEM: Wanted outline to be thicker
             * RESOURCE:http://stackoverflow.com/questions/6822888/android-canvas-draw-line-make-the-line-thicker
             * SOLUTION: added setStrokeWidth
             */
            black.setStrokeWidth(5f);
            g.drawPath(squareOutside, black);

            g.drawPath(eyeBall[2],eyes);

        }
        else
        {
            g.drawPath(eyeBall[3],eyes);
            g.drawCircle(350f,575f,20f, black);
            g.drawCircle(655f, 575f, 20f, black);
        }
    }

    public void drawNose(Canvas g, int index)
    {
        Path[] snouzer = new Path[4];

        Paint noser = new Paint(Color.BLACK);

        Path smallNose = new Path();
        smallNose.moveTo(500f, 670f);
        smallNose.lineTo(465f,750f);
        smallNose.lineTo(510f,750f);

        Path tomatoNose = new Path();
        tomatoNose.moveTo(470f,640f);
        tomatoNose.lineTo(470f,740f);
        tomatoNose.moveTo(530f,640f);
        tomatoNose.lineTo(530f,740f);
        tomatoNose.addArc(420f,720f,485f,760f,20f,250f);
        tomatoNose.addArc(515f,720f,580f,760f,267f,250f);

        snouzer[1]=smallNose;
        snouzer[2]=tomatoNose;

        if (index==1)
        {
            noser.setStyle(Paint.Style.STROKE);
            noser.setStrokeWidth(10f);
            g.drawPath(snouzer[1],noser);
        }
        else if (index==2)
        {
            noser.setStyle(Paint.Style.STROKE);
            noser.setStrokeWidth(10f);
            g.drawPath(snouzer[2],noser);
        }

    }

}
