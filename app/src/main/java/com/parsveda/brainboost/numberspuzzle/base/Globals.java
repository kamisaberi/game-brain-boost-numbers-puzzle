package com.parsveda.brainboost.numberspuzzle.base;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.parsveda.brainboost.numberspuzzle.model.ElementInfo;
import com.parsveda.brainboost.numberspuzzle.model.ElementInfos;
import com.parsveda.brainboost.numberspuzzle.model.PuzzleElementType;
import com.parsveda.brainboost.numberspuzzle.model.PuzzleElements;
import com.parsveda.brainboost.numberspuzzle.model.PuzzleInfo;
import com.parsveda.brainboost.numberspuzzle.model.PuzzleInfos;
import com.parsveda.brainboost.numberspuzzle.model.PuzzlePaths;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by kami on 12/16/2016.
 */
public class Globals {


    public static GoogleApiClient apiClient;
    public static String mCurrentSaveName = "snapshotTemp";

    public static final String PREF_SAVE_NAME_KEY = "stage";


    public static List<Button> buttons = new ArrayList<>();
    public static Context context;
    public static File fileSaveData;
    public static final String SAVE_FILE_NAME = "player.json";
    public static final boolean COPY_PUZZLE_IN_EXTERNAL_STORAGE = false;
    public static final boolean DEBUG_MODE = false;
    public static boolean CanSaveData = false;
    public static int DelayBetweenStageParts = 200;
    public static Typeface typeface;
    public static String LOG_TAG = "Three_Seconds";
    //public static Score currentStage = new Score();
    //public static List<Preset> presets = new ArrayList<>();
    //public static List<StageItem> stages = new ArrayList<>();
    //public static List<Level> levels = new ArrayList<>();
    public static PuzzleInfos puzzles = new PuzzleInfos();
    public static PuzzleInfo selectedPuzzle = new PuzzleInfo();
    public static int selectedPuzzleId = 0;
    public static int showHelp = 0;
    public static int credit = 0;
    public static int creditPerPuzzle = 5;
    public static int creditsNeededForSkip = 30;
    public static final int ELEMENT_TEXT_SIZE = 40;
    public static final int ELEMENT_SIZE = 45;
    public static final int ELEMENT_DISTANCE = 50;
    public static final int PATH_WIDTH = 6;
    public static final int PATH_LENGTH = 50;
    public static final int ELEMENTS_ANIMATION_DURATION = 200;
    public static PuzzleElements elements = new PuzzleElements();
    public static PuzzlePaths paths = new PuzzlePaths();
    public static ArrayList<ElementInfos> undoDatas = new ArrayList<>();


//    public static void loadPresets() {
//        try {
//
//            InputStream is = context.getAssets().open("Presets.xml");
//            //Log.d(Globals.LOG_TAG, "csdasadasd");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(is);
//
//            Element element = doc.getDocumentElement();
//            element.normalize();
//            NodeList nList = doc.getElementsByTagName("Preset");
//
//            for (int i = 0; i < nList.getLength(); i++) {
//                Node node = nList.item(i);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Preset preset = new Preset();
//                    //Element element2 = (Element) node;
//                    //Log.d(Globals.LOG_TAG, "------------------------------------------");
//                    preset.setName(((Element) node).getAttribute("name") + "");
//                    preset.setId(Integer.parseInt(((Element) node).getAttribute("id") + ""));
//                    //Log.d(Globals.LOG_TAG, ((Element) node).getAttribute("id") + "");
//                    //Log.d(Globals.LOG_TAG, ((Element) node).getAttribute("name") + "");
//
//                    NodeList nList2 = ((Element) node).getElementsByTagName("View");
//
//
//                    for (int j = 0; j < nList2.getLength(); j++) {
//                        Node node2 = nList2.item(j);
//                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
//
//                            //Element element3 = (Element) node2;
//                            ViewModel mdl = new ViewModel();
//                            //Log.d(Globals.LOG_TAG, ((Element) node2).getAttribute("attrib") + "");
//                            //Log.d(Globals.LOG_TAG, ((Element) node2).getAttribute("id") + "");
//                            mdl.setAttribs(((Element) node2).getAttribute("attrib") + "");
//                            mdl.setId(Integer.parseInt(((Element) node2).getAttribute("id") + ""));
//
//                            preset.getModels().add(mdl);
//                        }
//                    }
//                    Globals.presets.add(preset);
//                }
//            }
//        } catch (Exception e) {
//            Log.d(Globals.LOG_TAG, e.getMessage().toString());
//        }
//
//
//    }

    public static void copyPuzzle() {
        File extDir = context.getExternalFilesDir(null);
        File filePuzzle = new File(extDir, "Puzzles.xml");
        if (filePuzzle.exists() == false) {
            try {
                InputStream in = context.getAssets().open("Puzzles.xml");
                FileOutputStream fos = new FileOutputStream(filePuzzle);
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, read);
                }
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void loadPuzzles() {
        try {


            //InputStream is = context.getAssets().open("Puzzles.xml");
            InputStream is = null;
            if (Globals.COPY_PUZZLE_IN_EXTERNAL_STORAGE == true) {
                File extDir = context.getExternalFilesDir(null);
                File filePuzzle = new File(extDir, "Puzzles.xml");
                is = new FileInputStream(filePuzzle);
            } else {
                is = context.getAssets().open("Puzzles.xml");
            }
            //Log.d(Globals.LOG_TAG, "csdasadasd");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("Puzzle");
            int id = 0;
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    PuzzleInfo puzzleInfo = new PuzzleInfo();
                    puzzleInfo.setName(((Element) node).getAttribute("name") + "");
                    //puzzleInfo.setId(Integer.parseInt(((Element) node).getAttribute("id") + ""));
                    puzzleInfo.setId(id);
                    NodeList nList2 = ((Element) node).getElementsByTagName("Element");
                    for (int j = 0; j < nList2.getLength(); j++) {
                        Node node2 = nList2.item(j);
                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
                            //Element element3 = (Element) node2;
                            ElementInfo elementInfo = new ElementInfo();
                            elementInfo.setId(Integer.parseInt(((Element) node2).getAttribute("id") + ""));
                            elementInfo.setColumn(Integer.parseInt(((Element) node2).getAttribute("column") + ""));
                            elementInfo.setRow(Integer.parseInt(((Element) node2).getAttribute("row") + ""));
                            elementInfo.setValue(Integer.parseInt(((Element) node2).getAttribute("value") + ""));
                            elementInfo.setType(PuzzleElementType.fromInteger(Integer.parseInt(((Element) node2).getAttribute("type") + "")));
                            String cnnctstr = ((Element) node2).getAttribute("connect_to") + "";
                            String[] cnnts = cnnctstr.trim().split(",");
                            for (String s : cnnts) {
                                int a = Integer.parseInt(s);
                                elementInfo.getConnects().add(a);
                            }
                            puzzleInfo.getElements().add(elementInfo);
                        }
                    }
                    Globals.puzzles.add(puzzleInfo);
                }
                id++;
            }
        } catch (Exception e) {
            Log.d(Globals.LOG_TAG, e.getMessage().toString());
        }
    }

//    public static void loadStages() {
//        try {
//
//            InputStream is = context.getAssets().open("Stages.xml");
//            //Log.d(Globals.LOG_TAG, "csdasadasd");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(is);
//
//            Element element = doc.getDocumentElement();
//            element.normalize();
//            NodeList nList = doc.getElementsByTagName("Stage");
//
//            for (int i = 0; i < nList.getLength(); i++) {
//                Node node = nList.item(i);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    StageItem stageItem = new StageItem();
//                    //Element element2 = (Element) node;
//                    Log.d(Globals.LOG_TAG, "------------------------------------------");
//                    stageItem.setTitle(((Element) node).getAttribute("title") + "");
//
//                    stageItem.setId(Integer.parseInt(((Element) node).getAttribute("id") + ""));
//                    stageItem.setType(StageType.fromInteger(Integer.parseInt(((Element) node).getAttribute("type") + "")));
//                    stageItem.setTargetType(StageTargetType.fromInteger(Integer.parseInt(((Element) node).getAttribute("target_type") + "")));
//                    stageItem.setTarget(Integer.parseInt(((Element) node).getAttribute("target") + ""));
//                    stageItem.setChances(Integer.parseInt(((Element) node).getAttribute("chances") + ""));
//
//
//                    //Log.d(Globals.LOG_TAG, ((Element) node).getAttribute("id") + "");
//                    //Log.d(Globals.LOG_TAG, "type: " + stageItem.getType() + "");
//
//                    //Log.d(Globals.LOG_TAG, ((Element) node).getAttribute("title") + "");
//
////                    NodeList nList2 = ((Element) node).getElementsByTagName("View");
//
//
////                    for (int j = 0; j < nList2.getLength(); j++) {
////                        Node node2 = nList2.item(j);
////                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
////
////                            //Element element3 = (Element) node2;
////                            ViewModel mdl = new ViewModel();
////                            Log.d(Globals.LOG_TAG, ((Element) node2).getAttribute("attrib") + "");
////                            Log.d(Globals.LOG_TAG, ((Element) node2).getAttribute("id") + "");
////                            mdl.setAttribs(((Element) node2).getAttribute("attrib") + "");
////                            mdl.setId(Integer.parseInt(((Element) node2).getAttribute("id") + ""));
////
////                            preset.getModels().add(mdl);
////                        }
////                    }
//                    Globals.stages.add(stageItem);
//                }
//            }
//        } catch (Exception e) {
//            Log.d(Globals.LOG_TAG, e.getMessage().toString());
//        }
//
//
//    }

//    public static void loadQuestions() {
//        try {
//            InputStream is = context.getAssets().open("Questions.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(is);
//            Element element = doc.getDocumentElement();
//            element.normalize();
//            NodeList nList = doc.getElementsByTagName("Question");
//            for (int i = 0; i < nList.getLength(); i++) {
//
//                Log.d(Globals.LOG_TAG, "QQQ" + i);
//
//                Node node = nList.item(i);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Question question = new Question();
//                    //Element element2 = (Element) node;
//                    Log.d(Globals.LOG_TAG, "------------------------------------------");
//                    question.setText(((Element) node).getAttribute("text") + "");
//                    question.setId(Integer.parseInt(((Element) node).getAttribute("id") + ""));
//                    question.setAnswer(Integer.parseInt(((Element) node).getAttribute("answer") + ""));
//                    Globals.questions.add(question);
//                }
//            }
//        } catch (Exception e) {
//            Log.d(Globals.LOG_TAG, e.getMessage().toString());
//        }
//    }

//    public static void loadLevels() {
//        try {
//
//            InputStream is = context.getAssets().open("Levels.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(is);
//
//            Element element = doc.getDocumentElement();
//            element.normalize();
//            NodeList nList = doc.getElementsByTagName("Level");
//
//            for (int i = 0; i < nList.getLength(); i++) {
//                Node node = nList.item(i);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Level level = new Level();
//                    //Element element2 = (Element) node;
//                    Log.d(Globals.LOG_TAG, "------------------------------------------");
//
//                    level.setId(Integer.parseInt(((Element) node).getAttribute("id") + ""));
//                    level.setPartCount(Integer.parseInt(((Element) node).getAttribute("part_count") + ""));
//                    level.setSizeVar(Integer.parseInt(((Element) node).getAttribute("size_var") + ""));
//                    String prst = ((Element) node).getAttribute("preset_shapes_count") + "";
//                    Log.d(Globals.LOG_TAG, prst);
//                    String[] prsts = prst.trim().split(",");
//
//                    for (String s : prsts) {
//                        level.getPresetShapesCount().add(Integer.parseInt(s));
//                    }
//
//                    Globals.levels.add(level);
//                }
//            }
//        } catch (Exception e) {
//            Log.d(Globals.LOG_TAG, e.getMessage().toString());
//        }
//
//
//    }


}
