package com.parsveda.brainboost.numberspuzzle.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parsveda.brainboost.numberspuzzle.R;
import com.parsveda.brainboost.numberspuzzle.base.Globals;
import com.parsveda.brainboost.numberspuzzle.base.Utils;
import com.parsveda.brainboost.numberspuzzle.model.PuzzleElement;
import com.parsveda.brainboost.numberspuzzle.model.PuzzlePath;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class HelpActivity extends AppCompatActivity {


    AbsoluteLayout absPanel;
    TextView txtHint;
    public int step = 0;
    TextView btnRetry, btnUndo, btnSkip;

    boolean GotoGame;

    public String[] messages = {
            "Puzzles Of Number",
            "Only Clear All Shapes",
            "All Shapes That Connected To Each Other Must Have Same Values",
            "You Can Refresh Puzzle",
            "Undo One Step  If You Want",
            "You Can Skip Any Puzzle If You Have 30 Credit",
            "Touch Orange Shape",
            "Shapes That Connect Are Same And Cand Be Cleared",
            "Done .",
            " Touch To Go To Game.",
    };

    PuzzlePath puzzlePath_0_1, puzzlePath_1_2, puzzlePath_2_3, puzzlePath_3_4;
    PuzzleElement puzzleElement_0, puzzleElement_1, puzzleElement_2, puzzleElement_3, puzzleElement_4;
    LinearLayout mainPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        absPanel = (AbsoluteLayout) findViewById(R.id.absPanel);
        mainPanel = (LinearLayout) findViewById(R.id.mainPanel);

        btnRetry = (TextView) findViewById(R.id.btnRetry);
        btnUndo = (TextView) findViewById(R.id.btnUndo);
        btnSkip = (TextView) findViewById(R.id.btnSkip);


        //initializePuzzle();
        txtHint = (TextView) findViewById(R.id.txtHint);
        txtHint.setTypeface(Globals.typeface, Typeface.BOLD);

        txtHint.setText(messages[step]);

        new SimpleTooltip.Builder(HelpActivity.this)
                .anchorView(txtHint)
                .text(messages[step])
                .gravity(Gravity.BOTTOM)
                .animated(true)
                .transparentOverlay(true)
                .build()
                .show();


        mainPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (step == messages.length - 1)
//                    return;


                switch (step) {
                    case 0:
                        step++;
                        txtHint.setText(messages[step]);
                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(txtHint)
                                .text(messages[step])
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();

                        break;
                    case 1:
                        step++;
                        txtHint.setText(messages[step]);
                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(txtHint)
                                .text(messages[step])
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();

                        break;
                    case 2:
                        step++;
                        txtHint.setText(messages[step]);
                        btnRetry.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_red_retry));
                        btnUndo.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_undo));
                        btnSkip.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_skip));
                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(btnRetry)
                                .text(messages[step])
                                .gravity(Gravity.TOP)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();

                        break;
                    case 3:
                        step++;
                        txtHint.setText(messages[step]);
                        btnRetry.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_retry));
                        btnUndo.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_red_undo));
                        btnSkip.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_skip));
                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(btnUndo)
                                .text(messages[step])
                                .gravity(Gravity.TOP)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();


                        break;


                    case 4:
                        step++;
                        txtHint.setText(messages[step]);
                        btnRetry.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_retry));
                        btnUndo.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_undo));
                        btnSkip.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_red_skip));
                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(btnSkip)
                                .text(messages[step])
                                .gravity(Gravity.TOP)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();


                        break;









                    case 5:
                        step++;

                        txtHint.setText(messages[step]);
                        btnRetry.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_retry));
                        btnUndo.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_blue_undo));
                        puzzleElement_2.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_orange));
                        puzzleElement_2.setEnabled(true);


                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(puzzleElement_2)
                                .text(messages[step])
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();


                        break;
                    case 6:
                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(puzzleElement_2)
                                .text(messages[step])
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();

                        break;
                    case 7:
                        step++;
                        txtHint.setText(messages[step]);
                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(txtHint)
                                .text(messages[step])
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();

                        puzzleElement_0.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzleElement_0.setVisibility(View.INVISIBLE);
                            }
                        });

                        puzzleElement_1.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzleElement_1.setVisibility(View.INVISIBLE);
                            }
                        });

                        puzzleElement_3.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzleElement_3.setVisibility(View.INVISIBLE);
                            }
                        });

                        puzzleElement_4.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzleElement_4.setVisibility(View.INVISIBLE);
                            }
                        });


                        puzzlePath_0_1.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzlePath_0_1.setVisibility(View.INVISIBLE);
                            }
                        });

                        puzzlePath_3_4.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzlePath_3_4.setVisibility(View.INVISIBLE);
                            }
                        });


                        break;
                    case 8:
                        step++;
                        txtHint.setText(messages[step]);

                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(txtHint)
                                .text(messages[step])
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();

                        break;
                    case 9:
                        step++;
                        if (GotoGame == true) {
                            Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(HelpActivity.this, MenuActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        break;
                }

                if (getIntent() != null)
                    GotoGame = getIntent().getBooleanExtra("Game", true);


            }
        });


        puzzlePath_0_1 = new PuzzlePath(this);
        AbsoluteLayout.LayoutParams params_0_1 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_0_1.width = Utils.convertDpToPixel(getResources(), Globals.PATH_WIDTH);
        params_0_1.height = Utils.convertDpToPixel(getResources(), Globals.PATH_LENGTH * 1);
        params_0_1.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0 + (Globals.ELEMENT_SIZE / 2) - (Globals.PATH_WIDTH / 2));
        params_0_1.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0 + (Globals.ELEMENT_SIZE / 2));
        puzzlePath_0_1.setBackground(ContextCompat.getDrawable(this, R.drawable.line_circle_white));
        //puzzlePath_0_1.setId(R.id.btn3);
        puzzlePath_0_1.setFirstElementId(0);
        puzzlePath_0_1.setSecondElementId(1);
        absPanel.addView(puzzlePath_0_1, params_0_1);

        puzzlePath_1_2 = new PuzzlePath(this);
        AbsoluteLayout.LayoutParams params_1_2 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_1_2.width = Utils.convertDpToPixel(getResources(), Globals.PATH_WIDTH);
        params_1_2.height = Utils.convertDpToPixel(getResources(), Globals.PATH_LENGTH * 1);
        params_1_2.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0 + (Globals.ELEMENT_SIZE / 2) - (Globals.PATH_WIDTH / 2));
        params_1_2.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 1 + (Globals.ELEMENT_SIZE / 2));
        puzzlePath_1_2.setBackground(ContextCompat.getDrawable(this, R.drawable.line_circle_white));
        //puzzlePath_1_2.setId(R.id.btn3);
        puzzlePath_1_2.setFirstElementId(1);
        puzzlePath_1_2.setSecondElementId(2);
        absPanel.addView(puzzlePath_1_2, params_1_2);

        puzzlePath_2_3 = new PuzzlePath(this);
        AbsoluteLayout.LayoutParams params_2_3 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_2_3.width = Utils.convertDpToPixel(getResources(), Globals.PATH_LENGTH * 1);
        params_2_3.height = Utils.convertDpToPixel(getResources(), Globals.PATH_WIDTH);
        params_2_3.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0 + (Globals.ELEMENT_SIZE / 2));
        params_2_3.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 2 + (Globals.ELEMENT_SIZE / 2) - (Globals.PATH_WIDTH / 2));
        puzzlePath_2_3.setBackground(ContextCompat.getDrawable(this, R.drawable.line_circle_white));
        //puzzlePath_2_3.setId(R.id.btn3);
        puzzlePath_2_3.setFirstElementId(2);
        puzzlePath_2_3.setSecondElementId(3);
        absPanel.addView(puzzlePath_2_3, params_2_3);

        puzzlePath_3_4 = new PuzzlePath(this);
        AbsoluteLayout.LayoutParams params_3_4 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_3_4.width = Utils.convertDpToPixel(getResources(), Globals.PATH_LENGTH * 1);
        params_3_4.height = Utils.convertDpToPixel(getResources(), Globals.PATH_WIDTH);
        params_3_4.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 1 + (Globals.ELEMENT_SIZE / 2));
        params_3_4.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 2 + (Globals.ELEMENT_SIZE / 2) - (Globals.PATH_WIDTH / 2));
        puzzlePath_3_4.setBackground(ContextCompat.getDrawable(this, R.drawable.line_circle_white));
        //puzzlePath_3_4.setId(R.id.btn3);
        puzzlePath_3_4.setFirstElementId(3);
        puzzlePath_3_4.setSecondElementId(4);
        absPanel.addView(puzzlePath_3_4, params_3_4);


        puzzleElement_0 = new PuzzleElement(this);
        AbsoluteLayout.LayoutParams params_0 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_0.width = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_0.height = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_0.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0);
        params_0.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0);
        puzzleElement_0.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue));
        puzzleElement_0.getInfo().setValue(4);
        puzzleElement_0.update();
        //puzzleElement_0.setId(R.id.btn3);
        puzzleElement_0.setGravity(Gravity.CENTER);
        puzzleElement_0.setTextColor(ContextCompat.getColor(this, R.color.white));
        puzzleElement_0.setTypeface(Globals.typeface, Typeface.BOLD);
        puzzleElement_0.setTextSize(Globals.ELEMENT_TEXT_SIZE);
        absPanel.addView(puzzleElement_0, params_0);

        puzzleElement_1 = new PuzzleElement(this);
        AbsoluteLayout.LayoutParams params_1 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_1.width = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_1.height = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_1.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0);
        params_1.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 1);
        puzzleElement_1.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue));
        puzzleElement_1.getInfo().setValue(3);
        puzzleElement_1.update();
        //puzzleElement_1.setId(R.id.btn3);
        puzzleElement_1.setGravity(Gravity.CENTER);
        puzzleElement_1.setTextColor(ContextCompat.getColor(this, R.color.white));
        puzzleElement_1.setTypeface(Globals.typeface, Typeface.BOLD);
        puzzleElement_1.setTextSize(Globals.ELEMENT_TEXT_SIZE);
        absPanel.addView(puzzleElement_1, params_1);

        puzzleElement_2 = new PuzzleElement(this);
        AbsoluteLayout.LayoutParams params_2 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_2.width = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_2.height = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_2.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 0);
        params_2.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 2);
        puzzleElement_2.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue));
        puzzleElement_2.getInfo().setValue(1);
        puzzleElement_2.update();
        //puzzleElement_2.setId(R.id.btn3);
        puzzleElement_2.setGravity(Gravity.CENTER);
        puzzleElement_2.setTextColor(ContextCompat.getColor(this, R.color.white));
        puzzleElement_2.setTypeface(Globals.typeface, Typeface.BOLD);
        puzzleElement_2.setTextSize(Globals.ELEMENT_TEXT_SIZE);
        absPanel.addView(puzzleElement_2, params_2);
        puzzleElement_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                puzzlePath_1_2.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        puzzlePath_1_2.setVisibility(View.INVISIBLE);
                    }
                });


                puzzlePath_2_3.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        puzzlePath_2_3.setVisibility(View.INVISIBLE);
                    }
                });


                puzzleElement_2.animate().setDuration(200).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        puzzleElement_2.setVisibility(View.INVISIBLE);

                        step++;
                        txtHint.setText(messages[step]);

                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(puzzleElement_3)
                                .text(messages[step])
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();

                        new SimpleTooltip.Builder(HelpActivity.this)
                                .anchorView(puzzleElement_0)
                                .text(messages[step])
                                .gravity(Gravity.TOP)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                                .show();


                        puzzleElement_1.setText("4");
                        puzzleElement_1.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_green));
                        puzzleElement_0.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_green));
                        puzzleElement_3.setText("2");
                        puzzleElement_3.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_orange));
                        puzzleElement_4.setBackground(ContextCompat.getDrawable(HelpActivity.this, R.drawable.button_circle_orange));

                    }
                });


            }
        });


        puzzleElement_3 = new PuzzleElement(this);
        AbsoluteLayout.LayoutParams params_3 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_3.width = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_3.height = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_3.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 1);
        params_3.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 2);
        puzzleElement_3.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue));
        puzzleElement_3.getInfo().setValue(1);
        puzzleElement_3.update();
        //puzzleElement_3.setId(R.id.btn3);
        puzzleElement_3.setGravity(Gravity.CENTER);
        puzzleElement_3.setTextColor(ContextCompat.getColor(this, R.color.white));
        puzzleElement_3.setTypeface(Globals.typeface, Typeface.BOLD);
        puzzleElement_3.setTextSize(Globals.ELEMENT_TEXT_SIZE);
        absPanel.addView(puzzleElement_3, params_3);

        puzzleElement_4 = new PuzzleElement(this);
        AbsoluteLayout.LayoutParams params_4 = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
        params_4.width = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_4.height = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
        params_4.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 2);
        params_4.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * 2);
        puzzleElement_4.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue));
        puzzleElement_4.getInfo().setValue(2);
        puzzleElement_4.update();
        //puzzleElement_4.setId(R.id.btn3);
        puzzleElement_4.setGravity(Gravity.CENTER);
        puzzleElement_4.setTextColor(ContextCompat.getColor(this, R.color.white));
        puzzleElement_4.setTypeface(Globals.typeface, Typeface.BOLD);
        puzzleElement_4.setTextSize(Globals.ELEMENT_TEXT_SIZE);
        absPanel.addView(puzzleElement_4, params_4);


        puzzleElement_2.setEnabled(false);

    }

}
