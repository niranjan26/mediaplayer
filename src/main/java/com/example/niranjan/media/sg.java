package com.example.niranjan.media;

/**
 * Created by niranjan on 22/12/15.
 */

import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
        import java.io.FilenameFilter;
        import java.util.ArrayList;
        import java.util.HashMap;

public class sg {
    // SDCard Path
    //final String MEDIA_PATH = new String(MediaStore.Audio.Media.getContentUri("external").toString());

    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    // Constructor
    public sg(){

    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */
    public ArrayList<HashMap<String, String>> getPlayList(int c){
        //File[] home = Environment.getExternalStorageDirectory().listFiles();;
        String secStore = System.getenv("SECONDARY_STORAGE");
        File f_secs = new File(secStore);
        //File[] home = f_secs.listFiles();

           getMp3s(f_secs);

        // return songs list array
        return songsList;
    }

    /**
     * Class to filter files which are having .mp3 extension
     * */

    void getMp3s(File dir){
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isDirectory()){
                getMp3s(file);
            }else{
                //add mp3s to list here
                String fullPath = file.getAbsolutePath();
                int dot = fullPath.lastIndexOf(".");
                String ext = fullPath.substring(dot + 1);
                if(ext.equals("mp3")||ext.equals("MP3"))
                {
                    HashMap<String, String> song = new HashMap<String, String>();
                    song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                    song.put("songPath", file.getPath());
                    songsList.add(song);
                    // Adding each song to SongList
                    //Toast.makeText(MainActivity.this, " " + f.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}