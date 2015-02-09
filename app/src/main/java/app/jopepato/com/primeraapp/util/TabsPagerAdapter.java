package app.jopepato.com.primeraapp.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import app.jopepato.com.primeraapp.CrearContactoFragment;
import app.jopepato.com.primeraapp.ListaContactosFragment;

/**
 * Created by jopepato on 09/02/2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        //Como tenemos dos fragmentos, devolvemos un 2 directamente
        return 2;
    }

    /**
     *
     * @param position
     *
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CrearContactoFragment();
            case 1: return new ListaContactosFragment();
        }
        return null;
    }
}
