package com.parsveda.brainboost.numberspuzzle.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parsveda.brainboost.numberspuzzle.R;
import com.parsveda.brainboost.numberspuzzle.base.Globals;
import com.parsveda.brainboost.numberspuzzle.base.Utils;
import com.parsveda.brainboost.numberspuzzle.base.graph.CCBfs;
import com.parsveda.brainboost.numberspuzzle.base.graph.CCGraph;
import com.parsveda.brainboost.numberspuzzle.base.graph.CCPath;
import com.parsveda.brainboost.numberspuzzle.base.graph.CCPaths;
import com.parsveda.brainboost.numberspuzzle.base.graph.GraphEdge;
import com.parsveda.brainboost.numberspuzzle.model.ElementInfo;
import com.parsveda.brainboost.numberspuzzle.model.ElementInfos;
import com.parsveda.brainboost.numberspuzzle.model.PuzzleElement;
import com.parsveda.brainboost.numberspuzzle.model.PuzzleElementType;
import com.parsveda.brainboost.numberspuzzle.model.PuzzlePath;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainPanel;
    Random rand;
    public CountDownTimer timer;
    MediaPlayer mediaPlayer;
    AbsoluteLayout absPanel;
    RelativeLayout controlPanel;


    Button btnUndo;
    Button btnNext;
    Button btnRetry;
    Button btnBack;
    Button btnSkip;

    TextView txtCredit;
    TextView txtCreditTitle;


    TextView txtPuzzleNumber;
    TextView txtPuzzleNumberTitle;




    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        Log.d(Globals.LOG_TAG, "MAIN 1");
        final TextView txttime = (TextView) findViewById(R.id.txtTime);
        txttime.setTypeface(Globals.typeface, Typeface.BOLD);
        txttime.setTextColor(getResources().getColor(R.color.white));
        TextView txtscore = (TextView) findViewById(R.id.txtScoreValue);
        txtscore.setTypeface(Globals.typeface, Typeface.BOLD);
        txtscore.setTextColor(getResources().getColor(R.color.white));


        txtPuzzleNumber = (TextView) findViewById(R.id.txtPuzzleNumber);
        txtPuzzleNumber.setTypeface(Globals.typeface, Typeface.BOLD);
        txtPuzzleNumber.setText(Globals.selectedPuzzleId + "");


        txtPuzzleNumberTitle = (TextView) findViewById(R.id.txtPuzzleNumberTitle);
        txtPuzzleNumberTitle.setTypeface(Globals.typeface, Typeface.BOLD);




        txtCredit = (TextView) findViewById(R.id.txtCredit);
        txtCredit.setTypeface(Globals.typeface, Typeface.BOLD);
        txtCredit.setText(Globals.credit + "");

        txtCreditTitle= (TextView) findViewById(R.id.txtCreditTitle);
        txtCreditTitle.setTypeface(Globals.typeface, Typeface.BOLD);


        Log.d(Globals.LOG_TAG, "MAIN 2");
        rand = new Random();
        mainPanel = (RelativeLayout) findViewById(R.id.pnlMain);
        controlPanel = (RelativeLayout) findViewById(R.id.controlPanel);
        absPanel = (AbsoluteLayout) findViewById(R.id.absPanel);


        //absPanel.setEnabled(false);

        Globals.selectedPuzzle = Globals.puzzles.find(Globals.selectedPuzzleId);
        clear();
        Log.d(Globals.LOG_TAG, "MAIN 3");

        initializePuzzle();
        Log.d(Globals.LOG_TAG, "MAIN 4");
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRetry = (Button) findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Globals.selectedPuzzleId++;
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undo();
            }
        });


        btnSkip = (Button) findViewById(R.id.btnSkip);

//        if (Globals.credit >= Globals.creditsNeededForSkip) {
//            btnSkip.setEnabled(true);
//        } else {
//            btnSkip.setEnabled(false);
//        }

        //Toast.makeText(this,btnSkip.getText()+"", Toast.LENGTH_LONG).show();


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Globals.credit < Globals.creditsNeededForSkip) {
                    Toast.makeText(MainActivity.this, "You Do Not Have Enough Credit To Skip \n You Need 30 Credit", Toast.LENGTH_SHORT).show();
                    return;
                }


                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.layout_skip_alert);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setTitle("SKIP");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.txtTilte);
                text.setTypeface(Globals.typeface, Typeface.BOLD);

                Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
                btnYes.setTypeface(Globals.typeface, Typeface.BOLD);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Globals.selectedPuzzleId++;
                        Globals.credit -= Globals.creditsNeededForSkip;
                        try {
                            File extDir = getExternalFilesDir(null);
                            //String path = extDir.getAbsolutePath();
                            ResultActivity.file = new File(extDir, Globals.SAVE_FILE_NAME);
                            ResultActivity.createFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }
                });

                Button btnNo = (Button) dialog.findViewById(R.id.btnNo);
                btnNo.setTypeface(Globals.typeface, Typeface.BOLD);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();


//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("SKIP")
//                        .setMessage("Do You Want To Skip?")
//
//                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                Globals.selectedPuzzleId++;
//                                Globals.credit -= Globals.creditsNeededForSkip;
//                                try {
//
//                                    File extDir = getExternalFilesDir(null);
//                                    //String path = extDir.getAbsolutePath();
//                                    ResultActivity.file = new File(extDir, Globals.SAVE_FILE_NAME);
//                                    ResultActivity.createFile();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // do nothing
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();


            }
        });


//        timer = new CountDownTimer(Globals.TimeForAnyStageParts, Globals.IntervalOfShowingTime) {
//            public void onTick(long millisUntilFinished) {
//                Globals.currentStage.setTime((float) millisUntilFinished / 1000);
//                TextView txttime = (TextView) findViewById(R.id.txtTime);
//                txttime.setText(Globals.currentStage.getTime() + "");
//            }
//
//            public void onFinish() {
//
//                if (Globals.currentStage.getType() == GameType.SUDDEN_DEATH) {
//                    //clear();
//                    txttime.setText("0.000");
//                    timer.cancel();
//                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//                    startActivity(intent);
//                    finish();
//                    return;
//                }
//
//                Globals.currentStage.setTime(0);
//                TextView txttime = (TextView) findViewById(R.id.txtTime);
//                txttime.setText("0.000");
////                clear();
//                Globals.currentStage.addScore(-1);
//                TextView txtvalue = (TextView) findViewById(R.id.txtScoreValue);
//                txtvalue.setText(Globals.currentStage.getScore() + "");
//                timer.cancel();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //changeQuestion();
//                        timer.start();
//                    }
//                }, Globals.DelayBetweenStageParts);
//            }
//        };


    }


    public void changeTouchSituation(boolean enabled) {
        int childCount = absPanel.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = absPanel.getChildAt(i);
            view.setEnabled(enabled);
        }

        btnBack.setEnabled(enabled);
        btnRetry.setEnabled(enabled);
        btnNext.setEnabled(enabled);
        btnUndo.setEnabled(enabled);
        //btnSkip.setEnabled(enabled);

    }


    public void initializePuzzle() {


        for (ElementInfo element : Globals.selectedPuzzle.getElements()) {
            int baseRow = element.getRow();
            int baseColumn = element.getColumn();
            for (Integer nextId : element.getConnects()) {
                if (nextId > element.getId()) {

                    for (ElementInfo elmnt : Globals.selectedPuzzle.getElements()) {
                        if (elmnt.getId() == nextId) {
                            int nextRow = elmnt.getRow();
                            int nextcolumn = elmnt.getColumn();

                            if (nextcolumn == baseColumn) {

                                final PuzzlePath puzzlePath = new PuzzlePath(this);
                                AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
                                params.width = Utils.convertDpToPixel(getResources(), Globals.PATH_WIDTH);
                                params.height = Utils.convertDpToPixel(getResources(), Globals.PATH_LENGTH * Math.abs(element.getRow() - nextRow));
                                params.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * element.getColumn() + (Globals.ELEMENT_SIZE / 2) - (Globals.PATH_WIDTH / 2));
                                params.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * Math.min(element.getRow(), nextRow) + (Globals.ELEMENT_SIZE / 2));
                                puzzlePath.setBackground(ContextCompat.getDrawable(this, R.drawable.line_circle_white));
                                //puzzlePath.setId(R.id.btn3);
                                puzzlePath.setFirstElementId(element.getId());
                                puzzlePath.setSecondElementId(elmnt.getId());
                                absPanel.addView(puzzlePath, params);
                                Globals.paths.add(puzzlePath);

                            } else if (nextRow == baseRow) {

                                final PuzzlePath puzzlePath = new PuzzlePath(this);
                                AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
                                params.width = Utils.convertDpToPixel(getResources(), Globals.PATH_LENGTH * Math.abs(element.getColumn() - nextcolumn));
                                params.height = Utils.convertDpToPixel(getResources(), Globals.PATH_WIDTH);
                                params.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * Math.min(element.getColumn(), nextcolumn) + (Globals.ELEMENT_SIZE / 2));
                                params.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * element.getRow() + (Globals.ELEMENT_SIZE / 2) - (Globals.PATH_WIDTH / 2));
                                puzzlePath.setBackground(ContextCompat.getDrawable(this, R.drawable.line_circle_white));
                                //puzzlePath.setId(R.id.btn3);
                                puzzlePath.setFirstElementId(element.getId());
                                puzzlePath.setSecondElementId(elmnt.getId());
                                Globals.paths.add(puzzlePath);
                                absPanel.addView(puzzlePath, params);

                            }
                            break;
                        }
                    }
                }
            }
        }

        for (ElementInfo element : Globals.selectedPuzzle.getElements()) {

            final PuzzleElement puzzleElement = new PuzzleElement(this);
            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);
            params.width = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
            params.height = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_SIZE);
            params.x = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * element.getColumn());
            params.y = Utils.convertDpToPixel(getResources(), Globals.ELEMENT_DISTANCE * element.getRow());


            //Log.d(Globals.LOG_TAG, "BE");
            puzzleElement.setInfo(element.clone());
            switch (element.getType()) {
                case NUMBER:
                    puzzleElement.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue));
                    puzzleElement.update();
                    break;
                case HORIZONTAL_SWAPPER:
                    puzzleElement.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue_horizontal_swapper));
                    puzzleElement.getInfo().setValue(-1);
                    puzzleElement.setText("");
                    break;
                case VERTICAL_SWAPPER:
                    puzzleElement.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue_vertical_swapper));
                    puzzleElement.getInfo().setValue(-1);
                    puzzleElement.setText("");
                    break;
                case LEFT_ROTATOR:
                    puzzleElement.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue_left_rotator));
                    puzzleElement.getInfo().setValue(-1);
                    puzzleElement.setText("");
                    break;
                case RIGHT_ROTATOR:
                    puzzleElement.setBackground(ContextCompat.getDrawable(this, R.drawable.button_circle_blue_right_rotator));
                    puzzleElement.getInfo().setValue(-1);
                    puzzleElement.setText("");
                    break;
            }

            Log.d(Globals.LOG_TAG, element.getType().toString());
            //puzzleElement.setId(R.id.btn3);
            puzzleElement.setGravity(Gravity.CENTER);
            puzzleElement.setTextColor(ContextCompat.getColor(this, R.color.light_text));
            puzzleElement.setTypeface(Globals.typeface, Typeface.BOLD);
            puzzleElement.setTextSize(Globals.ELEMENT_TEXT_SIZE);


            puzzleElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (puzzleElement.getInfo().getConnects().size() == 0)
                        return;

                    ArrayList<Integer> ids = new ArrayList<>();
                    ArrayList<PuzzleElement> elements = new ArrayList<>();
                    switch (puzzleElement.getInfo().getType()) {
                        case NUMBER:
                            addDataToUndo();
                            //ArrayList<Integer> ids = Globals.paths.removeConnectedPathToSpecifiedElement(puzzleElement.getInfo().getId());
                            ids = removeConnectedPathToSpecifiedElement(puzzleElement.getInfo().getId());

//                            ElementInfos infos = Globals.undoDatas.get(Globals.undoDatas.size() - 1);
//                            for (int id : ids) {
//                                for (int k = 0; k < infos.size(); k++) {
//                                    if (infos.get(k).getId() == id) {
//                                        Log.d(Globals.LOG_TAG, "IIIDDDDDDDD : " + infos.get(k).getId());
//                                        //infos.remove(k);
//                                        //infos.add(finalElm.getInfo().clone());
//                                        break;
//                                    }
//                                }
//                            }


                            Globals.elements.updatePuzzleElementsInfo(ids, puzzleElement.getInfo());
                            puzzleElement.getInfo().setActive(false);
                            changeTouchSituation(false);
                            puzzleElement.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    puzzleElement.setVisibility(View.INVISIBLE);
                                    Log.d(Globals.LOG_TAG, "BEGIN");

                                    changeTouchSituation(true);
//                                    ElementInfos infos = Globals.undoDatas.get(Globals.undoDatas.size() - 1);
//                                    for (int k = 0; k < infos.size(); k++) {
//                                        if (infos.get(k).getId() == finalElm.getInfo().getId()) {
//                                            Log.d(Globals.LOG_TAG, "IIIDDDDDDDD : " + infos.get(k).getId());
//                                            infos.remove(k);
//                                            infos.add(finalElm.getInfo().clone());
//                                            break;
//                                        }
//                                    }


                                    removeCompletedElements();

                                    //addDataToUndo();
                                    Log.d(Globals.LOG_TAG, "END");
                                }
                            });
                            break;
                        case HORIZONTAL_SWAPPER:
                            PuzzleElement left = Globals.elements.getElementLeftOfPositionThatConnected(puzzleElement.getInfo());
                            PuzzleElement right = Globals.elements.getElementRightOfPositionThatConnected(puzzleElement.getInfo());
                            if (left != null && right != null) {
                                addDataToUndo();
                                int leftval = left.getInfo().getValue();
                                left.getInfo().setValue(right.getInfo().getValue());
                                left.update();
                                right.getInfo().setValue(leftval);
                                right.update();
                                ids = removeConnectedPathToSpecifiedElement(puzzleElement.getInfo().getId());
                                Globals.elements.updatePuzzleElementsInfo(ids, puzzleElement.getInfo());
                                puzzleElement.getInfo().setActive(false);
                                changeTouchSituation(false);
                                puzzleElement.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        puzzleElement.setVisibility(View.INVISIBLE);
                                        changeTouchSituation(true);
                                        Log.d(Globals.LOG_TAG, "BEGIN");
                                        removeCompletedElements();

                                        //addDataToUndo();
                                        Log.d(Globals.LOG_TAG, "END");
                                    }
                                });

                            }
                            break;
                        case VERTICAL_SWAPPER:
                            //Log.d(Globals.LOG_TAG, "VERTTTTTTTTTTTTTTTTTTTT 111111111111");
                            PuzzleElement top = Globals.elements.getElementTopOfPositionThatConnected(puzzleElement.getInfo());
                            PuzzleElement bottom = Globals.elements.getElementBottomOfPositionThatConnected(puzzleElement.getInfo());
                            //Log.d(Globals.LOG_TAG, "VERTTTTTTTTTTTTTTTTTTTT 222222222222");
                            if (top != null && bottom != null) {
                                //Log.d(Globals.LOG_TAG, "VERTTTTTTTTTTTTTTTTTTTT 3333333333333");
                                addDataToUndo();
                                int bottomval = bottom.getInfo().getValue();
                                bottom.getInfo().setValue(top.getInfo().getValue());
                                bottom.update();
                                top.getInfo().setValue(bottomval);
                                top.update();
                                ids = removeConnectedPathToSpecifiedElement(puzzleElement.getInfo().getId());

                                Globals.elements.updatePuzzleElementsInfo(ids, puzzleElement.getInfo());
                                puzzleElement.getInfo().setActive(false);
                                changeTouchSituation(false);
                                puzzleElement.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        puzzleElement.setVisibility(View.INVISIBLE);
                                        changeTouchSituation(true);
                                        Log.d(Globals.LOG_TAG, "BEGIN");
                                        removeCompletedElements();

                                        // addDataToUndo();

                                        Log.d(Globals.LOG_TAG, "END");
                                    }
                                });

                            }
                            break;
                        case LEFT_ROTATOR:
                            elements = Globals.elements.getElementsThatConnected(puzzleElement.getInfo());


                            break;
                        case RIGHT_ROTATOR:
                            elements = Globals.elements.getElementsThatConnected(puzzleElement.getInfo());
                            break;
                    }


                }
            });
            absPanel.addView(puzzleElement, params);
            Globals.elements.add(puzzleElement);
        }

        //addDataToUndo();

    }

    public void addDataToUndo() {
        ElementInfos infos = new ElementInfos();
        for (PuzzleElement puzzleElement : Globals.elements) {
            ElementInfo info = puzzleElement.getInfo().clone();
            //Log.d(Globals.LOG_TAG, String.format("Id:%d type:%s active:%b", info.getId(), info.getType().toString(), info.isActive()));
            infos.add(info);
        }

        //Log.d(Globals.LOG_TAG, "INFO SIZE : " + infos.size());
        Globals.undoDatas.add(infos);
    }

    public void updateLastUndoData() {
        ElementInfos infos = new ElementInfos();
        for (PuzzleElement puzzleElement : Globals.elements) {
            ElementInfo info = puzzleElement.getInfo().clone();
            //Log.d(Globals.LOG_TAG, String.format("Id:%d type:%s active:%b", info.getId(), info.getType().toString(), info.isActive()));
            infos.add(info);
        }

        //Log.d(Globals.LOG_TAG, "INFO SIZE : " + infos.size());
        Globals.undoDatas.remove(Globals.undoDatas.size() - 1);
        Globals.undoDatas.add(infos);
    }


    public void undo() {

        if (Globals.undoDatas.size() == 0)
            return;


        ElementInfos infos = Globals.undoDatas.get(Globals.undoDatas.size() - 1);

        for (ElementInfo info : infos) {
            for (final PuzzleElement puzzleElement : Globals.elements) {

                if (info.getId() == puzzleElement.getInfo().getId()) {
                    //info.setActive(true);
                    puzzleElement.setInfo(info.clone());
                    Log.d(Globals.LOG_TAG, String.format("Id:%d type:%s active:%b", puzzleElement.getInfo().getId(), puzzleElement.getInfo().getType().toString(), puzzleElement.getInfo().isActive()));
                    puzzleElement.update();

                    if (puzzleElement.getInfo().isActive() == true) {

                        puzzleElement.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION / 10).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzleElement.setVisibility(View.VISIBLE);
                            }
                        });
                    } else {
                        puzzleElement.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION / 10).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                puzzleElement.setVisibility(View.INVISIBLE);
                            }
                        });
                    }


//                    for (final PuzzlePath path : Globals.paths) {
//                        if (path.getFirstElementId() == info.getId() || path.getSecondElementId() == info.getId()) {
//                            path.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION / 10).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
//                                @Override
//                                public void onAnimationEnd(Animator animation) {
//                                    super.onAnimationEnd(animation);
//                                    path.setVisibility(View.VISIBLE);
//                                }
//                            });
//
//                        }
//                    }


//                    puzzleElement.setVisibility(View.VISIBLE);
                    break;
                }
            }
        }

        int a = 1;
        for (final PuzzlePath path : Globals.paths) {

            if (Globals.elements.find(path.getFirstElementId()).getInfo().isActive() == true
                    && Globals.elements.find(path.getSecondElementId()).getInfo().isActive() == true) {

                final int finalA = a;
                path.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION / 10).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        path.setVisibility(View.VISIBLE);
                        Log.d(Globals.LOG_TAG, "S " + finalA);
                    }
                });
            }
            a++;
        }


        Globals.undoDatas.remove(Globals.undoDatas.size() - 1);
    }

    public ArrayList<Integer> removeConnectedPathToSpecifiedElement(int elementId) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (final PuzzlePath path : Globals.paths) {
            if (path.getFirstElementId() == elementId || path.getSecondElementId() == elementId) {
                int id = path.getFirstElementId() == elementId ? path.getSecondElementId() : path.getFirstElementId();
                ids.add(id);
                path.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        path.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }
        return ids;
    }


    public void removeCompletedElements() {

        CCBfs bfs = new CCBfs();
        CCGraph graph = new CCGraph(false);
        int newId = 1;

        ArrayList<GraphEdge> graphEdges = new ArrayList<>();

        for (PuzzleElement element : Globals.elements) {
            if (element.getVisibility() == View.VISIBLE) {
                Log.d(Globals.LOG_TAG, "VALLLLL : " + element.getInfo().getValue());
                GraphEdge mp = new GraphEdge(element.getInfo().getId(), newId);
                graphEdges.add(mp);
                newId++;
            }
        }

        graph.nvertices = graphEdges.size();

        CCPaths paths = new CCPaths();

        for (PuzzleElement element : Globals.elements) {
            if (element.getVisibility() == View.VISIBLE) {
                int firstNewId = 0;
                for (GraphEdge mp : graphEdges) {
                    if (mp.getOriginalId() == element.getInfo().getId()) {
                        firstNewId = mp.getNewId();
                        break;
                    }
                }
                for (int a : element.getInfo().getConnects()) {
                    int secondNewId = 0;

                    for (GraphEdge mp : graphEdges) {
                        if (mp.getOriginalId() == a) {
                            secondNewId = mp.getNewId();
                            CCPath path = new CCPath(firstNewId, secondNewId);
                            paths.add(path);
                        }
                    }
                }
            }
        }

        graph.insertEdges(paths, false);
        bfs.connected_components(graph);
        ArrayList<ArrayList<Integer>> origcoms = new ArrayList<ArrayList<Integer>>();

        for (ArrayList<Integer> com : bfs.coms) {
            ArrayList<Integer> origcom = new ArrayList<>();
//            Log.d(Globals.LOG_TAG, "++++++++++++++++++++++++++++++++++++++++++++++++");
            for (int a : com) {
                for (GraphEdge mp : graphEdges) {
                    if (mp.getNewId() == a) {
                        int OrigId = mp.getOriginalId();
                        origcom.add(OrigId);
//                        Log.d(Globals.LOG_TAG, "NEW : " + a + " ORIG : " + OrigId + "");
                        break;
                    }
                }
            }
            origcoms.add(origcom);
//            Log.d(Globals.LOG_TAG, "++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        for (ArrayList<Integer> origcom : origcoms) {

            if (origcom.size() == 1 || origcom.size() == 0)
                continue;

            int baseValue = Globals.elements.find(origcom.get(0)).getInfo().getValue();
            boolean todelete = true;
            for (int a : origcom) {
                if (Globals.elements.find(a).getInfo().getValue() != baseValue) {
                    todelete = false;
                }
            }

            if (todelete == false)
                continue;

            for (int a : origcom) {


                PuzzleElement elm = null;
                if ((elm = Globals.elements.find(a)) == null)
                    continue;
                if (elm.getInfo().getType() != PuzzleElementType.NUMBER)
                    continue;


                final PuzzleElement finalElm = elm;


                //UPDATE UNODO DATA
//                ElementInfos infos = Globals.undoDatas.get(Globals.undoDatas.size() - 1);
////                for(ElementInfo info : infos)
////                {
////                    if (info.getId() == finalElm.getInfo().getId()) {
////                        info.setActive(false);
////                        break;
////                    }
////                }
//
//                for (int k = 0; k < infos.size(); k++) {
//                    if (infos.get(k).getId() == finalElm.getInfo().getId()) {
//                        //infos.get(k).setActive(false);
//                        infos.remove(k);
//                        infos.add(finalElm.getInfo().clone());
//                        break;
//                    }
//                }

                /////////////////////////////////////////
                finalElm.getInfo().setActive(false);
                elm.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

//                        ElementInfos infos = Globals.undoDatas.get(Globals.undoDatas.size() - 1);
//                        for (int k = 0; k < infos.size(); k++) {
//                            if (infos.get(k).getId() == finalElm.getInfo().getId()) {
//                                Log.d(Globals.LOG_TAG, "IIIDDDDDDDD : " + infos.get(k).getId());
//                                infos.remove(k);
//                                infos.add(finalElm.getInfo().clone());
//                                break;
//                            }
//                        }


                        finalElm.setVisibility(View.INVISIBLE);


                        if (checkVictory() == true) {
                            Globals.credit += Globals.creditPerPuzzle;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, Globals.DelayBetweenStageParts);
                        }
                    }
                });

                for (final PuzzlePath ppath : Globals.paths) {
                    if (ppath.getFirstElementId() == a || ppath.getSecondElementId() == a) {
                        ppath.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                ppath.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }

            }
        }


        //updateLastUndoData();

    }


    public boolean checkVictory() {
        for (PuzzleElement element : Globals.elements) {
            if (element.getVisibility() == View.VISIBLE) {
                return false;
            }
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        //timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //timer.cancel();
    }

    public void clear() {

        //mainPanel.removeAllViews();
        absPanel.removeAllViews();
        Globals.elements.clear();
        Globals.paths.clear();
        Globals.buttons.clear();
        //Globals.currentStage.setCurrentValue(0);
        Globals.undoDatas.clear();
    }
}
