package cs301.up.edu.avatarcreator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;

/**
 * Face class that extends SurfaceView to draw an Avatar for the user
 *
 * @author markusperry
 * @date Feb 10th, 2016
 */
public class Face extends SurfaceView {
    //Instance variables
    protected int skinColor;
    protected int eyeColor;
    protected int hairColor;
    protected Path[] hairStyles;
    protected int hairStyleIndex;
    protected int eyeStyle;
    protected int noseStyle;
    protected boolean smile=false;
    protected boolean easter=false;

    /**
     * Constructor Inherited form SurfaceView
     *
     * @issue I don't really know what this does.
     *
     * @param context Don't really know
     */
    public Face(Context context) {
        super(context);
        setWillNotDraw(false);
    }//ctor

    /**
     * Constructor inherited from SurfaceView
     *
     * @issue I don't know what this does
     *
     * @param context unknown
     * @param attrs unknown
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }//ctor

    /**
     * Constructor inherited from SurfaceView
     *
     * @issue I don't know what this does
     *
     * @param context unknown
     * @param attrs unknown
     * @param defStyleAttr unknown
     */
    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }//ctor

    /**
     * Constructor inherited from SurfaceView
     *
     * @issue I don't know what this does
     *
     * @param context unknown
     * @param attrs unknown
     * @param defStyleRes unknown
     */
    public Face(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
    }//ctor

    /**
     * draw method to draw the face onto the canvas
     *
     * @param g Canvas object where the face will be drawn upon.
     */
    @Override
    public void onDraw(Canvas g)
    {
        //draws aspects of the face to the canvas
        drawFace(g);
        drawHair(g, this.hairStyleIndex);
        drawEyes(g, this.eyeStyle);
        drawNose(g, noseStyle);
        /**
         * EXTERNAL CITATION
         * DATE: Feb 9th, 2016
         * PROBLEM: Wanted to draw drawable resource to the surfaceView
         * RESOURCE: http://stackoverflow.com/questions/7740687/add-image-to-surface-view-in-android
         * SOLUTION: Convert to BitMap and draw to canvas
         */

        //Creates the mouth from a drawable resource and draws it to canvas if
        //mouth switch is enabled
        Bitmap mouth = BitmapFactory.decodeResource(getResources(),R.drawable.mouth);
        Paint mouthColor = new Paint(Color.WHITE);
        Bitmap resized = Bitmap.createScaledBitmap(mouth,325,250,true);
        if (smile)
        {
            g.drawBitmap(resized,345f,800f,mouthColor);
        }

        //If the user found the easter egg, draw it to the canvas
        if (easter)
        {
            makeEasterEgg(g);
            easter=false;
        }

    }//onDraw

    /**
     * Method to randomize all instance variables in Face class to genereate
     * a random looking face.
     *
     * Method is called from listener class when random button is pressed.
     */
    public void randomize()
    {
        Random gen = new Random();

        //Generate random skin, eye, and hair color.
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

        //Pick random hair, eye, and nose style
        hairStyleIndex = gen.nextInt(3)+1;
        eyeStyle = gen.nextInt(3)+1;
        noseStyle = gen.nextInt(3)+1;
    }//randomize()

    /**
     * Helper method to draw hair based on a style index provided on a canvas.
     * @param g Canvas to draw the
     * @param index the hair style that should be drawn
     */
    public void drawHair(Canvas g, int index)
    {
        //Set hair color
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

        //Create mohawk path
        RectF mohawk = new RectF(363f,20f,663f,300f);
        Path hawk = new Path();
        hawk.addRoundRect(mohawk, 20f, 20f, Path.Direction.CW);

        //Create bald path
        Path bald = new Path();

        //Create bowl cut path
        Path bowlCut = new Path();
        bowlCut.addArc(85f,20f,910f,800f,180f,180f);

        //Fill path array with different hair styles
        hairStyles[1] = hawk;
        hairStyles[2] = bald;
        hairStyles[3] = bowlCut;

        //draw desired hair style
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
    }//drawHair()

    /**
     * Helper method to draw the eyes of the face on the given canvas
     * with a specific style index
     *
     * @param g canvs to draw the eyes on
     * @param index index that says which eyes to draw
     */
    public void drawEyes(Canvas g, int index)
    {
        //Define a black paint
        Paint black = new Paint(Color.BLACK);

        //Set eye color given by the current instance variable
        Paint eyes = new Paint();
        eyes.setColor(eyeColor);

        //Eye array to hold different eye styles
        Path[] eyeBall = new Path[4];

        //Create small eyes
        Path smallEyes = new Path();
        smallEyes.addOval(300f,500f,375f,530f,Path.Direction.CW);
        smallEyes.addOval(630f,500f,705,530f,Path.Direction.CW);

        //Create outside of square eyes
        Path squareOutside = new Path();
        RectF outsideEyeLeft = new RectF(275f,500f,400f,600f);
        RectF outsideEyeRight = new RectF(600f, 500f,725f, 600f);
        squareOutside.addRoundRect(outsideEyeLeft, 15f, 15f, Path.Direction.CW);
        squareOutside.addRoundRect(outsideEyeRight, 15f, 15f, Path.Direction.CW);

        //create square inside
        Path squareInside = new Path();
        squareInside.addCircle(338f, 545f, 30f, Path.Direction.CW);
        squareInside.addCircle(660f, 545f, 30f, Path.Direction.CW);

        //create round eyes
        Path roundEyes = new Path();
        roundEyes.addCircle(350f,575f,60f, Path.Direction.CW);
        roundEyes.addCircle(655f, 575f, 60f, Path.Direction.CW);

        //fill array
        eyeBall[1]=smallEyes;
        eyeBall[2]=squareInside;
        eyeBall[3]=roundEyes;

        //draw small eyes
        if (index==1)
        {
            g.drawPath(eyeBall[1],eyes);
        }

        //draw sqaure eyes
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

        //draw round eyes
        else
        {
            g.drawPath(eyeBall[3],eyes);
            g.drawCircle(350f,575f,20f, black);
            g.drawCircle(655f, 575f, 20f, black);
        }
    }//drawEyes()

    /**
     * Helper method to draw a nose.
     *
     * @param g the canvas to draw on
     * @param index the eye Nose style to draw
     */
    public void drawNose(Canvas g, int index)
    {
        //implement path array
        Path[] snouzer = new Path[4];

        //Black color
        Paint noser = new Paint(Color.BLACK);

        //Create small nose
        Path smallNose = new Path();
        smallNose.moveTo(500f, 670f);
        smallNose.lineTo(465f, 750f);
        smallNose.lineTo(510f, 750f);

        //create tomato nose
        Path tomatoNose = new Path();
        tomatoNose.moveTo(470f, 640f);
        tomatoNose.lineTo(470f, 740f);
        tomatoNose.moveTo(530f, 640f);
        tomatoNose.lineTo(530f, 740f);
        tomatoNose.addArc(420f, 720f, 485f, 760f, 20f, 250f);
        tomatoNose.addArc(515f, 720f, 580f, 760f, 267f, 250f);

        //create voldemort nose
        Path vold = new Path();
        vold.addOval(475, 650f, 485f, 730f, Path.Direction.CW);
        vold.addOval(515f, 650f, 525f, 730f, Path.Direction.CW);

        //fill array
        snouzer[1]=smallNose;
        snouzer[2]=tomatoNose;
        snouzer[3]=vold;

        //draw small nose
        if (index==1)
        {
            noser.setStyle(Paint.Style.STROKE);
            noser.setStrokeWidth(10f);
            g.drawPath(snouzer[1],noser);
        }

        //draw tomato nose
        else if (index==2)
        {
            noser.setStyle(Paint.Style.STROKE);
            noser.setStrokeWidth(10f);
            g.drawPath(snouzer[2],noser);
        }

        //draw voldemort nose
        else
        {
            noser.setStyle(Paint.Style.FILL);
            g.drawPath(snouzer[3], noser);
        }
    }//drawNose()

    /**
     * Helper method to draw the Face
     *
     * @param g canvas to draw Face on
     */
    public void drawFace(Canvas g)
    {
        //Add an oval face with desired skin color
        Path facial = new Path();
        facial.addOval(90f, 100f, 900f, 1250f, Path.Direction.CW);

        Paint facePaint = new Paint();
        facePaint.setColor(skinColor);
        facePaint.setStyle(Paint.Style.FILL);
        g.drawPath(facial,facePaint);
    }//drawFace()

    /**
     * Create an easter egg for the user.
     *
     * @param g canvas to draw on
     */
    public void makeEasterEgg(Canvas g)
    {
        //you have to find out
        Paint myPaint = new Paint(Color.WHITE);
        Bitmap face = BitmapFactory.decodeResource(getResources(),R.drawable.nux3);
        Bitmap resized = Bitmap.createScaledBitmap(face,g.getWidth(),g.getHeight(),true);

        g.drawBitmap(resized,0f,0f,myPaint);
    }//makeEasterEgg()
}//Face