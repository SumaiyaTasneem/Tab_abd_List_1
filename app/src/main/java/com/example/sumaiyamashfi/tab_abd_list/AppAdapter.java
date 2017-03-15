package com.example.sumaiyamashfi.tab_abd_list;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumaiya Mashfi on 12/8/2015.
 */
public class AppAdapter extends ArrayAdapter<ApplicationInfo> {

    private List<ApplicationInfo> applist = null;
    private List<Boolean> selectedapps = null;
    private Context context;
    private PackageManager packageManager;
    private  LayoutInflater layoutInflater;
    public AppAdapter(Context context, int resource, List<ApplicationInfo> objects, List<Boolean> sel_apps) {
        super(context, resource, objects);

        this.context = context;
        this.applist = objects;
        packageManager = context.getPackageManager();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.selectedapps = sel_apps;

    }

    public int getCount() {
        return ((null != applist)? applist.size() : 0);
    }

    public ApplicationInfo getItem(int position) {
        return ((null != applist) ? applist.get(position) : null);
    }

    public long getItemId(int position){
        return position;
    }

    static class Holder{
        TextView App_Name;
        CheckBox App_Check;
        ImageView App_Icon;
    }

    public View getView(final int position , View convertView, ViewGroup parent) {
        View view = convertView;

        Holder H = null;

        if(view == null) {
            view = layoutInflater.inflate(R.layout.list_item,null);
            H = new Holder();
            H.App_Name = (TextView)view.findViewById(R.id.app_name);
            H.App_Check = (CheckBox)view.findViewById(R.id.checkselect);
            H.App_Icon = (ImageView)view.findViewById(R.id.app_icon);
            view.setTag(H);
        }
        else
        {
            H = (Holder)view.getTag();
            H.App_Check.setOnCheckedChangeListener(null);
        }

        ApplicationInfo data = applist.get(position);
        if (data != null) {
            H.App_Check.setFocusable(false);
            H.App_Icon.setImageDrawable(data.loadIcon(packageManager));
            H.App_Icon.setTag(H.App_Check);
            H.App_Icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox C = (CheckBox)view.getTag();
                    C.performClick();
                }
            });
            H.App_Check.setChecked(selectedapps.get(position));
            H.App_Name.setText(data.loadLabel(packageManager));
            H.App_Name.setTag(H.App_Check);

            H.App_Name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox C = (CheckBox)view.getTag();
                    C.performClick();
                }
            });
            H.App_Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b == true) {
                        selectedapps.set(position, true);

                        ApplicationInfo A_I = applist.get(position);
                        int I;
                        boolean found = false;
                        for (I = 0; I < LockActivity.Selected_Apps.size(); I++)
                        {
                            String p_n = LockActivity.Selected_Apps.get(I);
                            String m_n = A_I.packageName;
                            if (p_n.equals(m_n) == true)
                            {
                                found = true;
                                break;
                            }
                        }
                        if (found == false)
                        {
                            LockActivity.Selected_Apps.add(A_I.packageName);
                        }

                    } else {
                        selectedapps.set(position, false);

                        ApplicationInfo A_I = applist.get(position);
                        int I;
                        boolean found = false;
                        int id = -1;
                        for (I = 0; I < LockActivity.Selected_Apps.size(); I++)
                        {
                            String p_n = LockActivity.Selected_Apps.get(I);
                            String m_n = A_I.packageName;
                            if (p_n.equals(m_n) == true)
                            {
                                id = I;
                                found = true;
                                break;
                            }
                        }
                        if (found == true)
                        {
                            LockActivity.Selected_Apps.remove(id);
                        }
                    }
                }
            });
        }
        return view;
    }
}
