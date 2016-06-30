package ro.drone.ciprian.joystick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout_joystick;
    TextView textView1, textView2, textView3, textView4, textView5;

    JoyStick js;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        layout_joystick = (RelativeLayout) findViewById(R.id.layout_joystick);

        js = new JoyStick(getApplicationContext(), layout_joystick, R.drawable.image_button);
        js.setStickSize(200, 200);
        js.setLayoutSize(700, 700);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(100);
        js.setMinimumDistance(15);

        layout_joystick.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if (arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    textView1.setText("X : " + String.valueOf(js.getX()));
                    textView2.setText("Y : " + String.valueOf(js.getY()));
                    textView3.setText("Angle : " + String.valueOf(js.getAngle()));
                    textView4.setText("Distance : " + String.valueOf(js.getDistance()));

                    switch (js.get8Direction()) {
                        case JoyStick.STICK_UP: {
                            textView5.setText("Direction : Up");
                            break;
                        }
                        case JoyStick.STICK_UP_RIGHT: {
                            textView5.setText("Direction : Up Right");
                            break;
                        }
                        case JoyStick.STICK_RIGHT: {
                            textView5.setText("Direction : Right");
                            break;
                        }
                        case JoyStick.STICK_DOWN_RIGHT: {
                            textView5.setText("Direction : Down Right");
                            break;
                        }
                        case JoyStick.STICK_DOWN: {
                            textView5.setText("Direction : Down");
                            break;
                        }
                        case JoyStick.STICK_DOWN_LEFT: {
                            textView5.setText("Direction : Down Left");
                            break;
                        }
                        case JoyStick.STICK_LEFT: {
                            textView5.setText("Direction : Left");
                            break;
                        }
                        case JoyStick.STICK_UP_LEFT: {
                            textView5.setText("Direction : Up Left");
                            break;
                        }
                    }
                } else if (arg1.getAction() == MotionEvent.ACTION_UP) {
                    textView1.setText("X : 0");
                    textView2.setText("Y : 0");
                    textView3.setText("Angle : 0");
                    textView4.setText("Distance : 0");
                    textView5.setText("Direction : -");
                }
                return true;
            }
        });
    }
}
