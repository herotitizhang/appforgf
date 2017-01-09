package com.angie.tony.playwithtony;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class EmoActivity extends Activity {

    private ImageView faceImageView;
    private ImageView backgroundImageView;
    private Button inMySongPlayButton;
    private Button beautifulPlayButton;
    private Button soulSisterPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emo);

        // set Image OnTouch listener
        faceImageView = (ImageView)findViewById(R.id.tony_face_image_view);
        backgroundImageView = (ImageView)findViewById(R.id.tony_background_image_view);
        ImageViewTouchListener imageViewTouchListener = new ImageViewTouchListener(backgroundImageView);
        faceImageView.setOnTouchListener(imageViewTouchListener);


        // set up listeners for playing songs
        ArrayList<Button> buttons = new ArrayList<Button>();

        inMySongPlayButton = (Button)findViewById(R.id.in_my_song_play_button);
        beautifulPlayButton = (Button)findViewById(R.id.beautiful_play_button);
        soulSisterPlayButton = (Button)findViewById(R.id.soul_sister_play_button);
        buttons.add(inMySongPlayButton);
        buttons.add(beautifulPlayButton);
        buttons.add(soulSisterPlayButton);

        SongPlayButtonClickListener inMySongPlayButtonListener =
                new SongPlayButtonClickListener(this, buttons, R.id.in_my_song_play_button, InMySongSoundService.class);
        SongPlayButtonClickListener beautifulPlayButtonListener =
            new SongPlayButtonClickListener(this, buttons, R.id.beautiful_play_button, BeautifulSoundService.class);
        SongPlayButtonClickListener soulSisterPlayButtonListener =
                new SongPlayButtonClickListener(this, buttons, R.id.soul_sister_play_button, SoulSisterSoundService.class);

        inMySongPlayButton.setOnClickListener(inMySongPlayButtonListener);
        beautifulPlayButton.setOnClickListener(beautifulPlayButtonListener);
        soulSisterPlayButton.setOnClickListener(soulSisterPlayButtonListener);
    }
}

class ImageViewTouchListener implements View.OnTouchListener {

    private static final String TAG = "ImageViewTouchListener";

    // for background images
    private ImageView backgroundImageView;
    private int bkImageIndex = 0;
    private int[] bkImages = {
            R.drawable.hendricks_park_bk,
            R.drawable.fifth_street_bk,
            R.drawable.moc_bk,
            R.drawable.emu_bk,
            R.drawable.hult_center_bk,
            R.drawable.king_estate_bk,
            R.drawable.hayward_field_bk,
            R.drawable.mcdonalds_bk,
            R.drawable.mos_bk,
            R.drawable.rose_garden_bk,
            R.drawable.saturday_market_bk,
            R.drawable.sealion_cave_bk,
            R.drawable.shelton_building_bk,
            R.drawable.the_vintage_bk,
            R.drawable.vrc_cinema_bk
    };

    public ImageViewTouchListener(ImageView backgroundImageView) {
        this.backgroundImageView = backgroundImageView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView imageView = (ImageView) v;
        int action= event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            float downX = event.getX();
            float downY = event.getY();
            if (!((downX > 105) && (downX < 589) && (downY > 611) && (downY < 1136))) {
                Log.d(TAG, "onTouch: change bk");
                this.bkImageIndex += 1;
                this.bkImageIndex %= bkImages.length;
                this.backgroundImageView.setImageResource(bkImages[bkImageIndex]);
            } else if ((downX > 307) && (downX < 423) && (downY > 976) && (downY < 1048)) {
                imageView.setImageResource(R.drawable.nose);
//                Log.d(TAG, "onTouch: nose");
            } else if ((downX > 326) && (downX < 407) && (downY > 882) && (downY < 967)) {
                imageView.setImageResource(R.drawable.cross_eye);
//                Log.d(TAG, "onTouch: cross eye");
            } else if ((downX > 226) && (downX < 316) && (downY > 902) && (downY < 960)) {
                imageView.setImageResource(R.drawable.close_left_eye);
//                Log.d(TAG, "onTouch: close left eye");
            } else if ((downX > 415) && (downX < 497) && (downY > 902) && (downY < 960)) {
                imageView.setImageResource(R.drawable.close_right_eye);
//                Log.d(TAG, "onTouch: close right eye");
            } else if ((downX > 242) && (downX < 471) && (downY > 742) && (downY < 852)) {
                imageView.setImageResource(R.drawable.forehead);
//                Log.d(TAG, "onTouch: forehead");
            } else if ((downX > 296) && (downX < 439) && (downY > 1088) && (downY < 1136)) {
                imageView.setImageResource(R.drawable.chin);
//                Log.d(TAG, "onTouch: chin");
            } else if ((downX > 172) && (downX < 256) && (downY > 1060) && (downY < 1110)) {
                imageView.setImageResource(R.drawable.left_cheek);
//                Log.d(TAG, "onTouch: left cheek");
            } else if ((downX > 485) && (downX < 560) && (downY > 1060) && (downY < 1110)) {
                imageView.setImageResource(R.drawable.right_cheek);
//                Log.d(TAG, "onTouch: right cheek");
            }
            Log.d(TAG, "onTouch: x: " + downX + "; y: " + downY);
            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            imageView.setImageResource(R.drawable.normal);
            return true;
        }
        return false;
    }
}

/**
 * Listener for a play/pause button
 */
class SongPlayButtonClickListener implements View.OnClickListener {
    protected Activity enclosingActivity;
    protected ArrayList<Button> buttons;
    private int buttonId;
    private Class associatedClass;
    protected boolean songIsPlaying = false;

    public SongPlayButtonClickListener(
            Activity activity, ArrayList<Button> allPlayButtons, int buttonId, Class associatedClass) {
        this.enclosingActivity = activity;
        this.buttons = allPlayButtons;
        this.buttonId = buttonId;
        this.associatedClass = associatedClass;
    }

    @Override
    public void onClick(View v) {
        if (songIsPlaying) {
            for (Button button: this.buttons) {
                if (button.getId() != this.buttonId) {
                    button.setEnabled(true);
                }
            }
            songIsPlaying = false;
            Intent intent = new Intent(enclosingActivity, associatedClass);
            intent.putExtra("playing", false);
            enclosingActivity.startService(intent);
        } else {
            for (Button button: this.buttons) {
                if (button.getId() != this.buttonId) {
                    button.setEnabled(false);
                }
            }
            songIsPlaying = true;
            Intent intent = new Intent(enclosingActivity, associatedClass);
            intent.putExtra("playing", true);
            enclosingActivity.startService(intent);
        }
    }
}
