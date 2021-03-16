package danielguirol.project.sneakersapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class MyDialog extends AppCompatDialogFragment {


    /* ************************************************************************************************************************************************************************************************************
     *                       DIALOG BOX TO HANDLE THE IMAGE BUTTON IN THE MAIN ACTIVITY                                                                                                                           *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //We're using Builder to provide clear separation between construction and separation
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setMessage("You must create an account first")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        return builder.create();
    }
}
