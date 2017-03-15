package com.example.sumaiyamashfi.tab_abd_list;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumaiya Mashfi on 1/14/2016.
 */
public class ListActivity_1 extends ListActivity {
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private ArrayList<Boolean> appselected = null;
    private AppAdapter listadapter = null;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_xml);

        packageManager = getPackageManager();
        new LoadApplications().execute();
//create array

    }


    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //TextView T = (TextView)v.findViewById(R.id.app_name);
        //String S = T.getText().toString();
        //Toast.makeText(ListActivity_1.this, S, Toast.LENGTH_SHORT).show();
        //CheckBox C = (CheckBox)v.findViewById(R.id.checkselect);
        //if (C.isChecked() == false) C.setChecked(true); else C.setChecked(false);

        /*
        CheckBox C = (CheckBox)v.findViewById(R.id.checkselect);
        C.performClick();

        TextView T = (TextView)v.findViewById(R.id.app_package);


            int K;
            boolean found = false;
            int found_id = 0;
            for (K = 0; K < LockActivity.S.Selected_Apps.size(); K++) {
                if (LockActivity.S.Selected_Apps.get(K) == T.getText().toString()) {
                    found = true;
                    found_id = K;
                    break;
                }
            }
            if (found == true)
            {
                if (C.isChecked() == false)
                {
                    LockActivity.S.Selected_Apps.remove(found_id);
                }
            }
            else
            {
                   if (C.isChecked() == true) {
                       LockActivity.S.Selected_Apps.add(T.getText().toString());

                   }
            }


*/
        //Intent intent = new Intent(ListActivity_1.this , ListActivity_2.class);
        //startActivity(intent);
      /*  ApplicationInfo app = applist.get(position);
        try {
            Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

            if (intent != null) {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
          Toast.makeText(ListActivity_1.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ListActivity_1.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }*/
    }

    private List<ApplicationInfo> checkForLaunchInTest(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();

        for (ApplicationInfo info : list) {
            try {
                if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return applist;
    }


    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected Void doInBackground(Void... params) {
            listadapter = new AppAdapter(ListActivity_1.this, R.layout.list_item, applist, appselected);
            return null;
        }

        protected void onPostExecute(Void result) {
            setListAdapter(listadapter);
            progress.dismiss();

            super.onPostExecute(result);
        }

        protected void onPreExecute() {
            progress = ProgressDialog.show(ListActivity_1.this, null, "Loading apps info...");

            applist = checkForLaunchInTest(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            appselected = new ArrayList<>();
            //Build the appselected list
            int I,II;
            for (I = 0; I < applist.size(); I++)
                appselected.add(false);
            for (I = 0; I < LockActivity.Locked_Apps.size(); I++ )
            {
                String pack_name = LockActivity.Locked_Apps.get(I);
                for (II = 0; II < applist.size(); II++) {
                    String curr_pack = applist.get(II).packageName;
                    if (pack_name.equals(curr_pack) == true) {
                        appselected.set(II, true);
                        break;
                    }
                }
            }


            super.onPreExecute();
        }

    }

    //foreground


}
