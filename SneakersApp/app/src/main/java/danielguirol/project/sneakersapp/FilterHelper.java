package danielguirol.project.sneakersapp;

import android.content.Context;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Vector;

public class FilterHelper extends Filter {
    Vector<JSONData> MyinitialData;
    MyAdapter adapter;
    Context context;


    public FilterHelper(Vector<JSONData> MyinitialData, MyAdapter adapter, Context context) {
        this.MyinitialData = MyinitialData;
        this.adapter = adapter;
        this.context = context;
    }

    /* ************************************************************************************************************************************************************************************************************
     *          RECEIVING AND FILTERING THE DATA IN THE INITIAL VECTOR                                                                                                                                            *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toLowerCase().trim();
            Vector<JSONData> foundfilter = new Vector<>();

            //set the filter to null
            JSONData Mydata = null;

            //if corresponding get the value and set it in the foundfilter
            for (int i = 0; i < MyinitialData.size(); i++) {
                Mydata = MyinitialData.get(i);

                //search
                if (Mydata.getBrand().toLowerCase().contains(constraint)) {
                    foundfilter.add(Mydata);
                }
            }

            //storing the results
            filterResults.values = foundfilter;

        } else {
            //get the initial data back
            filterResults.values = MyinitialData;
        }

        return filterResults;
    }

    //put the filtered values in the initial vector
    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.setinfo((Vector<JSONData>) filterResults.values);
        adapter.refresh();
    }
}
